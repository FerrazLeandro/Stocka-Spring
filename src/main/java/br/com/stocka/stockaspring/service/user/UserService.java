package br.com.stocka.stockaspring.service.user;

import java.util.List;
import java.util.Optional;

import br.com.stocka.stockaspring.model.UserModel;

public interface UserService {
    public Long save(UserModel userModel);
    public boolean existsByUsername(String username);
    public Optional<UserModel> findByUsername(String username);    
    public List<UserModel> findAll();
    public Optional<UserModel> findById(Long id);
    public void delete(UserModel userModel);    
}
