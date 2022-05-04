package com.example.demo.projection;

import java.util.List;
import java.util.Map;

public interface EntityWithOneLevelRelationship {
    Long getId();

    String getName();

    Map<String, Object> getAdditionalProperties();

    Map<String, List<LinkWithoutTargetRelationshipProjection>> getRelationships();
}
