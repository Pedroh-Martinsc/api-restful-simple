package com.api.restful.simples.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { // Todos métodos já foram incluídos após o extends no JpaRepository
}
