package my.project.demo.service.impl;

import my.project.demo.model.User;
import my.project.demo.repository.UserRepository;
import my.project.demo.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        LOGGER.info("The user was saved into db");
        return userRepository.save(user);
    }

    @Override
    public User getByLogin(String login) {
        LOGGER.info("The user was found by login" + login);
        return userRepository.getByLogin(login);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
