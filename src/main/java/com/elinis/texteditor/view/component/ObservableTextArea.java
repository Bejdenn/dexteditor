package com.elinis.texteditor.view.component;

import static com.elinis.texteditor.view.EditorState.SAVED;
import static com.elinis.texteditor.view.EditorState.UNSAVED;

import java.util.Objects;

import com.elinis.texteditor.view.EditorState;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;

public class ObservableTextArea extends TextArea {

    private ObjectProperty<EditorState> editorState = new SimpleObjectProperty<>(SAVED);

    private final Runnable onEditorStateChangedAction;

    public ObservableTextArea(Runnable onEditorStateChangedAction) {
        this.onEditorStateChangedAction = onEditorStateChangedAction;
        textProperty().addListener(this::onEditorStateChanged);
    }

    private void onEditorStateChanged(ObservableValue<? extends String> observableValue, String oldValue,
            String newValue) {
        // we do not declare an unsaved context if there is no new value
        if (newValue == null) {
            return;
        }

        if (SAVED.equals(editorState.get())) {
            editorState.set(UNSAVED);

            if (Objects.nonNull(onEditorStateChangedAction)) {
                onEditorStateChangedAction.run();
            }
        }
    }

    public ObjectProperty<EditorState> editorStateProperty() {
        return editorState;
    }
}
