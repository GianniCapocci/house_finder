package gr.hua.dit.house_finder.controller;

import gr.hua.dit.house_finder.dto.UserDto;
import gr.hua.dit.house_finder.entity.User;
import gr.hua.dit.house_finder.repository.UserRepository;
import gr.hua.dit.house_finder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String listRegisteredUsers(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/delete")
    public String showDeleteUserForm() {
        return "deleteuser";
    }

    @PostMapping("/delete")
    public String searchUserForDelete(@RequestParam String userEmail, Model model) {
        User user = userRepository.findByEmail(userEmail);
        model.addAttribute("user", user);
        return "deleteuser";
    }

    @Transactional
    @PostMapping("/delete/{email}")
    public String deleteUser(@PathVariable String email) {
        userRepository.deleteByEmail(email);
        return "redirect:/users";
    }
}
