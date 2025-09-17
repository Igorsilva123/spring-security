package spring.project.security.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.project.security.entity.authentication.DataToken;
import spring.project.security.entity.user.LoginRequest;
import spring.project.security.entity.user.RegisterRequest;
import spring.project.security.entity.user.User;
import spring.project.security.infra.security.TokenService;
import spring.project.security.service.ResponseDTO;
import spring.project.security.service.UserService;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest data){
        try {
            ResponseDTO response = userService.login(data);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping("register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest dto){
        userService.registerUser(dto);
        return ResponseEntity.ok("successfully registered user");
    }

}
