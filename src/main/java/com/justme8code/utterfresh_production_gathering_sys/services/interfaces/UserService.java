package com.justme8code.utterfresh_production_gathering_sys.services.interfaces;

import com.justme8code.utterfresh_production_gathering_sys.mappers.dtos.UserDto2;
import com.justme8code.utterfresh_production_gathering_sys.models.User;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.requests.CreateUserRequest;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    User findByEmail(String email);
    User findByUsername(String username);

    void createUser(CreateUserRequest createUserRequest);
    List<UserDto2> getAllUsers();
    void updateUser(User user);
    void deleteUser(Long id);


}
