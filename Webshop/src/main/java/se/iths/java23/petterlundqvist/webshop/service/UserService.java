package se.iths.java23.petterlundqvist.webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import se.iths.java23.petterlundqvist.webshop.model.User;
import se.iths.java23.petterlundqvist.webshop.repository.UserRepository;

@Service
@SessionScope
public class UserService {
    @Autowired
    UserRepository userRepository;

    private User signedInUser;

    public boolean validateUser(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        if(user == null || user.getAdmin()) {
            return false;
        } else {
            signedInUser = user;
            return true;
        }
    }

    public boolean validateAdmin(String email, String password) {
        User admin = userRepository.findByEmailAndPassword(email, password);
        return admin != null && admin.getAdmin();
    }

    public boolean createUser (String firstName, String lastName, String email, String password) {
        if(userRepository.existsByEmail(email)) {
            return false;
        } else {
            User user = new User(firstName, lastName, email, password, false);
            userRepository.save(user);
            signedInUser = user;
            return true;
        }
    }

    public void signOutUser() {
        if(signedInUser != null) {
            signedInUser = null;
        }
    }

    public User getSignedInUser() {
        return signedInUser;
    }
}
