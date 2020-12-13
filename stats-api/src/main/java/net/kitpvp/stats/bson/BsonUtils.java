package net.kitpvp.stats.bson;

import org.bson.Document;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.regex.Pattern;

public class BsonUtils {

    public static final Pattern PATH_SPLIT = Pattern.compile("\\.");

    public static <V> void setValue(@NotNull String path, @NotNull Document document, @Nullable V value) {
        walkPathAndSet(path, document, value);
    }

    public static <V> V getValue(@NotNull String path, @NotNull Document document, @Nullable V def) {
        return walkPathAndGet(path, document, def);
    }

    private static <T> void walkPathAndSet(String path, Document document, T value) {
        String[] splitPath = splitPath(path);
        Document walked = walkPath(splitPath, document);
        String lastPathSlice = splitPath[splitPath.length - 1];

        if(value != null) {
            walked.put(lastPathSlice, value);
        } else {
            walked.remove(lastPathSlice);
        }
    }

    private static <T> T walkPathAndGet(String path, Document document, T def) {
        String[] splitPath = splitPath(path);
        Document walked = walkPath(splitPath, document);
        String lastPathSlice = splitPath[splitPath.length - 1];

        try {
            T value = (T) walked.get(lastPathSlice);
            if(value != null)
                return value;

            return def;
        } catch(ClassCastException cause) {
            return def;
        }
    }

    private static String[] splitPath(String path) {
        return PATH_SPLIT.split(path, -1);
    }

    private static Document walkPath(String[] path, Document document) {
        return walkPath(path, document, 0);
    }

    private static Document walkPath(String[] path, Document document, int index) {
        if(path.length == 1 || index + 1 == path.length)
            return document;

        Document sub = null;

        try {
            sub = document.get(path[index], Document.class);
        } catch(ClassCastException ignored) {
            ignored.printStackTrace();
        }

        if(sub == null) {
            document.put(path[index], sub = new Document());
        }

        return walkPath(path, sub, index + 1);
    }

}
