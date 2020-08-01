package projectdefence.committer.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import projectdefence.committer.demo.models.bindings.SearchUsersBindModel;
import projectdefence.committer.demo.models.entities.User;
import projectdefence.committer.demo.models.services.UserServiceModel;
import projectdefence.committer.demo.services.PostService;
import projectdefence.committer.demo.services.RoleService;
import projectdefence.committer.demo.services.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    private final RoleService roleService;
    private final PostService postService;
    private final UserService userService;

    public HomeController(RoleService roleService, PostService postService, UserService userService) {
        this.roleService = roleService;
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView getHome(HttpSession httpSession, ModelAndView modelAndView) {
        this.roleService.init();



        if (httpSession.getAttribute("user") == null) {
            modelAndView.setViewName("index");
        } else {
            modelAndView.setViewName("home");
            modelAndView.addObject("allPosts", this.postService.getAll());
            modelAndView.addObject("user", httpSession.getAttribute("user"));
            modelAndView.addObject("userId", httpSession.getAttribute("id"));

            User u = (User) httpSession.getAttribute("user");
            User user = this.userService.getById(u.getId());
            if(user.getRole().getRoleName().toString().equals("ADMIN")){
                modelAndView.addObject("isADMIN",true);
            }
        }
        return modelAndView;
    }

}
