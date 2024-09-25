package com.example.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todo.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
}




//package com.example.todo;
//
//import java.util.*;
//
//
//public interface TodoRepository {
//    ArrayList<Todo> getTodos();
//    Todo addTodo(Todo todo);
//    Todo getTodoById(int todoId);
//    Todo updateTodo(int todoId, Todo todo);
//    void deleteTodo(int todoId);
//}
//
