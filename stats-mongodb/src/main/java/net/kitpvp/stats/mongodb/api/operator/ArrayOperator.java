package net.kitpvp.stats.mongodb.api.operator;

import net.kitpvp.stats.mongodb.api.Operator;

public interface ArrayOperator extends Operator {

    String arrayOperator();

    default String arrayCommand() {
        return "$" + this.arrayOperator();
    }

}
