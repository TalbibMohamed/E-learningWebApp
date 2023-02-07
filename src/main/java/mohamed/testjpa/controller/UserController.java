package mohamed.testjpa.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import mohamed.testjpa.model.Person.Person;
import mohamed.testjpa.model.Person.PersonDao;
import mohamed.testjpa.repository.UserRepository;

// import mohamed.testjpa.model.Person;
// import mohamed.testjpa.model.Student;
// import mohamed.testjpa.repository.PersonRepository;
// import mohamed.testjpa.repository.studentRepository;

@RestController
public class UserController {
    UserRepository userrp = new UserRepository();

    @PostMapping(value = "/Login")
    public RedirectView checkLogin(HttpServletRequest request, @RequestParam String username,
            @RequestParam String password) throws InstantiationException, IllegalAccessException, ParseException {
        RedirectView redirectView = new RedirectView();
        if (request.getSession().getAttribute("Person") != null) {
            redirectView.setUrl("./");
        } else {
            PersonDao Persondao = new PersonDao();
            Person Person = Persondao.checkLogin(username, password);
            if (Person != null) {
                HttpSession session = request.getSession();
                session.setAttribute("Person", Person);
                if (Person.getRole().equalsIgnoreCase("admin")) {
                    redirectView.setUrl("/AdminDashboard");
                } else if (Person.getRole().equalsIgnoreCase("student")) {
                    redirectView.setUrl("/studentDashboard");
                } else {
                    redirectView.setUrl("/teacherDashboard");
                }

            } else {
                redirectView.setUrl("./");
            }

        }

        return redirectView;
    }

    @GetMapping("/logout")
    public RedirectView logout(HttpServletRequest req) {
        HttpSession session = req.getSession();
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("./");

        session.removeAttribute("Person");
        session.invalidate();
        return redirectView;
    }

    // @GetMapping("/")
    // public RedirectView index(HttpServletRequest req) {
    // Person person = (Person) req.getSession().getAttribute("Person");
    // RedirectView redirectView = new RedirectView();
    // redirectView = userrp.acceptedRedirectView(person);

    // return redirectView;
    // }
    // @GetMapping

    // @Autowired
    // PersonRepository PersonRepository;

    // @Autowired
    // studentRepository studentRepository;

    // @GetMapping("/AdminDashboard/Persons") // get all Persons in table person
    // public ResponseEntity<List<Person>> getEmployees() {
    // try {
    // return new ResponseEntity<>(PersonRepository.findAll(), HttpStatus.OK);
    // } catch (Exception e) {
    // return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    // }
    // }

    // @PostMapping("/AdminDashboard/addPerson")
    // public ModelAndView newEmployee(@ModelAttribute("newPerson") Person person)
    // throws ParseException {
    // // Date dob = person.getDate_of_birth();
    // // SimpleDateFormat DateFor = new SimpleDateFormat("yyyy/MM/dd");
    // // String stringDate = DateFor.format(dob);
    // // Date newDob = new SimpleDateFormat("yyyy/MM/dd").parse(stringDate);
    // ModelAndView mdv = new ModelAndView();

    // mdv.setViewName("panels/adminPanel");
    // System.out.println("\n//////");
    // // System.out.println(person.getFirst_name());
    // // System.out.println(person.getRole());
    // System.out.println(person.getDate_of_birth());
    // Person newPerson = PersonRepository
    // .save(Person.builder()
    // .first_name(person.getFirst_name())
    // .last_name(person.getLast_name())
    // .Personname(person.getPersonname())
    // .password(person.getPassword())
    // .place_of_birth(person.getPlace_of_birth())
    // .date_of_birth(person.getDate_of_birth())

    // .role(person.getRole())
    // .gender(person.getGender())
    // .build());
    // System.out.println(person.getRole());
    // // if (person.getRole() == "student") {
    // // Student student = studentRepository
    // // .save(Student.builder()
    // // .id(person.getId())
    // // .id_class(1)
    // // .grade("l2")
    // // .build());
    // // System.out.println("Person add");
    // // }
    // // System.out.println("//////////////////");
    // // System.out.println(stringDate);
    // return mdv;
    // }

    // @GetMapping("/AdminDashboard/addStudent")
    // public void addStudent() {

    // Student student = studentRepository
    // .save(Student.builder()

    // .id_class(1)
    // .grade("l2")
    // .build());
    // }
}
