package org.tix.lab4_1.util;



import org.springframework.stereotype.Repository;
import org.tix.lab4_1.model.mainDB.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username) ;
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<User> findById(Long id) ;
    List<User> getAllUsers();
    User save(User user);
    Optional<User> findByEmail(String email);
}
