package gr.hua.dit.house_finder.service;

import gr.hua.dit.house_finder.dto.UserDto;
import gr.hua.dit.house_finder.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();

}
