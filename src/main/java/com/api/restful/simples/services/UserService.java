package com.api.restful.simples.services;

import com.api.restful.simples.models.User;
import com.api.restful.simples.repositories.TaskRepository;
import com.api.restful.simples.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service // Anotação para definir a classe como serviço
public class UserService {

    @Autowired // Anotação para "instanciar" a interface
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    // Classe para procurar pelo id e retornar o erro caso não tenha sucesso
    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById ( id );
        return user.orElseThrow ( () -> new RuntimeException (
                "Não encontrado. id: " + id + ", Tipos: " + User.class.getName ()
        ) );
    }

    @Transactional // Anotação para persistencia no DB
    // Metodo para criação de usuario
    public User create(User obj) {
        obj.setId ( null );
        obj = this.userRepository.save ( obj );
        this.taskRepository.saveAll ( obj.getTaks () );
        return obj;
    }

    // Metodo para update do usuario.
    public User update(User obj) {
        User newObj = findById ( obj.getId () );
        newObj.setPassword ( obj.getPassword () );
        return this.userRepository.save ( newObj );
    }

    // Metodo para deletar usuario
    public void delete(Long id) {
        findById ( id );
        try {
            this.userRepository.deleteById ( id );
        } catch (Exception e) {
            throw new RuntimeException ( e );
        }
    }
}