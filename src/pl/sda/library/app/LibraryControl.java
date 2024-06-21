package pl.sda.library.app;
import pl.sda.library.io.DataReader;
import pl.sda.library.model.Book;
import pl.sda.library.model.Library;

public class LibraryControl {
    private final int exit = 0;
    private final int addBook = 1;
    private final int printBooks = 2;
    private Library library = new Library();
    private DataReader dataReader = new DataReader();

    public void controlLoop(){
        int option;

        do {
            printOption();
            option = dataReader.getInt();
            switch (option) {
                case addBook:
                    addBook();
                    break;
                case printBooks:
                    printBooks();
                    break;
                case exit:
                    exit();
                    break;
                default:
                    System.out.println("Nieznana komenda");
            }
        } while (option != exit);
    }

    private void exit() {
        System.out.println("Do zaobaczenia!");
        dataReader.close();
    }

    private void printBooks() {
        library.printBooks();

    }

    private void addBook() {
        Book book = dataReader.readAndCreateBook();
        library.addBook(book);
    }

    private void printOption() {
        System.out.println("### M E N U    G Ł Ó W N E");
        System.out.println("## Wybierz opcję:");
        System.out.println("."+ exit +". - Wyjście z programu");
        System.out.println("."+ addBook +". - Wprowadź nową książke");
        System.out.println("."+ printBooks +". - Wyswietl dostepne ksiązki");
    }
}
