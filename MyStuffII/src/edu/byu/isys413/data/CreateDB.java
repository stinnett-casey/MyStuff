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

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Creates the database.  You can run this file to run the CreateDB.sql
 * file using the Connection Pool.
 *
 * - All comments in the CreateDB.sql file must end with semicolons (;)
 * - DROP TABLE errors are ignored when the table didn't exist
 * - Other errors stop the code immediately so you can fix them before
 * -   more SQL is run.
 *
 * Don't forget to change the connection string variables at the top of
 * the ConnectionPool.java file and the SQL_FILE variable in this file.
 *
 * Place "CreateDB.main(null)" in your tester's constructor to automatically
 * run the SQL script each time you start testing.
 *
 * @author Conan Albrecht
 * @version 1.1
*/
public class CreateDB {

    /** Set this to the full path of your file */
    public static final String SQL_FILE = "C:\\Users\\ISYStinnett\\Dropbox\\Java Programs 403\\MyStuffSprintII\\src\\edu\\byu\\isys413\\edu.byu.isys413.data\\CreateDB.sql";

    /** Drops everything from the database and recreates it */
    public static void main(String args[]) throws Exception {

        // get our connection
        Connection conn = ConnectionPool.getInstance().get();
        try{

            // start through the file
            Scanner scanner = new Scanner(new File(SQL_FILE)).useDelimiter(";");
            while (scanner.hasNext()) {
                String cmd = scanner.next().trim();
                if (cmd.startsWith("--") && cmd.contains("\n")) {
                    System.err.println(">>> Failed  >>> " + cmd);
                    System.err.println(">>> Reason  >>> You forgot to end a comment line with a semicolon (;).");
                    System.exit(1);

                } else if (cmd.startsWith("--") || cmd.isEmpty()) {
                    System.err.println(">>> Comment >>> " + cmd);

                }else if (cmd.toLowerCase().contains("drop table")) {
                    try {
                        Statement stmt = conn.createStatement();
                        stmt.execute(cmd);
                        stmt.close();
                        System.err.println(">>> Success >>> " + cmd);


                    }catch (SQLException e) {
                        if (e.getMessage().contains("not exist")) {
                            System.err.println(">>> Skipped >>> " + cmd);
                        }else{
                            System.err.println(">>> Failed  >>> " + cmd);
                            e.printStackTrace();
                            System.exit(1);
                        }//if
                    }

                }else {
                    try {                    	
                        Statement stmt = conn.createStatement();
                        stmt.execute(cmd);
                        stmt.close();
                        System.err.println(">>> Success >>> " + cmd);

                    }catch (SQLException e) {
                        System.err.println(">>> Failed  >>> " + cmd);
                        e.printStackTrace();
                        System.exit(1);
                    }

                }//if

                conn.commit();
            }//while

        }finally {
            ConnectionPool.getInstance().release(conn);

        }

    }//main

}//CreateDB class
