// Stores the time information and applies delay based on time information stored.

public class TimeSeries {

	// Stores time in seconds.
	int time;

	public TimeSeries() {
		time = -1;
	}

	public TimeSeries(int i) {
		time = i * 1000;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time * 1000;
	}

	public void generateTimeSeries() throws InterruptedException {
		Thread.sleep(time);
	}
}
