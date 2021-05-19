package com.elinis.texteditor.lib.filemanagement;

import java.io.File;
import java.util.Optional;
import java.util.Set;

import javax.annotation.PostConstruct;

import com.elinis.texteditor.lib.AbstractViewModel;

import org.springframework.stereotype.Component;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

/**
 * A customizable file chooser that offers some file managing actions.
 */
@Component
public class SpecialFileChooserImpl implements SpecialFileChooser {

    private static final FileChooser FILE_CHOOSER = new FileChooser();

    private Window owner;

    @PostConstruct
    private void setDefaultExtensionFilters() {
        addExtensionFilters(Set.of(DefaultExtensionFilter.ALL.getFilter(),
                DefaultExtensionFilter.TEXT.getFilter()));
    }

    @Override
    public <VM extends AbstractViewModel> SpecialFileChooser owner(VM owner) {
        this.owner = owner.getAssociatedScene().getWindow();
        return this;
    }

    @Override
    public SpecialFileChooserImpl addExtensionFilters(Set<ExtensionFilter> filterTypes) {
        filterTypes.stream().forEachOrdered(FILE_CHOOSER.getExtensionFilters()::add);
        return this;
    }

    @Override
    public Optional<File> openFile() {
        return Optional.ofNullable(FILE_CHOOSER.showOpenDialog(owner));
    }

    @Override
    public Optional<File> saveFile() {
        return Optional.ofNullable(FILE_CHOOSER.showSaveDialog(owner));
    }
}
