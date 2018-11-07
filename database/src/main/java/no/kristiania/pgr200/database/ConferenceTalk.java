package no.kristiania.pgr200.database;


import java.util.Objects;

public class ConferenceTalk {
    private String title, description;
    private Long id;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ConferenceTalk)) {
            return false;
        }
        ConferenceTalk other = (ConferenceTalk) o;
        return Objects.equals(title, other.title) && Objects.equals(description, other.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{ID: " + id + "\nTitle: " + getTitle() + "\nDescription; " + getDescription() + "}";
    }
}
