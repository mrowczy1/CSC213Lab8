package controllers;

import businesslogic.Timer;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Jon Mrowczynski
 */
public class TimerController {
	
	protected final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
	
	private Timer timer;
	
	protected ScheduledFuture<?> scheduledFuture;
	
	@FXML private Text duration;
	
	protected final Runnable scheduledRunnable = () -> {
		final long ms = timer.getElapsedTime();
		duration.setText("%02d:%02d.%d".formatted(ms / 60_000, (ms / 1_000) % 60, (ms / 100) % 10));
	};
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("unused") public TimerController() { this(new Timer()); } // TODO: Only change this line!
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	protected TimerController(final Timer timer) { this.timer = timer; }
	
	public void handleStart() {
		timer.start();
		// Start updating the displayed time of the timer.
		scheduledFuture =
				scheduledExecutorService.scheduleAtFixedRate(scheduledRunnable, 0, 100, TimeUnit.MILLISECONDS);
	}
	
	public void handleStop() {
		timer.stop();
		// Cancel updating the displayed time.
		if (scheduledFuture != null) { scheduledFuture.cancel(true); }
	}
	
	public void handleReset() {
		timer.reset();
		scheduledRunnable.run();
	}
	
	/**
	 * Since we are spawning our own thread to manage updating the displayed time, we want to make sure that this is
	 * called once we are absolutely sure, we no longer want to use the timer. Basically, this should be called when
	 * our program shuts down.
	 */
	public void shutdown() { scheduledExecutorService.shutdown(); }
}