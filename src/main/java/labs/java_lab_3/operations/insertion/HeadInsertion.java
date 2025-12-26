package labs.java_lab_3.operations.insertion;

import labs.java_lab_3.benchmark.ElementProvider;

import java.util.List;

/**
 * Inserts elements at the beginning of a list.
 * @param <E> element type
 */
public class HeadInsertion<E> extends InsertOperation<E> {
    
    public HeadInsertion(List<E> list, ElementProvider<E> provider) {
        super(list, provider);
    }

    @Override
    public E prepare() {
        insertionIndex = 0;
        return provider.supply();
    }
}