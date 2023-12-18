package todo.ornot.todobackend.user.dto;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String accessToken;
    private String tokentype = "Bearer ";

    public AuthResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
