package no.kristiania.pgr200.database.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Day {
    private LocalDate date;

    private Long id;

    public LocalDate getDate() {
        date = LocalDate.parse(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        return date;
    }

    public void setDate(LocalDate localDate) {
        this.date = localDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Day)) {
            return false;
        }
        Day other = (Day) o;
        return Objects.equals(date, other.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{Date: " + getDate() + "}";
    }



}
