package com.example;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.*;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Optional;

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/tasks")
@Tag(name = "Taskmanagement")
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Get("/")
    @Operation(summary = "Liefert eine Liste aller Aufgaben")
    public Iterable<Task> listTasks() {
        return taskRepository.findAll();
    }

    @Post("/")
    @Operation(summary = "Erlaubt das Erstellen einer neuen Aufgabe")
    public Task createTask(@Body Task task) {
        return taskRepository.create(task);
    }

    @Get("/{id}")
    public Task getTask(@PathVariable Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Put("/{id}")
    @Operation(summary = "Aktualisiert eine bestehende Aufgabe")
    public Optional<Task> updateTask(@PathVariable Long id, @Body Task task) {
        task.setId(id);
        return taskRepository.update(task);
    }

    @Delete("/{id}")
    @Operation(summary = "Löscht eine bestehende Aufgabe")
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }

    @Error(status = HttpStatus.NOT_FOUND)
    public HttpResponse<JsonError> notFound() {
        JsonError error = new JsonError("Task Not Found");

        return HttpResponse.<JsonError>notFound()
                .body(error); // (3)
    }
}
