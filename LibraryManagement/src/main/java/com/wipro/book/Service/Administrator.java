package com.wipro.book.Service;

import com.wipro.book.bean.BookBean;
import com.wipro.book.dao.BookDAO;

public class Administrator {

    public String addBook(BookBean bookBean) {

        if (bookBean == null) {
            return "INVALID";
        }

        if (bookBean.getIsbn() == null || bookBean.getIsbn().trim().isEmpty()) {
            return "INVALID";
        }

        if (bookBean.getBookName() == null || bookBean.getBookName().trim().isEmpty()) {
            return "INVALID";
        }

        if (bookBean.getCost() <= 0) {
            return "INVALID";
        }

        if (bookBean.getBookType() != 'G' && bookBean.getBookType() != 'T') {
            return "INVALID";
        }

        if (bookBean.getAuthor() == null) {
            return "INVALID";
        }

        BookDAO bookdao = new BookDAO();
        int result = bookdao.createBook(bookBean);

        if (result == 1) {
            return "SUCCESS";
        }

        return "FAILURE";
    }

    public BookBean viewBook(String isbn) {
        BookDAO bookdao = new BookDAO();
        return bookdao.fetchBook(isbn);
    }
}
