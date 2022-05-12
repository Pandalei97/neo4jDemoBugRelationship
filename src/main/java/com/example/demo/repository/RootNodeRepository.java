package com.example.demo.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.RootNode;
import com.example.demo.entity.SomeEntity;

@Repository
@Transactional
public interface RootNodeRepository extends Neo4jRepository<RootNode, Long> {
}
