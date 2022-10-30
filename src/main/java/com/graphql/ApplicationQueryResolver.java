package com.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApplicationQueryResolver implements GraphQLQueryResolver {

    private final ApplicationRepository applicationRepository;
    private final SessionRepository sessionRepository;

    public ApplicationQueryResolver(ApplicationRepository applicationRepository, SessionRepository sessionRepository) {
        this.applicationRepository = applicationRepository;
        this.sessionRepository = sessionRepository;
    }

    public Optional<Application> applicationById(Integer id) {
        return applicationRepository.findById(id);
    }

    public Optional<Application> applicationByUuid(String uuid) {
        return applicationRepository.findAllBySessionId(sessionRepository.findAllByuuid(uuid)
                        .stream().findFirst().orElseThrow(IllegalArgumentException::new).id)
                .stream().findFirst();
    }

}
