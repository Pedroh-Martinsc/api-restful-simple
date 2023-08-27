package com.api.restful.simples.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Todos métodos já foram incluídos após o extends no JpaRepository..
public interface UserRepository extends JpaRepository<User, Long> {
}
