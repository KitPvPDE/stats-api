package net.kitpvp.stats.function;

@FunctionalInterface
public interface BooleanUnaryOperator {

    boolean applyAsBoolean(boolean operand);
}
