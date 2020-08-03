package projectdefence.committer.demo.sheduling;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import projectdefence.committer.demo.models.entities.Event;
import projectdefence.committer.demo.services.EventService;
import projectdefence.committer.demo.services.UserService;

@Component
public class ScheduledTasks {
    private final EventService eventService;
    private LocalDateTime localDateTime;
    private final UserService userService;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public ScheduledTasks(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @Async
    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));

        this.localDateTime = LocalDateTime.now();
        for (Event e : this.eventService.getAll()) {
            if (e.getDateTime().isBefore(localDateTime)) {
                log.info("Event " + e.getName() + " by " + e.getCommitter().getNickname() + " has expired.");
                this.userService.deleteEvent(e, e.getCommitter());
                this.eventService.delete(e.getId());
            }
        }
    }
}