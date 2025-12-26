package labs.java_lab_3.operations.insertion;

import labs.java_lab_3.benchmark.ElementProvider;

import java.util.List;

/**
 * Inserts elements at the middle of a list.
 * @param <E> element type
 */
public class MiddleInsertion<E> extends InsertOperation<E> {
    
    public MiddleInsertion(List<E> list, ElementProvider<E> provider) {
        super(list, provider);
    }

    @Override
    public E prepare() {
        insertionIndex = targetList.size() / 2;
        return provider.supply();
    }
}