package softuni.one.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.one.model.binding.UserAddBindingModel;
import softuni.one.model.binding.UserLoginBindingModel;
import softuni.one.model.entity.User;
import softuni.one.model.service.UserServiceModel;
import softuni.one.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/login")
    private ModelAndView loginPost(@Valid
                                       @ModelAttribute("userLoginBindingModel") UserLoginBindingModel userLoginBindingModel,
                                   BindingResult bindingResult, ModelAndView modelAndView, HttpSession httpSession){

        if(bindingResult.hasErrors()){
            modelAndView.setViewName("redirect:/users/login");
        } else {
            modelAndView.setViewName("redirect:/home");
        }

        //TODO login in service
        //httpSession.setAttribute(userServiceModel);
        //httpSession.setAttribute(userId);
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registerPost(@Valid
                                     @ModelAttribute("userAddBindingModel") UserAddBindingModel userAddBindingModel,
                                     BindingResult bindingResult, ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            //TODO validation Message
            modelAndView.setViewName("redirect:/users/register");
        } else {
            UserServiceModel userToRegister = this.modelMapper.map(userAddBindingModel, UserServiceModel.class);
            UserServiceModel userServiceModel = this.userService.registerUser(userToRegister);
            modelAndView.setViewName("redirect:/users/login");
        }

        return modelAndView;
    }

}
