package pl.sda.library.app;
import pl.sda.library.controller.LibraryControl;

public class LibraryApp {
    private static final String APP_NAME = "Biblioteka v2.7";

    public static void main(String[] args) {
        System.out.println(APP_NAME);
        LibraryControl libControl = new LibraryControl();
        libControl.controlLoop();
    }
}
