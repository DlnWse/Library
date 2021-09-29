package fr.dylan.library.entity;


import javafx.collections.ObservableList;

public class Book {
    private int _id ;
    private String _title;
    private String _author;
    private int _year;
    private int _pages;

    public Book (int id, String title, String author, int year, int pages)
    {
        this._id = id;
        this._title = title;
        this._author = author;
        this._year = year;
        this._pages = pages;

    }

    public Book (String title, String author, int year, int pages)
    {
        this._title = title;
        this._author = author;
        this._year = year;
        this._pages = pages;

    }



    // getters
    public int get_id() {return _id;}
    public String get_title() {return _title;}
    public String get_author() {return _author;}
    public int get_year() {return _year;}
    public int get_pages() {return _pages;}

    // setters
    public void set_id(int _id) {this._id = _id;}
    public void set_title(String _title) {this._title = _title;}
    public void set_author(String _author) {this._author = _author;}
    public void set_year(int _year) {this._year = _year;}
    public void set_pages(int _pages) {this._pages = _pages;}
}
