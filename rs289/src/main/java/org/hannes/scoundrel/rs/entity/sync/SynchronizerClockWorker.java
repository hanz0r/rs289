package org.hannes.scoundrel.rs.entity.sync;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;

import org.hannes.scoundrel.clock.ClockException;
import org.hannes.scoundrel.clock.GatedClockWorker;
import org.hannes.scoundrel.util.Boot;

@Boot
@ApplicationScoped
public class SynchronizerClockWorker extends GatedClockWorker {

	/**
	 * 
	 */
	private Instance<Synchronizer> synchronizers;

	public SynchronizerClockWorker(TimeUnit unit) {
		super(unit);
	}

	@Override
	public Result delayedTick() throws ClockException {
		/*
		 * 
		 */
		synchronizers.forEach(Synchronizer::synchronize);
		
		/*
		 * 
		 */
		return Result.RESCHEDULE;
	}

}