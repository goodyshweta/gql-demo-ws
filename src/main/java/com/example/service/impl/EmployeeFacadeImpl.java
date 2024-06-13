package com.example.service.impl;

import com.example.facade.EmployeeFacade;
import com.example.model.Employee;
import com.example.service.EmployeeService;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class EmployeeFacadeImpl implements EmployeeService {

    @Autowired
    private EmployeeFacade employeeFacade;

    @Value("classpath:employee.graphqls")
    private Resource schemaResource;

    private GraphQL graphQL;

    @PostConstruct
    public void loadSchema() throws IOException {
        File schemaFile = schemaResource.getFile();
        TypeDefinitionRegistry registry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(registry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildWiring() {
        DataFetcher<List<Employee>> fetcher1 = data -> (List<Employee>) employeeFacade.fetchEmployees();

        DataFetcher<Employee> fetcher2 = data -> {
            return employeeFacade.findEmployeeByEmail("email");
        };

        return RuntimeWiring.newRuntimeWiring().type("Query",
                        typeWriting -> typeWriting.dataFetcher("getAllEmployee", fetcher1).dataFetcher("findEmployee", fetcher2))
                .build();

    }

    @Override
    public List<Employee> getEmployee() {
        return employeeFacade.fetchEmployees();
    }

    @Override
    public ExecutionResult getAll(String query) {
        return graphQL.execute(query);
    }

    @Override
    public ExecutionResult findByEmail(String query) {
        return graphQL.execute(query);
    }
}
