package com.makersacademy.acebook.service;
import com.makersacademy.acebook.model.User;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.makersacademy.acebook.repository.UserRepository;

@Service
public class UserService implements IUserService {
  @Autowired
  private UserRepository repository;

  @Autowired
  private FileStore fileStore;
  
  @Override
  public Boolean save(User user){
    if(!usernameExists(user.getUsername())){
      repository.save(user);
      return true;
    }else{
      return false;
    }
  }

  @Override
  public User findByUsername(String username){
    return repository.findByUsername(username);
  }
  
  @Override
  public Boolean usernameExists(String username){
    return repository.usernameExists(username);
  }
}
