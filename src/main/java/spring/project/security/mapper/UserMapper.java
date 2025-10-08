package spring.project.security.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import spring.project.security.entity.user.RegisterRequest;
import spring.project.security.entity.user.User;

@Component
public class UserMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User toEntity(RegisterRequest body){
        User newUser = new User();
        newUser.setEmail(body.email());
        newUser.setUsername(body.username());

        newUser.setPassword(passwordEncoder.encode(body.password()));
        newUser.setCpf(body.cpf());
        newUser.setActive(Boolean.TRUE);

        return newUser;
    }


}
