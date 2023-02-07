package mohamed.testjpa.model.myclass;

public class MyClass {
    private int id;
    private String label;
    private String level;

    public MyClass() {
    }

    // public MyClass(int id, String label, String level) {
    // this.id = id;
    // this.label = label;
    // this.level = level;
    // }

    public MyClass(int id, String label) {
        this.id = id;
        this.label = label;

    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public MyClass id(int id) {
        setId(id);
        return this;
    }

    public MyClass label(String label) {
        setLabel(label);
        return this;
    }

    // public MyClass level(String level) {
    // setLevel(level);
    // return this;
    // }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", label='" + getLabel() + "'" +
                ", level='" + getLevel() + "'" +
                "}";
    }

}
