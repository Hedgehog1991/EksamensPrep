package bookRecords;

import java.sql.Date;

public abstract class Books {

   String id, author, language;
    int year;

    public Books(String id, String author, int year, String language ) {
        this.id = id;
        this.author = author;
        this.language = language;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getLanguage() {
        return language;
    }

    public int getYear() {
        return year;
    }
}


