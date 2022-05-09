package fr.eni.bo;

public class Genre {

    private long id;

    private String label;

    public Genre() {
    }

    public Genre(long id, String label) {
        this.id = id;
        this.label = label;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label + "[id=" + id + "]";
    }
}
