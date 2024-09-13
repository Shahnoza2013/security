package uz.pdp.SpringSecurity.dto;

public record AuthenticationDto(
        String accessToken,
        String refreshToken
) {
}
