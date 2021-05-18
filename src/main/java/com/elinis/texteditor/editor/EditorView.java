package com.elinis.texteditor.frontend.editor;

import static javafx.scene.input.KeyCombination.CONTROL_DOWN;

import com.elinis.core.frontend.view.AbstractView;
import com.elinis.core.frontend.view.component.MenuBarBuilder;
import com.elinis.core.frontend.view.component.MenuItemType;

import org.springframework.stereotype.Component;

import de.saxsys.mvvmfx.InjectViewModel;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;

/**
 * The initial and currently only view of the application.
 */
@Component
public class EditorView extends AbstractView<EditorViewModel> {

    private MenuBar menuBar;
    private final TextArea textArea = new TextArea();

    @InjectViewModel
    private EditorViewModel viewModel;

    @Override
    public void initializeGUI() {
        setPrefSize(800, 650);

        menuBar = createMenuBar();
        setTop(menuBar);

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
