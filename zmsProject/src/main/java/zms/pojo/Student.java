package zms.pojo;

public class Student {

    private Integer studentId;
    private String studentName;
    private Integer klassId;
    private String studentPassword;

    public Integer getId() {
        return studentId;
    }

    public void setId(Integer id) {
        this.studentId = id;
    }

    public String getPassword() {
        return studentPassword;
    }

    public void setPassword(String password) {
        this.studentPassword = password;
    }

    public String getName() {
        return studentName;
    }

    public void setName(String name) {
        this.studentName = name;
    }

    public Integer getKlass_id() {
        return klassId;
    }

    public void setKlass_id(Integer klass_id) {
        this.klassId = klass_id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + studentId +
                ", password='" + studentPassword + '\'' +
                ", name='" + studentName + '\'' +
                ", klass_id=" + klassId +
                '}';
    }
}
