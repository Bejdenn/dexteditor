package com.elinis.texteditor.view;


import java.io.File;

import com.elinis.texteditor.helper.FileHelper;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class EditorSession {

    private StringProperty title = new SimpleStringProperty();
    private File file;
    private ObjectProperty<EditorState> editorStatus = new SimpleObjectProperty<>();

    public EditorSession() {
        editorStatus.addListener(this::buildTitle);
        editorStatus.set(EditorState.SAVED);
    }

    public void save() {
        editorStatus.set(editorStatus.get().getNextState());
    }

    private void buildTitle(ObservableValue<? extends EditorState> observableValue,
            EditorState oldValue, EditorState newValue) {
        title.set(String.format("TextEditor - %s %s", FileHelper.getFileNameWithoutPath(file),
                newValue.getToken()));
    }

    public StringProperty titleProperty() {
        return title;
    }

    public ObjectProperty<EditorState> editorStatusProperty() {
        return editorStatus;
    }
}
