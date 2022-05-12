package com.example.demo.projection;

import java.util.List;
import java.util.Map;

public interface EntityWithOneLevelRel1Projection {
    Long getId();

    String getName();

    Map<String, String> getAdditionalProperties();

    Map<String, List<LinkWithoutTargetRel1Projection>> getRel1();
}
