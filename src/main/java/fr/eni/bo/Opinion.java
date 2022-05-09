package fr.eni.bo;

public class Opinion {
    private long id;
    private int note;
    private String comment;
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
