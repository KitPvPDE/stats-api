package net.kitpvp.stats.utils;

import java.util.function.LongUnaryOperator;

public class Functions {

    public static LongUnaryOperator inverse() {
        return Functions::inverse;
    }

    public static long inverse(long l) {
        return -l;
    }

    public static double inverse(double d) {
        return -d;
    }

    public static int inverse(int i) {
        return -i;
    }
}
