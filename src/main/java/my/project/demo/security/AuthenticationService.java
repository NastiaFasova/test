package my.project.demo.security;

import my.project.demo.model.User;

public interface AuthenticationService {
    User login(String login, String password);
    User register(String login, String password);
}
