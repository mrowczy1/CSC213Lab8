package businesslogic;

/**
 * Allows for basic timing functionality. The timer can be started and stopped multiple times prior to being reset.
 * Rather, calling {@link #stop()} after a {@link #stop()} effectively resumes the timer.
 *
 * @author Jon Mrowczynski
 */
public class Timer {
	
	/**
	 * Represents the time at which the {@link Timer} was started.
	 */
	protected long startTime;
	
	/**
	 * Indicates whether the {@link Timer} is currently running.
	 */
	protected boolean isRunning;
	
	/**
	 * Helps keep track of the total amount of elapsed time in case the {@link Timer} is
	 */
	protected long elapsedTime;
	
	/**
	 * Start the timer. The only way to stop the timer is by calling {@link #stop()}.
	 */
	public void start() {
		isRunning = true;
		startTime = System.currentTimeMillis();
	}
	
	/**
	 * Stop the timer.
	 */
	public void stop() {
		isRunning = false;
		elapsedTime += System.currentTimeMillis() - startTime;
	}
	
	/**
	 * Reset the timer. Note that the timer will continue to run even after this method is called.
	 */
	public void reset() {
		startTime = isRunning ? System.currentTimeMillis() : 0;
		elapsedTime = 0;
	}
	
	/**
	 * Returns the current elapsed time in ms, taking into consideration only the time that the timer is running.
	 * This is set back to zero once {@link #reset()} is called.
	 *
	 * @return a {@code long} representing the number of ms that timer has been running.
	 */
	public long getElapsedTime() { return (isRunning ? System.currentTimeMillis() - startTime : 0) + elapsedTime; }
}