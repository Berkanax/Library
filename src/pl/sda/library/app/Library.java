package pl.sda.library.app;
import pl.sda.library.model.Book;

class Library {
    public static void main(String[] args) {
        final String appName = "Biblioteka v0.5";

        Book book1 = new Book("Zbuntowana","Veronica Roth",2012,367, "Amber", "9788324153497");
        Book book2 = new Book("Niezgodna","Veronica Roth",2011,350, "Amber", "9788324153718");
        Book book3 = new Book("Mitologia Nordycka","Neil Gaiman",2021,227, "Mag", "9788367023658");
        Book book4 = new Book("Wprowadzenie do javy","Y. Daniel Liang",2015,1542, "Helion");

        System.out.println(appName);
        System.out.println("### Książki dostepnę w bibliotece ###");
book1.printInfo();
book2.printInfo();
book3.printInfo();
book4.printInfo();
    }
}
