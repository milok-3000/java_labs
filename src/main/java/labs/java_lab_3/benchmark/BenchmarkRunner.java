package labs.java_lab_3.benchmark;

import labs.java_lab_3.operations.deletion.*;
import labs.java_lab_3.operations.insertion.*;
import labs.java_lab_3.operations.maintenance.ClearOperation;
import labs.java_lab_3.operations.retrieval.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Main benchmark runner comparing ArrayList and LinkedList performance.
 */
public class BenchmarkRunner {
    
    private static final int TRIALS = 5;
    private static final String TABLE_FORMAT = "| %-23s | %-10s | %-13s | %10.2f |%n";
    private static final String SEPARATOR = "+-------------------------+------------+---------------+------------+%n";
    
    /**
     * Runs a specific benchmark scenario.
     * @param operationName name of the operation being tested
     * @param iterations number of iterations per test
     * @param arrayOp operation for ArrayList
     * @param linkedOp operation for LinkedList
     * @param <T> element type
     */
    private static <T> void runBenchmark(String operationName, int iterations,
                                         Operation<T> arrayOp, Operation<T> linkedOp) {
        double arrayTotal = 0;
        double linkedTotal = 0;

        for (int trial = 0; trial < TRIALS; trial++) {
            PerformanceTest<T> arrayTest = new PerformanceTest<>(iterations, arrayOp);
            PerformanceTest<T> linkedTest = new PerformanceTest<>(iterations, linkedOp);

            arrayTest.run();
            linkedTest.run();

            arrayTotal += arrayTest.getElapsedMillis();
            linkedTotal += linkedTest.getElapsedMillis();
        }

        double arrayAvg = arrayTotal / TRIALS;
        double linkedAvg = linkedTotal / TRIALS;

        String iterLabel = formatIterations(iterations);
        System.out.printf(TABLE_FORMAT, operationName, iterLabel, "ArrayList", arrayAvg);
        System.out.printf(TABLE_FORMAT, operationName, iterLabel, "LinkedList", linkedAvg);
    }

    /**
     * Formats iteration count for display.
     */
    private static String formatIterations(int count) {
        if (count >= 1000) {
            return (count / 1000) + "k";
        }
        return String.valueOf(count);
    }

    /**
     * Tests insertion at collection end.
     */
    private static void benchmarkTailInsertion() {
        ElementProvider<Integer> provider = () -> 42;
        
        for (int ops = 50000; ops <= 1000000; ops *= 5) {
            List<Integer> arrayList = new ArrayList<>();
            List<Integer> linkedList = new LinkedList<>();
            
            runBenchmark("Tail Insertion",
                    ops,
                    new TailInsertion<>(arrayList, provider),
                    new TailInsertion<>(linkedList, provider));
        }
    }

    /**
     * Tests insertion at collection start.
     */
    private static void benchmarkHeadInsertion() {
        ElementProvider<Integer> provider = () -> 42;
        
        for (int ops = 50000; ops <= 1000000; ops *= 5) {
            List<Integer> arrayList = new ArrayList<>();
            List<Integer> linkedList = new LinkedList<>();
            
            runBenchmark("Head Insertion",
                    ops,
                    new HeadInsertion<>(arrayList, provider),
                    new HeadInsertion<>(linkedList, provider));
        }
    }

    /**
     * Tests insertion at collection middle.
     */
    private static void benchmarkMiddleInsertion() {
        ElementProvider<Integer> provider = () -> 42;
        
        for (int ops = 50000; ops <= 1000000; ops *= 5) {
            List<Integer> arrayList = new ArrayList<>();
            List<Integer> linkedList = new LinkedList<>();
            
            runBenchmark("Middle Insertion",
                    ops,
                    new MiddleInsertion<>(arrayList, provider),
                    new MiddleInsertion<>(linkedList, provider));
        }
    }

    /**
     * Tests deletion from collection start.
     */
    private static void benchmarkHeadDeletion() {
        ElementProvider<String> provider = () -> "X";
        
        for (int ops = 50000; ops <= 1000000; ops *= 5) {
            List<String> arrayList = new ArrayList<>();
            List<String> linkedList = new LinkedList<>();
            
            // Pre-populate lists
            for (int i = 0; i < ops; i++) {
                arrayList.add("X");
                linkedList.add("X");
            }
            
            runBenchmark("Head Deletion",
                    ops,
                    new HeadDeletion<>(arrayList, provider),
                    new HeadDeletion<>(linkedList, provider));
        }
    }

    /**
     * Tests deletion from collection end.
     */
    private static void benchmarkTailDeletion() {
        ElementProvider<String> provider = () -> "X";
        
        for (int ops = 50000; ops <= 1000000; ops *= 5) {
            List<String> arrayList = new ArrayList<>();
            List<String> linkedList = new LinkedList<>();
            
            for (int i = 0; i < ops; i++) {
                arrayList.add("X");
                linkedList.add("X");
            }
            
            runBenchmark("Tail Deletion",
                    ops,
                    new TailDeletion<>(arrayList, provider),
                    new TailDeletion<>(linkedList, provider));
        }
    }

    /**
     * Tests deletion from collection middle.
     */
    private static void benchmarkMiddleDeletion() {
        ElementProvider<String> provider = () -> "X";
        
        for (int ops = 50000; ops <= 1000000; ops *= 5) {
            List<String> arrayList = new ArrayList<>();
            List<String> linkedList = new LinkedList<>();
            
            for (int i = 0; i < ops; i++) {
                arrayList.add("X");
                linkedList.add("X");
            }
            
            runBenchmark("Middle Deletion",
                    ops,
                    new MiddleDeletion<>(arrayList, provider),
                    new MiddleDeletion<>(linkedList, provider));
        }
    }

    /**
     * Tests retrieval from collection start.
     */
    private static void benchmarkHeadRetrieval() {
        ElementProvider<String> provider = () -> "X";
        
        for (int ops = 50000; ops <= 1000000; ops *= 5) {
            List<String> arrayList = new ArrayList<>();
            List<String> linkedList = new LinkedList<>();
            
            for (int i = 0; i < 10; i++) {
                arrayList.add("X");
                linkedList.add("X");
            }
            
            runBenchmark("Head Retrieval",
                    ops,
                    new HeadRetrieval<>(arrayList, provider),
                    new HeadRetrieval<>(linkedList, provider));
        }
    }

    /**
     * Tests retrieval from collection end.
     */
    private static void benchmarkTailRetrieval() {
        ElementProvider<String> provider = () -> "X";
        
        for (int ops = 50000; ops <= 1000000; ops *= 5) {
            List<String> arrayList = new ArrayList<>();
            List<String> linkedList = new LinkedList<>();
            
            for (int i = 0; i < 10; i++) {
                arrayList.add("X");
                linkedList.add("X");
            }
            
            runBenchmark("Tail Retrieval",
                    ops,
                    new TailRetrieval<>(arrayList, provider),
                    new TailRetrieval<>(linkedList, provider));
        }
    }

    /**
     * Tests retrieval from collection middle.
     */
    private static void benchmarkMiddleRetrieval() {
        ElementProvider<String> provider = () -> "X";
        
        for (int ops = 50000; ops <= 1000000; ops *= 5) {
            List<String> arrayList = new ArrayList<>();
            List<String> linkedList = new LinkedList<>();
            
            for (int i = 0; i < 10; i++) {
                arrayList.add("X");
                linkedList.add("X");
            }
            
            runBenchmark("Middle Retrieval",
                    ops,
                    new MiddleRetrieval<>(arrayList, provider),
                    new MiddleRetrieval<>(linkedList, provider));
        }
    }

    /**
     * Tests collection clear operation.
     */
    private static void benchmarkClear() {
        for (int ops = 50000; ops <= 1000000; ops *= 5) {
            List<String> arrayList = new ArrayList<>();
            List<String> linkedList = new LinkedList<>();
            
            for (int i = 0; i < 10; i++) {
                arrayList.add("X");
                linkedList.add("X");
            }
            
            runBenchmark("Clear Operation",
                    ops,
                    new ClearOperation<>(arrayList),
                    new ClearOperation<>(linkedList));
        }
    }

    /**
     * Program entry point.
     */
    public static void main(String[] args) {
        System.out.println("=== ArrayList vs LinkedList Performance Benchmark ===\n");
        
        System.out.printf(SEPARATOR);
        System.out.printf("| %-23s | %-10s | %-13s | %10s |%n",
                "Operation", "Count", "Type", "Time (ms)");
        System.out.printf(SEPARATOR);
        
        benchmarkTailInsertion();
        benchmarkHeadInsertion();
        benchmarkMiddleInsertion();
        benchmarkHeadDeletion();
        benchmarkTailDeletion();
        benchmarkMiddleDeletion();
        benchmarkHeadRetrieval();
        benchmarkTailRetrieval();
        benchmarkMiddleRetrieval();
        benchmarkClear();
        
        System.out.printf(SEPARATOR);
    }
}