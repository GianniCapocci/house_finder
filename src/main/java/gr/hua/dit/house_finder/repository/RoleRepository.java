package gr.hua.dit.house_finder.repository;

import gr.hua.dit.house_finder.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
