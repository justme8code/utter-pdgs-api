package com.justme8code.utterfresh_production_gathering_sys.services.interfaces;

import com.justme8code.utterfresh_production_gathering_sys.models.User;

public interface UserService {
    User findByEmail(String email);
    User findByUsername(String username);

    void createUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);


}
