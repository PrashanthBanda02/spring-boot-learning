package com.example.todo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String todo;
    private String priority;
    private String status;

    public Todo() {}

    public Todo(String todo, String priority, String status) {
        this.todo = todo;
        this.priority = priority;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTodo() { return todo; }
    public void setTodo(String todo) { this.todo = todo; }
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}




//
//package com.example.todo;
//
//public class Todo  {
//    private int id;
//    private String todo;
//    private String priority;
//    private String status;
//
//    public Todo(int id, String todo, String priority, String status ){
//        this.id = id;
//        this.todo = todo;
//        this.priority = priority;
//        this.status = status;
//    }
//
//    public int getId() {return this.id;}
//
//    public void setId(int id) { this.id = id; }
//
//    public String getTodo(){ return  this.todo; }
//
//    public void setTodo(String todo) {this.todo = todo;}
//
//    public String getPriority(){ return  this.priority; }
//
//    public void setPriority(String priority) {this.priority = priority;}
//
//    public String getStatus(){ return  this.status; }
//
//    public void setStatus(String status) {this.status = status;}
//
//}