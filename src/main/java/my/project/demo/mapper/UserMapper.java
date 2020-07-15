package my.project.demo.mapper;

import my.project.demo.dto.UserDto;
import my.project.demo.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User getUser(UserDto userDto) {
        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setRoles(userDto.getRoles());
        return user;
    }

    public UserDto getUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
}
