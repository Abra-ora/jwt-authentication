package smile.com.spring6auth.jwt.service;

import smile.com.spring6auth.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getUsers();
    User getUserById(Long id);
    User getUserByEmail(String email);
    User updateUser(Long id, User user);
    User patchUser(Long id, User user);
    void DeleteUser(Long id);
}
