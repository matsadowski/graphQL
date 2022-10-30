package com.graphql;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController("/application")
public class ApplicationResource {

    private final ApplicationRepository repository;
    private final SessionRepository sessionRepository;
    private final GraphQLClient graphQLClient;

    public ApplicationResource(ApplicationRepository repository, SessionRepository sessionRepository, GraphQLClient graphQLClient) {
        this.repository = repository;
        this.sessionRepository = sessionRepository;
        this.graphQLClient = graphQLClient;
    }

    @PostMapping("/application/generator")
    ResponseEntity<List<Application>> saveApplication() {

        List<Application> apps = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            Application application = new Application();

            application.applicationNumber = "BLP" + "0000" + i;
            application.status = "NEW";
            application.createDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);

            Session session = new Session();
            session.modificationDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
            session.uuid = UUID.randomUUID().toString();

            Session ses = sessionRepository.save(session);

            application.sessionId = ses.id;

            repository.save(application);


            ResponseEntity.created(URI.create("/" + application.id)).body(application);
            apps.add(application);
        }

        return ResponseEntity.ok(apps);
    }

    @GetMapping("/application/test")
    void getApplications() {

        Random r = new Random();
        int low = 1;
        int high = 1000;

        Map<Integer, Long> verify = new HashMap<>();

        for (int i = 0; i <= 1000; i++) {
            int result = r.nextInt(high - low) + low;
            long milli = graphQLClient.getApplicationById(result);
            verify.put(i, milli);
           // System.out.println("Milliseconds of execute: " + milli);
        }

        System.out.println(verify.values().stream().mapToDouble(Long::doubleValue).average().getAsDouble());

    }
}
