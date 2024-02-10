package smile.com.spring6auth.jwt.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import smile.com.spring6auth.entity.User;
import smile.com.spring6auth.repository.UserRepo;

import java.util.List;

@Slf4j
@Service("userServiceImpl")
@ComponentScan("smile.com.spring6auth.repository")
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.getUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("No user found with this email " + username));
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving user {}", user);
        return userRepo.save(user);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id) {
        log.info("Fetching user with id {}", id);
        return userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("No user found with this id " + id));
    }

    @Override
    public User getUserByEmail(String email) {
        log.info("Fetching user with email {}", email);
        return userRepo.getUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No user found with this email " + email));
    }

    @Override
    public User updateUser(Long id, User user) {
        log.info("Updating user with id {}", id);
        User userToUpdate = getUserById(id);
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setRole(user.getRole());
        return userRepo.save(userToUpdate);
    }

    @Override
    public User patchUser(Long id, User user) {
        log.info("Patching user with id {}", id);
        User userToPatch = getUserById(id);
        if (user.getFirstName() != null) {
            userToPatch.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            userToPatch.setLastName(user.getLastName());
        }
        if (user.getEmail() != null) {
            userToPatch.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            userToPatch.setPassword(user.getPassword());
        }
        return userRepo.save(userToPatch);
    }

    @Override
    public void DeleteUser(Long id) {
        log.info("Deleting user with id {}", id);
        userRepo.deleteById(id);
    }
}
