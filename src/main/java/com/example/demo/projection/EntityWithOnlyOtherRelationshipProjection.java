package com.example.demo.projection;

import java.util.List;
import java.util.Map;

import com.example.demo.entity.SomeOtherEntity;

public interface EntityWithOnlyOtherRelationshipProjection extends EntityWithoutRelationshipProjection {

    Map<String, List<SomeOtherEntity>> getOtherRelationships();
}
