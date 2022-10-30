package com.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApplicationResolver implements GraphQLResolver<Application> {

    private final SessionRepository sessionRepository;

    public ApplicationResolver(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Optional<Session> session(Application application) {
        return sessionRepository.findById(1);
    }
}
