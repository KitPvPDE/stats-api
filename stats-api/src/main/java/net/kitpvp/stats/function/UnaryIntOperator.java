package net.kitpvp.stats.function;

@FunctionalInterface
public interface UnaryIntOperator<R> {

    R apply(R r, int i);

}
