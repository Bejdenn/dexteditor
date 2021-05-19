package com.elinis.texteditor;

import com.elinis.texteditor.lib.service.ViewService;
import com.elinis.texteditor.view.EditorView;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import de.saxsys.mvvmfx.MvvmFX;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX application class. The method {@link #startClient(String[])} must be
 * called from another class.
 */
@Configuration
@ComponentScan("com.elinis")
public class ApplicationStarter extends Application {

    private static final Logger LOGGER = LogManager.getLogger();

    public ApplicationStarter() {

    }

    /**
     * Starts this {@link Application}.
     */
    public void startClient(final String[] args) {
        launch(args);
    }

    /**
     * Runs the {@link SpringApplication} and registers the
     * {@code ApplicationContext} for the {@code MvvmFX framework}. Afterwards the
     * {@link ViewService} prepares the first application view.
     */
    @Override
    public void start(final Stage stage) throws Exception {
        try (ConfigurableApplicationContext context = SpringApplication.run(ApplicationStarter.class)) {
            MvvmFX.setCustomDependencyInjector(context::getBean);
            context.getBeanFactory().autowireBean(this);
            context.getBean(ViewService.class).prepareView(EditorView.class);
        } catch (final Exception e) {
            LOGGER.error("Error at application startup", e);
            throw e;
        }
    }
}
