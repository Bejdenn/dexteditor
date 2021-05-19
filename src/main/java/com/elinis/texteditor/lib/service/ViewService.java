package com.elinis.texteditor.lib.service;

import com.elinis.texteditor.lib.AbstractView;
import com.elinis.texteditor.lib.AbstractViewModel;
import com.elinis.texteditor.lib.filemanagement.SpecialFileChooser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * Service that manages views in the application.
 */
@Service
public class ViewService {

    private final SpecialFileChooser extendedFileChooser;

    @Autowired
    public ViewService(final SpecialFileChooser extendedFileChooser) {
        this.extendedFileChooser = extendedFileChooser;
    }

    /**
     * Prepares the {@link AbstractView View} and the {@link AbstractViewModel
     * ViewModel} and shows a {@link Stage} afterwards, holding both components.
     * 
     * @param <V>
     *            the view class
     * @param <VM>
     *            the view model class
     * @param viewClass
     *            the view that will be displayed in a new stage
     */
    public <V extends AbstractView<? extends VM>, VM extends AbstractViewModel> void prepareView(
            final Class<V> viewClass) {
        final ViewTuple<V, VM> viewTuple = FluentViewLoader.javaView(viewClass).load();
        final Region view = (Region) viewTuple.getView();
        final VM viewModel = viewTuple.getViewModel();

        final Stage stage = new Stage();
        final Scene scene = new Scene(view);

        viewModel.setAssociatedScene(scene);
        viewModel.requestStyleChange();

        stage.titleProperty().bind(viewModel.titleProperty());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @deprecated The current implementation of opening the file chooser is not
     *                 very fluent and is not a very good API. Search for
     *                 another way, to have a more builder-like way.
     */
    @Deprecated
    public SpecialFileChooser getFileChooser() {
        return extendedFileChooser;
    }
}
