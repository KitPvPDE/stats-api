package net.kitpvp.stats.mongodb.query.update;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.mongodb.api.Operator;

@RequiredArgsConstructor
public enum ArrayOperator implements Operator, net.kitpvp.stats.mongodb.api.operator.ArrayOperator {

    PUSH("push", "each"),
    PULL("pull", "in");
    private final String operator, arrayOperator;

    @Override
    public String operator() {
        return this.operator;
    }

    @Override
    public String arrayOperator() {
        return this.arrayOperator;
    }
}
