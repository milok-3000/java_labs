package labs.java_lab_3.operations.insertion;

import labs.java_lab_3.benchmark.ElementProvider;
import labs.java_lab_3.benchmark.Operation;

import java.util.List;

/**
 * Base class for list insertion operations.
 * @param <E> element type
 */
public abstract class InsertOperation<E> implements Operation<E> {
    protected final List<E> targetList;
    protected final ElementProvider<E> provider;
    protected int insertionIndex;

    /**
     * Constructs an insertion operation.
     * @param list target list for insertion
     * @param provider element provider
     */
    public InsertOperation(List<E> list, ElementProvider<E> provider) {
        this.targetList = list;
        this.provider = provider;
    }

    @Override
    public void execute(E element) {
        targetList.add(insertionIndex, element);
    }

    @Override
    public abstract E prepare();
}