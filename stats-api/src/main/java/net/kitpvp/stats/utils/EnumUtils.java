package net.kitpvp.stats.utils;

import java.util.function.BiPredicate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EnumUtils {
    public EnumUtils() {
    }

    public static <T extends Enum<T>> String nameToLowercase(T t) {
        return t.name().toLowerCase();
    }

    public static <E extends Enum<E>> E match(@NotNull Class<E> enumClass, @Nullable String name) {
        return match(enumClass, String::equals, name, null);
    }

    public static <E extends Enum<E>> E match(@NotNull Class<E> enumClass, @Nullable String name, E defaultEnum) {
        return match(enumClass, String::equals, name, defaultEnum);
    }

    public static <E extends Enum<E>> E matchIgnoringCase(@NotNull Class<E> enumClass, @Nullable String name) {
        return match(enumClass, String::equalsIgnoreCase, name, null);
    }

    public static <E extends Enum<E>> E matchIgnoringCase(@NotNull Class<E> enumClass, @Nullable String name, E defaultEnum) {
        return match(enumClass, String::equalsIgnoreCase, name, defaultEnum);
    }

    public static <E extends Enum<E>> E match(@NotNull Class<E> enumClass, @NotNull BiPredicate<String, String> test, @Nullable String name, E defaultEnum) {
        if (name == null) {
            return defaultEnum;
        } else {
            E[] elements = enumClass.getEnumConstants();
            for(E element : elements) {
                if(test.test(element.name(), name)) {
                    return element;
                }
            }
            return defaultEnum;
        }
    }
}
