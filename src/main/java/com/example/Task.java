package com.example;

import io.micronaut.serde.annotation.Serdeable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Serdeable
@Entity
@Schema(description = "Details einer Aufgabe")
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue
    @Schema(description = "ID der Aufgabe", example = "1")
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false, unique = false)
    @Schema(description = "Titel der Aufgabe", example = "Tierarzt Besuch")
    private String title;

    @NotNull
    @Column(name = "description", nullable = false, unique = false)
    @Schema(description = "Beschreibung der Aufgabe", example = "Katze zur Impfung bringen - 12. Juni 2025 um 11 Uhr")
    private String description;

    @NotNull
    @Column(name = "completed", nullable = false, unique = false)
    @Schema(description = "Status der Aufgabe", example = "true")
    private boolean completed = false;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return completed == task.completed && Objects.equals(id, task.id) && Objects.equals(title, task.title) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, completed);
    }
}
