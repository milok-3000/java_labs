package labs.java_lab_3.benchmark;

/**
 * Measures execution time of collection operations.
 * @param <T> element type being tested
 */
public class PerformanceTest<T> {
    private final int iterations;
    private final Operation<T> operation;
    private long elapsedNanos;

    /**
     * Constructs a performance test.
     * @param iterations number of times to execute the operation
     * @param operation operation to benchmark
     */
    public PerformanceTest(int iterations, Operation<T> operation) {
        this.iterations = iterations;
        this.operation = operation;
        this.elapsedNanos = 0;
    }

    /**
     * Runs the benchmark and measures time.
     */
    public void run() {
        elapsedNanos = 0;
        
        for (int i = 0; i < iterations; i++) {
            T element = operation.prepare();
            
            long startTime = System.nanoTime();
            operation.execute(element);
            long endTime = System.nanoTime();
            
            elapsedNanos += (endTime - startTime);
        }
    }

    /**
     * Gets elapsed time in nanoseconds.
     * @return total elapsed time
     */
    public long getElapsedNanos() {
        return elapsedNanos;
    }

    /**
     * Gets elapsed time in milliseconds.
     * @return elapsed time in milliseconds
     */
    public double getElapsedMillis() {
        return elapsedNanos / 1_000_000.0;
    }
}