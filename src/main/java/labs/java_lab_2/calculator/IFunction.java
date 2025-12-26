package labs.java_lab_2.calculator;

import java.util.List;

/**
 * Interface for defining custom calculator functions
 */
public interface IFunction {
    /**
     * Computes function value for given arguments
     * @param args list of function arguments
     * @return function application result
     */
    double apply(List<Double> args);
}