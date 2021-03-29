package net.kitpvp.stats.function;

@FunctionalInterface
public interface BooleanBiConsumer {

    void accept(boolean left, boolean right);
}
