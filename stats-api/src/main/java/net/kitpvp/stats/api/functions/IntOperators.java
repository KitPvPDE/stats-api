package net.kitpvp.stats.api.functions;

import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;

public interface IntOperators {

    static BinaryOperator<Integer> operator(IntBinaryOperator operator) {
        return operator::applyAsInt;
    }

}
