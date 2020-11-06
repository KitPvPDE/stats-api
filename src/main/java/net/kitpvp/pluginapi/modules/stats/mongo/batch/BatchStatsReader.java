package net.kitpvp.pluginapi.modules.stats.mongo.batch;

import lombok.Getter;
import lombok.Setter;
import net.kitpvp.pluginapi.modules.stats.StatsReader;
import org.bson.Document;


class BatchStatsReader implements StatsReader {

    @Getter @Setter
    private Document database;

    @Override
    public Object getSource() {
        return this.database;
    }

    @Override
    public <V> V find(String key, V def) {
        return this.getInternal(key, def);
    }

    private <T> T getInternal(String key, T defaultValue) {
        return find(key, this.database, defaultValue);
    }

    @SuppressWarnings("unchecked")
    private <T> T find(String key, Document document, T defaultValue) {
        if(!key.contains(".")){
            try{
                T result = (T) document.get(key);

                return result != null ? result : defaultValue;
            }catch(Throwable t){
                return defaultValue;
            }
        }else{
            String entry = key.substring(0, key.indexOf("."));
            String path = key.substring(entry.length() + 1);
            if(!document.containsKey(entry)){
                return defaultValue;
            }
            Document sub = document.get(entry, Document.class);

            return find(path, sub, defaultValue);
        }
    }

}