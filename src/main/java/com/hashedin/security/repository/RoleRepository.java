package com.hashedin.security.repository;

import com.hashedin.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

  Role findByName(String name);

  @Override
  void delete(Role role);

}
