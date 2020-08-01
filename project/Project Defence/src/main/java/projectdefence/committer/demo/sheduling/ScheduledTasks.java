package projectdefence.committer.demo.sheduling;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import projectdefence.committer.demo.models.entities.Event;
import projectdefence.committer.demo.models.services.EventServiceModel;
import projectdefence.committer.demo.services.EventService;

@Component
public class ScheduledTasks {
    private final EventService eventService;

    private final LocalDateTime localDateTime = LocalDateTime.now();
    private final ModelMapper modelMapper;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public ScheduledTasks(EventService eventService, ModelMapper modelMapper) {
        this.eventService = eventService;
        this.modelMapper = modelMapper;
    }

    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));

        for (Event e : this.eventService.getAll()) {
            if (e.getDateTime().isBefore(e.getDateTime())) {
                log.info("Event " + e.getName() + " by " + e.getCommitter().getNickname() + " has expired.");
                this.eventService.delete(e.getId());
            }
        }
    }
}