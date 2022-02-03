package zms.pojo;

public class Work_exe {

    private Integer work_id;
    private Integer exe_id;

    @Override
    public String toString() {
        return "Work_exe{" +
                "work_id=" + work_id +
                ", exe_id=" + exe_id +
                '}';
    }

    public Integer getWork_id() {
        return work_id;
    }

    public void setWork_id(Integer work_id) {
        this.work_id = work_id;
    }

    public Integer getExe_id() {
        return exe_id;
    }

    public void setExe_id(Integer exe_id) {
        this.exe_id = exe_id;
    }
}
