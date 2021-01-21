package net.kitpvp.stats.api.function;

@FunctionalInterface
public interface BooleanBiConsumer {

    void accept(boolean left, boolean right);
}
