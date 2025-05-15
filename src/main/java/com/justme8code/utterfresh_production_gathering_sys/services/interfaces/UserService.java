package com.justme8code.utterfresh_production_gathering_sys.services.interfaces;

import com.justme8code.utterfresh_production_gathering_sys.models.User;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.requests.CreateUserRequestDto;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.response.UserResponseDto;

import java.util.List;

public interface UserService {
    User findByEmail(String email);

    User findByUsername(String username);

    void createUser(CreateUserRequestDto createUserRequest);

    List<UserResponseDto> getAllUsers();

    void updateUser(User user);

    void deleteUser(Long id);


}
