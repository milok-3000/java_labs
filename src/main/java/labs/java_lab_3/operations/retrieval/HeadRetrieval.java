package labs.java_lab_3.operations.retrieval;

import labs.java_lab_3.benchmark.ElementProvider;

import java.util.List;

/**
 * Retrieves elements from the beginning of a list.
 * @param <E> element type
 */
public class HeadRetrieval<E> extends RetrievalOperation<E> {
    
    public HeadRetrieval(List<E> list, ElementProvider<E> provider) {
        super(list, provider);
    }

    @Override
    public E prepare() {
        retrievalIndex = 0;
        return provider.supply();
    }
}