package softuni.one.service;

import softuni.one.model.service.RoleServiceModel;

public interface RoleService {
    RoleServiceModel findByName(String name);
}
