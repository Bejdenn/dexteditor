package com.elinis.texteditor.lib.filemanagement;

import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Holds all extension filter that a file choose can apply. Mainly used at the configuration of a
 * {@link SpecialFileChooser}.
 */
public enum DefaultExtensionFilter {

    /** Includes all files. **/
    ALL(new ExtensionFilter("Alle Dateien", "*.*")),

    /** Includes all text files. **/
    TEXT(new ExtensionFilter("Textdateien", "*.txt")),

    /** Includes all Log files. **/
    LOG(new ExtensionFilter("Logdateien", "*.log"));

    private ExtensionFilter filter;

    private DefaultExtensionFilter(final ExtensionFilter filter) {
        this.filter = filter;
    }

    public ExtensionFilter getFilter() {
        return filter;
    }
}
