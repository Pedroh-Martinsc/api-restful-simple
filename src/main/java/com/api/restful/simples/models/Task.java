package com.api.restful.simples.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = Task.TABLE_NAME)
public class Task {
    public static final String TABLE_NAME = "task";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne // Podemos atribuir multiplas tarefas a um unico usuario com essa anotação
    @JoinColumn(name = "user_id", nullable = false, updatable = false) // Referenciar a chave primaria do user no DB
    private User user;

    @Column(name = "description", length = 255, nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 255)
    private String description;

    // Criação dos metodos - construtor vazio spring
    public Task() {
    }

    // Gerdado os contrutores - Getters e Setters
    public Task(Long id, User user, @NotNull String description) {
        this.id = id;
        this.user = user;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Criação dos equals e HashCode para comparações e validações
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (obj == null || getClass () != obj.getClass ()) return false;
        Task other = (Task) obj;
        if (this.id == null)
            if (other.id != null) return false;
            else if (!this.id.equals ( other.id )) return false;
        return Objects.equals ( this.id, other.id ) && Objects.equals ( this.user, other.user ) && Objects.equals ( this.description, other.description );
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode ());
        return result;
    }
}
