package com.example;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    Optional<Task> findById(long id);

    Task create(@NotNull Task task);

    void deleteById(long id);

    List<Task> findAll();

    Optional<Task> update(@NotNull Task task);
}
