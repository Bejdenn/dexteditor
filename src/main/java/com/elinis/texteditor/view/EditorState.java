package com.elinis.texteditor.view;

public enum EditorState implements State<EditorState> {

    SAVED {
        @Override
        public EditorState getNextState() {
            return UNSAVED;
        }
    },

    UNSAVED("*") {
        @Override
        public EditorState getNextState() {
            return SAVED;
        }
    };

    private final String NONE = "";

    private String token;

    EditorState() {
        this.token = NONE;
    }

    EditorState(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    boolean isSaved() {
        return SAVED.equals(this);
    }

    boolean isUnsaved() {
        return UNSAVED.equals(this);
    }
}

interface State<E extends Enum<?>> {
    E getNextState();
}
