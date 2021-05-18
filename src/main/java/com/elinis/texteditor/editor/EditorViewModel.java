package com.elinis.texteditor.frontend.editor;

import java.io.File;
import java.util.Optional;

import com.elinis.core.commons.util.FileUtils;
import com.elinis.core.frontend.view.AbstractViewModel;
import com.elinis.core.frontend.view.component.messagebox.MessageBox;
import com.elinis.core.frontend.view.component.messagebox.MessageButtonType;
import com.elinis.core.frontend.view.component.messagebox.MessageType;
import com.elinis.core.frontend.view.interaction.ViewModelAction;

import org.springframework.stereotype.Component;

import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * {@link AbstractViewModel ViewModel} for {@link EditorView}.
 */
@Component
public class EditorViewModel extends AbstractViewModel {

    private final StringProperty textAreaContent = new SimpleStringProperty();

    private final Command startNewFileCommand =
            new DelegateCommand(() -> new ViewModelAction(this::startNewFile));
    private final Command openFileCommand =
            new DelegateCommand(() -> new ViewModelAction(this::openFile));
    private final Command saveFileCommand =
            new DelegateCommand(() -> new ViewModelAction(this::saveFile));
    private final Command saveFileAsCommand =
            new DelegateCommand(() -> new ViewModelAction(this::saveFileAs));
    private final Command printFileCommand =
            new DelegateCommand(() -> new ViewModelAction(this::printFile));

    private final Command toggleDarkModeCommand =
            new DelegateCommand(() -> new ViewModelAction(this::toggleDarkMode));

    private final ObjectProperty<File> currentFile = new SimpleObjectProperty<>();

    @Override
    public void initialize() {
        setTitle(currentFile.get(), true);
        currentFile.addListener((obs, oldV, newV) -> setTitle(currentFile.get(), false));
    }

    private void startNewFile() {

    }

    private void openFile() {
        //@formatter:off
        final Optional<File> openedFileOpt = getViewService()
                .getFileChooser()
                .owner(this)
                .openFile();
        //@formatter:on

        if (openedFileOpt.isPresent()) {
            currentFile.set(openedFileOpt.get());
            textAreaContent.set(FileUtils.readFile(currentFile.get()));
        }
    }

    private void saveFile() {
        saveFileToFileSystem(Optional.of(currentFile.get()));
    }

    private void saveFileAs() {
        Optional<File> chosenfile = getViewService().getFileChooser().owner(this).saveFile();
        saveFileToFileSystem(chosenfile);
    }

    private void saveFileToFileSystem(Optional<File> chosenfile) {
        if (chosenfile.isPresent()) {
            FileUtils.saveFile(chosenfile.get(), textAreaContent.get());
        }
    }

    private void printFile() {
        MessageBox.show(this, MessageType.CONFIRMATION, "TestAlert", "message",
                MessageButtonType.OK);
    }

    private void toggleDarkMode() {
        requestStyleChange();
    }

    private void setTitle(final File file, final boolean saved) {
        titleProperty().set(String.format("TextEditor - %s %s",
                FileUtils.getFileNameWithoutPath(file), saved ? "" : "*"));
    }

    StringProperty textAreaContentProperty() {
        return textAreaContent;
    }

    Command getStartNewFileCommand() {
        return startNewFileCommand;
    }

    Command getOpenFileCommand() {
        return openFileCommand;
    }

    Command getSaveFileCommand() {
        return saveFileCommand;
    }

    Command getSaveFileAsCommand() {
        return saveFileAsCommand;
    }

    Command getPrintFileCommand() {
        return printFileCommand;
    }

    public Command getToggleDarkModeCommand() {
        return toggleDarkModeCommand;
    }
}
