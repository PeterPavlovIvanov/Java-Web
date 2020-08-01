package projectdefence.committer.demo.models.bindings;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDateTime;

public class EventAddBindModel {
    private String name;
    private LocalDateTime dateTime;

    public EventAddBindModel() {
    }

    public EventAddBindModel(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }

    @Length(min = 4, max = 20, message = "The event's name must be between 4 and 20 symbols.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "The date cannot be in the past.")
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
