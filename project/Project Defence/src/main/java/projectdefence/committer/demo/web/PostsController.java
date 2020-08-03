package projectdefence.committer.demo.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import projectdefence.committer.demo.models.bindings.PostAddBindModel;
import projectdefence.committer.demo.models.entities.Post;
import projectdefence.committer.demo.models.entities.User;
import projectdefence.committer.demo.models.services.PostServiceModel;
import projectdefence.committer.demo.models.services.UserServiceModel;
import projectdefence.committer.demo.services.PostService;
import projectdefence.committer.demo.services.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Collections;

@Controller
@RequestMapping("/posts")
public class PostsController {
    private final ModelMapper modelMapper;
    private final PostService postService;
    private final UserService userService;

    public PostsController(ModelMapper modelMapper, PostService postService, UserService userService) {
        this.modelMapper = modelMapper;
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public String getAdd(Model model,HttpSession httpSession) {
        if (!model.containsAttribute("postAddBindModel")) {
            model.addAttribute("postAddBindModel", new PostAddBindModel());
        }

        User u = (User) httpSession.getAttribute("user");
        User user = this.userService.getById(u.getId());
        if (user.getRole().getRoleName().toString().equals("ADMIN") || user.getRole().getRoleName().toString().equals("USER")) {
            //model.addAttribute("isADMIN", true);
            return "post-add";
        } else {
            return "unauthorized";
        }
    }

    @PostMapping("/add")
    public ModelAndView postAdd(@Valid
                                @ModelAttribute("postAddBindModel") PostAddBindModel postAddBindModel,
                                BindingResult bindingResult, ModelAndView modelAndView, RedirectAttributes redirectAttributes,
                                HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("postAddBindModel", postAddBindModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.postAddBindModel", bindingResult);
            modelAndView.setViewName("redirect:/posts/add");
        } else {
            this.postService.addACommit(postAddBindModel, httpSession.getAttribute("id").toString());
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView getDelete(@PathVariable(name = "id") String id, ModelAndView modelAndView, HttpSession httpSession) {
        String check = id;
        Post post = this.postService.getById(id);
        this.userService.delete(this.modelMapper.map(post, PostServiceModel.class));
        this.postService.delete(id);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @GetMapping("/voteUp")
    public ModelAndView voteUp(@RequestParam(name = "id") String id, ModelAndView modelAndView, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        Post post = this.postService.getById(id);
        this.postService.vote(user, post, 1);

        User u = (User) httpSession.getAttribute("user");
        User user2 = this.userService.getById(u.getId());
        if(user2.getRole().getRoleName().toString().equals("ADMIN")){
            modelAndView.addObject("isADMIN",true);
        }

        modelAndView.addObject("user", (User) httpSession.getAttribute("user"));
        modelAndView.addObject("allPosts", this.postService.getAll());
        modelAndView.addObject("hasVoted",true);
        modelAndView.setViewName("/home");
        return modelAndView;
    }
    


}