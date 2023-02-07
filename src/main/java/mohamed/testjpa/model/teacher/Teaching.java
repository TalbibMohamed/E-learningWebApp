package mohamed.testjpa.model.teacher;

public class Teaching {
    private String level;
    private String teachingClass;
    private String course_name;

    public Teaching() {
    }

    public Teaching(String level, String teachingClass, String course_name) {
        this.level = level;
        this.teachingClass = teachingClass;
        this.course_name = course_name;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTeachingClass() {
        return this.teachingClass;
    }

    public void setTeachingClass(String teachingClass) {
        this.teachingClass = teachingClass;
    }

    public String getCourse_name() {
        return this.course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public Teaching level(String level) {
        setLevel(level);
        return this;
    }

    public Teaching teachingClass(String teachingClass) {
        setTeachingClass(teachingClass);
        return this;
    }

    public Teaching course_name(String course_name) {
        setCourse_name(course_name);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " level='" + getLevel() + "'" +
                ", teachingClass='" + getTeachingClass() + "'" +
                ", course_name='" + getCourse_name() + "'" +
                "}";
    }

}
