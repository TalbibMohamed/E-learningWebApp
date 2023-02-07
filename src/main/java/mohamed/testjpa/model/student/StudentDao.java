package mohamed.testjpa.model.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import mohamed.testjpa.model.Course.Course;
import mohamed.testjpa.model.Course.CourseContent;
import mohamed.testjpa.model.Person.Person;

public class StudentDao {
    // connection to database connection for student
    public static Connection connectDB() throws InstantiationException, IllegalAccessException {

        String url = "jdbc:mysql://localhost:3306/daawbd?serverTimezone=UTC";
        String student = "AdminTP";
        String password = "Lilou2001";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connect = DriverManager.getConnection(url, student, password);
            System.out.println("connected to database successfully");
            return connect;
        } catch (ClassNotFoundException e) {
            System.out.println("something went wrong... (class not found)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }

    public Student getUser(int ID)
            throws InstantiationException, IllegalAccessException, ParseException, SQLException {

        Connection connection = null;
        String requete;
        PreparedStatement statement;

        Student student = null;

        try {

            connection = connectDB();

            requete = "SELECT * FROM student WHERE id = ? ";
            statement = connection.prepareStatement(requete);
            statement.setInt(1, ID);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                student = new Student();
                student.setId(result.getInt("id"));
                student.setId_class(result.getInt("id_class"));
                student.setGrade(result.getString("grade"));

            }

            statement.close();

            System.out.println("succés !");
        } catch (SQLException e) {
            System.out.println(e);
        }

        return student;
    }

    public ArrayList<CourseContent> getCourseContent(int id, String contentType)
            throws InstantiationException, IllegalAccessException {
        ArrayList<CourseContent> courseContentList = new ArrayList<CourseContent>();

        Connection connection = null;
        String requete;
        PreparedStatement statement;

        try {

            connection = connectDB();

            requete = "SELECT * FROM coursecontent WHERE id_course = ? and contentType='" + contentType + "'  ";
            statement = connection.prepareStatement(requete);
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                CourseContent courseContent = new CourseContent();
                courseContent.setId(result.getInt("id_course"));
                courseContent.setFile_path(result.getString("file_path"));
                courseContent.setChapterName(result.getString("chapter_name"));
                courseContent.setContentType(result.getString("contentType"));
                courseContentList.add(courseContent);
            }

            statement.close();

            System.out.println("succés !");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return courseContentList;
    }

    // public ArrayList<CourseContent> getCourseContent_TP(int id) throws
    // InstantiationException, IllegalAccessException {
    // ArrayList<CourseContent> courseContentList = new ArrayList<CourseContent>();

    // Connection connection = null;
    // String requete;
    // PreparedStatement statement;

    // try {

    // connection = connectDB();

    // requete = "SELECT * FROM coursecontent WHERE id_course = ? and
    // contentType='tp' ";
    // statement = connection.prepareStatement(requete);
    // statement.setInt(1, id);

    // ResultSet result = statement.executeQuery();

    // while (result.next()) {
    // CourseContent courseContent = new CourseContent();
    // courseContent.setId(result.getInt("id_course"));
    // courseContent.setFile_path(result.getString("file_path"));
    // courseContent.setChapterName(result.getString("chapter_name"));
    // courseContentList.add(courseContent);
    // }

    // statement.close();

    // System.out.println("succés !");
    // } catch (SQLException e) {
    // System.out.println(e);
    // }
    // return courseContentList;
    // }

    public String getStudentClass(int id) throws InstantiationException,
            IllegalAccessException, SQLException {
        String label = "";
        Connection connection = null;
        String requete = "select label from class , student where id_class=class.id and student.id =" + id + "";

        PreparedStatement statement;
        connection = connectDB();
        statement = connection.prepareStatement(requete);

        ResultSet result = statement.executeQuery();
        if (result.next()) {
            label = result.getString("label");

        }

        statement.close();

        System.out.println("succés !");
        return label;
    }

    public int getClassId(String name) throws InstantiationException, IllegalAccessException, SQLException {

        int idclass = 0;
        Connection connection = null;
        String requete = "select id from class where label='" + name + "'";

        PreparedStatement statement;
        connection = connectDB();
        statement = connection.prepareStatement(requete);

        ResultSet result = statement.executeQuery();
        if (result.next()) {
            idclass = result.getInt("id");

        }

        statement.close();

        System.out.println("succés !");
        return idclass;
    }

    public int getClassId(String name, String level)
            throws InstantiationException, IllegalAccessException, SQLException {

        int idclass = 0;
        Connection connection = null;
        String requete = "select id from class where label='" + name + "'and level = '" + level + "'";

        PreparedStatement statement;
        connection = connectDB();
        statement = connection.prepareStatement(requete);

        ResultSet result = statement.executeQuery();
        if (result.next()) {
            idclass = result.getInt("id");

        }

        statement.close();

        System.out.println("succés !");
        return idclass;
    }

    public int getCourseId(String name, String level)
            throws InstantiationException, IllegalAccessException, SQLException {

        int idclass = 0;
        Connection connection = null;
        String requete = "select id from course where label='" + name + "'and levelc ='" + level + "'";

        PreparedStatement statement;
        connection = connectDB();
        statement = connection.prepareStatement(requete);

        ResultSet result = statement.executeQuery();
        if (result.next()) {
            idclass = result.getInt("id");

        }

        statement.close();

        System.out.println("succés !");
        return idclass;
    }

    public ArrayList<Course> getCourses(String level)
            throws InstantiationException, IllegalAccessException, SQLException {

        ArrayList<Course> coursesList = new ArrayList<Course>();
        Connection connection = null;
        String requete = "select id , label , coefficient ,levelc,full_label from course where  levelc ='" + level
                + "'";
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

    public void addUser(Student student) throws InstantiationException, IllegalAccessException, SQLException {
        int id = student.getId();
        int id_class = student.getId_class();
        String grade = student.getGrade();
        Connection connect = connectDB();
        final String requete = "insert into student values (" + id + "," + id_class + ",'" + grade + "')";

        System.out.println(requete);
        PreparedStatement result = connect.prepareStatement(requete);

        result.execute();
    }

    public ArrayList<Student> getStudentList(Person person)
            throws InstantiationException, IllegalAccessException, SQLException {
        ArrayList<Student> studentList = new ArrayList<Student>();
        Connection connection = null;
        // String requete = " select student.id
        // ,first_name,last_name,dateOFbirth,place_of_birth,username,password,gender,grade,class.label
        // , role from person ,student,class where person.role='student' and
        // student.id=person.id and class.id=student.id_class ";
        String requete = " select distinct student.id ,first_name,last_name,dateOFbirth,place_of_birth,username,password,gender,grade,class.label , role  from person ,student,teach,class where student.id = person.id and class.id =teach.id_class and teach.id_class=student.id_class and  teach.id_teacher="
                + person.getId() + "";

        PreparedStatement statement;
        connection = connectDB();
        statement = connection.prepareStatement(requete);

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            Student st = new Student();
            st.setId(result.getInt("student.id"));
            st.setFirst_name(result.getString("first_name"));
            st.setLast_name(result.getString("last_name"));
            st.setDate_of_birth(result.getString("dateOFbirth"));
            st.setPlace_of_birth(result.getString("place_of_birth"));
            st.setUsername(result.getString("username"));
            st.setPassword(result.getString("password"));
            st.setGender(result.getString("gender"));
            st.setGrade(result.getString("grade"));
            st.setRole(result.getString("role"));
            st.setLabel(result.getString("label"));
            studentList.add(st);
        }

        statement.close();

        return studentList;

    }

    public ArrayList<Student> getStudentList()
            throws InstantiationException, IllegalAccessException, SQLException {
        ArrayList<Student> studentList = new ArrayList<Student>();
        Connection connection = null;
        String requete = " select student.id,first_name,last_name,dateOFbirth,place_of_birth,username,password,gender,grade,class.label, role from person ,student,class where person.role='student' and student.id=person.id and class.id=student.id_class ";

        PreparedStatement statement;
        connection = connectDB();
        statement = connection.prepareStatement(requete);

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            Student st = new Student();
            st.setId(result.getInt("student.id"));
            st.setFirst_name(result.getString("first_name"));
            st.setLast_name(result.getString("last_name"));
            st.setDate_of_birth(result.getString("dateOFbirth"));
            st.setPlace_of_birth(result.getString("place_of_birth"));
            st.setUsername(result.getString("username"));
            st.setPassword(result.getString("password"));
            st.setGender(result.getString("gender"));
            st.setGrade(result.getString("grade"));
            st.setRole(result.getString("role"));
            st.setLabel(result.getString("label"));
            studentList.add(st);
        }

        statement.close();

        return studentList;

    }

    public ArrayList<Student> getTeachingStudentList()
            throws InstantiationException, IllegalAccessException, SQLException {
        ArrayList<Student> studentList = new ArrayList<Student>();
        Connection connection = null;
        String requete = " select  student.id ,first_name,last_name,dateOFbirth,place_of_birth,username,password,gender,grade,class.label , role from person ,student,class where person.role='student' and student.id=person.id and class.id=student.id_class ";

        PreparedStatement statement;
        connection = connectDB();
        statement = connection.prepareStatement(requete);

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            Student st = new Student();
            st.setId(result.getInt("student.id"));
            st.setFirst_name(result.getString("first_name"));
            st.setLast_name(result.getString("last_name"));
            st.setDate_of_birth(result.getString("dateOFbirth"));
            st.setPlace_of_birth(result.getString("place_of_birth"));
            st.setUsername(result.getString("username"));
            st.setPassword(result.getString("password"));
            st.setGender(result.getString("gender"));
            st.setGrade(result.getString("grade"));
            st.setRole(result.getString("role"));
            st.setLabel(result.getString("label"));
            studentList.add(st);
        }

        statement.close();

        return studentList;

    }

    public void changeStudent(int id_class, String grade, int id)
            throws InstantiationException, IllegalAccessException, SQLException {
        Connection connection;
        connection = connectDB();
        final String requete = "update student set id_class=" + id_class + ", grade='" + grade + "' WHERE id=" + id
                + "";
        System.out.println(requete);
        PreparedStatement result = connection.prepareStatement(requete);
        result.execute();
        System.out.println("updated");
    }

    public String getCourseLevel(String course_name)
            throws InstantiationException, IllegalAccessException, SQLException {
        String level = null;
        Connection connection = null;
        String requete = "select levelc from course where label='" + course_name + "'";

        PreparedStatement statement;
        connection = connectDB();
        statement = connection.prepareStatement(requete);

        ResultSet result = statement.executeQuery();
        if (result.next()) {
            level = result.getString("levelc");

        }

        statement.close();

        System.out.println("succés !");
        return level;
    }

    public void uploadFile(int id_course, String path, String chapter_name, String file_type)
            throws InstantiationException, IllegalAccessException, SQLException {

        Connection connection = null;
        String requete = "insert into courseContent values(" + id_course + ",'" + path + "','" + chapter_name + "','"
                + file_type + "')";
        System.out.println(requete);
        PreparedStatement statement;
        connection = connectDB();
        statement = connection.prepareStatement(requete);
        statement.execute();
    }

}
