package com.example.demo.entity;

import java.util.List;
import java.util.Map;

import org.springframework.data.neo4j.core.schema.CompositeProperty;
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
public class RootNode {
    @Id
    @GeneratedValue
    protected Long id;

    @Property
    protected String name;

    @Relationship
    protected Map<String, SinglePropertyNode> singleProperties;

    @Relationship
    protected Map<String, List<MultiplePropertyNode>> multipleProperties;

    public RootNode(String name) {
        this.name = name;
    }
}
