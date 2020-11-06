package net.kitpvp.stats.mongodb.query.filter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Operator implements net.kitpvp.stats.mongodb.api.Operator {

    EQUALS("eq"), 
    LESS("lt"), 
    GREATER("gt"),
    GREATER_THAN_EQUAL("gte"),
    LESS_THAN_EQUAL("lte"),
    ;

    private final String operator;

    @Override
    public String operator() {
        return this.operator;
    }
}
