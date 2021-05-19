package com.elinis.texteditor.lib;

import com.elinis.texteditor.lib.service.ViewService;
import com.elinis.texteditor.lib.style.ViewStyle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.saxsys.mvvmfx.Initialize;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

@Component
public abstract class AbstractViewModel implements ViewModel {

    protected static final String STYLE_CHANGE_MESSAGE = "styleChange";

    private Scene associatedScene;
    private Style currentStyle;
    private final StringProperty title = new SimpleStringProperty();

    private ViewService viewService;

    public AbstractViewModel() {
        subscribe(STYLE_CHANGE_MESSAGE, (key, payload) -> {
            currentStyle = ViewStyle.getNextStyle(currentStyle);
            new JMetro(currentStyle).setScene(associatedScene);
        });
    }

    @Autowired
    public AbstractViewModel(ViewService viewService) {
        this();
        this.viewService = viewService;
    }

    /**
     * This method can be used to initialize something after the view model is
     * injected by the MvvmFx framework.
     */
    @Initialize
    protected void initialize() {
        // empty because overriding this method is optional
    }

    public ViewService getViewService() {
        return viewService;
    }

    public final void requestStyleChange() {
        publish(STYLE_CHANGE_MESSAGE);
    }

    public void setAssociatedScene(final Scene associatedScene) {
        this.associatedScene = associatedScene;
    }

    public Scene getAssociatedScene() {
        return associatedScene;
    }

    public Style getCurrentStyle() {
        return currentStyle;
    }

    public StringProperty titleProperty() {
        return title;
    }
}
