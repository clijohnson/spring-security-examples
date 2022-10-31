package com.hashedin.security.service.impl;

import com.hashedin.security.entity.Privilege;
import com.hashedin.security.entity.Role;
import com.hashedin.security.entity.User;
import com.hashedin.security.repository.UserRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  public UserDetailsServiceImpl() {
    super();
  }

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    final User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("No user found with username: " + username);
    }
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                                                                  user.isEnabled(), true, true, true,
                                                                  getAuthorities(user.getRoles()));
  }
  private Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
    return getGrantedAuthorities(getPrivileges(roles));
  }
  private List<String> getPrivileges(final Collection<Role> roles) {
    final List<String> privileges = new ArrayList<>();
    final List<Privilege> collection = new ArrayList<>();
    for (final Role role : roles) {
      privileges.add(role.getName());
      collection.addAll(role.getPrivileges());
    }
    for (final Privilege item : collection) {
      privileges.add(item.getName());
    }
    return privileges;
  }
  private List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
    final List<GrantedAuthority> authorities = new ArrayList<>();
    for (final String privilege : privileges) {
      authorities.add(new SimpleGrantedAuthority(privilege));
    }
    return authorities;
  }

}
