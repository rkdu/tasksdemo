package com.example;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class TaskControllerTest {

    private BlockingHttpClient blockingClient;
    @BeforeEach
    void setup() {
        blockingClient = client.toBlocking();
    }

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void testCRUD() {

        Task task = new Task();
        task.setDescription("Description");
        task.setTitle("Title");
        task.setCompleted(false);
        HttpRequest<?> postRequest = HttpRequest.POST("/tasks", task); // <3>
        HttpResponse<?> postResponse = blockingClient.exchange(postRequest);
        assertEquals(HttpStatus.OK, postResponse.getStatus());


        HttpRequest<?> request = HttpRequest.GET("/tasks");
        HttpResponse<List<Task>> response = blockingClient.exchange(request, Argument.listOf(Task.class));
        assertEquals(HttpStatus.OK, response.getStatus());
        assertTrue(response.getBody().isPresent());
        Optional<List<Task>> list = response.getBody();
        assertTrue(list.isPresent());
        assertEquals(1, list.get().size());
        Task task1 = list.get().getFirst();
        assertFalse(task1.getCompleted());
        assertEquals(task.getDescription(), task1.getDescription());
        assertEquals(task.getTitle(), task1.getTitle());

        HttpRequest<?> getRequest = HttpRequest.GET("/tasks/" + task1.getId());
        HttpResponse<Task> getResponse = blockingClient.exchange(getRequest, Task.class);
        assertEquals(HttpStatus.OK, getResponse.getStatus());
        assertTrue(getResponse.getBody().isPresent());
        Optional<Task> taskResult = getResponse.getBody();
        assertTrue(taskResult.isPresent());
        assertEquals(task1, taskResult.get());

        task1.setCompleted(true);
        task1.setDescription("New");
        HttpRequest<?> putRequest = HttpRequest.PUT("/tasks/" + task1.getId(), task1);
        HttpResponse<Task> putResponse = blockingClient.exchange(putRequest, Task.class);
        assertEquals(HttpStatus.OK, putResponse.getStatus());
        assertTrue(putResponse.getBody().isPresent());
        taskResult = putResponse.getBody();
        assertTrue(taskResult.isPresent());
        assertEquals(task1, taskResult.get());

        HttpRequest<?> deleteRequest = HttpRequest.DELETE("/tasks/" + task1.getId());
        HttpResponse<Task> deleteResponse = blockingClient.exchange(deleteRequest);
        assertEquals(HttpStatus.OK, deleteResponse.getStatus());
    }
}

