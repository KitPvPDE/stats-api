package net.kitpvp.stats.api.function;

@FunctionalInterface
public interface BooleanTriConsumer {

    void accept(boolean left, boolean center, boolean right);
}
