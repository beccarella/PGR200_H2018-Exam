package no.kristiania.pgr200.database.entity;

import java.time.LocalTime;
import java.util.Objects;

public class Timeslot {
	private LocalTime time;

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTime(LocalTime localTime) {
		this.time = localTime;
	}

	public LocalTime getTime() {
		return time;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Timeslot)) {
			return false;
		}
		Timeslot other = (Timeslot) o;
		return Objects.equals(time, other.time);
	}

	@Override
	public int hashCode() {
		return Objects.hash(time);
	}

	public String toString() {
		return "Time: " + getTime();
	}
}