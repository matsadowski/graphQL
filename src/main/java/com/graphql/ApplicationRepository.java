package com.graphql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    @Override
    Optional<Application> findById(Integer id);

    List<Application> findAllBySessionId(Integer sessionId);


}

