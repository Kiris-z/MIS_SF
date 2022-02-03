package zms.pojo;

import java.sql.Date;

public class Worktable {
    private Integer worktableId;
    private String title;
    private Integer teacherId;
    private Integer courseId;
    private Integer klassId;
    private Date createDate;
    private Date deadLine;

    @Override
    public String toString() {
        return "Worktable{" +
                "worktableId=" + worktableId +
                ", title='" + title + '\'' +
                ", teacherId=" + teacherId +
                ", courseId=" + courseId +
                ", klassId=" + klassId +
                ", createDate=" + createDate +
                ", deadLine=" + deadLine +
                '}';
    }

    public Integer getWorktableId() {
        return worktableId;
    }

    public void setWorktableId(Integer worktableId) {
        this.worktableId = worktableId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getKlassId() {
        return klassId;
    }

    public void setKlassId(Integer klassId) {
        this.klassId = klassId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }
}
