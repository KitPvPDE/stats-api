package net.kitpvp.stats.mongodb.api;

public interface Operator {

    String getOperator();

    default String getMongoOperator() {
        return "$" + this.getOperator();
    }

}
