package net.kitpvp.stats.query.update;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ArrayAction {

    PUSH("push", "each"),
    PULL("pull", "in");

    private final String mongoOperator, mongoArrayOperator;

    public final String getMongoOperator() {
        return "$" + this.mongoOperator;
    }

    public String getMongoArrayOperator() {
        return "$" + this.mongoArrayOperator;
    }
}
