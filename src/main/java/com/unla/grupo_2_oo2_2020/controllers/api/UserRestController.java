package com.unla.grupo_2_oo2_2020.controllers.api;

import java.util.HashMap;
import java.util.Map;

import com.unla.grupo_2_oo2_2020.services.ISecurityService;
import com.unla.grupo_2_oo2_2020.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    @Qualifier("securityService")
    private ISecurityService securityService;

    @Autowired
    @Qualifier("userService")
    private IUserService userService;

    @GetMapping("/getLoggedUser")
    public ResponseEntity<?> getLoggedUser() {
        Map<String, String> result = new HashMap<String, String>();

        result.put("username", securityService.findLoggedInUsername());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getLoggedUserRole")
    public ResponseEntity<?> getLoggedUserRole() {
        Map<String, String> result = new HashMap<String, String>();

        userService.findByUsername(securityService.findLoggedInUsername()).getRoles().stream()
                .forEach(e -> result.put(e.getName() , e.getName()));
        return ResponseEntity.ok(result);
    }
}