package com.elinis.texteditor.lib.component;

import java.util.function.UnaryOperator;

import de.saxsys.mvvmfx.utils.commands.Command;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination.Modifier;

/**
 * Multi-step builder to construct a {@link MenuBar}.
 */
public class MenuBarBuilder {

    private final MenuBar menuBar = new MenuBar();
    private UnaryOperator<String> translationProvider;

    private MenuBarBuilder() {
        // only static access to builder
    }

    public static MenuBarBuilder newMenuBar() {
        return new MenuBarBuilder();
    }

    public MenuBarBuilder withTranslator(final UnaryOperator<String> translationProvider) {
        this.translationProvider = translationProvider;
        return this;
    }

    public MenuBuilder newMenu(final String identifier) {
        return new MenuBuilder(identifier);
    }

    public MenuBar buildMenuBar() {
        return menuBar;
    }

    /**
     * Sub-builder to construct a single {@link Menu} for a {@link MenuBar}.
     */
    public class MenuBuilder {

        private final Menu menu;

        private MenuBuilder(final String identifier) {
            menu = new Menu();
            menu.setId(identifier);
            menu.setText(MenuBarBuilder.this.translationProvider.apply(menu.getId()));
        }

        public MenuItemBuilder newMenuItem(final String identifier) {
            return new MenuItemBuilder(identifier);
        }

        public MenuBarBuilder buildMenu() {
            MenuBarBuilder.this.menuBar.getMenus().add(menu);
            return MenuBarBuilder.this;
        }

        /**
         * Sub-builder to construct a single {@link MenuItem} for a {@link Menu}.
         */
        public class MenuItemBuilder {

            private MenuItem menuItem;
            private final String identifier;

            private MenuItemBuilder(final String identifier) {
                this.identifier = identifier;
                ofType(MenuItemType.DEFAULT);
            }

            public MenuItemBuilder ofType(final MenuItemType type) {
                menuItem = MenuItemFactory.getFor(type);
                setIdentification(identifier);
                return this;
            }

            private MenuItemBuilder setIdentification(final String identifier) {
                menuItem.setId(identifier);
                menuItem.setText(MenuBarBuilder.this.translationProvider.apply(identifier));
                return this;
            }

            public MenuItemBuilder withKeyCombination(final Modifier keyCombination,
                    final KeyCode keyCode) {
                menuItem.setAccelerator(new KeyCodeCombination(keyCode, keyCombination));
                return this;
            }

            public MenuItemBuilder withCommand(final Command command) {
                menuItem.setOnAction(e -> command.execute());
                return this;
            }

            public MenuBuilder buildMenuItem() {
                MenuBuilder.this.menu.getItems().add(menuItem);
                return MenuBuilder.this;
            }
        }
    }
}
