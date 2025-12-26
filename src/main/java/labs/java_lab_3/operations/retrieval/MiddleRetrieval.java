package labs.java_lab_3.operations.retrieval;

import labs.java_lab_3.benchmark.ElementProvider;

import java.util.List;

/**
 * Retrieves elements from the middle of a list.
 * @param <E> element type
 */
public class MiddleRetrieval<E> extends RetrievalOperation<E> {
    
    public MiddleRetrieval(List<E> list, ElementProvider<E> provider) {
        super(list, provider);
    }

    @Override
    public E prepare() {
        retrievalIndex = targetList.size() / 2;
        return provider.supply();
    }
}