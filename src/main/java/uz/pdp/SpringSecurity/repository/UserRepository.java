package uz.pdp.SpringSecurity.repository;



import uz.pdp.SpringSecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findFirstByUsernameAndPassword(String username, String password);
    Optional<User> findFirstByUsername(String username);
}
