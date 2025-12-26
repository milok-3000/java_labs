package labs.java_lab_3.operations.retrieval;

import labs.java_lab_3.benchmark.ElementProvider;

import java.util.List;

/**
 * Retrieves elements from the end of a list.
 * @param <E> element type
 */
public class TailRetrieval<E> extends RetrievalOperation<E> {
    
    public TailRetrieval(List<E> list, ElementProvider<E> provider) {
        super(list, provider);
    }

    @Override
    public E prepare() {
        retrievalIndex = targetList.isEmpty() ? 0 : targetList.size() - 1;
        return provider.supply();
    }
}