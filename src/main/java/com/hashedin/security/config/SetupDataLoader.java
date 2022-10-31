package com.hashedin.security.config;


import com.hashedin.security.entity.Privilege;
import com.hashedin.security.entity.Role;
import com.hashedin.security.entity.User;
import com.hashedin.security.repository.PrivilegeRepository;
import com.hashedin.security.repository.RoleRepository;
import com.hashedin.security.repository.UserRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

  private boolean alreadySetup = false;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private PrivilegeRepository privilegeRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public void onApplicationEvent(final ContextRefreshedEvent event) {
    if (alreadySetup) {
      return;
    }

    // create initial privileges
    final Privilege readPrivilege = createPrivilegeIfNotFound("READ_ADVERTISEMENT");
    final Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_ADVERTISEMENT");

    // create initial roles
    final List<Privilege> adminPrivileges = new ArrayList<>(Arrays.asList(readPrivilege, writePrivilege));
    final List<Privilege> userPrivileges = new ArrayList<>(Arrays.asList(readPrivilege));
    final Role adminRole = createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
    final Role userRole = createRoleIfNotFound("ROLE_USER", userPrivileges);

    // create initial user
    createUserIfNotFound("admin", "admin@abc.com", "admin",  new ArrayList<>(Arrays.asList(adminRole)));
    createUserIfNotFound("user","user@abc.com","user",new ArrayList<>(Arrays.asList(userRole)));
    alreadySetup = true;
  }

  @Transactional
  Privilege createPrivilegeIfNotFound(final String name) {
    Privilege privilege = privilegeRepository.findByName(name);
    if (privilege == null) {
      privilege = new Privilege(name);
      privilege = privilegeRepository.save(privilege);
    }
    return privilege;
  }

  @Transactional
  Role createRoleIfNotFound(final String name, final Collection<Privilege> privileges) {
    Role role = roleRepository.findByName(name);
    if (role == null) {
      role = new Role(name);
    }
    role.setPrivileges(privileges);
    role = roleRepository.save(role);
    return role;
  }

  @Transactional
  User createUserIfNotFound(final String username, final String email, final String password, final Collection<Role> roles) {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      user = new User();
      user.setEmail(email);
      user.setUsername(username);
      user.setPassword(passwordEncoder.encode(password));
      user.setEmail(email);
      user.setEnabled(true);
    }
    user.setRoles(roles);
    user = userRepository.save(user);
    return user;
  }

}