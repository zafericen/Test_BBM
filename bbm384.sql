DROP DATABASE IF EXISTS bbm384;
CREATE DATABASE IF NOT EXISTS bbm384;

use BBM384;

DROP TABLE IF EXISTS User;
CREATE TABLE User
(
    UserID VARCHAR(36) PRIMARY KEY,
    Username VARCHAR(255) NOT NULL,
    Email VARCHAR(255),
    Password VARCHAR(255) NOT NULL,
    LastOnline BIGINT UNSIGNED,
    RegisterDate BIGINT UNSIGNED,
    Enabled BOOLEAN DEFAULT 1,
    ProfilePicture LONGBLOB,
    UserRole VARCHAR(16),
    UNIQUE (Username, Email),
    CHECK (UserRole IN ('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MERCHANT', 'ROLE_COMMUNITY_MODERATOR'))
);

DROP TABLE IF EXISTS Category;
CREATE TABLE Category
(
    CategoryID VARCHAR(36) PRIMARY KEY,
    CategoryName VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS Community;
CREATE TABLE Community
(
    CommunityID VARCHAR(36) PRIMARY KEY,
    CategoryID VARCHAR(36),
    FOREIGN KEY (CategoryID) REFERENCES Category(CategoryID)
);

DROP TABLE IF EXISTS Post;
CREATE TABLE Post
(
    UserID VARCHAR(36),
    CommunityID VARCHAR(36),
    PostID VARCHAR(36) PRIMARY KEY,
    Title VARCHAR(255) NOT NULL,
    Description TEXT NOT NULL,
    PostDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE SET NULL ,
    FOREIGN KEY (CommunityID) REFERENCES Community(CommunityID) ON DELETE CASCADE
);

DROP TABLE IF EXISTS Product;
CREATE TABLE Product
(
    ProductID VARCHAR(36) PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Description TEXT NOT NULL,
    Price DOUBLE NOT NULL,
    SaleNumber INT,
    CategoryID VARCHAR(36),
    AverageRating FLOAT DEFAULT 1 CHECK (AverageRating >= 1 AND AverageRating <= 5),
    FOREIGN KEY (CategoryID) REFERENCES Category(CategoryID)
);

DROP TABLE IF EXISTS PostComment;
CREATE TABLE PostComment
(
    CommentID VARCHAR(36) PRIMARY KEY,
    UserID VARCHAR(36),
    PostID VARCHAR(36),
    CommentDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    Comment TEXT NOT NULL,
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE SET NULL,
    FOREIGN KEY (PostID) REFERENCES Post(PostID) ON DELETE CASCADE
);

DROP TABLE IF EXISTS ProductComment;
CREATE TABLE ProductComment
(
    CommentID VARCHAR(36),
    UserID VARCHAR(36),
    ProductID VARCHAR(36),
    CommentDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    Comment TEXT NOT NULL,
    Rating INT NOT NULL CHECK (Rating >= 0 AND Rating <= 5),
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE SET NULL,
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID) ON DELETE CASCADE,
    PRIMARY KEY (CommentID, ProductID)
);

DROP TABLE IF EXISTS Image;
CREATE TABLE Image
(
    ImageID VARCHAR(36) PRIMARY KEY,
    Image LONGBLOB NOT NULL
);

DROP TABLE IF EXISTS ProductImage;
CREATE TABLE ProductImage
(
    ImageID VARCHAR(36),
    ProductID VARCHAR(36),
    FOREIGN KEY (ImageID) REFERENCES Image(ImageID) ON DELETE CASCADE,
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID) ON DELETE CASCADE
);

DROP TABLE IF EXISTS PostImage;
CREATE TABLE PostImage
(
    ImageID VARCHAR(36),
    PostID VARCHAR(36),
    FOREIGN KEY (ImageID) REFERENCES Image(ImageID) ON DELETE CASCADE,
    FOREIGN KEY (PostID) REFERENCES Post(PostID) ON DELETE CASCADE
);

DROP TABLE IF EXISTS CommentImage;
CREATE TABLE CommentImage
(
    ImageID VARCHAR(36),
    CommentID VARCHAR(36),
    FOREIGN KEY (ImageID) REFERENCES Image(ImageID) ON DELETE CASCADE,
    FOREIGN KEY (CommentID) REFERENCES ProductComment(CommentID) ON DELETE CASCADE
);

DROP TABLE IF EXISTS Favourites;
CREATE TABLE Favourites
(
    UserID VARCHAR(36),
    ProductID VARCHAR(36),
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE SET NULL,
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID) ON DELETE CASCADE
);

DROP TABLE IF EXISTS Question;
CREATE TABLE Question
(
    QuestionID VARCHAR(36) PRIMARY KEY,
    UserID VARCHAR(36),
    ProductID VARCHAR(36),
    QuestionDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    Question TEXT NOT NULL,
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE SET NULL,
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID) ON DELETE CASCADE
);

DROP TABLE IF EXISTS Answer;
CREATE TABLE Answer
(
    UserID VARCHAR(36),
    ProductID VARCHAR(36),
    QuestionID VARCHAR(36),
    AnswerDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    Answer TEXT NOT NULL,
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE SET NULL,
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID) ON DELETE CASCADE,
    FOREIGN KEY (QuestionID) REFERENCES Question(QuestionID) ON DELETE CASCADE
);

DROP TABLE IF EXISTS Sells;
CREATE TABLE Sells
(
    SellID VARCHAR(36) PRIMARY KEY,
    UserID VARCHAR(36),
    ProductID VARCHAR(36),
    SaleNumber INT NOT NULL CHECK (SaleNumber >= 0),
    FOREIGN KEY (UserID) REFERENCES USER(UserID) ON DELETE CASCADE,
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID) ON DELETE CASCADE
);

DROP TABLE IF EXISTS VisitedProduct;
CREATE TABLE VisitedProduct
(
    UserID VARCHAR(36),
    ProductID VARCHAR(36),
    VisitDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES USER(UserID) ON DELETE CASCADE,
    FOREIGN KEY (ProductID) REFERENCES PRODUCT(ProductID) ON DELETE CASCADE
);

DROP TABLE IF EXISTS UserCategoryInterest;
CREATE TABLE UserCategoryInterest
(
    UserID VARCHAR(36),
    CategoryID VARCHAR(36),
    InterestPoint FLOAT NOT NULL CHECK (InterestPoint >= 0 AND InterestPoint <= 1),
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE,
    FOREIGN KEY (CategoryID) REFERENCES Category(CategoryID)
);

delimiter //
CREATE TRIGGER UpdateAverageRating
    AFTER INSERT ON ProductComment
    FOR EACH ROW
BEGIN
    DECLARE totalRating DOUBLE;
    DECLARE ratingCount INT;
    SET @totalRating = (SELECT SUM(Rating) FROM ProductComment WHERE ProductID = NEW.ProductID);
    SET ratingCount = (SELECT COUNT(*) FROM ProductComment WHERE ProductID = NEW.ProductID);
    UPDATE Product SET AverageRating = totalRating / ratingCount WHERE ProductID = NEW.ProductID;
END;//
delimiter ;

delimiter //
CREATE TRIGGER BanUser
    AFTER UPDATE ON User
    FOR EACH ROW
BEGIN
    IF NEW.Enabled = 0 THEN
        UPDATE PostComment SET UserID = NULL WHERE UserID = NEW.UserID;
        UPDATE ProductComment SET UserID = NULL WHERE UserID = NEW.UserID;
        DELETE FROM Favourites WHERE UserID = NEW.UserID;
        UPDATE Question SET UserID = NULL WHERE UserID = NEW.UserID;
        UPDATE Answer SET UserID = NULL WHERE UserID = NEW.UserID;
        DELETE FROM Sells WHERE UserID = NEW.UserID;
        DELETE FROM VisitedProduct WHERE UserID = NEW.UserID;
        DELETE FROM UserCategoryInterest WHERE UserID = NEW.UserID;
    END IF;
END;//
delimiter ;

insert into Category values (UUID(), 'Electronics');
insert into Category values(UUID(), 'Health');
insert into User values (UUID(), 'admin', 'admin@gmail.com',  '$2a$12$sRS5KVMp4EqgcwSqrzj1IuWZG/QvAz/8Ik8.rZUnyMyC2oh4yyLGW' , UNIX_TIMESTAMP(), UNIX_TIMESTAMP(), 1, NULL, 'ROLE_ADMIN');
insert into User values (UUID(), 'user', 'user@gmail.com',  '$2a$12$sRS5KVMp4EqgcwSqrzj1IuWZG/QvAz/8Ik8.rZUnyMyC2oh4yyLGW' , UNIX_TIMESTAMP(), UNIX_TIMESTAMP(), 1, NULL, 'ROLE_USER');