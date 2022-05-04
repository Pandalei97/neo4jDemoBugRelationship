package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.neo4j.core.schema.CompositeProperty;
import org.springframework.data.neo4j.core.schema.DynamicLabels;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Node
public class SomeEntity {

    @Id
    @GeneratedValue
    protected Long id;

    @Property
    protected String name;

    @CompositeProperty
    protected Map<String, String> additionalProperties;

    @Relationship
    protected Map<String, List<SomeLink>> relationships;
}