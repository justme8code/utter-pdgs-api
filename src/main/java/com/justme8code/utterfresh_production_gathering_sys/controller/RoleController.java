package com.justme8code.utterfresh_production_gathering_sys.controller;

import com.justme8code.utterfresh_production_gathering_sys.dtos.RoleDto;
import com.justme8code.utterfresh_production_gathering_sys.dtos.RoleMapper;
import com.justme8code.utterfresh_production_gathering_sys.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleController(RoleRepository roleRepository,
                          RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        List<RoleDto> roles = roleRepository.findAll().stream().map(roleMapper::toDto).toList();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
