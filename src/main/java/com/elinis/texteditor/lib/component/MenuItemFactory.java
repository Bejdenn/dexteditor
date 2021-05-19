package com.elinis.texteditor.lib.component;

import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;

class MenuItemFactory {

    private MenuItemFactory() {
        // only static access is allowed
    }

    public static MenuItem getFor(MenuItemType type) {
        switch (type) {
            case DEFAULT:
                return new MenuItem();
            case RADIO:
                return new RadioMenuItem();
            default:
                throw new IllegalArgumentException(String
                        .format("Cannot return a menu item instance for unknown type %s", type));
        }
    }
}
