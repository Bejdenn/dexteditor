package com.elinis.texteditor.lib.filemanagement;

import java.io.File;
import java.util.Optional;
import java.util.Set;

import com.elinis.texteditor.lib.AbstractViewModel;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Interface that describes how an special {@link FileChooser} implementation
 * should behave. The idea here is to provide a helper API to extend the
 * functionality/calling mechanism of the {@code FileChooser}. This interface
 * holds a backing {@code FileChooser} instance it is not possible to inherit
 * from {@code FileChooser} class.
 */
public interface SpecialFileChooser {

    /**
     * Registers the window where the file chooser was called from. This is
     * necessary to block any interaction in the owner window while the chooser
     * is active.
     * <p>
     * This is a building action that returns an {@link SpecialFileChooser}
     * instance for further preparation.
     * 
     * @param <VM>
     *            the view model class
     * @param owner
     *            the view model that calls the file chooser
     * 
     * @return the file chooser
     */
    <VM extends AbstractViewModel> SpecialFileChooser owner(VM owner);

    /**
     * Adds {@link ExtensionFilter ExtensionFilters} to the backed
     * {@link FileChooser}.
     * <p>
     * This is a building action that returns an {@link SpecialFileChooser}
     * instance for further preparation.
     * 
     * @param filterTypes
     *            the filter types that should be added
     * 
     * @return the file chooser
     */
    SpecialFileChooser addExtensionFilters(Set<ExtensionFilter> filterTypes);

    /**
     * Opens a {@link FileChooser} and returns an {@link Optional} with the
     * {@link File}, if any was selected. Otherwise (or if an exception occurs)
     * the caller will get an empty Optional.
     * <p>
     * This is a terminating action.
     * 
     * @return an optional with the chosen file or an empty optional
     */
    Optional<File> openFile();

    /**
     * Opens a {@link FileChooser} and returns an {@link Optional} with the
     * newly created {@link File} or an existing one.
     * <p>
     * <b> NOTE</b>: This process only creates or loads an file. Other means
     * (like writing/appending new text content to the file) have to be done
     * separately.
     * <p>
     * This is a terminating action.
     * 
     * @return the file
     */
    Optional<File> saveFile();
}
