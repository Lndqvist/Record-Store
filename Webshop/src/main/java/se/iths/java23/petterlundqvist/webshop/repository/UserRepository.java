package se.iths.java23.petterlundqvist.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.java23.petterlundqvist.webshop.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmailAndPassword(String email, String password);
    Boolean existsByEmail(String email);
}
