package com.example.demo.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.RootNode;
import com.example.demo.entity.SinglePropertyNode;
import com.example.demo.entity.SomeEntity;
import com.example.demo.entity.SomeExtendedEntity;
import com.example.demo.entity.SomeLink;
import com.example.demo.projection.EntityWithOneLevelRel1Projection;
import com.example.demo.projection.ExtendedEntityWithOnlyRel2Projection;
import com.example.demo.projection.ExtendedEntityWithRel2AndOneLevelRel1Projection;
import com.example.demo.repository.RootNodeRepository;
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
        n1.setRel1(Map.of("has_rel1_with", Arrays.asList(r1)));
        n2.setRel1(Map.of("has_rel1_with", Arrays.asList(r2)));

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
        neo4jTemplate.saveAs(n1, EntityWithOneLevelRel1Projection.class);
        return n1;
    }

    @PostMapping("createAnotherExample")
    SomeEntity createAnotherExample(){
        SomeExtendedEntity n1 = new SomeExtendedEntity();
        SomeExtendedEntity n2 = new SomeExtendedEntity();
        SomeExtendedEntity n3 = new SomeExtendedEntity();

        SomeLink r1 = new SomeLink();

        SomeEntity m1 = new SomeEntity();

        // Init Nodes
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
        n2.setRel2(Map.of("has_rel2_with", Arrays.asList(n3)));

        n3.setName("N3");
        n3.setAdditionalProperties(Map.of(
                "attr1", "value1",
                "attr2", "value2"
        ));

        // Init a SomeEntity
        m1.setName("M1");
        m1.setAdditionalProperties(Map.of(
                "attr1", "value1",
                "attr2", "value2"
        ));

        // Init relationships
        n1.setRel2(Map.of("has_rel2_with", Arrays.asList(n2)));

        r1.setAdditionalProperties(Map.of(
                "attr1", "value1",
                "attr2", "value2"
        ));
        r1.setTarget(n2);
        n1.setRel1(Map.of("has_rel1_with", Arrays.asList(r1)));



        // Save (n1)->(n2)->(n3) without m1 and r1
        neo4jTemplate.saveAs(n1, ExtendedEntityWithOnlyRel2Projection.class);

        return n1;
    }

}
