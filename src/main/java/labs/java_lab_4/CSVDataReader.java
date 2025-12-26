package labs.java_lab_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Abstract base class for reading CSV data into collections.
 * Uses standard Java libraries without external dependencies.
 * @param <T> collection type
 * @param <E> element type
 */
public abstract class CSVDataReader<T extends Collection<E>, E> {
    
    private final String resourcePath;
    private final char delimiter;
    private final boolean skipHeader;

    /**
     * Constructs reader with default comma delimiter and header skipping.
     * @param resourcePath path to CSV resource
     */
    public CSVDataReader(String resourcePath) {
        this(resourcePath, ',', true);
    }

    /**
     * Constructs reader with custom delimiter.
     * @param resourcePath path to CSV resource
     * @param delimiter column delimiter character
     */
    public CSVDataReader(String resourcePath, char delimiter) {
        this(resourcePath, delimiter, true);
    }

    /**
     * Constructs reader with full customization.
     * @param resourcePath path to CSV resource
     * @param delimiter column delimiter character
     * @param skipHeader whether to skip first line
     */
    public CSVDataReader(String resourcePath, char delimiter, boolean skipHeader) {
        this.resourcePath = resourcePath;
        this.delimiter = delimiter;
        this.skipHeader = skipHeader;
    }

    /**
     * Reads CSV data into the provided collection.
     * @param collection target collection for parsed objects
     * @throws IOException if reading fails
     */
    public void loadInto(T collection) throws IOException {
        try (InputStream stream = getClass().getResourceAsStream(resourcePath)) {
            if (stream == null) {
                throw new IOException("Resource not found: " + resourcePath);
            }

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(stream, StandardCharsets.UTF_8)
            );
            
            // Skip header if needed
            if (skipHeader) {
                reader.readLine();
            }
            
            // Read all records
            List<String[]> records = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] fields = line.split(String.valueOf(delimiter));
                    records.add(fields);
                }
            }
            
            processRecords(records, collection);
        }
    }

    /**
     * Processes CSV records and populates the collection.
     * @param records list of parsed CSV records
     * @param collection target collection
     * @throws IOException if processing fails
     */
    protected abstract void processRecords(List<String[]> records, T collection) throws IOException;

    /**
     * Parses a single CSV record into an object.
     * @param fields array of field values
     * @return parsed object
     */
    protected abstract E parseRecord(String[] fields);
}