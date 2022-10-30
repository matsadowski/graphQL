package com.graphql;

import graphql.ExecutionResult;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;
import graphql.servlet.GraphQLInvocationInputFactory;
import graphql.servlet.GraphQLQueryInvoker;
import graphql.servlet.GraphQLSingleInvocationInput;
import graphql.servlet.internal.GraphQLRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class GraphQLClient {
    private final GraphQLWebClient graphQLWebClient;

    private final GraphQLInvocationInputFactory invocationInputFactory;


    private final GraphQLQueryInvoker queryInvoker;

    public GraphQLClient(GraphQLWebClient graphQLWebClient, GraphQLInvocationInputFactory invocationInputFactory, GraphQLQueryInvoker queryInvoker) {
        this.graphQLWebClient = graphQLWebClient;
        this.invocationInputFactory = invocationInputFactory;
        this.queryInvoker = queryInvoker;
    }

    public long getApplicationById(Integer id) {

        long startMilli = System.nanoTime();

        String query = """
                query {
                   applicationById(id: $id)
                   {
                       id
                   }
                 }
                """;

        query = query.replace("$id", id.toString());
        GraphQLRequest request = new GraphQLRequest(query, null, null);


        GraphQLSingleInvocationInput invocationInput = invocationInputFactory.create(request);
        ExecutionResult result = queryInvoker.query(invocationInput);


        if (result.getData() == null) {
            throw new IllegalArgumentException("Illegal value of GraphQL");
        }

        return System.nanoTime() - startMilli;
    }
}
