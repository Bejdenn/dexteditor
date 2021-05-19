package com.elinis.texteditor.lib.component.messagebox;

import javafx.scene.control.Alert.AlertType;

public enum MessageType {
    INFORMATION(AlertType.INFORMATION),

    WARNING(AlertType.WARNING),

    ERROR(AlertType.ERROR),

    EXCEPTION(AlertType.ERROR),

    CONFIRMATION(AlertType.CONFIRMATION);

    private AlertType alertType;

    private MessageType() {
        this(AlertType.NONE);
    }

    private MessageType(AlertType alertType) {
        this.alertType = alertType;
    }

    public AlertType getAlertType() {
        return alertType;
    }
}
