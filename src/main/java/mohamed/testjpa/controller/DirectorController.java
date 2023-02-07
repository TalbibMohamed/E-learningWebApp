package mohamed.testjpa.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.naming.ldap.Rdn;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import mohamed.testjpa.model.Course.Course;
import mohamed.testjpa.model.Course.CourseContent;
import mohamed.testjpa.model.Person.Person;
import mohamed.testjpa.model.Person.PersonDao;
import mohamed.testjpa.model.myclass.MyClass;
import mohamed.testjpa.model.student.Student;
import mohamed.testjpa.model.student.StudentDao;
import mohamed.testjpa.model.teacher.Teacher;
import mohamed.testjpa.model.teacher.TeacherDao;
import mohamed.testjpa.model.teacher.Teaching;
import mohamed.testjpa.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestBody;

@Controller

public class DirectorController {
    UserRepository userRp = new UserRepository();
    StudentDao studentDao = new StudentDao();
    TeacherDao teacherDao = new TeacherDao();

    PersonDao personDao = new PersonDao();

    @GetMapping("/AdminDashboard")
    public ModelAndView AdminDashboard(HttpServletRequest request, Model model)
            throws InstantiationException, IllegalAccessException, ParseException, SQLException {
        Person person = (Person) request.getSession().getAttribute("Person");
        ModelAndView mdv = userRp.acceptedView(person);

        ArrayList<Teacher> teacher_list = new ArrayList<Teacher>();
        teacher_list = teacherDao.getTeacherList();
        model.addAttribute("teacher_list", teacher_list);

        // Adding student list to model to display it in admin panel

        ArrayList<Student> student_list = new ArrayList<Student>();
        student_list = studentDao.getStudentList();
        model.addAttribute("student_list", student_list);

        // list L3 COURSEs
        ArrayList<Course> coursesList_L3 = new ArrayList<Course>();
        coursesList_L3 = studentDao.getCourses("l3");
        model.addAttribute("L3_courses", coursesList_L3);
        // list L2 COURSEs
        ArrayList<Course> coursesList_L2 = new ArrayList<Course>();
        coursesList_L2 = studentDao.getCourses("l3");
        model.addAttribute("L2_courses", coursesList_L2);

        return mdv;
    }

    @PostMapping("/AdminDashboard/addUser")
    public RedirectView newPerson(@ModelAttribute("newPerson") Person person, @RequestParam String teacher_grade,
            @RequestParam String hiring_date,
            @RequestParam String student_class,
            @RequestParam String student_degree,
            @RequestParam String teaching_level,
            @RequestParam(required = false) ArrayList<String> class_list,
            @RequestParam(required = false) String course_grp1,
            @RequestParam(required = false) String course_grp2,
            @RequestParam(required = false) String course_grp3)
            throws InstantiationException, IllegalAccessException, SQLException {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/AdminDashboard");
        PersonDao personDao = new PersonDao();
        personDao.addUser(person);
        int id = personDao.getLastUser().getId();
        System.out.println(id);
        StudentDao studentDao = new StudentDao();
        if (person.getRole().equalsIgnoreCase("student")) {

            System.out.println(studentDao.getClassId(student_class));
            System.out.println(student_degree);

            Student student = new Student(studentDao.getClassId(student_class, student_degree), student_degree, id);
            studentDao.addUser(student);

        } else {
            TeacherDao teacherDao = new TeacherDao();
            Teacher teacher = new Teacher(id, teacher_grade, hiring_date);
            teacherDao.addUser(teacher);
            ArrayList<MyClass> classList = new ArrayList<MyClass>();
            classList = personDao.getClassLevel(teaching_level);
            int id_course_grp1 = 0;
            int id_course_grp2 = 0;
            int id_course_grp3 = 0;
            System.out.println(classList);
            // int id_class = studentDao.getClassId(class_grp1, teaching_level);
            System.out.println(course_grp3);
            System.out.println(course_grp2);
            System.out.println(course_grp1);
            System.out.println(class_list);
            for (int i = 0; i < class_list.size(); i++) {
                if (class_list.get(i).equalsIgnoreCase("grp2")) {
                    id_course_grp2 = studentDao.getCourseId(course_grp2, teaching_level);
                    teacherDao.assign_teacher_work(id, id_course_grp2,
                            studentDao.getClassId(class_list.get(i), teaching_level));
                }
                if (class_list.get(i).equalsIgnoreCase("grp3")) {
                    id_course_grp3 = studentDao.getCourseId(course_grp3, teaching_level);
                    teacherDao.assign_teacher_work(id, id_course_grp3,
                            studentDao.getClassId(class_list.get(i), teaching_level));
                }
                if (class_list.get(i).equalsIgnoreCase("grp1")) {
                    id_course_grp1 = studentDao.getCourseId(course_grp1, teaching_level);
                    teacherDao.assign_teacher_work(id, id_course_grp1,
                            studentDao.getClassId(class_list.get(i), teaching_level));
                }
            }

            // System.out.println(class_list.get(1));

        }

        return redirectView;

    }

    @PostMapping("/AdminDashboard/delete")
    public RedirectView deleteUser(@RequestParam String id_person)
            throws InstantiationException, IllegalAccessException, SQLException {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/AdminDashboard");

        int id = Integer.valueOf(id_person);
        PersonDao personDao = new PersonDao();
        personDao.deleteuser(id);
        return redirectView;
    }

    @PostMapping("/AdminDashboard/updateStudent")
    public RedirectView updateStudent(@ModelAttribute("updatedStudent") Student student,
            @RequestParam String grade,
            @RequestParam String class_name,
            @RequestParam String id_student)
            throws InstantiationException, IllegalAccessException, SQLException, ParseException {
        RedirectView rdv = new RedirectView();
        rdv.setUrl("/AdminDashboard");
        /*
         * plan : - bring old student with id_student
         * - bring id_class with class_name
         * - alter table student with id_class and grade
         * - compare if there is changes between old student and new model student and
         * send query for the changed attribute
         * notice = in the case of grade and class we dont need to compare because we
         * have tomatch possibilities
         * so we send the query directly
         * 
         */
        // change student class and grade
        // get id class(group)from level
        int id = Integer.valueOf(id_student);
        int id_class = studentDao.getClassId(class_name, grade);
        studentDao.changeStudent(id_class, grade, id);
        // update student info
        Person oldStudent = new Person();

        oldStudent = personDao.getUser(id);

        if (!oldStudent.getFirst_name().equals(student.getFirst_name())) {
            personDao.update("first_name", student.getFirst_name(), id);
        }
        if (!oldStudent.getLast_name().equals(student.getLast_name())) {
            personDao.update("last_name", student.getLast_name(), id);
        }
        if (!oldStudent.getUsername().equals(student.getUsername())) {
            personDao.update("username", student.getUsername(), id);
        }
        if (!oldStudent.getDate_of_birth().equals(student.getDate_of_birth())) {
            personDao.update("dateOFbirth", student.getDate_of_birth(), id);
        }
        if (!oldStudent.getPassword().equals(student.getPassword())) {
            personDao.update("password", student.getPassword(), id);
        }
        if (!oldStudent.getPlace_of_birth().equals(student.getPlace_of_birth())) {
            personDao.update("place_of_birth", student.getPlace_of_birth(), id);
        }
        return rdv;
    }

    @GetMapping("/teacherProfile")
    public ModelAndView teacherEditing(HttpServletRequest request, Model model, @RequestParam String id)
            throws InstantiationException, IllegalAccessException, ParseException, SQLException {
        ModelAndView mdv = new ModelAndView();
        Person person = (Person) request.getSession().getAttribute("Person");
        // parse string id to integer
        int id_teacher = Integer.valueOf(id);
        Teacher teacher = new Teacher();
        Person teacher_info = new Person();

        teacher = teacherDao.getUser(id_teacher);
        teacher_info = personDao.getUser(id_teacher);

        if (teacher_info == null) {
            mdv.setViewName("/index");
            return mdv;
        }
        if (person != null) {

            if (person.getRole().equalsIgnoreCase("admin")) {
                mdv.setViewName("panels/teacherProfile");
            } else {
                if (person.getRole().equalsIgnoreCase("teacher") && person.getId() == id_teacher) {
                    mdv.setViewName("panels/teacherProfile");
                } else {
                    mdv = userRp.acceptedView(person);
                }
            }

        } else {
            mdv.setViewName("panels/index");
            return mdv;
        }
        System.out.println(id);

        /*
         * plan : - retrieve teacher info
         * - retrieve courses and class thats teacher teaching
         */

        // retreive teacher information

        teacher.setFirst_name(teacher_info.getFirst_name());
        teacher.setLast_name(teacher_info.getLast_name());
        teacher.setDate_of_birth(teacher_info.getDate_of_birth());
        teacher.setPlace_of_birth(teacher_info.getPlace_of_birth());
        teacher.setGender(teacher_info.getGender());
        teacher.setUsername(teacher_info.getUsername());
        teacher.setPassword(teacher_info.getPassword());
        teacher.setRole(teacher_info.getRole());
        System.out.println(teacher);
        // send teacher object to teacher detail
        model.addAttribute("teacher", teacher);
        // send teaching info of teacher
        ArrayList<Teaching> teaching_list = new ArrayList<Teaching>();
        teaching_list = teacherDao.get_Teacher_assignement(id_teacher);
        model.addAttribute("teachingInfo", teaching_list);
        System.out.println(teaching_list);

        // list L3 COURSEs
        ArrayList<Course> coursesList_L3 = new ArrayList<Course>();
        coursesList_L3 = studentDao.getCourses("l3");
        model.addAttribute("L3_courses", coursesList_L3);
        // list L2 COURSEs
        ArrayList<Course> coursesList_L2 = new ArrayList<Course>();
        coursesList_L2 = studentDao.getCourses("l2");
        model.addAttribute("L2_courses", coursesList_L2);
        return mdv;
    }

    @PostMapping("/updateTeacher")
    public RedirectView postMethodName(HttpServletRequest request, @ModelAttribute("teacher") Person person,
            @RequestParam(required = false) String teacher_grade,
            @RequestParam(required = false) String hiring_date)
            throws InstantiationException, IllegalAccessException, SQLException, ParseException {
        RedirectView rdv = new RedirectView();
        rdv.setUrl("./teacherProfile?id=" + person.getId() + "");
        System.out.println(person.toString());
        Person oldTeacher = new Person();
        Person gerant = (Person) request.getSession().getAttribute("Person");
        oldTeacher = personDao.getUser(person.getId());

        if (!oldTeacher.getFirst_name().equals(person.getFirst_name())) {
            personDao.update("first_name", person.getFirst_name(), person.getId());
        }
        if (!oldTeacher.getLast_name().equals(person.getLast_name())) {
            personDao.update("last_name", person.getLast_name(), person.getId());
        }
        if (!oldTeacher.getUsername().equals(person.getUsername())) {
            personDao.update("username", person.getUsername(), person.getId());
        }
        if (!oldTeacher.getDate_of_birth().equals(person.getDate_of_birth())) {
            personDao.update("dateOFbirth", person.getDate_of_birth(), person.getId());
        }
        if (!oldTeacher.getPassword().equals(person.getPassword())) {
            personDao.update("password", person.getPassword(), person.getId());
        }
        if (!oldTeacher.getPlace_of_birth().equals(person.getPlace_of_birth())) {
            personDao.update("place_of_birth", person.getPlace_of_birth(), person.getId());
        }
        // change hiring date and grade by admin not by teacher
        if (gerant.getRole().equalsIgnoreCase("admin")) {
            teacherDao.update("degree", teacher_grade, person.getId());
            teacherDao.update("hiring_date", hiring_date, person.getId());
        }
        // change teaching class and courses by admin

        return rdv;
    }

    @GetMapping("/deleteAssignment")
    public RedirectView deleteAssignment(HttpServletRequest request,
            @RequestParam String id,
            @RequestParam String course,
            @RequestParam String my_class,
            @RequestParam String old_level) throws InstantiationException, IllegalAccessException, SQLException {
        RedirectView rdv = new RedirectView();
        Person person = (Person) request.getSession().getAttribute("Person");
        if (person.getRole().equalsIgnoreCase("admin")) {
            int id_teacher = Integer.parseInt(id);

            int id_course = studentDao.getCourseId(course, old_level);
            int id_class = studentDao.getClassId(my_class, old_level);

            teacherDao.deleteTeacherAssignment(id_class, id_course, id_teacher);
            rdv.setUrl("./teacherProfile?id=" + id_teacher + "");
        } else {
            rdv = userRp.acceptedRedirectView(person);

        }

        return rdv;
    }

    @PostMapping("/updateTeachingInfo")
    public RedirectView updateTeachingInfo(@RequestParam(required = false) String class_list,
            @RequestParam(required = false) String course_grp1,
            @RequestParam(required = false) String course_grp2,
            @RequestParam(required = false) String course_grp3,
            @RequestParam(required = false) String old_level,
            @RequestParam(required = false) String old_class,
            @RequestParam(required = false) String teaching_level,
            @RequestParam(required = false) String old_course,
            @RequestParam(required = true) String id)
            throws InstantiationException, IllegalAccessException, SQLException {
        RedirectView rdv = new RedirectView();
        int id_teacher = Integer.parseInt(id);
        rdv.setUrl("./teacherProfile?id=" + id_teacher + "");
        /*
         * plan
         * retrieve old_class id
         * retrieve old_course id
         * send old data to assignment method
         */

        /*
         * retrieve old teaching info for teacher
         */
        int old_course_id = studentDao.getCourseId(old_course, old_level);
        int old_class_id = studentDao.getClassId(old_class, old_level);
        // System.out.println(class_list);
        // System.out.println("old class" + old_class);
        // System.out.println("old course" + old_course);
        // System.out.println("new course " + course_grp1);
        // System.out.println("new course " + course_grp2);
        // System.out.println("new course " + course_grp3);
        // System.out.println("old teach level" + old_level);
        // System.out.println("new teach level" + teaching_level);

        try {
            switch (class_list) {
                case "grp1":
                    System.out.println("true");
                    teacherDao.updateTeacherAssignment(course_grp1, class_list, id_teacher, old_class_id,
                            old_course_id, teaching_level);
                    break;
                case "grp2":
                    System.out.println("true");
                    teacherDao.updateTeacherAssignment(course_grp2, class_list, id_teacher, old_class_id,
                            old_course_id, teaching_level);
                    break;
                case "grp3":
                    System.out.println("true");
                    teacherDao.updateTeacherAssignment(course_grp3, class_list, id_teacher, old_class_id,
                            old_course_id, teaching_level);
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            return rdv;
        }

        return rdv;

    }

    @PostMapping("/AddTeachingInfo")
    public RedirectView addAssignment(@RequestParam String teaching_level,
            @RequestParam(required = false) ArrayList<String> class_list,
            @RequestParam(required = false) String course_grp1,
            @RequestParam(required = false) String course_grp2,
            @RequestParam(required = false) String course_grp3,
            @RequestParam(required = false) String id_teacher)
            throws InstantiationException, IllegalAccessException, SQLException {
        RedirectView rdv = new RedirectView();
        int id_course_grp1 = 0;
        int id_course_grp2 = 0;
        int id_course_grp3 = 0;
        int id = Integer.parseUnsignedInt(id_teacher);
        for (int i = 0; i < class_list.size(); i++) {
            if (class_list.get(i).equalsIgnoreCase("grp2")) {
                id_course_grp2 = studentDao.getCourseId(course_grp2, teaching_level);
                teacherDao.assign_teacher_work(id, id_course_grp2,
                        studentDao.getClassId(class_list.get(i), teaching_level));
            }
            if (class_list.get(i).equalsIgnoreCase("grp3")) {
                id_course_grp3 = studentDao.getCourseId(course_grp3, teaching_level);
                teacherDao.assign_teacher_work(id, id_course_grp3,
                        studentDao.getClassId(class_list.get(i), teaching_level));
            }
            if (class_list.get(i).equalsIgnoreCase("grp1")) {
                id_course_grp1 = studentDao.getCourseId(course_grp1, teaching_level);
                teacherDao.assign_teacher_work(id, id_course_grp1,
                        studentDao.getClassId(class_list.get(i), teaching_level));
            }
        }
        rdv.setUrl("./teacherProfile?id=" + id + "");
        return rdv;
    }

    public static void main(String args[]) throws InstantiationException, IllegalAccessException, SQLException {
        StudentDao studentDao = new StudentDao();
        // System.out.println(studentDao.getCourseId("TL", "L2"));
        ArrayList<Object> globalList = new ArrayList<Object>();
        ArrayList<Object> list = new ArrayList<Object>();
        ArrayList<Course> courses = new ArrayList<Course>();
        ArrayList<CourseContent> courseContent_cours = null;
        ArrayList<CourseContent> courseContent_TP = null;
        ArrayList<CourseContent> courseContent_TD = null;

        courses = studentDao.getCourses("l3");
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
        // System.out.println(globalList.get(1));
        // // ((ArrayList<CourseContent>) daaw.get(1)).get(0).getFile_path()
        // ArrayList<Object> course = new ArrayList<Object>();
        // course = (ArrayList<Object>) globalList.get(1);

        // Course courseInfo = new Course();
        // courseInfo = (Course) course.get(0);

        // System.out.println(courseInfo);
        // courseContent = new ArrayList<CourseContent>();
        // courseContent = (ArrayList<CourseContent>) course.get(1);

        // System.out.println(courseContent);
        for (int i = 0; i < globalList.size(); i++) {
            ArrayList<Object> course = new ArrayList<Object>();
            course = (ArrayList<Object>) globalList.get(i);
            Course courseInfo = new Course();
            courseInfo = (Course) course.get(0);

            System.out.println("Course : " + courseInfo.getLabel());
            courseContent_cours = new ArrayList<CourseContent>();
            System.out.println("course content cours: ");
            courseContent_cours = (ArrayList<CourseContent>) course.get(1);
            for (int j = 0; j < courseContent_cours.size(); j++) {
                System.out.println(courseContent_cours.get(j).getChapterName());
            }
            courseContent_TP = new ArrayList<CourseContent>();
            System.out.println("course content tp: ");
            courseContent_TP = (ArrayList<CourseContent>) course.get(2);
            for (int j = 0; j < courseContent_TP.size(); j++) {
                System.out.println(courseContent_TP.get(j).getChapterName());
            }
            courseContent_TD = new ArrayList<CourseContent>();
            System.out.println("course content td: ");
            courseContent_TD = (ArrayList<CourseContent>) course.get(3);
            for (int j = 0; j < courseContent_TD.size(); j++) {
                System.out.println(courseContent_TD.get(j).getChapterName());
            }
            System.out.println();
            System.out.println();
        }
    }
}
