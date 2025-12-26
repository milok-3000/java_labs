package labs.java_lab_3.operations.deletion;

import labs.java_lab_3.benchmark.ElementProvider;

import java.util.List;

/**
 * Removes elements from the middle of a list.
 * @param <E> element type
 */
public class MiddleDeletion<E> extends DeletionOperation<E> {
    
    public MiddleDeletion(List<E> list, ElementProvider<E> provider) {
        super(list, provider);
    }

    @Override
    public E prepare() {
        deletionIndex = targetList.size() / 2;
        return provider.supply();
    }
}