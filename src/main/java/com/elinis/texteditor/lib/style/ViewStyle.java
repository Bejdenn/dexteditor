package com.elinis.texteditor.lib.style;

import jfxtras.styles.jmetro.Style;

/**
 * ViewStyle
 */
public enum ViewStyle {

    LIGHT(Style.LIGHT), DARK(Style.DARK);

    private final Style style;

    private ViewStyle(final Style style) {
        this.style = style;
    }

    public static Style getNextStyle(Style currentStyle) {
        if (currentStyle == null) {
            return LIGHT.style;
        } else {
            switch (currentStyle) {
                case LIGHT:
                    return DARK.style;
                case DARK:
                    return LIGHT.style;
                default:
                    throw new IllegalArgumentException(
                            String.format("Cannot resolve unknown type %s", currentStyle));
            }
        }
    }
}
