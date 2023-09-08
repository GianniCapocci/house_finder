package gr.hua.dit.house_finder.service.impl;

import gr.hua.dit.house_finder.dto.UserDto;
import gr.hua.dit.house_finder.entity.Role;
import gr.hua.dit.house_finder.entity.User;
import gr.hua.dit.house_finder.repository.RoleRepository;
import gr.hua.dit.house_finder.repository.UserRepository;
import gr.hua.dit.house_finder.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = (userDto.getEmail().toLowerCase().contains("@hua")) ? roleRepository.findByName("ROLE_ADMIN") : roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);

        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();
        String[] name = user.getName().split(" ");
        userDto.setFirstName(name[0]);
        userDto.setLastName(name[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}