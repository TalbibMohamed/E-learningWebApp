package mohamed.testjpa.model.teacher;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import mohamed.testjpa.model.Course.Course;

public class TeacherDao {
    // connection to database connection for teacher
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

    public static ArrayList<Teacher> getTeacherList()
            throws InstantiationException, IllegalAccessException, SQLException {
        ArrayList<Teacher> studentList = new ArrayList<Teacher>();
        Connection connection = null;
        String requete = " select  teacher.id ,first_name,last_name,dateOFbirth,place_of_birth,username,password,gender,degree,hiring_date,role from person ,teacher where person.role='teacher' and teacher.id=person.id ";

        PreparedStatement statement;
        connection = connectDB();
        statement = connection.prepareStatement(requete);

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            Teacher st = new Teacher();
            st.setId(result.getInt("teacher.id"));
            st.setFirst_name(result.getString("first_name"));
            st.setLast_name(result.getString("last_name"));
            st.setDate_of_birth(result.getString("dateOFbirth"));
            st.setPlace_of_birth(result.getString("place_of_birth"));
            st.setUsername(result.getString("username"));
            st.setPassword(result.getString("password"));
            st.setGender(result.getString("gender"));
            st.setHiringDate(result.getString("hiring_date"));
            st.setRole(result.getString("role"));
            st.setDegree(result.getString("degree"));
            studentList.add(st);
        }

        statement.close();

        return studentList;

    }

    public Teacher getUser(int ID)
            throws InstantiationException, IllegalAccessException, ParseException, SQLException {

        Connection connection = null;
        String requete;
        PreparedStatement statement;

        Teacher teacher = null;

        try {

            connection = connectDB();

            requete = "SELECT * FROM teacher WHERE id = ? ";
            statement = connection.prepareStatement(requete);
            statement.setInt(1, ID);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                teacher = new Teacher();
                teacher.setId(result.getInt("id"));
                teacher.setDegree(result.getString("degree"));
                teacher.setHiringDate(result.getString("hiring_date"));

            }

            statement.close();
            System.out.println(teacher);
            System.out.println("succés !");
        } catch (SQLException e) {
            System.out.println(e);
        }

        return teacher;
    }

    public static ArrayList<Teaching> get_Teacher_assignement(int ID)
            throws InstantiationException, IllegalAccessException, ParseException, SQLException {
        ArrayList<Teaching> teaching_list = new ArrayList<Teaching>();
        Connection connection = null;
        String requete;
        PreparedStatement statement;
        try {
            connection = connectDB();
            requete = "select class.level ,class.label as class ,course.label as course  from teach , course,class where teach.id_teacher =?  and teach.id_class=class.id and  teach.id_course=course.id ";
            statement = connection.prepareStatement(requete);
            statement.setInt(1, ID);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Teaching tch = new Teaching();
                tch.setCourse_name(result.getString("course"));
                tch.setLevel(result.getString("level"));
                tch.setTeachingClass(result.getString("class"));
                teaching_list.add(tch);
            }
            statement.close();
            System.out.println("succés !");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return teaching_list;
    }

    public ArrayList<String> getCourses(Teacher teacher) throws InstantiationException, IllegalAccessException {
        Connection connection = null;
        String requete;
        PreparedStatement statement;

        ArrayList<String> courses = new ArrayList<String>();

        try {

            connection = connectDB();

            requete = "SELECT DISTINCT id_course , full_label FROM teach,course where id_teacher = " + teacher.getId()
                    + " AND id_course = id ;";
            statement = connection.prepareStatement(requete);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                courses.add(result.getString("full_label"));
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return courses;
    }

    public void addUser(Teacher teacher) throws InstantiationException, IllegalAccessException, SQLException {
        int id = teacher.getId();
        String grade = teacher.getDegree();
        String hiring_date = teacher.getHiringDate();
        Connection connect = connectDB();
        final String requete = "insert into teacher values (" + id + ",'" + grade + "','" + hiring_date + "')";

        System.out.println(requete);
        PreparedStatement result = connect.prepareStatement(requete);

        result.execute();
    }

    public void update(String column, String value, int id)
            throws SQLException, InstantiationException, IllegalAccessException {

        Connection connection;
        connection = connectDB();
        String requete;
        requete = "UPDATE  teacher SET  " + column + "= '" + value + "'  WHERE id = " + id + " ";
        PreparedStatement result = connection.prepareStatement(requete);
        result.execute();
        System.out.println("UPDATED");
        // System.out.println(result);
        // System.out.println(requete);

    }

    public void assign_teacher_work(int id_teacher, int id_course, int id_class)
            throws SQLException, InstantiationException, IllegalAccessException {
        Connection connection = null;
        final String requete;
        PreparedStatement statement;

        connection = connectDB();
        requete = "insert into teach values(" + id_teacher + "," + id_course + "," + id_class + ")";
        statement = connection.prepareStatement(requete);
        PreparedStatement result = connection.prepareStatement(requete);
        result.execute();
        System.out.println("success !!");

    }

    public void deleteTeacherAssignment(int id_class, int id_course, int id_teacher)
            throws InstantiationException, IllegalAccessException, SQLException {

        Connection connection;
        connection = connectDB();
        String requete;
        System.out.println(id_class);
        System.out.println(id_teacher);
        System.out.println(id_course);
        requete = "delete from teach where id_teacher = " + id_teacher + " and id_class = " + id_class
                + " and id_course = " + id_course + " ";
        PreparedStatement result = connection.prepareStatement(requete);
        System.out.println(requete);
        result.execute();
        System.out.println("DELETED");
    }

    public void updateTeacherAssignment(String course_grp, String classroom, int id_teacher, int old_class_id,
            int old_course_id, String teaching_level)
            throws InstantiationException, IllegalAccessException, SQLException {

        Connection connection;
        connection = connectDB();
        String requete;
        requete = "update teach set id_course =(Select id from course where levelc like'" + teaching_level
                + "' and label like '" + course_grp + "') , id_class = (Select id from class where level like '"
                + teaching_level + "' and  label like '" + classroom + "') where id_teacher="
                + id_teacher + " and id_class=" + old_class_id + " and id_course =" + old_course_id + "";
        PreparedStatement result = connection.prepareStatement(requete);
        System.out.println(requete);
        result.execute();
        System.out.println("UPDATED");

    }

    public ArrayList<Course> getCourses(int id_teacher)
            throws InstantiationException, IllegalAccessException, SQLException {

        ArrayList<Course> coursesList = new ArrayList<Course>();
        Connection connection = null;
        // String requete = "select id , label , coefficient ,levelc,full_label from
        // course where levelc ='" + level
        // + "'";
        String requete = "select id , label , coefficient ,levelc,full_label from course , teach where course.id = teach.id_course and teach.id_teacher="
                + id_teacher;
        System.out.println(requete);
        PreparedStatement statement;
        connection = connectDB();
        statement = connection.prepareStatement(requete);

        ResultSet result = statement.executeQuery();
        while (result.next()) {
            Course c = new Course();
            c.setId(result.getInt("id"));
            c.setLabel(result.getString("label"));
            c.setCoefficient(result.getInt("coefficient"));
            c.setLevel(result.getString("levelc"));
            c.setFull_label(result.getString("full_label"));
            coursesList.add(c);
        }

        statement.close();

        System.out.println("succés !");
        return coursesList;
    }

    public void deleteFile(String file_path) throws InstantiationException, IllegalAccessException, SQLException {

        Connection connection;
        connection = connectDB();
        String requete;
        requete = "DELETE FROM courseContent where file_path='" + file_path + "'";
        PreparedStatement result = connection.prepareStatement(requete);
        result.execute();
        System.out.println("DELETED");
    }
}
