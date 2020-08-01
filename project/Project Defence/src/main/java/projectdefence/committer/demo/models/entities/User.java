package projectdefence.committer.demo.models.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class User extends BaseEntity {

    private String nickname;
    private String password;
    private String email;
    private Role role;
    private List<Post> posts;
    private List<Event> events;

    public User() {
    }

    @Column(nullable = false, unique = true)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<Role> authorities() {
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setRoleName(this.getRole().getRoleName().toString().equals("ADMIN") ?
                RoleName.ADMIN : RoleName.USER);
        roles.add(role);
        return roles;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    @Column(nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManyToOne
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
