package com.elinis.texteditor.view;

import java.io.File;

import com.elinis.texteditor.lib.AbstractViewModel;
import com.elinis.texteditor.lib.component.messagebox.MessageBox;
import com.elinis.texteditor.lib.component.messagebox.MessageButtonType;
import com.elinis.texteditor.lib.component.messagebox.MessageType;
import com.elinis.texteditor.lib.interaction.ViewModelAction;

import org.springframework.stereotype.Component;

import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AccessLevel;
import lombok.Getter;

/**
 * {@link AbstractViewModel ViewModel} for {@link EditorView}.
 */
@Component
@Getter(value = AccessLevel.PACKAGE)
public class EditorViewModel extends AbstractViewModel {

    @Getter(AccessLevel.NONE)
    private final StringProperty textAreaContent = new SimpleStringProperty();

    @Getter(AccessLevel.NONE)
    private final ObjectProperty<EditorState> editorState = new SimpleObjectProperty<>();

    private final Command startNewFileCommand = new DelegateCommand(() -> new ViewModelAction(this::startNewFile));

    private final Command saveFileAsCommand = new DelegateCommand(() -> new ViewModelAction(this::saveFileAs));

    private final Command printFileCommand = new DelegateCommand(() -> new ViewModelAction(this::printFile));

    private final Command toggleDarkModeCommand = new DelegateCommand(() -> new ViewModelAction(this::toggleDarkMode));

    private final Command openFileCommand = new DelegateCommand(() -> new ViewModelAction(this::openFile));

    private final Command saveFileCommand = new DelegateCommand(() -> new ViewModelAction(this::saveFile));

    @Getter(AccessLevel.NONE)
    private final ObjectProperty<File> currentFile = new SimpleObjectProperty<>();

    private EditorSession editorSession = new EditorSession();

    @Override
    public void initialize() {
        titleProperty().bindBidirectional(editorSession.titleProperty());
        editorState.bindBidirectional(editorSession.editorStatusProperty());
    }

    private void startNewFile() {
        if (editorState.get().isUnsaved()) {
            MessageBox.show(this, MessageType.CONFIRMATION, "TestAlert", "message", MessageButtonType.OK);
        } else {
            // logic to create new file
        }
    }

    private void openFile() {
        //@formatter:off
        // getViewService()
        //     .getFileChooser()
        //     .owner(this)
        //     .openFile()
        //     .ifPresent(file -> {

        //     });
        //@formatter:on
    }

    private void saveFile() {
        // saveFileToFileSystem(Optional.of(currentFile.get()));
        editorSession.save();
    }

    // private void saveFileToFileSystem(Optional<File> chosenFile) {
    //     chosenFile.ifPresent(file -> FileHelper.saveFile(file, textAreaContent.get()));
    // }

    private void saveFileAs() {
        // Optional<File> chosenFile = getViewService().getFileChooser().owner(this).saveFile();
        // saveFileToFileSystem(chosenFile);
    }

    private void printFile() {
        MessageBox.show(this, MessageType.CONFIRMATION, "TestAlert", "message", MessageButtonType.OK);
    }

    private void toggleDarkMode() {
        requestStyleChange();
    }

    StringProperty textAreaContentProperty() {
        return textAreaContent;
    }

    ObjectProperty<EditorState> editorStateProperty() {
        return editorState;
    }
}
