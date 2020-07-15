package my.project.demo.service;

import my.project.demo.model.User;

public interface UserService {
    User save(User user);

    User getByLogin(String login);

    void deleteById(Long id);
}
