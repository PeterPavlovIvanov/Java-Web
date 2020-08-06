package projectdefence.committer.demo.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import projectdefence.committer.demo.models.bindings.EventAddBindModel;
import projectdefence.committer.demo.models.entities.Event;
import projectdefence.committer.demo.models.entities.RoleName;
import projectdefence.committer.demo.models.entities.User;
import projectdefence.committer.demo.models.services.EventServiceModel;
import projectdefence.committer.demo.services.EventService;
import projectdefence.committer.demo.services.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/events")
public class EventsController {
    private final UserService userService;
    private final EventService eventService;
    private final ModelMapper modelMapper;

    public EventsController(UserService userService, EventService eventService, ModelMapper modelMapper) {
        this.userService = userService;
        this.eventService = eventService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String getAdd(Model model, HttpSession httpSession) {
        if (!model.containsAttribute("eventAddBindModel")) {
            model.addAttribute("eventAddBindModel", new EventAddBindModel());
        }

        User u = (User) httpSession.getAttribute("user");
        model.addAttribute("user",u);
        User user = this.userService.getById(u.getId());
        if (user.getRole().getRoleName().toString().equals("ADMIN")) {
            model.addAttribute("isADMIN", true);
            return "event-add";
        } else {
            return "unauthorized";
        }
    }

    @PostMapping("/add")
    public ModelAndView postAdd(@Valid
                                @ModelAttribute("eventAddBindModel") EventAddBindModel eventAddBindModel,
                                BindingResult bindingResult, ModelAndView modelAndView, RedirectAttributes redirectAttributes,
                                HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("eventAddBindModel", eventAddBindModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.eventAddBindModel", bindingResult);
            modelAndView.setViewName("redirect:/events/add");
        } else {
            this.eventService.addEvent(eventAddBindModel, httpSession.getAttribute("id").toString());
            modelAndView.setViewName("redirect:/events/all");
        }

        return modelAndView;
    }

    @GetMapping("/all")
    public String getAll(Model model, HttpSession httpSession) {
        model.addAttribute("allEvents", this.eventService.getAll());
        if (this.eventService.getAll().size() == 0) {
            model.addAttribute("noEvents", true);
        }

        User u = (User) httpSession.getAttribute("user");
        model.addAttribute("user", u);
        User user = this.userService.getById(u.getId());
        if (user.getRole().getRoleName().toString().equals("ADMIN")) {
            model.addAttribute("isADMIN", true);
            return "events-all";
        } else if (user.getRole().getRoleName().toString().equals("USER")) {
            return "events-all";
        } else {
            return "unauthorized";
        }
    }

    @GetMapping("/participate/{id}")
    public String participate(@PathVariable(name = "id") String id, Model model, HttpSession httpSession) {
//        User u = (User) httpSession.getAttribute("user");
//        if (u == null || (u.getRole().getRoleName() != RoleName.ADMIN && u.getRole().getRoleName() != RoleName.USER)) {
//            return "unauthorized";
//        }

        User userReg = this.userService.getById(httpSession.getAttribute("id").toString());
        Event event = this.eventService.getById(id);
        this.eventService.participate(event, userReg);

        model.addAttribute("currentParticipants", event.getParticipants());

        return "redirect:/events/event/?id=" + id;
    }

    @GetMapping("/leave/{id}")
    public String leave(@PathVariable(name = "id") String id, Model model, HttpSession httpSession) {
//        User u = (User) httpSession.getAttribute("user");
//        if (u == null || (u.getRole().getRoleName() != RoleName.ADMIN && u.getRole().getRoleName() != RoleName.USER)) {
//            return "unauthorized";
//        }

        User userReg = this.userService.getById(httpSession.getAttribute("id").toString());
        Event event = this.eventService.getById(id);
        this.eventService.leave(event, userReg);

        model.addAttribute("currentParticipants", event.getParticipants());

        return "redirect:/events/event/?id=" + id;
    }

    @GetMapping("/event")
    public String getDetails(@RequestParam String id, Model model, HttpSession httpSession) {
        User user2 = this.userService.getById(httpSession.getAttribute("id").toString());
        model.addAttribute("user", user2);

        boolean isParticipant = this.eventService.isParticipant(this.eventService.getById(id), user2);
        EventServiceModel eventServiceModel = this.modelMapper.map(this.eventService.getById(id), EventServiceModel.class);

        model.addAttribute("eventServiceModel", eventServiceModel);
        model.addAttribute("isParticipant", isParticipant);
        model.addAttribute("isOwner",
                this.eventService.isOwner(
                        this.modelMapper.map(eventServiceModel, Event.class),
                        user2.getId()
                )
        );
        if (user2.getRole().getRoleName().toString().equals("ADMIN")) {
            model.addAttribute("isADMIN", true);
        } else if (user2.getRole().getRoleName().toString().equals("USER")) {

        } else {
            return "unauthorized";
        }
        return "event-view";
    }

}
