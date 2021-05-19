package com.elinis.texteditor.helper;

import java.lang.reflect.Field;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class that enable reflective access to type properties.
 */
public class ReflectionHelper {

    private static final Logger LOGGER = LogManager.getLogger();

    private ReflectionHelper() {
        // only static access is allowed
    }

    /**
     * Accesses the instance and sets the field (which will be searched for by the
     * given field name) to the new value.
     * 
     * @param instance  the instance to access
     * @param fieldName the field name of the field
     * @param value     the new value
     */
    public static void setField(Object instance, String fieldName, Object value) {
        try {
            final Field fieldToAccess = instance.getClass().getDeclaredField(fieldName);
            fieldToAccess.setAccessible(true);
            fieldToAccess.set(instance, value);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            LOGGER.error("Error when trying to access field {} of type {}.", fieldName,
                    instance.getClass().getSimpleName());
        }
    }
}
