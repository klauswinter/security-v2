package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserServiceImpl;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserServiceImpl userService;

    @Autowired
    public UsersController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/new")
    public String addNewPerson(Model model) {
        model.addAttribute("user", new User());
        return "new_user";
    }

    @PostMapping()
    public String createNewUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.updateUser(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "redirect:/users";
    }

}
