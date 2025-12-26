package labs.java_lab_3.operations.maintenance;

import labs.java_lab_3.benchmark.Operation;

import java.util.List;

/**
 * Clears all elements from a list.
 * @param <E> element type
 */
public class ClearOperation<E> implements Operation<E> {
    private final List<E> targetList;

    public ClearOperation(List<E> list) {
        this.targetList = list;
    }

    @Override
    public void execute(E element) {
        targetList.clear();
    }

    @Override
    public E prepare() {
        return null;
    }
}