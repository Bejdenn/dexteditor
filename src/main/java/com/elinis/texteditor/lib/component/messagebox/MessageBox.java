package com.elinis.texteditor.lib.component.messagebox;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.elinis.texteditor.lib.AbstractViewModel;
import com.elinis.texteditor.lib.i18n.Translatable;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import jfxtras.styles.jmetro.JMetro;

public class MessageBox {

    public static <VM extends AbstractViewModel> void show(VM owner, MessageType messageType,
            String title, String message, MessageButtonType... messageButtonTypes) {
        Alert alert = new Alert(messageType.getAlertType());
        alert.setTitle(title);
        alert.setContentText(message);
        alert.initOwner(owner.getAssociatedScene().getWindow());

        List<ButtonType> buttonTypes =
                Stream.of(messageButtonTypes).map(Translatable::getTranslation).map(ButtonType::new)
                        .collect(Collectors.toList());
        alert.getButtonTypes().addAll(buttonTypes);

        applyStyle(owner, alert);

        alert.getDialogPane().requestFocus();

        alert.showAndWait();
    }

    public static <VM extends AbstractViewModel> void applyStyle(VM owner, Alert alert) {
        new JMetro(owner.getCurrentStyle()).setScene(alert.getOwner().getScene());
    }

    public static void addIcon(Alert alert) {
        alert.getDialogPane().setGraphic(new ImageView(("icon.png").toString()));
    }
}
