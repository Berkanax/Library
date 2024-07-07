const express = require('express');
const fs = require('fs');
const path = require('path');
const app = express();

app.use(express.json());
app.use(express.static('src/resources/static'));

const dataFilePath = path.join(__dirname, '..', 'data.json');

// Load initial data
let data = JSON.parse(fs.readFileSync(dataFilePath, 'utf8'));

app.post('/saveBookPage', (req, res) => {
    const { fileName, content } = req.body;
    const filePath = path.join(__dirname, '..', 'books', fileName);

    fs.writeFile(filePath, content, (err) => {
        if (err) {
            console.error(err);
            res.status(500).send('Error saving file');
        } else {
            data.books.push({ ...req.body, url: `books/${fileName}` });
            fs.writeFileSync(dataFilePath, JSON.stringify(data, null, 2));
            res.status(200).send('File saved');
        }
    });
});

app.post('/saveMagazinePage', (req, res) => {
    const { fileName, content } = req.body;
    const filePath = path.join(__dirname, '..', 'magazines', fileName);

    fs.writeFile(filePath, content, (err) => {
        if (err) {
            console.error(err);
            res.status(500).send('Error saving file');
        } else {
            data.magazines.push({ ...req.body, url: `magazines/${fileName}` });
            fs.writeFileSync(dataFilePath, JSON.stringify(data, null, 2));
            res.status(200).send('File saved');
        }
    });
});

app.post('/saveReaderPage', (req, res) => {
    const { fileName, content } = req.body;
    const filePath = path.join(__dirname, '..', 'readers', fileName);

    fs.writeFile(filePath, content, (err) => {
        if (err) {
            console.error(err);
            res.status(500).send('Error saving file');
        } else {
            data.readers.push({ ...req.body, url: `readers/${fileName}` });
            fs.writeFileSync(dataFilePath, JSON.stringify(data, null, 2));
            res.status(200).send('File saved');
        }
    });
});

app.post('/deleteBook', (req, res) => {
    const { isbn } = req.body;
    const filePath = path.join(__dirname, '..', 'books', `${isbn}.html`);

    fs.unlink(filePath, (err) => {
        if (err) {
            console.error(err);
            res.status(500).send('Error deleting file');
        } else {
            data.books = data.books.filter(book => book.isbn !== isbn);
            fs.writeFileSync(dataFilePath, JSON.stringify(data, null, 2));
            res.status(200).send('File deleted');
        }
    });
});

app.post('/deleteMagazine', (req, res) => {
    const { title } = req.body;
    const filePath = path.join(__dirname, '..', 'magazines', `${title}.html`);

    fs.unlink(filePath, (err) => {
        if (err) {
            console.error(err);
            res.status(500).send('Error deleting file');
        } else {
            data.magazines = data.magazines.filter(magazine => magazine.title !== title);
            fs.writeFileSync(dataFilePath, JSON.stringify(data, null, 2));
            res.status(200).send('File deleted');
        }
    });
});

app.get('/search', (req, res) => {
    const query = req.query.query.toLowerCase();
    const category = req.query.category;
    let results = [];

    if (category === 'books') {
        results = data.books.filter(book => book.title.toLowerCase().includes(query));
    } else if (category === 'magazines') {
        results = data.magazines.filter(magazine => magazine.title.toLowerCase().includes(query));
    } else if (category === 'readers') {
        results = data.readers.filter(reader => reader.name.toLowerCase().includes(query) || reader.surname.toLowerCase().includes(query));
    }

    res.json({ results });
});

app.listen(3000, () => {
    console.log('Server running on port 3000');
});
app.get('/searchReader', (req, res) => {
    const pesel = req.query.pesel.toLowerCase();
    const results = data.readers.filter(reader => reader.pesel.startsWith(pesel));
    res.json({ results });
});
