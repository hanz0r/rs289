package org.hannes.scoundrel.clock;

import org.hannes.scoundrel.util.Boot;

@Boot
public class Testing extends GatedClockWorker {

	public Testing() {
		super(TimeUnit.GAME_CYCLE);
	}

	@Override
	public Result delayedTick() throws ClockException {
		System.out.println("hello");
		return Result.RESCHEDULE;
	}

}