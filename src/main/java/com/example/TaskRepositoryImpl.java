package com.example;

import io.micronaut.transaction.annotation.ReadOnly;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

@Singleton
public class TaskRepositoryImpl implements TaskRepository{

    private final EntityManager entityManager;

    public TaskRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @ReadOnly // <3>
    public Optional<Task> findById(long id) {
        return Optional.ofNullable(entityManager.find(Task.class, id));
    }

    @Override
    @Transactional // <4>
    public Task create(Task task) {
        entityManager.persist(task);
        return task;
    }

    @Override
    @Transactional // <4>
    public void deleteById(long id) {
        findById(id).ifPresent(entityManager::remove);
    }

    @ReadOnly // <3>
    public List<Task> findAll() {
        String qlString = "SELECT t FROM Task as t";
        TypedQuery<Task> query = entityManager.createQuery(qlString, Task.class);
        return query.getResultList();
    }

    @Override
    @Transactional // <4>
    public Optional<Task> update(Task task) {
        entityManager.createQuery("UPDATE Task SET description = :description, title = :title, completed = : completed  where id = :id")
                .setParameter("title", task.getTitle())
                .setParameter("description", task.getDescription())

                .setParameter("completed", task.getCompleted())
                .setParameter("id", task.getId())
                .executeUpdate();
        return findById(task.getId());
    }

}
