package com.example.demo.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.SomeEntity;
import com.example.demo.dao.SomeLink;
import com.example.demo.projection.EntityWithOneLevelRelationshipProjection;
import com.example.demo.repository.SomeEntityRepository;

@RestController
@RequestMapping("/entity")
public class SomeEntityController {

    @Autowired
    private SomeEntityRepository someEntityRepository;

    @Autowired
    private Neo4jTemplate neo4jTemplate;

    @PostMapping("createExample")
    SomeEntity create() {
        SomeEntity n1 = new SomeEntity();
        SomeEntity n2 = new SomeEntity();
        SomeEntity n3 = new SomeEntity();

        SomeLink r1 = new SomeLink();
        SomeLink r2 = new SomeLink();

        // Init nodes
        n1.setName("N1");
        n1.setAdditionalProperties(Map.of(
                "attr1", "value1",
                "attr2", "value2"
        ));

        n2.setName("N2");
        n2.setAdditionalProperties(Map.of(
                "attr1", "value1",
                "attr2", "value2"
        ));

        n3.setName("N3");
        n3.setAdditionalProperties(Map.of(
                "attr1", "value1",
                "attr2", "value2"
        ));

        // Init relationships
        r1.setAdditionalProperties(Map.of(
                "attr1", "value1",
                "attr2", "value2"
        ));
        r1.setTarget(n2);

        r2.setAdditionalProperties(Map.of(
                "attr1", "value1",
                "attr2", "value2"
        ));
        r2.setTarget(n3);

        // Add relationships
        n1.setRelationships(Map.of("has_relationship_with", Arrays.asList(r1)));
        n2.setRelationships(Map.of("has_relationship_with", Arrays.asList(r2)));

        // Save entity
        // (n1)-[r1]->(n2)-[r2]->(n3)
        someEntityRepository.save(n1);

        return n1;
    }

    @PostMapping("modifyExample")
    SomeEntity modify(@RequestParam("id") Long id){
        // Get (n1)-[r1]->(n2). To simplify the code, we don't make verification here
        SomeEntity n1 = someEntityRepository.findByIdWithLevelOneLinks(id).get();
        // Modify n1 and save as the projection, in the database, we've got r2 detached.
        n1.setName("newN1");
        neo4jTemplate.saveAs(n1, EntityWithOneLevelRelationshipProjection.class);
        return n1;
    }


}
