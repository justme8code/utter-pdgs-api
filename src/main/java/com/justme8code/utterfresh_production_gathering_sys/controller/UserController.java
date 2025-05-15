package com.justme8code.utterfresh_production_gathering_sys.controller;

import com.justme8code.utterfresh_production_gathering_sys.dtos.StaffDto;
import com.justme8code.utterfresh_production_gathering_sys.models.Staff;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.requests.CreateUserRequestDto;
import com.justme8code.utterfresh_production_gathering_sys.res_req_models.response.UserResponseDto;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.StaffService;
import com.justme8code.utterfresh_production_gathering_sys.services.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final StaffService staffService;

    public UserController(UserService userService, StaffService staffService) {
        this.userService = userService;
        this.staffService = staffService;
    }

    @PostMapping
    public ResponseEntity<Void> createUserReq(@RequestBody CreateUserRequestDto createUserRequest) {
        userService.createUser(createUserRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{userId}/staffs")
    public ResponseEntity<Staff> createStaffReq(@PathVariable Long userId, @RequestBody Staff staff) {
        staffService.createStaff(userId, staff);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userId}/staffs")
    public ResponseEntity<Staff> getStaff(@PathVariable Long userId) {
        Staff staff = staffService.getStaffById(userId);
        return new ResponseEntity<>(staff, HttpStatus.OK);
    }

    @GetMapping("/staffs")
    public ResponseEntity<List<StaffDto>> getAllStaffs() {
        return new ResponseEntity<>(staffService.getAllStaffs(), HttpStatus.OK);
    }
}
