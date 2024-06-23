package pl.sda.library.app;
import pl.sda.library.io.DataReader;
import pl.sda.library.model.Book;
import pl.sda.library.model.Library;

public class LibraryControl {
    private static final int EXIT = 0;
    private static final int ADD_BOOK = 1;
    private static final int PRINT_BOOKS = 2;

    private Library library = new Library();
    private DataReader dataReader = new DataReader();

    public void controlLoop(){
        int option;

        do {
            printOption();
            option = dataReader.getInt();
            switch (option) {
                case ADD_BOOK:
                    addBook();
                    break;
                case PRINT_BOOKS:
                    printBooks();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    System.out.println("Nieznana komenda");
            }
        } while (option != EXIT);
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
        System.out.println("."+ EXIT +". - Wyjście z programu");
        System.out.println("."+ ADD_BOOK +". - Wprowadź nową książke");
        System.out.println("."+ PRINT_BOOKS +". - Wyswietl dostepne ksiązki");
    }
}
