package softuni.one.model.service;

public class RoleServiceModel extends BaseServiceModel {
    private String name;

    public RoleServiceModel() {
    }

    public RoleServiceModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
