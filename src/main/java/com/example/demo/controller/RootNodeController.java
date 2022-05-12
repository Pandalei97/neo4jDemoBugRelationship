package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.MultiplePropertyNode;
import com.example.demo.entity.RootNode;
import com.example.demo.entity.SinglePropertyNode;
import com.example.demo.repository.RootNodeRepository;

@RestController
@RequestMapping("/entity")
public class RootNodeController {

    @Autowired
    private RootNodeRepository rootNodeRepository;

    @PostMapping("createRootNode")
    RootNode createRootNode() {
        RootNode root = new RootNode("Root");

        SinglePropertyNode s1 = new SinglePropertyNode("S1");
        SinglePropertyNode s2 = new SinglePropertyNode("S2");
        SinglePropertyNode s3 = new SinglePropertyNode("S3");

        MultiplePropertyNode m1 = new MultiplePropertyNode("M1");
        MultiplePropertyNode m2 = new MultiplePropertyNode("M2");


        /*
            (root)-[rel_single]->(s1)-[rel_multi]->(m1)->[rel_single]->(s2)
         */
        root.setSingleProperties(Map.of("rel_single", s1));
        s1.setMultipleProperties(Map.of("rel_multi", List.of(m1)));
        m1.setSingleProperties(Map.of("rel_single", s2));


        /*
            (root)-[rel_multi]->(m2)-[rel_single]->(s3)
         */
        root.setMultipleProperties(Map.of("rel_multi", List.of(m2)));
        m2.setSingleProperties(Map.of("rel_single", s3));

        rootNodeRepository.save(root);

        return root;

    }

}
