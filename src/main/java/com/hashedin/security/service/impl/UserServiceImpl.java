package com.hashedin.security.service.impl;

import com.hashedin.security.entity.User;
import com.hashedin.security.repository.RoleRepository;
import com.hashedin.security.repository.UserRepository;
import com.hashedin.security.service.UserService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;


//
//  @Override
//  public User registerNewUserAccount(final UserDto accountDto) {
//    if (emailExists(accountDto.getEmail())) {
//      throw new UserAlreadyExistException("There is an account with that email address: " + accountDto.getEmail()); }
//    final User user = new User();
//
//    user.setFirstName(accountDto.getFirstName());
//    user.setLastName(accountDto.getLastName());
//    user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
//    user.setEmail(accountDto.getEmail());
//    user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
//    return userRepository.save(user);
//  }

  public void saveRegisteredUser(final User user) {
    userRepository.save(user);
  }


}
