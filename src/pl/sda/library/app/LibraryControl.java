package pl.sda.library.app;
import pl.sda.library.io.DataReader;
import pl.sda.library.model.Book;
import pl.sda.library.model.Library;
import pl.sda.library.model.Magazine;

public class LibraryControl {
    private static final int EXIT = 0;
    private static final int ADD_BOOK = 1;
    private static final int ADD_MAGAZIN = 2;
    private static final int PRINT_BOOKS = 3;
    private static final int PRINT_MAGAZIN = 4;

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
                case ADD_MAGAZIN:
                    addMagazine();
                    break;
                case PRINT_BOOKS:
                    printBooks();
                    break;
                case PRINT_MAGAZIN:
                    printMagazines();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    System.out.println("Nieznana komenda");
            }
        } while (option != EXIT);
    }

    private void printMagazines() {
        library.printMagazin();
    }

    private void addMagazine() {
        Magazine magazine = dataReader.readAndCreateMagazine();
        library.addMagazine(magazine);

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
        System.out.println("."+ ADD_MAGAZIN +". - Wprowadź nową czasopismo");
        System.out.println("."+ PRINT_BOOKS +". - Wyswietl dostepne ksiązki");
        System.out.println("."+ PRINT_MAGAZIN +". - Wyswietl dostepne czasopisma");
    }
}
