package edu.byu.isys413.cstinnet.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.byu.isys413.cstinnet.web.*;
import edu.byu.isys413.data.*;

public class Validation implements Action {

	/** Constructor */
	public Validation(){		
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    // ensure we have a number to guess for
	    HttpSession session = request.getSession();
	    
	    Customer cust = BusinessObjectDAO.getInstance().searchForBO("Customer", new SearchCriteria("validationcode", request.getParameter("vid")));	    
	    cust.setValidated(true);
	    cust.save();
	    // log the user in
	    session.setAttribute("userid", cust.getId());    
	    return "Validated.jsp";
	}
	
}
