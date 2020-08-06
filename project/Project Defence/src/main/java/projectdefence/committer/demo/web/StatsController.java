package projectdefence.committer.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import projectdefence.committer.demo.models.entities.User;
import projectdefence.committer.demo.services.UserService;
import projectdefence.committer.demo.services.impl.LastLoggedInService;
import projectdefence.committer.demo.services.impl.StatsService;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;

@Controller
public class StatsController {

    private StatsService statsService;
    private final UserService userService;
    private LastLoggedInService lastLoggedInService;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:MM:ss");


    public StatsController(StatsService statsService, UserService userService, LastLoggedInService lastLoggedInService) {
        this.statsService = statsService;
        this.userService = userService;
        this.lastLoggedInService = lastLoggedInService;
    }

    @GetMapping("/stats")
    public String stats(Model model, HttpSession httpSession) {
        if(httpSession.getAttribute("id") == null){
            return "unauthorized";
        }
        model.addAttribute("lastLogged", lastLoggedInService.getLoggedOn());

        User u = (User) httpSession.getAttribute("user");
        model.addAttribute("user",u);
        User user = this.userService.getById(u.getId());
        if (user.getRole().getRoleName().toString().equals("ADMIN")) {
            model.addAttribute("isADMIN", true);
            model.addAttribute("requestCount", statsService.getRequestCount());
            model.addAttribute("startedOn", statsService.getStartedOn());
        }

        return "stats";
    }

}
