package net.kitpvp.stats.function;

@FunctionalInterface
public interface UnaryBiIntOperator<R> extends UnaryBiIntFunction<R, R> {

    @Override
    R apply(R r, int i, int j);
}
