package com.justme8code.utterfresh_production_gathering_sys.res_req_models.response;

import com.justme8code.utterfresh_production_gathering_sys.dtos.UserDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class LoginResponse {
    private UserDto user;
    private String jwtToken;
}