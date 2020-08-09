package projectdefence.committer.demo.services.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import projectdefence.committer.demo.models.bindings.EventAddBindModel;
import projectdefence.committer.demo.models.entities.Event;
import projectdefence.committer.demo.models.entities.Post;
import projectdefence.committer.demo.models.entities.User;
import projectdefence.committer.demo.models.services.EventServiceModel;
import projectdefence.committer.demo.repositories.EventRepository;
import projectdefence.committer.demo.repositories.UserRepository;
import projectdefence.committer.demo.services.EventService;
import projectdefence.committer.demo.sheduling.ScheduledTasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public EventServiceImpl(EventRepository eventRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }


    @Override
    public void addEvent(EventAddBindModel eventAddBindModel, String id) {
        EventServiceModel eventServiceModel = this.modelMapper.map(eventAddBindModel, EventServiceModel.class);
        Event event = this.modelMapper.map(eventServiceModel, Event.class);
        User user = this.userRepository.findById(id).orElse(null);
        event.setCommitter(user);

        if (user.getEvents() == null) {
            List<Event> events = new ArrayList<>();
            events.add(event);
            user.setEvents(events);
        } else {
            user.getEvents().add(event);
        }
        this.userRepository.save(user);
    }

    @Override
    public List<Event> getAll() {
        List<Event> events = this.eventRepository.findAll();
        Collections.reverse(events);
        //reverse so the most recently uploaded event is on the top
        return events;
    }

    @Override
    public void delete(String id) {
        this.eventRepository.deleteById(id);
    }

    @Override
    public Event getById(String id) {
        return this.eventRepository
                .findById(id)
                .map(e -> this.modelMapper.map(e, Event.class))
                .orElse(null);
    }

    @Override
    public void participate(Event e, User u) {
        if (!this.isParticipant(e, u)) {
            e.getParticipants().add(u);

            this.eventRepository.saveAndFlush(e);
        }
    }

    @Override
    public void leave(Event e, User u) {

        for (int i = 0; i < e.getParticipants().size(); i++) {
            if (e.getParticipants().get(i).getId().equals(u.getId())) {
                e.getParticipants().remove(i);
                break;
            }
        }
        this.eventRepository.saveAndFlush(e);
    }

    @Override
    public boolean isParticipant(Event e, User u) {
        if (e.getParticipants() == null) {
            List<User> users = new ArrayList<>();
            e.setParticipants(users);
        }

        for (int i = 0; i < e.getParticipants().size(); i++) {
            if (e.getParticipants().get(i).getId().equals(u.getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isOwner(Event event, String userId) {
        if (event.getCommitter().getId().equals(userId)) {
            return true;
        }
        return false;
    }
}
