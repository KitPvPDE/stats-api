package net.kitpvp.stats.local;

import net.kitpvp.stats.query.update.ArrayAction;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.regex.Pattern;

public class DocumentUtils {

    private static final Pattern PATH_SPLIT = Pattern.compile(".");

    static <V> void setValue(@NotNull String path, @NotNull Document document, @Nullable V value) {
        if(value != null) {
            walkPath(path, document).put(path, value);
        } else {
            walkPath(path, document).remove(path);
        }
    }

    static <V> void updateList(@NotNull String path, @NotNull Document document, ArrayAction action, @Nullable List<V> value) {
        if(value != null) {
            walkPath(path, document).put(path, value);
        } else {
            walkPath(path, document).remove(path);
        }
    }

    @SuppressWarnings("unchecked")
    static <V> V getValue(@NotNull String path, @NotNull Document document, @Nullable V def) {
        Document root = walkPath(path, document);

        try {
            V value = (V) document.get(path);
            if(value != null)
                return value;

            return def;
        } catch(ClassCastException cause) {
            return def;
        }
    }

    public static Document walkPath(String path, Document document) {
        if(!path.contains("."))
            return document;

        String[] sections = PATH_SPLIT.split(path, 2);
        Document sub = null;
        try {
            sub = document.get(sections[0], Document.class);
        } catch(ClassCastException ignored) { }

        if(sub == null) {
            document.put(sections[0], sub = new Document());
        }

        return walkPath(sections[1], sub);
    }

}
