package com.hashedin.security.repository;

import com.hashedin.security.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

  Privilege findByName(String name);

  @Override
  void delete(Privilege privilege);

}