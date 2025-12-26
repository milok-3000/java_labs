package labs.java_lab_3.operations.deletion;

import labs.java_lab_3.benchmark.ElementProvider;
import labs.java_lab_3.benchmark.Operation;

import java.util.List;

/**
 * Base class for list deletion operations.
 * @param <E> element type
 */
public abstract class DeletionOperation<E> implements Operation<E> {
    protected final List<E> targetList;
    protected final ElementProvider<E> provider;
    protected int deletionIndex;

    /**
     * Constructs a deletion operation.
     * @param list target list for deletion
     * @param provider element provider
     */
    public DeletionOperation(List<E> list, ElementProvider<E> provider) {
        this.targetList = list;
        this.provider = provider;
    }

    @Override
    public void execute(E element) {
        if (!targetList.isEmpty()) {
            targetList.remove(deletionIndex);
        }
    }

    @Override
    public abstract E prepare();
}