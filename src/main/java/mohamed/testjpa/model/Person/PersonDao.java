package mohamed.testjpa.model.Person;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import mohamed.testjpa.model.Course.Course;
import mohamed.testjpa.model.myclass.MyClass;

public class PersonDao {

    // connection to database
    public static Connection connectDB() throws InstantiationException, IllegalAccessException {
        String url = "jdbc:mysql://localhost:3306/daawbd?serverTimezone=UTC";
        String person = "AdminTP";
        String password = "Lilou2001";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection(url, person, password);
            return connect;
        } catch (ClassNotFoundException e) {
            System.out.println("something went wrong... (class not found)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }

    public void addUser(Person person) throws InstantiationException, IllegalAccessException, SQLException {
        String first_name = person.getFirst_name();
        String last_name = person.getLast_name();
        String username = person.getUsername();
        String password = person.getPassword();
        String place_of_birth = person.getPlace_of_birth();
        String gender = person.getGender();
        String role = person.getRole();
        String dateOFbirth = person.getDate_of_birth();
        System.out.println(dateOFbirth);
        Connection connect = connectDB();
        final String requete = "insert into person values (default,'"
                + first_name + "','" + last_name + "','" + place_of_birth
                + "','"
                + username + "','" + password + "','" + role + "','" + gender + "','" + dateOFbirth + "')";
        System.out.println(requete);
        PreparedStatement result = connect.prepareStatement(requete);
        // result.setString(1, FirstName);
        // result.setString(2, LastName);
        // result.setString(3, username);

        // result.setString(6, password);
        result.execute();
    }

    public Person getUser(int ID)
            throws InstantiationException, IllegalAccessException, ParseException {

        Connection connection = null;
        String requete;
        PreparedStatement statement;

        Person person = null;

        try {

            connection = connectDB();

            requete = "SELECT * FROM person WHERE id = ? ";
            statement = connection.prepareStatement(requete);
            statement.setInt(1, ID);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                person = new Person();
                person.setFirst_name(result.getString("first_name"));
                person.setLast_name(result.getString("last_name"));
                person.setRole(result.getString("role"));
                person.setGender(result.getString("gender"));
                person.setUsername(result.getString("username"));
                person.setPassword(result.getString("password"));

                person.setDate_of_birth(result.getString("dateOFbirth"));
                person.setPlace_of_birth(result.getString("place_of_Birth"));
            }

            statement.close();

            System.out.println("succés !");
        } catch (SQLException e) {
            System.out.println(e);
        }

        return person;
    }

    // public void deleteuser(int id) throws SQLException, InstantiationException,
    // IllegalAccessException {

    // Connection connection;

    // connection = connectDB();

    // final String requete = "DELETE FROM person WHERE idUser ='" + id + "' ";
    // PreparedStatement result = connection.prepareStatement(requete);
    // result.execute();
    // System.out.println("deleted");

    // }
    public Person getLastUser() throws InstantiationException, IllegalAccessException {

        Connection connection = null;
        String requete;
        PreparedStatement statement;

        Person person = null;

        try {

            connection = connectDB();

            requete = " select *from person ORDER BY id DESC LIMIT 1";
            statement = connection.prepareStatement(requete);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                person = new Person();
                person.setId(result.getInt("id"));
                person.setFirst_name(result.getString("first_name"));
                person.setLast_name(result.getString("last_name"));
                person.setRole(result.getString("role"));
                person.setGender(result.getString("gender"));
                person.setUsername(result.getString("username"));
                person.setPassword(result.getString("password"));

                person.setDate_of_birth(result.getString("dateOFbirth"));
                person.setPlace_of_birth(result.getString("place_of_Birth"));
            }

            statement.close();

            System.out.println("succés !");
        } catch (SQLException e) {
            System.out.println(e);
        }

        return person;
    }

    public ArrayList<Person> getUsers() throws InstantiationException,
            IllegalAccessException, ParseException {

        Connection connection = null;
        String requete;
        PreparedStatement statement;

        Person person = null;
        ArrayList<Person> usersList = new ArrayList<Person>();

        try {

            connection = connectDB();

            requete = "SELECT * FROM person where role!='admin'";
            statement = connection.prepareStatement(requete);

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                person = new Person();
                person.setId(result.getInt("id"));
                person.setFirst_name(result.getString("first_name"));
                person.setLast_name(result.getString("last_name"));
                person.setGender(result.getString("gender"));
                person.setRole(result.getString("role"));
                person.setUsername(result.getString("username"));
                person.setPassword(result.getString("password"));
                person.setDate_of_birth(result.getString("dateOFbirth"));
                person.setPlace_of_birth(result.getString("place_of_birth"));
                usersList.add(person);
            }

            statement.close();

            System.out.println("Users --- succés !");
        } catch (SQLException e) {
            System.out.println(e);
        }

        return usersList;
    }

    public Person checkLogin(String username, String password)
            throws InstantiationException, IllegalAccessException, ParseException {

        Connection connection = null;
        String requete;
        PreparedStatement statement;

        Person person = null;

        try {

            connection = connectDB();

            requete = "SELECT * FROM person WHERE username = ? and password = ?";
            statement = connection.prepareStatement(requete);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet result = statement.executeQuery();
            System.out.println(result);
            if (result.next()) {
                person = new Person();
                person.setId(result.getInt("id"));
                person.setFirst_name(result.getString("first_name"));
                person.setLast_name(result.getString("last_name"));
                person.setGender(result.getString("gender"));
                person.setRole(result.getString("role"));
                person.setUsername(result.getString("username"));
                person.setPassword(result.getString("password"));
                person.setDate_of_birth(result.getString("dateOFbirth"));
                person.setPlace_of_birth(result.getString("place_of_birth"));
            }

            statement.close();

            System.out.println("sucess");
        } catch (SQLException e) {
            System.out.println(e);
        }

        return person;
    }

    public void deleteuser(int id) throws SQLException, InstantiationException, IllegalAccessException {
        Connection connection;
        connection = connectDB();
        final String requete = "DELETE FROM person WHERE id =" + id + "";
        PreparedStatement result = connection.prepareStatement(requete);
        result.execute();
        System.out.println("deleted");
    }
    // public int getCoureID(int id) throws SQLException, InstantiationException,
    // IllegalAccessException {
    // Connection connection;
    // connection = connectDB();
    // final String requete = "DELETE FROM person WHERE id =" + id + "";
    // PreparedStatement result = connection.prepareStatement(requete);
    // result.execute();
    // System.out.println("deleted");
    // }

    public static ArrayList<MyClass> getClassLevel(String level)
            throws SQLException, InstantiationException, IllegalAccessException {
        ArrayList<MyClass> classList = new ArrayList<MyClass>();
        Connection connect = null;
        connect = connectDB();
        final String requete = "SELECT id,label from class where level ='" + level + "'";
        PreparedStatement statement = connect.prepareStatement(requete);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            MyClass myClass = new MyClass();
            myClass.setId(result.getInt("id"));
            myClass.setLabel(result.getString("label"));
            classList.add(myClass);
        }
        return classList;
    }

    // public static ArrayList<Course> getCoursesLevel(String level)
    // throws SQLException, InstantiationException, IllegalAccessException {
    // ArrayList<Course> coursesList = new ArrayList<Course>();
    // Connection connect = null;
    // connect = connectDB();
    // final String requete = "SELECT id,label from course where levelc ='" + level
    // + "'";
    // PreparedStatement statement = connect.prepareStatement(requete);
    // ResultSet result = statement.executeQuery();

    // while (result.next()) {
    // Course mycourse = new Course();
    // mycourse.setId(result.getInt("id"));
    // mycourse.setLabel(result.getString("label"));
    // coursesList.add(mycourse);
    // }
    // return coursesList;
    // }
    public void update(String column, String value, int id)
            throws SQLException, InstantiationException, IllegalAccessException {

        Connection connection;
        connection = connectDB();
        String requete;
        requete = "UPDATE  person SET  " + column + "= '" + value + "'  WHERE id = " + id + " ";
        PreparedStatement result = connection.prepareStatement(requete);
        result.execute();
        System.out.println("UPDATED");
        // System.out.println(result);
        // System.out.println(requete);

    }
    // public void assign_teacher_work(int id_teacher, int id_course, int id_class)
    // throws SQLException, InstantiationException, IllegalAccessException {
    // Connection connection = null;
    // final String requete;
    // PreparedStatement statement;

    // connection = connectDB();
    // requete = "insert into teach values(" + id_teacher + "," + id_course + "," +
    // id_class + ")";
    // statement = connection.prepareStatement(requete);
    // PreparedStatement result = connection.prepareStatement(requete);
    // result.execute();
    // System.out.println("success !!");

    // }

    // public void updateTeacherAssignment(String course_grp, String classroom, int
    // id_teacher, int old_class_id,
    // int old_course_id, String teaching_level)
    // throws InstantiationException, IllegalAccessException, SQLException {

    // Connection connection;
    // connection = connectDB();
    // String requete;
    // requete = "update teach set id_course =(Select id from course where levelc
    // like'" + teaching_level
    // + "' and label like '" + course_grp + "') , id_class = (Select id from class
    // where level like '"
    // + teaching_level + "' and label like '" + classroom + "') where id_teacher="
    // + id_teacher + " and id_class=" + old_class_id + " and id_course =" +
    // old_course_id + "";
    // PreparedStatement result = connection.prepareStatement(requete);
    // System.out.println(requete);
    // result.execute();
    // System.out.println("UPDATED");

    // }

}
