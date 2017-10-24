package com.jas.botornot.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jas.botornot.models.Role;
import com.jas.botornot.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    List<User> findAll();
    List<User> findByRoles_NameIs(String name);

}