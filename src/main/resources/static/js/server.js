const express = require('express');
const bodyParser = require('body-parser');
const fs = require('fs');
const path = require('path');
const app = express();
const port = 3000;

app.use(bodyParser.json());
app.use(express.static(path.join(__dirname, 'static')));
app.set('view engine', 'hbs');

// Wczytaj dane
const dataFilePath = path.join(__dirname, 'data.json');
let data = JSON.parse(fs.readFileSync(dataFilePath, 'utf8'));

// Zapisz dane
function saveData() {
    fs.writeFileSync(dataFilePath, JSON.stringify(data, null, 2));
}

// Wyświetl stronę główną
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'templates', 'index.html'));
});

// Wyświetl stronę welcome
app.get('/welcome', (req, res) => {
    res.sendFile(path.join(__dirname, 'templates', 'welcome.html'));
});

// Wyświetl stronę usuwania
app.get('/delete', (req, res) => {
    res.sendFile(path.join(__dirname, 'templates', 'delete.html'));
});

// Wyszukaj wyniki
app.get('/search-result.html', (req, res) => {
    const query = req.query.query.toLowerCase();
    const category = req.query.category;
    let results = [];

    if (category === 'books') {
        results = data.books.filter(book => book.title.toLowerCase().includes(query));
    } else if (category === 'magazines') {
        results = data.magazines.filter(magazine => magazine.title.toLowerCase().includes(query));
    } else if (category === 'readers') {
        results = data.readers.filter(reader => reader.firstName.toLowerCase().includes(query) || reader.lastName.toLowerCase().includes(query));
    }

    res.render('search-result', { query, results });
});

// Dodaj książkę
app.post('/addBook', (req, res) => {
    const { title, author, publisher, isbn, year, pages } = req.body;
    data.books.push({ title, author, publisher, isbn, year, pages });
    saveData();
    res.redirect('/welcome');
});

// Dodaj magazyn
app.post('/addMagazine', (req, res) => {
    const { title, publisher, language, date } = req.body;
    data.magazines.push({ title, publisher, language, date });
    saveData();
    res.redirect('/welcome');
});

// Dodaj czytelnika
app.post('/addReader', (req, res) => {
    const { firstName, lastName, pesel } = req.body;
    data.readers.push({ firstName, lastName, pesel });
    saveData();
    res.redirect('/welcome');
});

// Usuń książkę
app.post('/deleteBook', (req, res) => {
    const { isbn } = req.body;
    data.books = data.books.filter(book => book.isbn !== isbn);
    saveData();
    res.sendStatus(200);
});

// Usuń magazyn
app.post('/deleteMagazine', (req, res) => {
    const { title } = req.body;
    data.magazines = data.magazines.filter(magazine => magazine.title !== title);
    saveData();
    res.sendStatus(200);
});

// Szukaj czytelnika
app.get('/searchReader', (req, res) => {
    const query = req.query.pesel;
    const results = data.readers.filter(reader => reader.pesel.startsWith(query));
    res.json({ results });
});

// Wyświetl szablon książki
app.get('/book/:isbn', (req, res) => {
    const book = data.books.find(book => book.isbn === req.params.isbn);
    if (book) {
        res.render('book-template', book);
    } else {
        res.sendStatus(404);
    }
});

// Wyświetl szablon magazynu
app.get('/magazine/:title', (req, res) => {
    const magazine = data.magazines.find(magazine => magazine.title === req.params.title);
    if (magazine) {
        res.render('magazine-template', magazine);
    } else {
        res.sendStatus(404);
    }
});

// Uruchom serwer
app.listen(port, () => {
    console.log(`Server is running on http://localhost:${port}`);
});
