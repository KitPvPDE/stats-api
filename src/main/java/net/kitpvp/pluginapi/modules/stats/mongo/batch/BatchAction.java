package net.kitpvp.pluginapi.modules.stats.mongo.batch;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.StatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.special.NumberKey;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public enum BatchAction {

    SET("set"), INC("inc"), PUSH("push", "each"), PULL("pull", "in")
    ;

    @Getter
    private final String command, arrayCommand;

    BatchAction(String command) {
        this.command = command;
        this.arrayCommand = null;
    }

    public <K, V> void append(BatchStatsReader statsReader, StatsKey<K, V> statsKey, K k, V v) {
        switch(this) {
            case INC:
                V current = statsReader.rawStatKey(statsKey, k);
                if(!(statsKey instanceof NumberKey)) {
                    new UnsupportedOperationException("Cannot " + this + " this stats key " + statsKey + " (Key: " + k + " Current: " + current + " New: " + v).printStackTrace();
                    return;
                }

                NumberKey<K, V> numberKey = (NumberKey<K, V>) statsKey;
                V updated = numberKey.inc(current, v);
                statsKey.append(statsReader.getDatabase(), k, updated);
                break;
            case SET:
                statsKey.append(statsReader.getDatabase(), k, v);
                break;
            case PULL:
            case PUSH:
                if(!(v instanceof Collection)) {
                    new UnsupportedOperationException("Cannot " + this + " this stats key " + statsKey + " (Key: " + k + " Current: " + statsReader.getStatKey(statsKey, k) + " New: " + v).printStackTrace();
                    return;
                }
                V currentObject = statsReader.getStatKey(statsKey, k);
                if(!(currentObject instanceof Collection) && !(currentObject instanceof Document)) {
                    new UnsupportedOperationException("Cannot " + this + " this stats key " + statsKey + " (Old is " + currentObject + ", " + (currentObject != null ? currentObject.getClass() : "") + ")").printStackTrace();
                    return;
                }


                Collection<?> currentList = currentObject instanceof List ? (List<?>) currentObject :
                        (Collection<?>) ((Document)currentObject).get("$" + this.getArrayCommand(), List.class);
                Collection<?> value = (Collection<?>) v;
                List<Object> updatedList = new ArrayList<>(currentList);
                for(Object object : value) {
                    if(!updatedList.contains(object)) {
                        updatedList.add(object);
                    }
                }
                statsReader.getDatabase().append(statsKey.getKey(k), new Document("$" + this.getArrayCommand(), updatedList));
                break;
        }
    }
}
