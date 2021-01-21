package net.kitpvp.stats.api.function;

@FunctionalInterface
public interface BooleanBinaryOperator {

    boolean applyAsBoolean(boolean left, boolean right);
}
