package com.justme8code.utterfresh_production_gathering_sys.res_req_models.response;

import com.justme8code.utterfresh_production_gathering_sys.models.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class LoginResponse {
    private UserDto user;
    private String jwtToken;
}