package mohamed.testjpa.model.Person;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

public class Person {

    private int id;

    private String first_name;

    private String last_name;

    private String place_of_birth;

    private String username;

    private String password;

    private String role;

    private String gender;

    private String date_of_birth;

    public Person() {
    }

    public Person(int id, String first_name, String last_name, String place_of_birth, String username, String password,
            String role, String gender, String date_of_birth) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.place_of_birth = place_of_birth;
        this.username = username;
        this.password = password;
        this.role = role;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
    }

    public Person(String first_name, String last_name, String place_of_birth,
            String username, String password,
            String role, String gender, String date_of_birth) {

        this.first_name = first_name;
        this.last_name = last_name;
        this.place_of_birth = place_of_birth;
        this.username = username;
        this.password = password;
        this.role = role;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPlace_of_birth() {
        return this.place_of_birth;
    }

    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDate_of_birth() {
        return this.date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public Person id(int id) {
        setId(id);
        return this;
    }

    public Person first_name(String first_name) {
        setFirst_name(first_name);
        return this;
    }

    public Person last_name(String last_name) {
        setLast_name(last_name);
        return this;
    }

    public Person place_of_birth(String place_of_birth) {
        setPlace_of_birth(place_of_birth);
        return this;
    }

    public Person username(String username) {
        setUsername(username);
        return this;
    }

    public Person password(String password) {
        setPassword(password);
        return this;
    }

    public Person role(String role) {
        setRole(role);
        return this;
    }

    public Person gender(String gender) {
        setGender(gender);
        return this;
    }

    public Person date_of_birth(String date_of_birth) {
        setDate_of_birth(date_of_birth);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", first_name='" + getFirst_name() + "'" +
                ", last_name='" + getLast_name() + "'" +
                ", place_of_birth='" + getPlace_of_birth() + "'" +
                ", username='" + getUsername() + "'" +
                ", password='" + getPassword() + "'" +
                ", role='" + getRole() + "'" +
                ", gender='" + getGender() + "'" +
                ", date_of_birth='" + getDate_of_birth() + "'" +
                "}";
    }
}
