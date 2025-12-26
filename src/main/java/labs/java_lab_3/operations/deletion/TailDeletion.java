package labs.java_lab_3.operations.deletion;

import labs.java_lab_3.benchmark.ElementProvider;

import java.util.List;

/**
 * Removes elements from the end of a list.
 * @param <E> element type
 */
public class TailDeletion<E> extends DeletionOperation<E> {
    
    public TailDeletion(List<E> list, ElementProvider<E> provider) {
        super(list, provider);
    }

    @Override
    public E prepare() {
        deletionIndex = targetList.isEmpty() ? 0 : targetList.size() - 1;
        return provider.supply();
    }
}