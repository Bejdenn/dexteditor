package com.elinis.texteditor.lib.interaction;

import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.commands.Action;

/**
 * Custom implementation of {@link Action}. It takes a {@link Runnable} as a
 * parameter and allows to omit creating anonymous class implementations in the
 * {@link ViewModel} and have double colon functions
 * <code>'this::someVoidMethod'</code> or a simple lambda function executing a
 * void method, instead.
 */
public class ViewModelAction extends Action {

    protected Runnable function;

    public ViewModelAction(final Runnable function) {
        this.function = function;
    }

    @Override
    protected void action() throws Exception {
        function.run();
    }
}
