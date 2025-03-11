package com.hr.hrsystem.repo;

import com.hr.hrsystem.entity.User;
import com.hr.hrsystem.projection.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUserName(String userName);

    void deleteByUserName(String userName);

    @Query(value = "select u.username as username from tbl_user u;", nativeQuery = true)
    List<UserProjection> findAllProjections();



}
