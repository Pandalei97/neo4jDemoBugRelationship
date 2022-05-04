package com.example.demo.projection;

import java.util.Map;

public interface LinkWithoutTargetRelationshipProjection {
    Long getId();

    Map<String, String> getAdditionalProperties();

    EntityWithoutRelationshipProjection getTarget();

}
