package com.example.demo.projection;

import java.util.List;
import java.util.Map;

public interface EntityWithOneLevelRelationshipProjection {
    Long getId();

    String getName();

    Map<String, String> getAdditionalProperties();

    Map<String, List<LinkWithoutTargetRelationshipProjection>> getRelationships();
}
