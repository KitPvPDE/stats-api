package net.kitpvp.stats.api.functions;

@FunctionalInterface
public interface BiLongToLongFunction {

    long applyToLong(long l, long m);
}
