package net.kitpvp.stats.mongodb.query.update;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Operator implements net.kitpvp.stats.mongodb.api.Operator {

    SET("set"),
    UNSET("unset"),
    INC("inc"),
    DEC("dec"),
    MUL("mul"),
    ;

    private final String operator;

    @Override
    public String operator() {
        return this.operator;
    }
}
