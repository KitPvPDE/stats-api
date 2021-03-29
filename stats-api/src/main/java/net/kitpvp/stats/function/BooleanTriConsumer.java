package net.kitpvp.stats.function;

@FunctionalInterface
public interface BooleanTriConsumer {

    void accept(boolean left, boolean center, boolean right);
}
