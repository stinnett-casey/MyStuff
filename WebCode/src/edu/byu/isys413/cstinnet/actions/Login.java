package edu.byu.isys413.cstinnet.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import edu.byu.isys413.cstinnet.web.Action;
import edu.byu.isys413.data.BusinessObjectDAO;
import edu.byu.isys413.data.Customer;
import edu.byu.isys413.data.SearchCriteria;

public class Login implements Action {

	/** Constructor */
	public Login(){		
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    // ensure we have a number to guess for
	    HttpSession session = request.getSession();
	    System.out.println("Inside the login.java in process");
	    
	    Customer cust = null;
	    try {
		    cust = BusinessObjectDAO.getInstance().searchForBO("Customer", new SearchCriteria("email", request.getParameter("email")), new SearchCriteria("password", request.getParameter("password")));
		    		    
	    	session.setAttribute("userid", cust.getId());
	    	session.setAttribute("name", cust.getFirst_name());
	    	if (request.getParameter("return") == "json"){
	    		Gson gson = new Gson();
	    		System.out.println(gson.toJson(cust));
	    		return gson.toJson(cust);  	    		
	    	}
	    	else{
	    		 return "Products.jsp";
	    	}
	    	
	    }catch(Exception e){
	    	request.setAttribute("error", "Oh dear. Either your email or password did not work. Have you made an account with us?");
	    	return "Login.jsp";
	    }
	 
	   
	}
	
	
	
}
