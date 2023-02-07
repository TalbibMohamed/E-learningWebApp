package mohamed.testjpa.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import mohamed.testjpa.model.Course.Course;
import mohamed.testjpa.model.Course.CourseContent;
import mohamed.testjpa.model.Person.Person;
import mohamed.testjpa.model.Person.PersonDao;
import mohamed.testjpa.model.schedule.ScheduleDao;
import mohamed.testjpa.model.student.Student;
import mohamed.testjpa.model.student.StudentDao;
import mohamed.testjpa.model.teacher.Teacher;
import mohamed.testjpa.model.teacher.TeacherDao;
import mohamed.testjpa.model.teacher.Teaching;
import mohamed.testjpa.repository.UserRepository;

@Controller
public class TeacherController {
    UserRepository userRp = new UserRepository();

    // @GetMapping("/teacherDashboard")
    // public ModelAndView teacherDashboard(HttpServletRequest request, Model model)
    // throws InstantiationException, IllegalAccessException, ParseException,
    // SQLException {
    // Person person = (Person) request.getSession().getAttribute("Person");
    // ModelAndView mdv = userRp.acceptedView(person);
    // if (person == null) {
    // return mdv;
    // }
    // Teacher teacher = new Teacher();
    // TeacherDao teacherDao = new TeacherDao();
    // teacher = teacherDao.getUser(person.getId());
    // model.addAttribute("teacher", teacher);
    // return mdv;
    // }

    @GetMapping("/teacherDashboard")
    public ModelAndView teacherDashboard(HttpServletRequest request, Model model)
            throws InstantiationException, IllegalAccessException, ParseException, SQLException {
        Person person = (Person) request.getSession().getAttribute("Person");
        ModelAndView mdv = userRp.acceptedView(person);
        if (person == null) {
            return mdv;
        }
        Teacher teacher = new Teacher();
        TeacherDao teacherDao = new TeacherDao();
        teacher = teacherDao.getUser(person.getId());
        model.addAttribute("teacher", teacher);
        ArrayList<String> courses = teacherDao.getCourses(teacher);
        model.addAttribute("coursesList", courses);

        model.addAttribute("schedules", new ScheduleDao().getScheduleBref(teacher, courses));
        return mdv;
    }

    @GetMapping("/schedule")
    public ModelAndView schedule(HttpServletRequest request, Model model)
            throws InstantiationException, IllegalAccessException, ParseException, SQLException {
        ModelAndView mdv = new ModelAndView();
        ScheduleDao scheduleDao = new ScheduleDao();
        Person person = (Person) request.getSession().getAttribute("Person");
        if (person != null
                && (person.getRole().equalsIgnoreCase("admin") || person.getRole().equalsIgnoreCase("teacher"))) {
            if (person.getRole().equalsIgnoreCase("teacher")) {
                Teacher teacher = new Teacher();
                TeacherDao teacherDao = new TeacherDao();
                teacher = teacherDao.getUser(person.getId());
                model.addAttribute("teacher", teacher);
                model.addAttribute("schedule", scheduleDao.getScheduleTable(teacher));
            } else {
                mdv.setViewName("index");
            }

            mdv.setViewName("panels/showschedule");
        } else {
            mdv = userRp.acceptedView(person);
        }

        return mdv;
    }

    @GetMapping("/studentsList")
    public ModelAndView Studentlist(HttpServletRequest request, Model model)
            throws InstantiationException, IllegalAccessException, ParseException, SQLException {
        ModelAndView mdv = new ModelAndView();
        ArrayList<Student> studentsList = new ArrayList<Student>();
        StudentDao stdao = new StudentDao();

        Person person = (Person) request.getSession().getAttribute("Person");
        if (person != null
                && (person.getRole().equalsIgnoreCase("admin") || person.getRole().equalsIgnoreCase("teacher"))) {

            studentsList = stdao.getStudentList(person);
            model.addAttribute("studentsList", studentsList);
            if (person.getRole().equalsIgnoreCase("teacher")) {
                Teacher teacher = new Teacher();
                TeacherDao teacherDao = new TeacherDao();
                teacher = teacherDao.getUser(person.getId());
                model.addAttribute("teacher", teacher);
            } else {

            }

            mdv.setViewName("panels/studentsList");
        } else {
            mdv = userRp.acceptedView(person);
        }

        return mdv;
    }

    @PostMapping("/upload")
    public RedirectView doPost(HttpServletRequest request, HttpServletResponse response, @RequestParam String file_type,
            @RequestParam String chapter_name, @RequestParam String course_name)
            throws IOException, ServletException, InstantiationException, IllegalAccessException, SQLException {
        // miss instruction
        /*
         * request parameter => grade of course file
         * type of course content
         * course of that courseContent like daaw or gl (courses list thats teached by
         * connected teacher )
         * title of file
         * need tamplate Course management
         */
        // get the file chosen by the user
        RedirectView rdv = new RedirectView();
        rdv.setUrl("/courseManagement");
        Part filePart = request.getPart("fileToUpload");

        InputStream fileInputStream = filePart.getInputStream();
        if (filePart.getSubmittedFileName().endsWith(".pdf")) {
            StudentDao std = new StudentDao();
            String level = std.getCourseLevel(course_name);
            // for example, you can copy the uploaded file to the server
            // note that you probably don't want to do this in real life!
            // upload it to a file host like S3 or GCS instead
            // craeate folders if not are exist in server
            File folderLevel = new File(
                    "src/main/resources/static/courses/" + level);
            folderLevel.mkdir();
            File folderDirectoryCourse = new File(
                    "src/main/resources/static/courses/" + level + "/" + course_name);
            folderDirectoryCourse.mkdir();
            File folderDirectoryFileType = new File(
                    "src/main/resources/static/courses/" + level + "/" + course_name + "/" + file_type);
            folderDirectoryFileType.mkdir();

            File fileToSave = new File(
                    "src/main/resources/static/courses/" + level + "/" + course_name + "/" + file_type + "/"
                            + filePart.getSubmittedFileName());
            Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
            // insertion in data base
            System.out.println("////");
            String path = "/courses/" + level + "/" + course_name + "/" + file_type + "/"
                    + filePart.getSubmittedFileName();
            System.out.println(path);
            int id_course = std.getCourseId(course_name, level);
            std.uploadFile(id_course, path, chapter_name, file_type);
            return rdv;
        }
        response.getOutputStream().println("<p>Oops ! u have to upload a pdf file </p>");
        return null;

    }

    @GetMapping("/courseManagement")
    public ModelAndView courseMaj(HttpServletRequest req, Model model)
            throws InstantiationException, IllegalAccessException, ParseException, SQLException {
        Person person = (Person) req.getSession().getAttribute("Person");
        ModelAndView mdv = new ModelAndView();
        StudentDao std = new StudentDao();
        if (person != null) {
            if (person.getRole().equalsIgnoreCase("teacher")) {
                ArrayList<Teaching> teaching_list = new ArrayList<Teaching>();
                TeacherDao teacherDao = new TeacherDao();
                teaching_list = teacherDao.get_Teacher_assignement(person.getId());
                Teacher teacher = new Teacher();
                teacher = teacherDao.getUser(person.getId());
                model.addAttribute("teacher", teacher);
                model.addAttribute("teachingInfo", teaching_list);
                mdv.setViewName("panels/courseManagement");
                // courses with content
                ArrayList<Object> globalList = new ArrayList<Object>();
                ArrayList<Object> list = new ArrayList<Object>();
                ArrayList<Course> courses = new ArrayList<Course>();
                ArrayList<CourseContent> courseContent_cours = null;
                ArrayList<CourseContent> courseContent_TP = null;
                ArrayList<CourseContent> courseContent_TD = null;
                StudentDao studentDao = new StudentDao();
                courses = teacherDao.getCourses(person.getId());
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
                model.addAttribute("courses", globalList);
            } else {
                mdv = userRp.acceptedView(person);
            }
        } else {
            mdv = userRp.acceptedView(person);
        }

        return mdv;
    }

    @PostMapping("/deleteFile")
    public RedirectView deleteFile(@RequestParam String file_path)
            throws IOException, InstantiationException, IllegalAccessException, SQLException {
        RedirectView rdv = new RedirectView();
        System.out.println("//////");
        System.out.println(file_path);

        // delete file from server
        Path myPath = Paths.get(file_path);

        boolean fileDeleted = Files.deleteIfExists(myPath);

        if (fileDeleted) {

            System.out.println("File deleted");
        } else {

            System.out.println("File does not exist");
        }
        // delete file from database
        TeacherDao teacherDao = new TeacherDao();
        teacherDao.deleteFile(file_path);
        rdv.setUrl("/courseManagement");
        return rdv;
    }

}
