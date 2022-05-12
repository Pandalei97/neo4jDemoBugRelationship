package com.example.demo.entity;

import java.util.List;
import java.util.Map;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import lombok.Data;

@Data
@Node("SomeExtendedEntity")
public class SomeExtendedEntity extends SomeEntity {

    @Relationship
    protected Map<String, List<SomeExtendedEntity>> rel2;

    @Relationship
    protected Map<String, SomeEntity> rel3;

}
