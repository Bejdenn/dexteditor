package com.elinis.texteditor.view;

import static javafx.scene.input.KeyCombination.CONTROL_DOWN;

import com.elinis.texteditor.lib.AbstractView;
import com.elinis.texteditor.lib.component.MenuBarBuilder;
import com.elinis.texteditor.lib.component.MenuItemType;
import com.elinis.texteditor.view.component.ObservableTextArea;

import org.springframework.stereotype.Component;

import de.saxsys.mvvmfx.InjectViewModel;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyCode;

/**
 * The initial and currently only view of the application.
 */
@Component
public class EditorView extends AbstractView<EditorViewModel> {

    private ObservableTextArea textArea;
    private MenuBar menuBar;

    @InjectViewModel
    private EditorViewModel viewModel;

    @Override
    public void initializeGUI() {
        setPrefSize(800, 650);

        menuBar = createMenuBar();
        setTop(menuBar);

        textArea = new ObservableTextArea(null);
        textArea.editorStateProperty().bindBidirectional(getViewModel().editorStateProperty());
        textArea.textProperty().bindBidirectional(getViewModel().textAreaContentProperty());

        setCenter(textArea);
    }

    protected MenuBar createMenuBar() {
        //@formatter:off
        return MenuBarBuilder
            .newMenuBar()
                .withTranslator(this::getTranslation)
                .newMenu("file")
                    .newMenuItem("newFile")
                        .withCommand(getViewModel().getStartNewFileCommand())
                        .withKeyCombination(CONTROL_DOWN, KeyCode.N)
                    .buildMenuItem()
                    .newMenuItem("openFile")
                        .withCommand(getViewModel().getOpenFileCommand())
                        .withKeyCombination(CONTROL_DOWN, KeyCode.O)
                    .buildMenuItem()
                    .newMenuItem("saveFile")
                        .withCommand(getViewModel().getSaveFileCommand())
                        .withKeyCombination(CONTROL_DOWN, KeyCode.S)
                    .buildMenuItem()
                    .newMenuItem("saveFileAs")
                        .withCommand(getViewModel().getSaveFileAsCommand())
                        .withKeyCombination(CONTROL_DOWN, KeyCode.S)
                    .buildMenuItem()
                    .newMenuItem("printFile")
                        .withCommand(getViewModel().getPrintFileCommand())
                        .withKeyCombination(CONTROL_DOWN, KeyCode.P)
                    .buildMenuItem()
                .buildMenu()
                .newMenu("options")
                    .newMenuItem("toggleDarkMode")
                        .ofType(MenuItemType.RADIO)
                        .withCommand(getViewModel().getToggleDarkModeCommand())
                    .buildMenuItem()
                .buildMenu()
            .buildMenuBar();
        //@formatter:on
    }

    @Override
    protected EditorViewModel getViewModel() {
        return viewModel;
    }
}
