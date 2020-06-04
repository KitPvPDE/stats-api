package net.kitpvp.pluginapi.modules.stats.mongo.find;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Comparison {

    EQUALS("eq"), LESS("lt"), GREATER("gt"), GREATER_OR_EQUAL("gte"), LESS_OR_EQUAL("lte"),

    ;

    @Getter
    private final String command;
}
