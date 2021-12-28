package back;

import java.util.HashMap;

public class BookList {
    static HashMap<String, Integer> bookList = new HashMap<>();

    BookList() {
        if(bookList.isEmpty()) {
            bookList.put("lul", 2);
            bookList.put("kek", 1);
            bookList.put("omegalul", 3);
        }
    }

    HashMap<String, Integer> getBookList() {
        return bookList;
    }

    int getBooksAmount(String name) {
        return bookList.get(name);
    }

    void setNewAmount(String name, int amount) {
        bookList.remove(name);
        bookList.put(name, amount);
    }

    void takeBook(String name) {
        int amount = bookList.get(name) - 1;
        setNewAmount(name, amount);
    }

    void returnBook(String name) {
        int amount = bookList.get(name) + 1;
        setNewAmount(name, amount);
    }

    void newBook(String name) {
        bookList.put(name, 1);
    }
}
