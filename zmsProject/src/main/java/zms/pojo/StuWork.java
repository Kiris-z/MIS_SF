package zms.pojo;

public class StuWork {
    private Integer stuId;
    private Integer workId;
    private String stuAnswer;
    private Integer stuScore;
    private String notes;
    private Integer state;

    @Override
    public String toString() {
        return "Stu_work{" +
                "stuId=" + stuId +
                ", workId=" + workId +
                ", stuAnswer='" + stuAnswer + '\'' +
                ", stuScore=" + stuScore +
                ", notes='" + notes + '\'' +
                ", state=" + state +
                '}';
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public String getStuAnswer() {
        return stuAnswer;
    }

    public void setStuAnswer(String stuAnswer) {
        this.stuAnswer = stuAnswer;
    }

    public Integer getStuScore() {
        return stuScore;
    }

    public void setStuScore(Integer stuScore) {
        this.stuScore = stuScore;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
