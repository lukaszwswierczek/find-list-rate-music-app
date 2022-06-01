package pl.lukaszswierczek.findListRateApp.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;


@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    @ResponseBody
    public String displayCurrentUser(@AuthenticationPrincipal CurrentUser customUser) {
        User entityUser = customUser.getUser();
        return "Hello " + entityUser.getUsername();
    }

    //Należy pamiętać, że ze względu na unikalność nazwy użytkownika,
    //akcja wywoła się poprawnie tylko raz.
    @GetMapping("/create-admin")
    @ResponseBody
    public String createUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        userService.saveUser(user);
        return "admin";
    }

    @GetMapping("/register")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/register";
    }

    @PostMapping("/register")
    public String createNewUser(@ModelAttribute @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/register";
        }
        userService.saveUser(user);
        return "redirect:/";
    }


}