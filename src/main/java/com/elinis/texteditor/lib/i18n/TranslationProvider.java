package com.elinis.texteditor.lib.i18n;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TranslationProvider {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String bundlePath = "com.elinis.texteditor.i18n.TranslationResources";
    private static final Locale locale = new Locale("de");

    private TranslationProvider() {
        // only static access
    }

    /**
     * Returns the translation as a string by searching for the given resource
     * key. The name space is defined by the given {@link Class}.
     * 
     * @param clazz
     *            a class instance, usually the one requesting a translation
     * @param resourceKey
     *            the key to search for
     * 
     * @return a translation
     */
    public static String getTranslation(Class<?> clazz, String resourceKey) {
        return findTranslationInBundle(clazz.getSimpleName(), resourceKey);
    }

    /**
     * Returns the translation as a string by searching for the given resource
     * key. The name space is defined by the given name space.
     * 
     * @param namespace
     * @param resourceKey
     *            the key to search for
     * 
     * @return a translation
     */
    public static String getTranslation(String namespace, String resourceKey) {
        return findTranslationInBundle(namespace, resourceKey);
    }

    private static String findTranslationInBundle(final String namespace,
            final String resourceKey) {
        Objects.requireNonNull(namespace, "Namespace cannot be null");
        Objects.requireNonNull(resourceKey, "ResourceKey cannot be null");

        final StringBuilder builder =
                new StringBuilder().append(namespace).append(".").append(resourceKey);

        try {
            return ResourceBundle.getBundle(bundlePath, locale).getString(builder.toString());
        } catch (final MissingResourceException ex) {
            LOGGER.error("No translation found for key: " + builder.toString());
            return "[" + builder.toString() + "]";
        }
    }
}
