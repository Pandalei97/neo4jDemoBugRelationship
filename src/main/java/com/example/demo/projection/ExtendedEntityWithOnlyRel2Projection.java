package com.example.demo.projection;

import java.util.List;
import java.util.Map;

import com.example.demo.entity.SomeExtendedEntity;

public interface ExtendedEntityWithOnlyRel2Projection extends EntityWithoutRel1Projection {

    Map<String, List<SomeExtendedEntity>> getRel2();
}
