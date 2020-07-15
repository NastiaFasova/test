package my.project.demo.controller;

import javax.validation.Valid;
import my.project.demo.model.User;
import my.project.demo.security.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();
        User user = new User();
        model.addObject("user", user);
        model.setViewName("/login");
        return model;
    }

    @PostMapping
    public ModelAndView login(@Valid User user) {
        ModelAndView modelAndView = null;
        User validateUser = authenticationService.login(user.getLogin(), user.getPassword());
        System.out.println(validateUser);
        if (validateUser != null) {
            modelAndView = new ModelAndView("/welcome");
            modelAndView.addObject("login", validateUser.getLogin());
        } else {
            modelAndView = new ModelAndView("/login");
            modelAndView.addObject("msg", "Username or Password is wrong!!");
        }
        return modelAndView;
    }
}
