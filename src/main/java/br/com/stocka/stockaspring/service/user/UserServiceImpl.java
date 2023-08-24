package br.com.stocka.stockaspring.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.stocka.stockaspring.model.UserModel;
import br.com.stocka.stockaspring.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public Long save(UserModel userModel) {
        userModel.setPassword( new BCryptPasswordEncoder().encode(userModel.getPassword()) );
        UserModel userCreated = userRepository.save(userModel);        
        return userCreated.getUserId();
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
    
    @Override
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserModel> findById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    @Override
    public void delete(UserModel UserModel) {
        userRepository.delete(UserModel);
    }

    @Override
    public Optional<UserModel> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }    

}
