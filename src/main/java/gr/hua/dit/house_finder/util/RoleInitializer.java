package gr.hua.dit.house_finder.util;

import gr.hua.dit.house_finder.entity.Role;
import gr.hua.dit.house_finder.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        initializeRoles();
    }

    private void initializeRoles() {
        createRoleIfNotExists("ROLE_ADMIN");
        createRoleIfNotExists("ROLE_USER");
    }

    private void createRoleIfNotExists(String roleName) {
        if (roleRepository.findByName(roleName) == null) {
            Role role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
        }
    }
}