package org.hannes.scoundrel.clock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.hannes.scoundrel.clock.ClockWorker.Result;
import org.hannes.scoundrel.util.Boot;
import org.hannes.scoundrel.util.Initializable;

@ApplicationScoped
public final class Clock implements Runnable, Initializable {

	/**
	 * The rate at which the clock sends out tick updates
	 */
	private static final long CYCLE_RATE = 50L;
	
	/**
	 * The logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Clock.class.getName());
	
	/**
	 * The collection of workers
	 */
	private final List<ClockWorker> workers = new ArrayList<>();
	
	/**
	 * The scheduled executorservice responsible for the clock updates
	 */
	private final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

	/**
	 * The collection of ClockWorkers that are loaded at startup
	 */
	@Inject @Boot private Instance<ClockWorker> boot_clockworkers;

	@Override
	public void initialize() throws Exception {
		/*
		 * Load up all of the startup-workers
		 */
		boot_clockworkers.forEach(w -> submit(w));
		
		/*
		 * Start the clock service
		 */
		service.scheduleAtFixedRate(this, CYCLE_RATE, CYCLE_RATE, TimeUnit.MILLISECONDS);
		
		/*
		 * Info for sysadmin
		 */
		logger.info("Clock started with cycle delay of " + CYCLE_RATE);
	}

	@Override
	public void run() {
		synchronized(workers) {
			for (Iterator<ClockWorker> iterator = workers.iterator(); iterator.hasNext(); ) {
				ClockWorker worker = iterator.next();
				try {
					/*
					 * Make the worker do his thing. If anything other than reschedule result is
					 * returned, remove it from the active workers
					 */
					if (worker.tick() != Result.RESCHEDULE) {
						iterator.remove();
					}
				} catch (ClockException e) {
					/*
					 * Handle the exception higher up the stack
					 */
					worker.handleException(this, e);
					
					/*
					 * Remove the clock worker to prevent further errors
					 */
					iterator.remove();
					
					/*
					 * output
					 */
					logger.info("deregistered clockworker " + worker);
				}
			}
		}
	}

	/**
	 * Submit a worker to the collection of workers
	 * @param worker
	 */
	public void submit(ClockWorker worker) {
		synchronized (workers) {
			workers.add(worker);
		}
	}
	
	/**
	 * submit a single use runnable to be executed next cycle
	 * @param runnable
	 */
	public void submit(Runnable runnable) {
		this.submit(() -> {
			runnable.run();
			return Result.STOP;
		});
	}

}