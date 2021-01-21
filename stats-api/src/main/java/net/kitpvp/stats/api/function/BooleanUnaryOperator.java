package net.kitpvp.stats.api.function;

@FunctionalInterface
public interface BooleanUnaryOperator {

    boolean applyAsBoolean(boolean operand);
}
