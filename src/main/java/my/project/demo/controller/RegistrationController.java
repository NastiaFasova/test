package my.project.demo.controller;

import my.project.demo.dto.UserDto;
import my.project.demo.mapper.UserMapper;
import my.project.demo.model.User;
import my.project.demo.security.AuthenticationService;
import my.project.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public ModelAndView showRegistrationForm() {
        ModelAndView model = new ModelAndView();
        User user = new User();
        model.addObject("user", user);
        model.setViewName("/register");
        return model;
    }

    @PostMapping
    public ModelAndView registerUserAccount
            (@Valid User user, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        if (!bindingResult.hasErrors()) {
            authenticationService.register(user.getLogin(), user.getPassword());
            model.setViewName("redirect:/login");
            return model;
        }
        model.setViewName("/register");
        return model;
    }
}
