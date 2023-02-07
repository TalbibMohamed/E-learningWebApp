package mohamed.testjpa.model.Course;

public class Course {
    private int id;
    private String label;
    private String level;
    private int Coefficient;
    private String full_label;

    public Course(int id, String label, String level, int Coefficient) {
        this.id = id;
        this.label = label;
        this.level = level;
        this.Coefficient = Coefficient;
    }

    public String getFull_label() {
        return this.full_label;
    }

    public void setFull_label(String full_label) {
        this.full_label = full_label;
    }

    public int getCoefficient() {
        return this.Coefficient;
    }

    public void setCoefficient(int Coefficient) {
        this.Coefficient = Coefficient;
    }

    public Course() {
    }

    public Course(int id, String label, String level) {
        this.id = id;
        this.label = label;
        this.level = level;
    }

    public Course(int id, String label, int Coefficient) {
        this.id = id;
        this.label = label;
        this.Coefficient = Coefficient;
    }

    public Course(int id, String label) {
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

}
