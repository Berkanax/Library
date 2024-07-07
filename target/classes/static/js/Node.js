const express = require('express');
const fs = require('fs');
const path = require('path');
const app = express();

app.use(express.json());
app.use(express.static('public'));

app.post('/saveBookPage', (req, res) => {
    const { fileName, content } = req.body;
    const filePath = path.join(__dirname, 'public', 'books', fileName);

    fs.writeFile(filePath, content, (err) => {
        if (err) {
            console.error(err);
            res.status(500).send('Error saving file');
        } else {
            res.status(200).send('File saved');
        }
    });
});

app.post('/deleteBook', (req, res) => {
    const { isbn } = req.body;
    const filePath = path.join(__dirname, 'public', 'books', `${isbn}.html`);

    fs.unlink(filePath, (err) => {
        if (err) {
            console.error(err);
            res.status(500).send('Error deleting file');
        } else {
            res.status(200).send('File deleted');
        }
    });
});

app.post('/deleteMagazine', (req, res) => {
    const { title } = req.body;
    const filePath = path.join(__dirname, 'public', 'magazines', `${title}.html`);

    fs.unlink(filePath, (err) => {
        if (err) {
            console.error(err);
            res.status(500).send('Error deleting file');
        } else {
            res.status(200).send('File deleted');
        }
    });
});

app.listen(3000, () => {
    console.log('Server running on port 3000');
});
