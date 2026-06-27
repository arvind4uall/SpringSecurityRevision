package com.fsdarvind.springSecurityRevision.repository;

import com.fsdarvind.springSecurityRevision.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
