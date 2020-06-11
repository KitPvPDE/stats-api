package net.kitpvp.pluginapi.modules.stats.mongo.find;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Order {

    ASCENDING(1), DESCENDING(-1)

    ;

    @Getter
    private final int order;
}
