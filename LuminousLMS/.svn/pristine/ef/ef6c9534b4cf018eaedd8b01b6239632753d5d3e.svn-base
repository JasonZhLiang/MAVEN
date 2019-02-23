package com.linguaclassica.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowAlertServlet
 */
@WebServlet("/ShowAlertServlet")
public class ShowAlertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAlertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String printString = "";  
        PrintWriter out = response.getWriter();
        String docType ="<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n"; 
        
        printString = 
        		"<html>\n" +
                "<head><TITLE>AlpheiosPlus Exercise</TITLE>" +
        		"<style>"+
                "body {"+
                "background-color: LightGray; }"+
                "p { padding:20px; }"+
                "#alert { "+
        		"position:absolute;"+
                "top:80px;"+
        		"left:400px;"+
                "width:400px;"+
        		"height: 350px;"+
        		"background-color: white;"+
        		"border:thin solid black }"+
                "</style>"+
        		"</head>"+
                "<body>"+
        		"<div id='alert'>"+
        		"<p>This exercise has not yet been made part of an assignment.  Results can be "+
        		"computed only for assignments.</p>"+
        		"<p>If this is an exercise that you have created, you can view the content by "+
        		"clicking 'edit' in the 'Create/edit exercises' tab."+
        		"</div>"+
                "</body></html>";        

		out.println(docType + printString); 
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
