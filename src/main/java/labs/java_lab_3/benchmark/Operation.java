package labs.java_lab_3.benchmark;

/**
 * Represents a single operation that can be benchmarked.
 * @param <T> type of element the operation works with
 */
public interface Operation<T> {
    /**
     * Executes the operation with the provided element.
     * @param element element to use in the operation
     */
    void execute(T element);
    
    /**
     * Prepares necessary state before execution.
     * @return element to be used in execution
     */
    T prepare();
}