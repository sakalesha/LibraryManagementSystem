package com.library;

import com.library.controller.LibraryController;

public class main {
    public static void main(String[] args) {
        LibraryController controller = new LibraryController();
        controller.addBook("The Bhagwat Gita", "Vyasa", "978-8172344795", false);
        controller.addBook("Effective Java", "Joshua Bloch", "978-0134685991", false);
        controller.displayAllBooks();
    }
}
+