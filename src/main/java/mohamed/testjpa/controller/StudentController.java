package mohamed.testjpa.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import mohamed.testjpa.model.Course.Course;
import mohamed.testjpa.model.Course.CourseContent;
import mohamed.testjpa.model.Person.Person;
import mohamed.testjpa.model.student.Student;
import mohamed.testjpa.model.student.StudentDao;
import mohamed.testjpa.repository.UserRepository;

@Controller
public class StudentController {
    UserRepository userRp = new UserRepository();

    @GetMapping("/studentDashboard")
    public ModelAndView Studentlist(HttpServletRequest request, Model model)
            throws InstantiationException, IllegalAccessException, ParseException, SQLException {
        Person person = (Person) request.getSession().getAttribute("Person");
        if (person == null) {
            ModelAndView mdv = userRp.acceptedView(person);
            return mdv;
        }
        ModelAndView mdv = userRp.acceptedView(person);
        StudentDao studentDao = new StudentDao();
        Student student = new Student();
        student = studentDao.getUser(person.getId());
        // we use this array to get any information about student
        // ArrayList<String> AdditionalInfo = new ArrayList<String>();
        // AdditionalInfo.add(studentDao.getStudentClass(person.getId()));
        String label = studentDao.getStudentClass(person.getId());
        model.addAttribute("className", label);
        model.addAttribute("student", student);
        ArrayList<Object> globalList = new ArrayList<Object>();
        ArrayList<Object> list = new ArrayList<Object>();
        ArrayList<Course> courses = new ArrayList<Course>();
        ArrayList<CourseContent> courseContent_cours = null;
        ArrayList<CourseContent> courseContent_TP = null;
        ArrayList<CourseContent> courseContent_TD = null;

        courses = studentDao.getCourses(student.getGrade());
        for (int i = 0; i < courses.size(); i++) {
            list = new ArrayList<Object>();
            courseContent_cours = new ArrayList<CourseContent>();
            courseContent_TP = new ArrayList<CourseContent>();
            courseContent_TD = new ArrayList<CourseContent>();

            courseContent_cours = studentDao.getCourseContent(courses.get(i).getId(), "cours");
            courseContent_TP = studentDao.getCourseContent(courses.get(i).getId(), "tp");
            courseContent_TD = studentDao.getCourseContent(courses.get(i).getId(), "td");
            list.add(courses.get(i));
            list.add(courseContent_cours);
            list.add(courseContent_TP);
            list.add(courseContent_TD);
            globalList.add(list);

        }
        // // ((ArrayList<CourseContent>) daaw.get(1)).get(0).getFile_path()
        // ArrayList<Object> course = new ArrayList<Object>();
        // course = (ArrayList<Object>) globalList.get(1);

        // Course courseInfo = new Course();
        // courseInfo = (Course) course.get(0);

        // System.out.println(courseInfo);
        // courseContent = new ArrayList<CourseContent>();
        // courseContent = (ArrayList<CourseContent>) course.get(1);

        // System.out.println(courseContent);
        // for (int i = 0; i < globalList.size(); i++) {
        // ArrayList<Object> course = new ArrayList<Object>();
        // course = (ArrayList<Object>) globalList.get(i);
        // Course courseInfo = new Course();
        // courseInfo = (Course) course.get(0);

        // System.out.println("Course : " + courseInfo.getLabel());
        // courseContent = new ArrayList<CourseContent>();
        // System.out.println("course content : ");
        // courseContent = (ArrayList<CourseContent>) course.get(1);
        // for (int j = 0; j < courseContent.size(); j++) {
        // System.out.println(courseContent.get(j).getChapterName());
        // }
        // System.out.println();
        // System.out.println();
        // }
        // ArrayList<Course> coursesList = new ArrayList<Course>();
        // coursesList = studentDao.getCourses(student.getGrade());
        model.addAttribute("courses", globalList);
        return mdv;
    }
}
