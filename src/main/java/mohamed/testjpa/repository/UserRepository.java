package mohamed.testjpa.repository;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import mohamed.testjpa.model.Person.Person;

public class UserRepository {
    public ModelAndView acceptedView(Person person) {
        ModelAndView mdv = new ModelAndView();
        if (person != null) {
            if (person.getRole().equalsIgnoreCase("admin")) {
                mdv.setViewName("panels/adminPanel");
            } else if (person.getRole().equalsIgnoreCase("student")) {
                mdv.setViewName("panels/studentPanel");
            } else {
                mdv.setViewName("panels/profPanel");
            }

        } else {
            mdv.setViewName("index");
        }
        return mdv;
    }

    public RedirectView acceptedRedirectView(Person person) {
        RedirectView mdv = new RedirectView();
        if (person != null) {
            if (person.getRole().equalsIgnoreCase("admin")) {
                mdv.setUrl("./AdminDashboard");
            } else if (person.getRole().equalsIgnoreCase("student")) {
                mdv.setUrl("./StudentDashboard");
            } else {
                mdv.setUrl("./teacherDashboard");
            }

        } else {
            mdv.setUrl("./index");
        }
        return mdv;
    }
}
