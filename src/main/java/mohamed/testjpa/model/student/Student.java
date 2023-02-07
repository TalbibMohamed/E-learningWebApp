package mohamed.testjpa.model.student;

import mohamed.testjpa.model.Person.Person;

public class Student extends Person {

    int id_class;

    String grade;

    int id;

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    String label;

    public Student() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_class() {
        return this.id_class;
    }

    public void setId_class(int id_class) {
        this.id_class = id_class;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Student id_class(int id_class) {
        setId_class(id_class);
        return this;
    }

    public Student grade(String grade) {
        setGrade(grade);
        return this;
    }

    public Student(int id_class, String grade, int id) {
        this.id_class = id_class;
        this.grade = grade;
        this.id = id;
    }

    public Student(int id_class, String grade) {
        super();
        this.id_class = id_class;
        this.grade = grade;

    }

}
