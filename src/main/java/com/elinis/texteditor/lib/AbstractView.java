package com.elinis.texteditor.lib;

import com.elinis.texteditor.lib.i18n.TranslationProvider;

import org.springframework.stereotype.Component;

import de.saxsys.mvvmfx.JavaView;
import de.saxsys.mvvmfx.ViewModel;
import javafx.scene.layout.BorderPane;

/**
 * Base class for {@link JavaView Views}. Sets some general implementation that
 * should be used across every view.
 * 
 * @param <VM>
 *            The assigned ViewModelBase
 */
@Component
public abstract class AbstractView<VM extends AbstractViewModel> extends BorderPane
        implements JavaView<VM> {

    public void initialize() {
        initializeGUI();
    }

    /**
     * Initializes custom GUI components in the implementing class. This is
     * useful if you want to add some special elements to a extended view.
     */
    protected void initializeGUI() {
        // empty because overriding this method is optional
    }

    /**
     * Returns the {@link ViewModel} instance associated with the
     * {@link JavaView View} that calls this method.
     * 
     * @return the view model instance
     */
    protected abstract VM getViewModel();

    /**
     * Returns a translation by utilizing the {@link TranslationProvider} and
     * searching for the key <code>className.resourceKey</code>. The class name
     * will be determined by calling {@link Object#getClass()}.
     * 
     * @param resourceKey
     * 
     * @return
     */
    public String getTranslation(final String resourceKey) {
        return TranslationProvider.getTranslation(getClass(), resourceKey);
    }
}
