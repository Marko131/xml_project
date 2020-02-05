package tim10.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tim10.project.model.user.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    List<User> findUserByExpertise(String expertise);
    List<User> getAllByRole(String role);
}

