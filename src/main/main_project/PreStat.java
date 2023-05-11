package main_project;

import main_project.Library;

public class PreStat {

    //#1 queries
    static final String displayNormalBooks = "SELECT * FROM normalBook;";
    static final String displayAcousticBooks = "SELECT id, author, language, CASE WHEN freeTrailPeriod = 1 THEN 'Yes' ELSE 'No' END AS freeTrailPeriod FROM acousticBook;";
    static final String displayLoanRecord = "SELECT * FROM loanRecord;";



    //#2 statements
    static final String createNormalBookTable =
            "CREATE TABLE IF NOT EXISTS normalBook" +
                    "("
                    + "id VARCHAR(64) NOT NULL UNIQUE, "
                    + "author VARCHAR(64), "
                    + "year INT CHECK (year >= -9999 AND year <= 9999), "
                    + "language VARCHAR(64), "
                    + "numberOfHardCopies INT, "
                    + "loanPeriod DATE, "
                    + "PRIMARY KEY(id)"
                    + ")";

    static final String createAcousticBookTable =
            "CREATE TABLE IF NOT EXISTS acousticBook" +
                    "("
                    + "id VARCHAR(64) NOT NULL UNIQUE, "
                    + "author VARCHAR(64), "
                    + "language VARCHAR(64), "
                    + "freeTrailPeriod BOOLEAN, "
                    + "PRIMARY KEY(id)"
                    + ")";

    static final String createLoanRecordTable =
            "CREATE TABLE IF NOT EXISTS loanRecord" +
                    "("
                    + "id INT AUTO_INCREMENT, "
                    + "user VARCHAR(64) UNIQUE, "
                    + "bookType VARCHAR(8) CHECK (bookType IN('acoustic', 'normal')), "
                    + "bookId VARCHAR(64),"
                    + "PRIMARY KEY(id)"
                    + ")";



    static final String bookInserts = "INSERT INTO normalBook(id, author, year, language, numberOfHardCopies, loanPeriod) VALUES (?,?,?,?,?,?)" +
            " ON DUPLICATE KEY UPDATE id=VALUES(id), author=VALUES(author), year=VALUES(year), language=VALUES(language), numberOfHardCopies=VALUES(numberOfHardCopies), loanPeriod=VALUES(loanPeriod);";

    static final String audioBookInsert = "INSERT INTO acousticBook(id, author, language, freeTrailPeriod) VALUES (?,?,?,?)" +
            " ON DUPLICATE KEY UPDATE id=VALUES(id), author=VALUES(author), language=VALUES(language), freeTrailPeriod=VALUES(freeTrailPeriod);";

    static final String loanRecordInsert = "INSERT INTO loanRecord(user, bookType, bookId) VALUES (?,?,?);";


    static final String increaseBooks =   "UPDATE normalBook SET numberOfHardCopies = numberOfHardCopies + 1 WHERE id = ?;";
    static final String subtractBooks =   "UPDATE normalBook SET numberOfHardCopies = numberOfHardCopies - 1 WHERE id = ?;";

}
