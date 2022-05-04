package com.example.demo.dao;

import java.util.Map;

import org.springframework.data.neo4j.core.schema.CompositeProperty;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import lombok.Data;

@Data
@RelationshipProperties
public class SomeLink {
    @Id
    @GeneratedValue
    protected Long id;

    @CompositeProperty
    protected Map<String, String> additionalProperties;

    @TargetNode
    protected SomeEntity target;
}
