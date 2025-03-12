package com.justme8code.utterfresh_production_gathering_sys.res_req_models.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ResetPasswordRequest {
    private String userEmail;
    private String password;
    private String newPassword;
}