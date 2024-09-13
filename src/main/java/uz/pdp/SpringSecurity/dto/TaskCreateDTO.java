package uz.pdp.SpringSecurity.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;

public record TaskCreateDTO(
        Integer id,
        String name,
        String description,
        LocalDate deadLine
) {
}
