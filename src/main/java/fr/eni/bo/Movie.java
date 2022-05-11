package fr.eni.bo;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Movie {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(max = 250)
    @Column(length = 250, nullable = false)
    private String title;

    @Min(value = 1990)
    private int year;

    @Min(value = 1)
    private int duration;

    @NotBlank
    @Size(min = 20, max = 250)
    @Column(length = 250, nullable = false)
    private String synopsis;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Participant director;


    @ManyToMany
    private List<Participant> actors;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Genre genre;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "movie_id")
    private List<Opinion> opinions;

    public Movie() {
        actors = new ArrayList<>();
        opinions = new ArrayList<>();
    }

    public Movie(long id, String title, int year, int duration, String synopsis) {
        this();
        this.id = id;
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.synopsis = synopsis;
    }

    public Movie(long id, String title, int year, int duration, String synopsis, Participant director, Genre genre) {
        this();
        this.id = id;
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.synopsis = synopsis;
        this.director = director;
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Participant getDirector() {
        return director;
    }

    public void setDirector(Participant director) {
        this.director = director;
    }

    public List<Participant> getActors() {
        if (actors == null) {
            actors = new ArrayList<>();
        }
        return actors;
    }

    public void setActors(List<Participant> actors) {
        this.actors = actors;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Opinion> getOpinions() {
        if (opinions == null) {
            opinions = new ArrayList<>();
        }
        return opinions;
    }

    public void setOpinions(List<Opinion> opinions) {
        this.opinions = opinions;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Movie [id=");
        builder.append(id);
        builder.append("]\n\ttitle : ");
        builder.append(title);
        builder.append("[year : ");
        builder.append(year);
        builder.append(", duration : ");
        builder.append(duration);
        builder.append("]\n\tSynopsis : ");
        builder.append(synopsis);
        builder.append("\n\tDirector : ");
        builder.append(director);
        builder.append("\n\tActors : ");
        builder.append(actors);
        builder.append("\n\tgenre : ");
        builder.append(genre);
        return builder.toString();
    }

}
