package pl.sda.library.app;

import pl.sda.library.io.DataReader;
import pl.sda.library.model.Book;

import java.util.Scanner;

class Library {
    public static void main(String[] args) {
        final String appName = "Biblioteka v0.8";

        Book[] books = new Book[1000];
        DataReader dataReader = new DataReader();
        books[0] = dataReader.readAndCreateBook();
        books[1] = dataReader.readAndCreateBook();
        dataReader.close();

        System.out.println(appName);
        System.out.println("### Książki dostepnę w bibliotece ###");
        books[0].printInfo();
        books[1].printInfo();

    }
}

//books[0] = new Book("Zbuntowana","Veronica Roth",2012,367, "Amber", "9788324153497");
//books[1] = new Book("Niezgodna","Veronica Roth",2011,350, "Amber", "9788324153718");
//books[2] = new Book("Mitologia Nordycka","Neil Gaiman",2021,227, "Mag", "9788367023658");
//books[3] = new Book("Wprowadzenie do javy","Y. Daniel Liang",2015,1542, "Helion");
