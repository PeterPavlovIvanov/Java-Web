package projectdefence.committer.demo.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import projectdefence.committer.demo.models.entities.Role;
import projectdefence.committer.demo.models.entities.RoleName;
import projectdefence.committer.demo.models.services.RoleServiceModel;
import projectdefence.committer.demo.repositories.RoleRepository;
import projectdefence.committer.demo.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void init() {
        if (this.roleRepository.count() == 0) {
            Role admin = new Role();
            admin.setRoleName(RoleName.ADMIN);
            admin.setAuthority("ADMIN");
            this.roleRepository.saveAndFlush(admin);

            Role member = new Role();
            member.setRoleName(RoleName.USER);
            admin.setAuthority("USER");
            this.roleRepository.saveAndFlush(member);
        }
    }

    @Override
    public RoleServiceModel getByRoleName(RoleName roleName) {
        return this.roleRepository
                .findByRoleName(roleName)
                .map(u -> this.modelMapper.map(u, RoleServiceModel.class))
                .orElse(null);
    }
}
