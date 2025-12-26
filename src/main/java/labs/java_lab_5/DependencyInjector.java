package labs.java_lab_5;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Dependency injection container that automatically wires dependencies
 * based on configuration file mappings.
 * @param <T> type of object to inject dependencies into
 */
public class DependencyInjector<T> {
    
    private static final String DEFAULT_CONFIG = "/properties";
    private final String configurationFile;
    private final Map<Class<?>, Object> instanceCache;

    /**
     * Creates injector with custom configuration file.
     * @param configPath path to properties configuration
     */
    public DependencyInjector(String configPath) {
        this.configurationFile = configPath;
        this.instanceCache = new HashMap<>();
    }

    /**
     * Creates injector with default configuration.
     */
    public DependencyInjector() {
        this(DEFAULT_CONFIG);
    }

    /**
     * Injects dependencies into all annotated fields of the target object.
     * @param target object to inject dependencies into
     * @return the same object with injected dependencies
     * @throws InjectionException if injection fails
     */
    public T inject(T target) throws InjectionException {
        if (target == null) {
            throw new InjectionException("Cannot inject into null object");
        }

        Class<?> targetClass = target.getClass();
        Properties configuration = loadConfiguration();

        for (Field field : targetClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(InjectDependency.class)) {
                injectField(target, field, configuration);
            }
        }

        return target;
    }

    /**
     * Injects a single field with its dependency.
     */
    private void injectField(T target, Field field, Properties config) throws InjectionException {
        Class<?> fieldType = field.getType();
        
        if (!fieldType.isInterface()) {
            throw new InjectionException(
                "Field " + field.getName() + " must be an interface type"
            );
        }

        String implementationClassName = config.getProperty(fieldType.getName());
        
        if (implementationClassName == null) {
            throw new InjectionException(
                "No implementation configured for: " + fieldType.getName()
            );
        }

        Object implementation = createOrGetInstance(implementationClassName, fieldType);
        setFieldValue(target, field, implementation);
    }

    /**
     * Creates new instance or retrieves cached one.
     */
    private Object createOrGetInstance(String className, Class<?> expectedType) throws InjectionException {
        try {
            Class<?> implementationClass = Class.forName(className);
            
            if (!expectedType.isAssignableFrom(implementationClass)) {
                throw new InjectionException(
                    className + " does not implement " + expectedType.getName()
                );
            }

            // Use cache for singleton behavior
            return instanceCache.computeIfAbsent(implementationClass, clazz -> {
                try {
                    return clazz.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("Failed to instantiate " + className, e);
                }
            });
            
        } catch (ClassNotFoundException e) {
            throw new InjectionException("Implementation class not found: " + className, e);
        }
    }

    /**
     * Sets field value using reflection.
     */
    private void setFieldValue(T target, Field field, Object value) throws InjectionException {
        boolean wasAccessible = field.canAccess(target);
        
        try {
            field.setAccessible(true);
            field.set(target, value);
        } catch (IllegalAccessException e) {
            throw new InjectionException(
                "Cannot access field: " + field.getName(), e
            );
        } finally {
            field.setAccessible(wasAccessible);
        }
    }

    /**
     * Loads configuration from properties file.
     */
    private Properties loadConfiguration() throws InjectionException {
        Properties props = new Properties();
        
        try (InputStream stream = getClass().getResourceAsStream(configurationFile)) {
            if (stream == null) {
                throw new FileNotFoundException(
                    "Configuration file not found: " + configurationFile
                );
            }
            props.load(stream);
        } catch (Exception e) {
            throw new InjectionException(
                "Failed to load configuration from: " + configurationFile, e
            );
        }
        
        return props;
    }

    /**
     * Retrieves current configuration path.
     * @return configuration file path
     */
    public String getConfigurationFile() {
        return configurationFile;
    }

    /**
     * Clears instance cache.
     */
    public void clearCache() {
        instanceCache.clear();
    }
}