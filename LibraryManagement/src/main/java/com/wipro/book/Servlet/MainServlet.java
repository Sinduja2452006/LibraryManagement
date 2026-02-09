package com.wipro.book.Servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.wipro.book.Service.Administrator;
import com.wipro.book.bean.AuthorBean;
import com.wipro.book.bean.BookBean;
import com.wipro.book.dao.AuthorDAO;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    System.out.println("HELLO FROM SERVLET (doGet)");
	    doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation=request.getParameter("operation");
		if(operation.equals("AddBook")) {
			String result=addBook(request);
			if(result.equals("SUCCESS")) {
				response.sendRedirect("menu.html");
			}
			else if(result.equals("INVALID")) {
				response.sendRedirect("Invalid.html");
			}
			else if(result.equals("FAILURE")) {
				response.sendRedirect("failure.html");
			}
		}
		else if(operation.equals("Search")) {
			String isbn=request.getParameter("isbn");
			BookBean bookBean=viewBook(isbn);
			if(bookBean==null) {
				response.sendRedirect("Invalid.html");
			}
			else {
				HttpSession session=request.getSession();
				session.setAttribute("book",bookBean);
				RequestDispatcher rd=request.getRequestDispatcher("ViewServlet");
				rd.forward(request, response);

				}
		}
	}
	
	public String addBook(HttpServletRequest request) {

	    System.out.println("INSIDE addBook()");

	    String isbn = request.getParameter("isbn");
	    String bookName = request.getParameter("bookName");
	    String bookType = request.getParameter("bookType");
	    String authorName = request.getParameter("authorName");
	    String cost = request.getParameter("cost");

	    System.out.println("ISBN = " + isbn);
	    System.out.println("BOOK NAME = " + bookName);
	    System.out.println("BOOK TYPE = " + bookType);
	    System.out.println("AUTHOR NAME = " + authorName);
	    System.out.println("COST = " + cost);

	    AuthorDAO authorDAO = new AuthorDAO();
	    if (authorDAO.getAuthor(authorName) == null) {
	        System.out.println("AUTHOR NOT FOUND");
	        return "INVALID";
	    }

	    BookBean bookbean = new BookBean();
	    bookbean.setIsbn(isbn);
	    bookbean.setBookName(bookName);
	    bookbean.setBookType(bookType.charAt(0));
	    bookbean.setCost(Float.parseFloat(cost));
	    bookbean.setAuthor(authorDAO.getAuthor(authorName));

	    String result = new Administrator().addBook(bookbean);
	    System.out.println("RESULT FROM ADMIN = " + result);

	    return result;
	}

    public BookBean viewBook(String isbn) {
    	Administrator administrator=new Administrator();
    	return administrator.viewBook(isbn);
    }
}