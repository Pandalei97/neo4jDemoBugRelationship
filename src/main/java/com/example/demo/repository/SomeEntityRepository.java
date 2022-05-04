package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.SomeEntity;

@Repository
@Transactional
public interface SomeEntityRepository extends Neo4jRepository<SomeEntity, Long> {

    @Query("MATCH p=(n)-[*0..1]->(m) WHERE id(n)=$id RETURN n, collect(relationships(p)), collect(m);")
    Optional<SomeEntity> findByIdWithLevelOneLinks(@Param("id") Long id);
}
