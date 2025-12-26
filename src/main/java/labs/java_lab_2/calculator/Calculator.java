package labs.java_lab_2.calculator;

import java.util.*;

/**
 * Expression parser and evaluator with support for custom functions and variables.
 */
public class Calculator {
    private final Map<String, IFunction> functions;
    private final Map<String, Double> variables;

    /**
     * Initializes calculator with built-in functions
     */
    public Calculator() {
        this.functions = new HashMap<>();
        this.variables = new HashMap<>();
        initializeDefaultFunctions();
    }

    /**
     * Registers a new function in the calculator
     * @param functionName function identifier
     * @param implementation function logic
     */
    public void addFunction(String functionName, IFunction implementation) {
        if (functions.containsKey(functionName)) {
            throw new IllegalArgumentException("Function '" + functionName + "' already exists");
        }
        functions.put(functionName, implementation);
    }

    /**
     * Declares a variable with given value
     * @param varName variable identifier
     * @param val variable value
     */
    public void addVariable(String varName, double val) {
        if (variables.containsKey(varName)) {
            throw new IllegalArgumentException("Variable '" + varName + "' already declared");
        }
        variables.put(varName, val);
    }

    /**
     * Updates value of existing variable
     * @param varName variable identifier
     * @param newVal new value
     */
    public void editVariable(String varName, double newVal) {
        if (!variables.containsKey(varName)) {
            throw new IllegalArgumentException("Variable '" + varName + "' not found");
        }
        variables.put(varName, newVal);
    }

    /**
     * Retrieves variable value
     * @param varName variable identifier
     * @return variable value
     */
    public double getVariable(String varName) {
        if (!variables.containsKey(varName)) {
            throw new IllegalArgumentException("Variable '" + varName + "' is undefined");
        }
        return variables.get(varName);
    }

    /**
     * Evaluates mathematical expression
     * @param input expression string
     * @return computation result
     */
    public double processExpression(String input) {
        TokenStream tokenStream = tokenize(input);
        return evaluateExpression(tokenStream);
    }

    /**
     * Initializes built-in mathematical functions
     */
    private void initializeDefaultFunctions() {
        // Minimum value
        functions.put("min", arguments -> {
            if (arguments.isEmpty()) {
                throw new IllegalArgumentException("Function 'min' requires at least one argument");
            }
            return arguments.stream().min(Double::compare).orElseThrow();
        });

        // Maximum value
        functions.put("max", arguments -> {
            if (arguments.isEmpty()) {
                throw new IllegalArgumentException("Function 'max' requires at least one argument");
            }
            return arguments.stream().max(Double::compare).orElseThrow();
        });

        // Power function
        functions.put("pow", arguments -> {
            if (arguments.size() != 2) {
                throw new IllegalArgumentException("Function 'pow' requires exactly 2 arguments");
            }
            return Math.pow(arguments.get(0), arguments.get(1));
        });

        // Random number generator
        functions.put("rand", arguments -> {
            double lowerBound = 0;
            double upperBound = 1000;
            
            if (arguments.size() > 2) {
                throw new IllegalArgumentException("Function 'rand' accepts maximum 2 arguments");
            }
            if (arguments.size() == 2) {
                lowerBound = arguments.get(0);
            }
            if (!arguments.isEmpty()) {
                upperBound = arguments.get(arguments.size() - 1);
            }
            
            return lowerBound + Math.random() * (upperBound - lowerBound);
        });

        // Arithmetic mean
        functions.put("mean", arguments -> {
            if (arguments.isEmpty()) {
                throw new IllegalArgumentException("Function 'mean' requires at least one argument");
            }
            return arguments.stream().mapToDouble(Double::doubleValue).average().orElseThrow();
        });
    }

    /**
     * Prompts user for variable value
     */
    private double requestVariableValue(String varName) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter value for '" + varName + "': ");
        String input = scanner.nextLine();
        return processExpression(input);
    }

    /**
     * Tokenizes input string
     */
    private TokenStream tokenize(String input) {
        List<Token> tokens = new ArrayList<>();
        int idx = 0;
        int openBrackets = 0;

        while (idx < input.length()) {
            char current = input.charAt(idx);

            // Skip whitespace
            if (Character.isWhitespace(current)) {
                idx++;
                continue;
            }

            // Operators and delimiters
            if (current == '(') {
                tokens.add(new Token(TokenKind.OPEN_PAREN, "("));
                openBrackets++;
                idx++;
            } else if (current == ')') {
                tokens.add(new Token(TokenKind.CLOSE_PAREN, ")"));
                openBrackets--;
                idx++;
            } else if (current == '+') {
                tokens.add(new Token(TokenKind.PLUS, "+"));
                idx++;
            } else if (current == '-') {
                tokens.add(new Token(TokenKind.MINUS, "-"));
                idx++;
            } else if (current == '*') {
                tokens.add(new Token(TokenKind.MULTIPLY, "*"));
                idx++;
            } else if (current == '/' || current == ':') {
                tokens.add(new Token(TokenKind.DIVIDE, String.valueOf(current)));
                idx++;
            } else if (current == ',') {
                tokens.add(new Token(TokenKind.SEPARATOR, ","));
                idx++;
            } else if (Character.isDigit(current)) {
                // Parse number
                StringBuilder numberBuilder = new StringBuilder();
                while (idx < input.length() && 
                       (Character.isDigit(input.charAt(idx)) || 
                        input.charAt(idx) == '.' || 
                        input.charAt(idx) == ',')) {
                    numberBuilder.append(input.charAt(idx));
                    idx++;
                }
                
                String numStr = numberBuilder.toString().replace(',', '.');
                // Check for trailing separator
                if (numStr.endsWith(".")) {
                    numStr = numStr.substring(0, numStr.length() - 1);
                    idx--;
                }
                tokens.add(new Token(TokenKind.NUMBER, numStr));
            } else if (Character.isLetter(current)) {
                // Parse function or variable name
                StringBuilder nameBuilder = new StringBuilder();
                while (idx < input.length() && 
                       (Character.isLetterOrDigit(input.charAt(idx)) || 
                        input.charAt(idx) == '_')) {
                    nameBuilder.append(input.charAt(idx));
                    idx++;
                }
                
                String identifier = nameBuilder.toString();
                if (functions.containsKey(identifier)) {
                    tokens.add(new Token(TokenKind.FUNCTION, identifier));
                } else {
                    // Request value if variable is undefined
                    if (!variables.containsKey(identifier)) {
                        double value = requestVariableValue(identifier);
                        variables.put(identifier, value);
                    }
                    tokens.add(new Token(TokenKind.VARIABLE, identifier));
                }
            } else {
                throw new IllegalArgumentException("Invalid character: '" + current + "'");
            }
        }

        if (openBrackets != 0) {
            throw new IllegalArgumentException("Unbalanced parentheses");
        }

        tokens.add(new Token(TokenKind.END, ""));
        return new TokenStream(tokens);
    }

    /**
     * Starts expression evaluation
     */
    private double evaluateExpression(TokenStream stream) {
        Token current = stream.peek();
        if (current.kind == TokenKind.END) {
            return 0;
        }
        return parseAddSubtract(stream);
    }

    /**
     * Handles addition and subtraction
     */
    private double parseAddSubtract(TokenStream stream) {
        double result = parseMultiplyDivide(stream);

        while (true) {
            Token token = stream.next();
            
            if (token.kind == TokenKind.PLUS) {
                result += parseMultiplyDivide(stream);
            } else if (token.kind == TokenKind.MINUS) {
                result -= parseMultiplyDivide(stream);
            } else if (token.kind == TokenKind.END || 
                       token.kind == TokenKind.CLOSE_PAREN || 
                       token.kind == TokenKind.SEPARATOR) {
                stream.pushBack();
                return result;
            } else {
                throw new IllegalArgumentException("Syntax error at position " + stream.position());
            }
        }
    }

    /**
     * Handles multiplication and division
     */
    private double parseMultiplyDivide(TokenStream stream) {
        double result = parseUnaryAndAtom(stream);

        while (true) {
            Token token = stream.next();
            
            if (token.kind == TokenKind.MULTIPLY) {
                result *= parseUnaryAndAtom(stream);
            } else if (token.kind == TokenKind.DIVIDE) {
                double divisor = parseUnaryAndAtom(stream);
                if (divisor == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                result /= divisor;
            } else if (token.kind == TokenKind.END || 
                       token.kind == TokenKind.CLOSE_PAREN || 
                       token.kind == TokenKind.SEPARATOR ||
                       token.kind == TokenKind.PLUS ||
                       token.kind == TokenKind.MINUS) {
                stream.pushBack();
                return result;
            } else {
                throw new IllegalArgumentException("Syntax error at position " + stream.position());
            }
        }
    }

    /**
     * Handles atomic expressions and unary operators
     */
    private double parseUnaryAndAtom(TokenStream stream) {
        Token token = stream.next();

        switch (token.kind) {
            case FUNCTION:
                stream.pushBack();
                return evaluateFunction(stream);
                
            case VARIABLE:
                return variables.get(token.content);
                
            case MINUS:
                return -parseUnaryAndAtom(stream);
                
            case NUMBER:
                return Double.parseDouble(token.content);
                
            case OPEN_PAREN:
                double value = parseAddSubtract(stream);
                Token closingBracket = stream.next();
                if (closingBracket.kind != TokenKind.CLOSE_PAREN) {
                    throw new IllegalArgumentException(
                        "Expected ')' at position " + stream.position() + 
                        ", found: " + closingBracket.content
                    );
                }
                return value;
                
            default:
                throw new IllegalArgumentException(
                    "Unexpected token '" + token.content + 
                    "' at position " + stream.position()
                );
        }
    }

    /**
     * Evaluates function call
     */
    private double evaluateFunction(TokenStream stream) {
        Token funcToken = stream.next();
        String funcName = funcToken.content;
        
        Token openParen = stream.next();
        if (openParen.kind != TokenKind.OPEN_PAREN) {
            throw new IllegalArgumentException(
                "Expected '(' after function name, found: " + openParen.content
            );
        }

        List<Double> arguments = new ArrayList<>();
        Token lookahead = stream.peek();
        
        if (lookahead.kind != TokenKind.CLOSE_PAREN) {
            do {
                arguments.add(parseAddSubtract(stream));
                Token separator = stream.next();
                
                if (separator.kind == TokenKind.CLOSE_PAREN) {
                    break;
                }
                
                if (separator.kind != TokenKind.SEPARATOR) {
                    throw new IllegalArgumentException(
                        "Expected separator ',' or ')' in function call"
                    );
                }
            } while (true);
        } else {
            stream.next(); // consume ')'
        }

        return functions.get(funcName).apply(arguments);
    }

    @Override
    public String toString() {
        return String.format(
            "Calculator [Functions: %s, Token types: %d]",
            functions.keySet(),
            TokenKind.values().length
        );
    }

    /**
     * Token types
     */
    private enum TokenKind {
        OPEN_PAREN, CLOSE_PAREN,
        PLUS, MINUS, MULTIPLY, DIVIDE,
        NUMBER, SEPARATOR,
        FUNCTION, VARIABLE,
        END
    }

    /**
     * Token representation
     */
    private static class Token {
        final TokenKind kind;
        final String content;

        Token(TokenKind kind, String content) {
            this.kind = kind;
            this.content = content;
        }
    }

    /**
     * Token stream for processing
     */
    private static class TokenStream {
        private final List<Token> tokens;
        private int currentPosition;

        TokenStream(List<Token> tokens) {
            this.tokens = tokens;
            this.currentPosition = 0;
        }

        Token next() {
            return tokens.get(currentPosition++);
        }

        Token peek() {
            return tokens.get(currentPosition);
        }

        void pushBack() {
            currentPosition--;
        }

        int position() {
            return currentPosition;
        }
    }
}