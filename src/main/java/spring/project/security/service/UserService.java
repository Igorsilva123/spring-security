package spring.project.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.project.security.entity.user.LoginRequest;
import spring.project.security.entity.user.RegisterRequest;
import spring.project.security.entity.user.User;
import spring.project.security.infra.security.TokenService;
import spring.project.security.mapper.UserMapper;
import spring.project.security.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseDTO login(LoginRequest dto) {
        User user = userRepository.findByEmailIgnoreCase(dto.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        String token = tokenService.generateToken(user);
        String refreshToken = tokenService.generateRefreshToken(user);
        return new ResponseDTO(user.getUsername(), token, refreshToken);
    }

    @Transactional
    public void registerUser(RegisterRequest body) {
        Optional<User> user = userRepository.findByEmailIgnoreCase(body.email());
        if (user.isPresent()) {
            throw new RuntimeException("E-mail já registrado.");
        }
        User newUser = userMapper.toEntity(body);

        userRepository.save(newUser);

        String token = tokenService.generateToken(newUser);


    }
}



