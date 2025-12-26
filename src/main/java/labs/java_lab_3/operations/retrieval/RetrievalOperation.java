package labs.java_lab_3.operations.retrieval;

import labs.java_lab_3.benchmark.ElementProvider;
import labs.java_lab_3.benchmark.Operation;

import java.util.List;

/**
 * Base class for list retrieval operations.
 * @param <E> element type
 */
public abstract class RetrievalOperation<E> implements Operation<E> {
    protected final List<E> targetList;
    protected final ElementProvider<E> provider;
    protected int retrievalIndex;

    /**
     * Constructs a retrieval operation.
     * @param list target list for retrieval
     * @param provider element provider
     */
    public RetrievalOperation(List<E> list, ElementProvider<E> provider) {
        this.targetList = list;
        this.provider = provider;
    }

    @Override
    public void execute(E element) {
        if (!targetList.isEmpty()) {
            element = targetList.get(retrievalIndex);
        }
    }

    @Override
    public abstract E prepare();
}