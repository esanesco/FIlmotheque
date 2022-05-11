package fr.eni.bo;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Opinion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Range(min = 1, max = 5)
    private int note;

    @NotBlank
    private String comment;

    @ManyToOne
    private Member member;

    public Opinion() {
    }

    public Opinion(long id, int note, String comment, Member member) {
        this.id = id;
        this.note = note;
        this.comment = comment;
        this.member = member;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "Opinion [id=" + id + ", note=" + note + ", comment=" + comment + ", member=" + member + "]";
    }
}
