package edu.byu.isys413.cstinnet.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.*;

import edu.byu.isys413.cstinnet.web.*;
import edu.byu.isys413.data.*;
import java.util.*;

public class GetConceptuals implements Action {
	
	/** Constructor */
	public GetConceptuals(){		
	}
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    // ensure we have a number to guess for
	    HttpSession session = request.getSession();
	    
	    String letters = request.getParameter("letters");
	    System.out.println(letters + " In getConceptuals line 26");
	    List <PhysicalProduct> returnList = new LinkedList <PhysicalProduct>();
	    
	    List <ConceptualProduct> conceptualList = BusinessObjectDAO.getInstance().searchForList("ConceptualProduct", new SearchCriteria("product_name", letters + "%", SearchCriteria.LIKE));
	    
	    for(ConceptualProduct cp: conceptualList){
	    	List <PhysicalProduct> physList = cp.getPhysicalProductsWithThisId();
	    	
	    	for(PhysicalProduct pp: physList){
	    		if (pp.getUsed().toString().equals(request.getParameter("condition"))){
	    			returnList.add(pp);
	    		}
	    		
	    	}//inner for
	    }//outer for
	    
	    Gson gson = new Gson();
	    System.out.println(gson.toJson(returnList));
	    
	    return gson.toJson(returnList);
	    
	}

}
