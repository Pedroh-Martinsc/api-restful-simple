// Criando o primeiro modelo do projeto
package com.api.restful.simples.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Objects;

@Entity // Definindo essa classe como uma entidade
@Table(name = User.TABLE_NAME) // Definindo a criação da tabela com o nome de "user"
public class User {

    public interface CreateUser { // Inteface para validação do not null, not empty e size
    }

    public interface UpdateUser { // Inteface para validação do not null, not empty e size
    }

    public static final String TABLE_NAME = "user";

    @Id // Definindo o id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Anotação estrategio para gerar o id no banco de dados "incremento"

    @Column(name = "id", unique = true) // id Unico para caso perca de backup não duplicar valor

    // Definindo atributos e anotações do Spring no usuário para gerar as informações no DB
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true) // Definindo o nome da coluna, quantidade maxima de letras, não podendo ser nulo e sendo unico,

    //Definindo uma segunda validação para o valor não ser uma String vazia nem nulo no username
    @NotNull(groups = CreateUser.class)
    @NotEmpty(groups = CreateUser.class)

    @Size(groups = CreateUser.class, min = 2, max = 100) // Definindo valores maximos e minimos do username em tempo real
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Somente leitura para não retornar a senha no front
    @Column(name = "password", length = 60, nullable = false) // Definindo o nome da coluna, quantidade maxima de letras, não podendo ser nulo

    //Definindo uma segunda validação para o valor não ser uma String vazia nem nulo no password
    @NotNull(groups = {CreateUser.class, UpdateUser.class})
    @NotEmpty(groups = {CreateUser.class, UpdateUser.class})
    @Size(groups = CreateUser.class, min = 8, max = 60) // Definindo valores maximos e minimos do password em tempo real
    private String password;

    //private List<Task> taks = new ArrayList<Task> ();

    // Criação dos metodos - construtor vazio spring
    public User() {
    }

    public User(Long id, @NotNull(groups = CreateUser.class) String username, @NotNull(groups = {CreateUser.class, UpdateUser.class}) String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // Criação dos Getters E Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Criação dos equals e HashCode para verficações e validações do objeto
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (obj == null || getClass () != obj.getClass ()) return false;
        User other = (User) obj;
        if (this.id == null)
            if (other.id != null) return false;
            else if (!this.id.equals ( other.id )) return false;
        return Objects.equals ( this.id, other.id ) && Objects.equals ( this.username, other.username ) && Objects.equals ( this.password, other.password );
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode ());
        return result;
    }
}