package net.kitpvp.stats.mongodb.api;

public interface Operator {

    String operator();

    default String command() {
        return "$" + this.operator();
    }

}
