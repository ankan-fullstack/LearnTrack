package com.airtribe.learntrack.util;

import java.util.HashMap;
import java.util.Map;

import com.airtribe.learntrack.enums.EntityType;

public class IdGenerator {
    private static Map<EntityType, Integer> idCounters = new HashMap<>();

    public static int getNextId(EntityType entityType) {
        int currentId = idCounters.getOrDefault(entityType, 0);
        int updatedId = currentId + 1;
        idCounters.put(entityType, updatedId);
        return updatedId;
    }

}


