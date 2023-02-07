package mohamed.testjpa.model.teacher;

import mohamed.testjpa.model.Person.Person;

public class Teacher extends Person {
    private int id;
    private String degree;
    private String hiringDate;

    public Teacher() {
    }

    public Teacher(int id, String degree, String hiringDate) {
        this.id = id;
        this.degree = degree;
        this.hiringDate = hiringDate;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDegree() {
        return this.degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getHiringDate() {
        return this.hiringDate;
    }

    public void setHiringDate(String hiringDate) {
        this.hiringDate = hiringDate;
    }

    public Teacher id(int id) {
        setId(id);
        return this;
    }

    public Teacher degree(String degree) {
        setDegree(degree);
        return this;
    }

    public Teacher hiringDate(String hiringDate) {
        setHiringDate(hiringDate);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", degree='" + getDegree() + "'" +
                ", hiringDate='" + getHiringDate() + "'" +
                "}";
    }

}
