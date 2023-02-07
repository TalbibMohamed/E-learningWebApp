package mohamed.testjpa.model.schedule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mohamed.testjpa.model.teacher.Teacher;
import mohamed.testjpa.model.teacher.TeacherDao;

public class ScheduleDao {

    final ArrayList<String> days = new ArrayList<String>(
            Arrays.asList("sunday", "monday", "tuesday", "wednesday", "thursday"));
    final ArrayList<String> periods = new ArrayList<String>(
            Arrays.asList("08:30:00", "10:00:00", "11:30:00", "13:00:00", "14:30:00"));
    final ArrayList<String> types = new ArrayList<String>(Arrays.asList("a", "s", "tp"));

    public static Connection connectDB() throws InstantiationException, IllegalAccessException {

        String url = "jdbc:mysql://localhost:3306/daawbd?serverTimezone=UTC";
        String teacher = "AdminTP";
        String password = "Lilou2001";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connect = DriverManager.getConnection(url, teacher, password);
            System.out.println("connected to database successfully");
            return connect;
        } catch (ClassNotFoundException e) {
            System.out.println("something went wrong... (class not found)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }

    public Map<String, List<String>> getScheduleTable(Teacher teacher)
            throws InstantiationException, IllegalAccessException {
        Connection connection = null;
        String requete;
        PreparedStatement statement;

        Map<String, List<String>> allSchedules = new LinkedHashMap<>();

        try {
            connection = connectDB();
            for (String day : days) {

                ArrayList<String> schedules = new ArrayList<String>();

                for (String period : periods) {
                    requete = "SELECT room,start_time,end_time,days,class.label as groupe , levelc, course.label FROM schedule,class,course WHERE id_teacher = "
                            + teacher.getId() + " AND id_class = class.id AND id_course = course.id AND days='" + day
                            + "' AND start_time='" + period + "' order by start_time;;";
                    statement = connection.prepareStatement(requete);
                    ResultSet result = statement.executeQuery();
                    if (result.next()) {
                        Schedule schedule = new Schedule(0, result.getString("groupe"), result.getString("label"),
                                result.getString("levelc"), result.getString("room"), result.getString("start_time"),
                                result.getString("end_time"), result.getString("days"));
                        String schedulestr = schedule.getRoom() + " " + schedule.getCourse() + " " + schedule.getLevel()
                                + " " + schedule.getGroupe();
                        schedules.add(schedulestr);
                    } else {
                        Schedule schedule = new Schedule(0, "", "", "", "", "", "", "");
                        String schedulestr = schedule.getRoom() + " " + schedule.getCourse() + " "
                                + schedule.getGroupe();
                        schedules.add(schedulestr);
                    }

                    statement.close();
                }
                allSchedules.put(day, schedules);
            }

            System.out.println("succés !");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return allSchedules;

    }

    public Map<String, Map<String, List<String>>> getScheduleBref(Teacher teacher, ArrayList<String> courses)
            throws InstantiationException, IllegalAccessException {
        Connection connection = null;
        String requete;
        PreparedStatement statement;

        Map<String, Map<String, List<String>>> allSchedules = new LinkedHashMap<>();

        try {
            connection = connectDB();
            for (String course : courses) {

                Map<String, List<String>> perType = new LinkedHashMap<>();

                for (String type : types) {
                    ArrayList<String> schedules = new ArrayList<String>();

                    requete = "SELECT room,start_time,end_time,days,class.label as groupe , levelc, course.label FROM schedule,class,course WHERE id_teacher = "
                            + teacher.getId()
                            + " AND id_class = class.id AND id_course = course.id  AND course.full_label=\"" + course
                            + "\" AND room LIKE '" + type + "%' order by start_time;";
                    statement = connection.prepareStatement(requete);

                    ResultSet result = statement.executeQuery();
                    while (result.next()) {
                        Schedule schedule = new Schedule(0, result.getString("groupe"), result.getString("label"),
                                result.getString("levelc"), result.getString("room"), result.getString("start_time"),
                                result.getString("end_time"), result.getString("days"));
                        String schedulestr = schedule.getDayS() + " - " + schedule.getStart_time() + " - "
                                + schedule.getRoom() + " - " + schedule.getLevel() + " " + schedule.getGroupe();
                        schedules.add(schedulestr);
                    }

                    perType.put(type, schedules);
                    statement.close();
                }
                allSchedules.put(course, perType);
            }

            System.out.println("succés !");
        } catch (SQLException e) {
            System.out.println(e);
        }

        return allSchedules;

    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        Map<String, Map<String, List<String>>> h = new ScheduleDao().getScheduleBref(new Teacher(20, null, null),
                new TeacherDao().getCourses(new Teacher(20, null, null)));

        for (Map.Entry<String, Map<String, List<String>>> outerEntry : h.entrySet()) {
            System.out.println(outerEntry.getKey());
            for (Map.Entry<String, List<String>> innerEntry : outerEntry.getValue().entrySet()) {
                System.out.println(innerEntry.getKey());
                for (String item : innerEntry.getValue()) {
                    System.out.println(item);
                }
            }
        }

    }

}
