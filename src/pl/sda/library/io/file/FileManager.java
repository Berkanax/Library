package pl.sda.library.io.file;

import pl.sda.library.app.model.Library;

public interface FileManager {
    Library importData();
    void exportData(Library library);
}