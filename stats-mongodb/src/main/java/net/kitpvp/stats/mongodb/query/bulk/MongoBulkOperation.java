package net.kitpvp.stats.mongodb.query.bulk;

import com.mongodb.client.model.WriteModel;
import org.bson.Document;

public interface MongoBulkOperation {

    WriteModel<? extends Document> model();
}
