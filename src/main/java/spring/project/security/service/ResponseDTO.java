package spring.project.security.service;

import spring.project.security.entity.user.User;

public record ResponseDTO(String username, String accessToken, String refreshToken) {
    public static ResponseDTO fromUser(User username, String accessToken, String refreshToken) {
        return new ResponseDTO(username.getUsername(), accessToken, refreshToken);
    }
}