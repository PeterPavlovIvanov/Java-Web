package projectdefence.committer.demo.services;

import projectdefence.committer.demo.models.bindings.EventAddBindModel;
import projectdefence.committer.demo.models.entities.Event;
import projectdefence.committer.demo.models.services.EventServiceModel;

import java.util.List;

public interface EventService {
    void addEvent(EventAddBindModel eventAddBindModel, String id);
    List<Event> getAll();

    void delete(String id);
}
