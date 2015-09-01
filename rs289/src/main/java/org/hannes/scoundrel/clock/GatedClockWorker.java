package org.hannes.scoundrel.clock;

public abstract class GatedClockWorker implements ClockWorker {

	/**
	 * The current amount of time passed
	 */
	private int clock;

	/**
	 * The clock unit
	 */
	private final TimeUnit unit;

	/**
	 * 
	 * @param unit
	 */
	public GatedClockWorker(TimeUnit unit) {
		this.unit = unit;
	}

	@Override
	public Result tick() throws ClockException {
		return ++clock % unit.getCycle_units() == 0 ? delayedTick() : Result.RESCHEDULE;
	}

	/**
	 * 
	 * 
	 * @return
	 * @throws ClockException
	 */
	public abstract Result delayedTick() throws ClockException;

	/**
	 * The clock units
	 * 
	 * @author Red
	 *
	 */
	public static enum TimeUnit {
		/*
		 * A game cycle is 600 ms
		 */
		GAME_CYCLE(12),
		
		/*
		 * A minute is 60 seconds
		 */
		MINUTE(1200),
		
		/*
		 * An hour is 3600 seconds
		 */
		HOUR(72000);
		
		private final int cycle_units;

		private TimeUnit(int cycle_units) {
			this.cycle_units = cycle_units;
		}

		public int getCycle_units() {
			return cycle_units;
		}

	}

}