package com.example.demo.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import lombok.Data;

@Data
@Node("Property")
public class PropertyNode {
    @Id
    @GeneratedValue
    protected Long id;

    @Property
    protected String name;
}
