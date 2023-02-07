package mohamed.testjpa.model.schedule;

import java.sql.Time;

public class Schedule {
    private int id_teacher;
    private String groupe;
    private String level;
    private String course;
    private String room;
    private String start_time;
    private String end_time;
    public int getId_teacher() {
        return id_teacher;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getStart_time() {
        start_time = start_time.substring(0, start_time.length()-3);
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        end_time = end_time.substring(0, end_time.length()-3);
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getDayS() {
        return dayS;
    }

    public void setDayS(String dayS) {
        this.dayS = dayS;
    }

    private String dayS;

    public void setId_teacher(int id_teacher) {
        this.id_teacher = id_teacher;
    }

    public Schedule(int id_teacher, String groupe, String course, String level, String room, String start_time,
            String end_time, String dayS) {
        this.id_teacher = id_teacher;
        this.groupe = groupe;
        this.course = course;
        this.level = level;
        this.room = room;
        this.start_time = start_time;
        this.end_time = end_time;
        this.dayS = dayS;
    }

}
