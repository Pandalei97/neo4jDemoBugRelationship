package com.example.demo.projection;

import java.util.List;
import java.util.Map;

import com.example.demo.entity.SomeExtendedEntity;

public interface ExtendedEntityWithRel2AndOneLevelRel1Projection extends EntityWithOneLevelRel1Projection {
    Map<String, List<SomeExtendedEntity>> getRel2();
}
