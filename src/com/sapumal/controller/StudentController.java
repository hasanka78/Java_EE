package com.sapumal.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sapumal.dao.StudentDAO;
import com.sapumal.dao.StudentDAOImplementation;
import com.sapumal.model.Student;


public class StudentController extends HttpServlet {

	private StudentDAO dao;
	private static final long serialVersionUID = 1L;
	public static final String lIST_STUDENT   =   "/listStudent.jsp";
	public static final String INSERT_OR_EDIT =   "/student.jsp";

	public StudentController() {
		dao = new StudentDAOImplementation();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String forward = "";
		String action = request.getParameter("action");


		if(action.equalsIgnoreCase("delete")) {
			forward = lIST_STUDENT;
			int studentId = Integer.parseInt( request.getParameter("studentId") );
			dao.deleteStudent(studentId);
			request.setAttribute("students", dao.getAllStudents() );
		}
		else if(action.equalsIgnoreCase("edit")) {
			forward = INSERT_OR_EDIT;
			int studentId = Integer.parseInt( request.getParameter("studentId") );
			Student student = dao.getStudentById(studentId);
			request.setAttribute("student", student);
		}
		else if(action.equalsIgnoreCase("insert")) {
			forward = INSERT_OR_EDIT;
		}
		else {
			forward = lIST_STUDENT;
			request.setAttribute("students", dao.getAllStudents() );
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Student student = new Student();
		//HttpSession session = request.getSession();
		Map<String, String> messages = new HashMap<String, String>();
		if(validateInput(request,messages,student)){
			//processStudent(request,student);
			if( student.getStudentId()==0 ){
				dao.addStudent(student);
				messages.put("success", "successfully added");
			}

			else {
				dao.updateStudent(student);
				messages.put("success", "successfully updated");
			}


			//request.setAttribute("messages", messages);
			//session.setAttribute("messages", messages);
			response.sendRedirect("StudentController?action=listStudent");	

		}
		else{
			messages.put("failure", "form is invalid");
			request.setAttribute("student", student);
			request.setAttribute("messages", messages);
			RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
			view.forward(request, response);
		}


	}






	private void processStudent(HttpServletRequest request, Student student) {
		int studentId = request.getParameter("studentId") == null || request.getParameter("studentId").isEmpty() ? 0 :Integer.parseInt(request.getParameter("studentId")) ;
		student.setFirstName( request.getParameter( "firstName" ) );
		student.setLastName( request.getParameter( "lastName" ) );
		student.setCourse( request.getParameter( "course" ) );
		student.setYear( Integer.parseInt( request.getParameter( "year" ) ) );
		student.setStudentId(studentId);
	}

	private boolean validateInput(HttpServletRequest request, Map<String, String> messages, Student student) {
		boolean state = true;
		if(request.getParameter("firstName") == null ||request.getParameter("firstName").isEmpty()){
			state = false;
			messages.put("errorUerName", "user name is not valid");
		}
		else if(!request.getParameter("firstName").matches("[A-Za-z0-9]+")){
			state = false;
			messages.put("errorUerName", "name format is wrong");
		}

		else if(request.getParameter("firstName").length()>20){
			state = false;
			messages.put("errorUerName", "name is too long");
		}
		
		processStudent(request,student);
		
		return state;
	}




}