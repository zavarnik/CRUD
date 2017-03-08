package realProject.controller;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import realProject.model.User;
import realProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class userController {
    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }



    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user){
        if(user.getId() == 0){
            this.userService.addUser(user);
        }else {
            this.userService.updateUser(user);
        }

        return "redirect:/users/1";
    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id){
        this.userService.removeUser(id);

        return "redirect:/users/1";
    }

    @RequestMapping("edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model){
        model.addAttribute("user", this.userService.getUserById(id));
        model.addAttribute("usersList", this.userService.usersList());

        return "users";
    }

    @RequestMapping("userdata/{id}")
    public String userData(@PathVariable("id")int id, Model model){
        model.addAttribute("user", this.userService.getUserById(id));

        return "userdata";
    }
    @RequestMapping (value = "/users/{pageid}", method = RequestMethod.GET)
    public String listUsers(@PathVariable int pageid, Model model) {

        int total = 5;
        if(pageid==1){}
        else{
            pageid=(pageid-1)*total+1;
        }
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userService.findUsersByPage(pageid, total));

        return "users";
    }
    @RequestMapping(value = "/user/find")
    public String findUserById(int id, Model model){
        try {
            model.addAttribute("user", this.userService.getUserById(id));
        } catch (ObjectNotFoundException e) {

        }
        return "userdata";
    }
}
