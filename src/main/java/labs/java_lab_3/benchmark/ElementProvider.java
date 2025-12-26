package labs.java_lab_3.benchmark;

/**
 * Functional interface for providing elements during benchmark operations.
 * @param <T> type of element to provide
 */
@FunctionalInterface
public interface ElementProvider<T> {
    /**
     * Supplies an element for the operation.
     * @return element instance
     */
    T supply();
}