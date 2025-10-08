package spring.project.security.entity.user;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
                    @NotBlank
                    String username,
                    @NotBlank
                    String email,
                    @NotBlank
                    String password,
                    @NotBlank
                    String cpf,
                    Boolean active
)
{
}
