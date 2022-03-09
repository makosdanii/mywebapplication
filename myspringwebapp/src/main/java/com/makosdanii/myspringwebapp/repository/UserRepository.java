package com.makosdanii.myspringwebapp.repository;

import com.makosdanii.myspringwebapp.entity.Users;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Users, String> {

    List<Users> findByEmailContaining(String id);
}
