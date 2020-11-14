package net.kitpvp.stats.query.update;

import org.bson.Document;

@FunctionalInterface
public interface Update<T> {

    Update<T> append(T t);

    default Document document(Document document, String sub) {
        Document operatorDocument = document.get(sub, Document.class);
        if(operatorDocument == null) {
            document.put(sub, operatorDocument = new Document());
        }
        return operatorDocument;
    }
}
