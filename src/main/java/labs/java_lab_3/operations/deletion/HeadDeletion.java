package labs.java_lab_3.operations.deletion;

import labs.java_lab_3.benchmark.ElementProvider;

import java.util.List;

/**
 * Removes elements from the beginning of a list.
 * @param <E> element type
 */
public class HeadDeletion<E> extends DeletionOperation<E> {
    
    public HeadDeletion(List<E> list, ElementProvider<E> provider) {
        super(list, provider);
    }

    @Override
    public E prepare() {
        deletionIndex = 0;
        return provider.supply();
    }
}