package uz.pdp.SpringSecurity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor

public class Task extends BaseEntity{

    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "dead_line")
    private LocalDate deadLine;

    @Builder
    public Task(String name, String description, LocalDate deadLine) {
        this.name = name;
        this.description = description;
        this.deadLine = deadLine;
    }
}
