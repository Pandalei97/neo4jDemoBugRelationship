package com.example.demo.projection;

import java.util.Map;

public interface LinkWithoutTargetRel1Projection {
    Long getId();

    Map<String, String> getAdditionalProperties();

    EntityWithoutRel1Projection getTarget();

}
