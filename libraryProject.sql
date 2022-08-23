CREATE TABLE booksInLibrary(
bookID int not null auto_increment,
bookTitle varchar(100) not null,
author varchar(100) not null,
numberOfCopies int not null,
PRIMARY KEY(bookID)
);

CREATE TABLE borrowedBooks(
borrowedBookID int not null auto_increment,
bookID int,
bookTitle varchar(100) not null,
author varchar(100) not null,
borrowedAt timestamp default current_timestamp not null,
PRIMARY KEY(borrowedBookID),
FOREIGN KEY(bookID) REFERENCES booksInLibrary(bookID)
);

