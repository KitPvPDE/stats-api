package net.kitpvp.stats.query.update;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Action {

    SET("set"),
    UNSET("unset"),
    INC("inc"),
    DEC("dec"),
    MUL("mul"),
    ;

    private final String mongoOperator;

    public final String getMongoOperator() {
        return "$" + this.mongoOperator;
    }
}
