package net.kitpvp.stats.query.filter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Comparison {

    EQUALS("eq"), 
    LESS("lt"), 
    GREATER("gt"),
    GREATER_THAN_EQUAL("gte"),
    LESS_THAN_EQUAL("lte"),
    ;

    private final String mongoOperator;

    public String getMongoOperator() {
        return "$" + this.mongoOperator;
    }
}
