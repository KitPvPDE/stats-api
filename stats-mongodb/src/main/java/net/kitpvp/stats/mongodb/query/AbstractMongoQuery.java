package net.kitpvp.stats.mongodb.query;

import net.almson.object.ReferenceCountedObject;
import net.almson.object.ResourceLeakDetector;

public abstract class AbstractMongoQuery extends ReferenceCountedObject {

    public static final ResourceLeakDetector LEAK_DETECTOR = ResourceLeakDetector.
            newResourceLeakDetector(ResourceLeakDetector.Level.LIGHT, 128, -1);

    public AbstractMongoQuery() {
        super(LEAK_DETECTOR);
    }

    @Override
    protected final void destroy() { }
}
