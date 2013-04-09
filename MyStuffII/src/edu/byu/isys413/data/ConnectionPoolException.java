/////////////////////////////////////////////////////////////////
///   This file is an example of an Object Relational Mapping in
///   the ISys Core at Brigham Young University.  Students
///   may use the code as part of the 413 course in their
///   milestones following this one, but no permission is given
///   to use this code is any other way.  Since we will likely
///   use this code again in a future year, please DO NOT post
///   the code to a web site, share it with others, or pass
///   it on in any way.

package edu.byu.isys413.data;

/**
 * Signals that there are no more connections left in the pool.
 *
 * @author Conan C. Albrecht
 * @version 2010-02-01
 */
public class ConnectionPoolException extends Exception {
  
  /** Creates a new instance of ConnectionPoolException */
  public ConnectionPoolException(String s) {
    super(s);
  }//constructor
  
  /** Creates a new instance of ConnectionPoolException, with an embedded exception */
  public ConnectionPoolException(String s, Throwable t) {
    super(s, t);
  }//constructor
  
}//class
