package com.example.examiasf.controller;

import com.example.examiasf.entity.User;
import com.example.examiasf.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

        import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String listUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users/list-users";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "users/user-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("userId") int id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "users/user-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        userService.save(user);
        redirectAttributes.addFlashAttribute("message", "User saved successfully!");
        redirectAttributes.addFlashAttribute("messageType", "success");
        return "redirect:/users/list";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("userId") int id, RedirectAttributes redirectAttributes) {
        userService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "User deleted successfully!");
        redirectAttributes.addFlashAttribute("messageType", "danger");
        return "redirect:/users/list";
    }
}