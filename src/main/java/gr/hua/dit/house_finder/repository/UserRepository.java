package gr.hua.dit.house_finder.repository;

import gr.hua.dit.house_finder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    void deleteByEmail(String email);
    User findByName(String name);
}
