package com.example.demo.projection;

import java.util.Map;

public interface EntityWithoutRelationshipProjection {
    Long getId();

    String getName();

    Map<String, String> getAdditionalProperties();
}
