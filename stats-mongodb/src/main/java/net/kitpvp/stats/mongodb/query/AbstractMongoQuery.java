package net.kitpvp.stats.mongodb.query;

import com.mongodb.client.MongoCollection;
import net.almson.object.ReferenceCountedObject;
import net.almson.object.ResourceLeakDetector;
import net.kitpvp.stats.async.SyncExecutor;
import net.kitpvp.stats.async.AsyncTask;
import net.kitpvp.stats.mongodb.connection.MongoDBCollection;
import net.kitpvp.stats.reference.ReferenceCounted;
import org.bson.Document;

import java.util.function.Supplier;

public abstract class AbstractMongoQuery extends ReferenceCounted implements AsyncTask {

    public static final boolean MAIN_THREAD_CHECK = System.getProperty("net.kitpvp.stats.checkmain", "true").equals("true");

    private final SyncExecutor syncExecutor;
    private final Supplier<MongoCollection<Document>> mongoCollectionSupplier;

    public AbstractMongoQuery() {
        this.syncExecutor = null;
        this.mongoCollectionSupplier = null;
    }

    public AbstractMongoQuery(Supplier<MongoCollection<Document>> mongoCollectionSupplier) {
        this.syncExecutor = null;
        this.mongoCollectionSupplier = mongoCollectionSupplier;
    }

    public AbstractMongoQuery(MongoDBCollection mongoDBCollection) {
        this.syncExecutor = mongoDBCollection.syncExecutor();
        this.mongoCollectionSupplier = mongoDBCollection::collection;
    }

    public AbstractMongoQuery(MongoCollection<Document> mongoCollection) {
        this.syncExecutor = null;
        this.mongoCollectionSupplier = () -> mongoCollection;
    }

    @Override
    public SyncExecutor syncExecutor() {
        return syncExecutor == null ? SyncExecutor.DIRECT : syncExecutor;
    }

    protected MongoCollection<Document> getMongoCollection() {
        return mongoCollectionSupplier == null ? null : this.mongoCollectionSupplier.get();
    }

    protected void checkForMainThread() {
        if(MAIN_THREAD_CHECK && syncExecutor != null && syncExecutor.isMainThread()) {
            throw new IllegalStateException("Synchronous database action via main server thread");
        }
    }

    @Override
    protected final void destroy() { }
}
