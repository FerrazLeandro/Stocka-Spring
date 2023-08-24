package br.com.stocka.stockaspring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.stocka.stockaspring.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUsername(String username);    
    Boolean existsByUsername(String username);
}
