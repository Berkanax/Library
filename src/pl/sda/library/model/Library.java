package pl.sda.library.model;

public class Library {
    private static final int MAX_PUBLICATIONS = 2000;
    private int publicationsNumber = 0;
    private Publication[] publications = new Publication[MAX_PUBLICATIONS];

    public void addBook(Book book){
        if(publicationsNumber < MAX_PUBLICATIONS){
            publications[publicationsNumber] = book;
            publicationsNumber++;
        }else {
            System.out.println("Brak miejsca w bibliotece");
        }

    }

    public void printBooks(){
        int countBooks = 0;
        for (int i = 0; i < publicationsNumber; i++) {
            if(publications[i] instanceof Book)
            publications[i].printInfo();
            countBooks++;
        }
        if(countBooks == 0){
            System.out.println("Brak książek");
        }
    }

    public void addMagazine(Magazine magazine){
        if(publicationsNumber < MAX_PUBLICATIONS){
            publications[publicationsNumber] = magazine;
            publicationsNumber++;
        }else {
            System.out.println("Brak miejsca w bibliotece");
        }

    }
    public void printMagazin(){
        int countMagazins = 0;
        for (int i = 0; i < publicationsNumber; i++) {
            if (publications[i] instanceof Magazine) {
                publications[i].printInfo();
                countMagazins++;
            }
        }
        if(countMagazins == 0){
            System.out.println("Brak Czasopism");

        }
    }

}
