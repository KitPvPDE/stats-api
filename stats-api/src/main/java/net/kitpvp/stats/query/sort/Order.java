package net.kitpvp.stats.query.sort;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Order {

    ASCENDING(1), DESCENDING(-1)
    ;

    private final int order;
}

