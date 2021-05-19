package com.elinis.texteditor.helper;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;

/**
 * Utility class for file handling.
 */
public class FileHelper {

    private FileHelper() {
        // only static access
    }

    /**
     * Returns the file name of the given {@link File} as a {@code String}.
     * 
     * @param file the file for which its file name will be returned
     * 
     * @return the file name
     */
    public static String getFileNameWithoutPath(File file) {
        if (file != null) {
            return FilenameUtils.getName(file.getName());
        } else {
            // return TranslationProvider.getTranslation(FileUtils.class,
            // "withoutTitle");
        }

        return "";
    }

    /**
     * Returns the contents of a {@link File} as a single {@code String} that also
     * contains all special characters like line-break, tabs, etc.
     * 
     * @param file the file that should be red
     * 
     * @return a string with the content of a file
     */
    public static String readFile(File file) {
        Objects.requireNonNull(file, "The given file cannot be null!");
        try {
            return Files.readAllLines(Paths.get(file.getPath()), StandardCharsets.UTF_8).stream()
                    .collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new IllegalStateException(String.format("Error while processing file: %s", file.getName()));
        }
    }

    public static void saveFile(File file, String newContent) {
        Objects.requireNonNull(file, "File cannot be empty or null");
        try (RandomAccessFile stream = new RandomAccessFile(file.getName(), "rw")) {
            byte[] strBytes = newContent.getBytes();
            ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
            buffer.put(strBytes);
            buffer.flip();
            stream.getChannel().write(buffer);
        } catch (IOException e) {
            throw new IllegalStateException(String.format("Error while processing file: %s", file.getName()));
        }
    }
}
