package net.kitpvp.stats.reference;

import net.almson.object.ReferenceCountedObject;
import net.almson.object.ResourceLeakDetector;

public class ReferenceCounted extends ReferenceCountedObject {

    public static final ResourceLeakDetector LEAK_DETECTOR = ResourceLeakDetector.
            newResourceLeakDetector(ResourceLeakDetector.Level.LIGHT, 128, -1);

    public ReferenceCounted() {
        super(LEAK_DETECTOR);
    }

    @Override
    protected void destroy() {

    }
}
