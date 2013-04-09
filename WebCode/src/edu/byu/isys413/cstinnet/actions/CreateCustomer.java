package edu.byu.isys413.cstinnet.actions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.byu.isys413.cstinnet.web.*;
import edu.byu.isys413.data.*;

public class CreateCustomer implements Action {

	public CreateCustomer(){
	}
	
	 public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		    
		    HttpSession session = request.getSession();		  
		    
		    Customer cust = null;
		    cust = BusinessObjectDAO.getInstance().create("Customer");
		    cust.setFirst_name(request.getParameter("firstname"));
		    cust.setLast_name(request.getParameter("lastname"));
		    cust.setPhone(request.getParameter("phone"));
		    cust.setAddress(request.getParameter("address"));	
		    cust.setState(request.getParameter("state"));
		    cust.setCity(request.getParameter("city"));
		    cust.setZip(request.getParameter("zip"));
		    cust.setEmail(request.getParameter("email"));
		    cust.setPassword(request.getParameter("password"));
		    String vid = GUID.generate();
		    cust.setValidationcode(vid);
		    cust.save();
		    	   
	        
		    
		    Membership membership = null;
		    membership = BusinessObjectDAO.getInstance().create("Membership");
		    membership.setCcnumber(request.getParameter("ccnumber"));
		    membership.setCustomerid(cust.getId());
		    membership.save();
		  
		    
		    BatchEmail.send(MailSettings.smtpHost, MailSettings.smtpUserName, request.getParameter("email"), "Validation for MyStuff", "Dear "
		    + request.getParameter("firstname") + " " + request.getParameter("lastname") + ", \n\t Thank you for signing up with MyStuff! " +
		    "Here is your validation code: http://localhost:8080/Webcode/edu.byu.isys413.cstinnet.actions.Validation.action?vid=" + vid 
		    + "\n\nYou are sure to find all of the items that you need here! Hopefully you will like it enough to tell your friends and share the wealth. Cheers. \n\n -MyStuff");
		    
		    
		    return "Validation.jsp";
	 }
	
	
}
