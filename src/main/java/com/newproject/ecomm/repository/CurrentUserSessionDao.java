package com.newproject.ecomm.repository;

import com.newproject.ecomm.model.CurrentUserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CurrentUserSessionDao extends JpaRepository<CurrentUserSession,Integer> {
    public Optional<CurrentUserSession> findById(Integer userId);

    public CurrentUserSession findByUniqueId(String uniqueId);

    @Query("select session.id from CurrentUserSession session where session.userId= ?")
    public String findUserById(Integer userId);
}
