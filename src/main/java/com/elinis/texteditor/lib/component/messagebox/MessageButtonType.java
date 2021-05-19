package com.elinis.texteditor.lib.component.messagebox;

import com.elinis.texteditor.lib.i18n.Translatable;
import com.elinis.texteditor.lib.i18n.TranslationProvider;

public enum MessageButtonType implements Translatable {

    OK("OK");

    private String description;

    MessageButtonType(String description) {
        this.description = description;
    }


    @Override
    public String getTranslation() {
        return TranslationProvider.getTranslation(getClass(), description);
    }
}
