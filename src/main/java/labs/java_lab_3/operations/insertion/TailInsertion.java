package labs.java_lab_3.operations.insertion;

import labs.java_lab_3.benchmark.ElementProvider;

import java.util.List;

/**
 * Inserts elements at the end of a list.
 * @param <E> element type
 */
public class TailInsertion<E> extends InsertOperation<E> {
    
    public TailInsertion(List<E> list, ElementProvider<E> provider) {
        super(list, provider);
    }

    @Override
    public E prepare() {
        insertionIndex = targetList.isEmpty() ? 0 : targetList.size() - 1;
        return provider.supply();
    }
}