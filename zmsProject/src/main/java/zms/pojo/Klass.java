package zms.pojo;

public class Klass {
    private Integer klassId;
    private String klassName;

    @Override
    public String toString() {
        return "Klass{" +
                "klassId=" + klassId +
                ", klassName='" + klassName + '\'' +
                '}';
    }

    public Integer getKlassId() {
        return klassId;
    }

    public void setKlassId(Integer klassId) {
        this.klassId = klassId;
    }

    public String getKlassName() {
        return klassName;
    }

    public void setKlassName(String klassName) {
        this.klassName = klassName;
    }
}
