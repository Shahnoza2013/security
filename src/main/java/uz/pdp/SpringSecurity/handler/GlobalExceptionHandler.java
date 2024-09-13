package uz.pdp.SpringSecurity.handler;

import uz.pdp.SpringSecurity.dto.ErrorBodyDto;
import uz.pdp.SpringSecurity.exeption.UsernameOrPasswordWrong;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({UsernameOrPasswordWrong.class})
    public ErrorBodyDto usernameOrPasswordWrong(HttpServletRequest request, UsernameOrPasswordWrong exception){
        return new ErrorBodyDto(
                exception.getStatus().value(),
                request.getRequestURI(),
                request.getRequestURL().toString(),
                exception.getClass().toString(),
                exception.getMessage(),
                LocalDateTime.now());
    }
}
