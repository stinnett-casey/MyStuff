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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.ibm.icu.util.Calendar;

/**
 * Tests for the program.  
 *
 * See http://www.junit.org/apidocs/org/junit/Assert.html for the
 * available assertions you can make.
 * 
 * @version 1.2
 */
public class Tester {

    // for comparing dates
    SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    public Tester() throws Exception {
        CreateDB.main(null);
    }

    /** Example test */
    @Test
    public void TestExample() throws Exception {
      String st1 = "Hi There";
      String st2 = "Hi There";
      assertEquals(st1, st2);
    }





    /** Test the Employee BO (also tests the Person BO) */
    @Test
    public void TestEmployee() throws Exception {
      Employee emp1 = BusinessObjectDAO.getInstance().create("Employee", "emp7");
      emp1.setUsername("cstinnet");
      emp1.setFirst_name("Casey");
      emp1.setMiddle_name("John");
      emp1.setLast_name("Stinnett");
      emp1.setHire_date(new Date());
      emp1.setSalary(100000.50f);
      emp1.setPhone("9282432783");
      emp1.setStoreid("store1");
      emp1.setDirty();
      emp1.setObjectAlreadyInDB(false);
      emp1.save();

      Cache.getInstance().clear();
      //Tests reading from the database
      Employee emp2 = BusinessObjectDAO.getInstance().read("emp7");
      assertEquals(emp1.getId().trim(), emp2.getId().trim());
      assertEquals(emp1.getUsername(), emp2.getUsername());
      assertEquals(emp1.getFirst_name(), emp2.getFirst_name());
      assertEquals(emp1.getMiddle_name(), emp2.getMiddle_name());
      assertEquals(emp1.getLast_name(), emp2.getLast_name());
      assertEquals(SDF.format(emp1.getHire_date()), SDF.format(emp2.getHire_date()));
      assertTrue(emp1.getSalary() - emp2.getSalary() < 0.1);
      assertEquals(emp1.getPhone(), emp2.getPhone());
      assertEquals(emp1.getStoreid(), emp2.getStoreid());

      emp2.setUsername("katysue");
      emp2.setFirst_name("Chelsea");
      emp2.setMiddle_name("Kay");
      emp2.setLast_name("Stinnett");
      emp2.setHire_date(new Date());
      emp2.setSalary(108888.50f);
      emp2.setPhone("9282436936");
      emp2.setStoreid("store2");
      emp2.save();
      
      Cache.getInstance().clear();
      
      Employee emp3 = BusinessObjectDAO.getInstance().read("emp7");
      //Tests reading from the database
      assertEquals(emp2.getId().trim(), emp3.getId().trim());
      assertEquals(emp2.getUsername(), emp3.getUsername());
      assertEquals(emp2.getFirst_name(), emp3.getFirst_name());
      assertEquals(emp2.getMiddle_name(), emp3.getMiddle_name());
      assertEquals(emp2.getLast_name(), emp3.getLast_name());
      assertEquals(SDF.format(emp2.getHire_date()), SDF.format(emp3.getHire_date()));
      assertTrue(emp2.getSalary() - emp3.getSalary() < 0.1);
      assertEquals(emp2.getPhone(), emp3.getPhone());
      assertEquals(emp2.getStoreid(), emp3.getStoreid());
      
      // emp4 should now have been read from the Cache and be the same object
      Employee emp4 = BusinessObjectDAO.getInstance().read("emp7");
      assertEquals(emp3, emp4);

      // test the search methods
      List<Employee> emps = BusinessObjectDAO.getInstance().searchForAll("Employee");
      assertEquals(emps.size(), 3);  // 2 from CreateDB
      Employee emp = BusinessObjectDAO.getInstance().searchForBO("Employee", new SearchCriteria("id", "emp1"));
      assertEquals(emp.getId().trim(), "emp1");
      List<Employee> emps2 = BusinessObjectDAO.getInstance().searchForList("Employee", new SearchCriteria("username", "%e%", SearchCriteria.LIKE));
      assertEquals(emps2.size(), 3);

   // test deleting
      BusinessObjectDAO.getInstance().delete(emp2);
      

    }


    /** Test the Store BO */
    @Test
    public void TestStore() throws Exception {
        Store store1 = BusinessObjectDAO.getInstance().create("Store", "store7");
        store1.setLocation("Provo");
        store1.setPhone("5478851332");
        store1.setAddress("11 N 400 E");
        store1.setState("Utah");
        store1.setCity("Provo");
        store1.setZip("84604");
        store1.setManagerid("emp2");
        store1.setSalestaxrate(.06f);
        store1.save();
        

        Cache.getInstance().clear();
        //Tests reading from the database
        Store store2 = BusinessObjectDAO.getInstance().read("store7");
        assertEquals(store1.getId().trim(), store2.getId().trim());
        assertEquals(store1.getLocation(), store2.getLocation());
        assertEquals(store1.getManagerid(), store2.getManagerid());
        assertEquals(store1.getPhone(), store2.getPhone());
        assertEquals(store1.getAddress(), store2.getAddress());
        assertEquals(store1.getState(), store2.getState());
        assertEquals(store1.getCity(), store2.getCity());
        assertEquals(store1.getZip(), store2.getZip());
        assertTrue(store1.getSalestaxrate() - store2.getSalestaxrate() == 0);

        store2.setLocation("Ogden");
        store2.setManagerid("emp1");
        store2.setPhone("8018675309");
        store2.setAddress("250 N 900 E");
        store2.setState("Utah");
        store2.setCity("Ogden");
        store2.setZip("84701");
        store2.setSalestaxrate(.052f);
        store2.save();
        
        Cache.getInstance().clear();
        
        Store store3 = BusinessObjectDAO.getInstance().read("store7");
        //Tests reading from the database
        assertEquals(store2.getId().trim(), store3.getId().trim());
        assertEquals(store2.getLocation(), store3.getLocation());
        assertEquals(store2.getManagerid(), store3.getManagerid());
        assertEquals(store2.getPhone(), store3.getPhone());
        assertEquals(store2.getAddress(), store3.getAddress());
        assertEquals(store2.getState(), store3.getState());
        assertEquals(store2.getCity(), store3.getCity());
        assertEquals(store2.getZip(), store3.getZip());
        assertTrue(store2.getSalestaxrate() - store3.getSalestaxrate() == 0);
        
        
        // store4 should now have been read from the Cache and be the same object
        Store store4 = BusinessObjectDAO.getInstance().read("store7");
        assertEquals(store3, store4);

     // test deleting
        BusinessObjectDAO.getInstance().delete(store2);
      // Employee tests the search methods, so no need to test them again -Albrecht
    }
    
    /** Test the StoreProduct BO */
    @Test
    public void TestStoreProduct() throws Exception {
    	
    	ConceptualProduct conprod = BusinessObjectDAO.getInstance().create("ConceptualProduct", "concepprod1");
    	Store store = BusinessObjectDAO.getInstance().create("Store", "store1");
    	
        StoreProduct storeproduct1 = BusinessObjectDAO.getInstance().create("StoreProduct", "storeproduct7");
        storeproduct1.setStoreId(store);
        storeproduct1.setConceptualProductId(conprod);
        storeproduct1.setQuantity(45);
        storeproduct1.setShelf_location("a3s9");
        storeproduct1.save();
        

        Cache.getInstance().clear();
        //Tests reading from the database
        StoreProduct storeproduct2 = BusinessObjectDAO.getInstance().read("storeproduct7");
        assertEquals(storeproduct1.getId().trim(), storeproduct2.getId().trim());
        assertEquals(storeproduct1.getStoreId(), storeproduct2.getStoreId());
        assertEquals(storeproduct1.getConceptualProductId(), storeproduct2.getConceptualProductId());
        assertEquals(storeproduct1.getQuantity(), storeproduct2.getQuantity());
        assertEquals(storeproduct1.getShelf_location(), storeproduct2.getShelf_location());

        storeproduct2.setStoreId(store);
        storeproduct2.setConceptualProductId(conprod);
        storeproduct2.setQuantity(32);
        storeproduct2.setShelf_location("a5s16");
        storeproduct2.save();
        
        Cache.getInstance().clear();
        
        StoreProduct storeproduct3 = BusinessObjectDAO.getInstance().read("storeproduct7");
        //Tests reading from the database
        assertEquals(storeproduct3.getId().trim(), storeproduct2.getId().trim());
        assertEquals(storeproduct3.getStoreId(), storeproduct2.getStoreId());
        assertEquals(storeproduct3.getConceptualProductId(), storeproduct2.getConceptualProductId());
        assertEquals(storeproduct3.getQuantity(), storeproduct2.getQuantity());
        assertEquals(storeproduct3.getShelf_location(), storeproduct2.getShelf_location());
        
        
        // storeproduct4 should now have been read from the Cache and be the same object
        StoreProduct storeproduct4 = BusinessObjectDAO.getInstance().read("storeproduct7");
        assertEquals(storeproduct3, storeproduct4);

     // test deleting
        BusinessObjectDAO.getInstance().delete(storeproduct2);
      // Employee tests the search methods, so no need to test them again -Albrecht
    }   
    
    
    

    /** Test the Customer BO */
    @Test
    public void TestCustomer() throws Exception {
        Customer customer1 = BusinessObjectDAO.getInstance().create("Customer", "cust7");
        customer1.setFirst_name("Charlie");
        customer1.setLast_name("Buckets");
        customer1.setPhone("801-774-3564");
        customer1.setEmail("charlie@buckets.com");
        customer1.setAddress("16 N 987 E");
        customer1.setState("Maryland");
        customer1.setCity("New Hampshire");
        customer1.setZip("48872");
        customer1.save();
        

        Cache.getInstance().clear();
        
        //Tests reading from the database
        Customer customer2 = BusinessObjectDAO.getInstance().read("cust7");
        assertEquals(customer1.getId().trim(), customer2.getId().trim());
        assertEquals(customer1.getFirst_name(), customer2.getFirst_name());
        assertEquals(customer1.getLast_name(), customer2.getLast_name());
        assertEquals(customer1.getPhone(), customer2.getPhone());
        assertEquals(customer1.getEmail(), customer2.getEmail());
        assertEquals(customer1.getAddress(), customer2.getAddress());
        assertEquals(customer1.getState(), customer2.getState());
        assertEquals(customer1.getCity(), customer2.getCity());
        assertEquals(customer1.getZip(), customer2.getZip());
        

        customer2.setFirst_name("Melony");
        customer2.setLast_name("Sheets");
        customer2.setPhone("801-321-2222");
        customer2.setEmail("cMelony@buckets.com");
        customer2.setAddress("160 N 74 E");
        customer2.setState("New York");
        customer2.setCity("New York");
        customer2.setZip("33248");
        customer2.save();
        
        Cache.getInstance().clear();
        
        Customer customer3 = BusinessObjectDAO.getInstance().read("cust7");
        //Tests reading from the database
        assertEquals(customer3.getId().trim(), customer2.getId().trim());
        assertEquals(customer3.getFirst_name(), customer2.getFirst_name());
        assertEquals(customer3.getLast_name(), customer2.getLast_name());
        assertEquals(customer3.getPhone(), customer2.getPhone());
        assertEquals(customer3.getEmail(), customer2.getEmail());
        assertEquals(customer3.getAddress(), customer2.getAddress());
        assertEquals(customer3.getState(), customer2.getState());
        assertEquals(customer3.getCity(), customer2.getCity());
        assertEquals(customer3.getZip(), customer2.getZip());
        
        // customer4 should now have been read from the Cache and be the same object
        Customer customer4 = BusinessObjectDAO.getInstance().read("cust7");
        assertEquals(customer3, customer4);

     // test deleting
        BusinessObjectDAO.getInstance().delete(customer2);
      // Employee tests the search methods, so no need to test them again -Albrecht
    }
    
    
    /** Test the ConceptualProduct BO */
    @Test
    public void TestConceptualProduct() throws Exception {
        ConceptualProduct conceptualproduct1 = BusinessObjectDAO.getInstance().create("ConceptualProduct", "conceptualproduct7");
        conceptualproduct1.setProduct_name("Boxing gloves");
        conceptualproduct1.setDescription("Very red.");
        conceptualproduct1.setManufacturer("Boxie Co.");
        conceptualproduct1.setAvg_cost(23.87f);
        conceptualproduct1.setCommissionrate(.012f);
        conceptualproduct1.setSku("688L");
        conceptualproduct1.save();
        

        Cache.getInstance().clear();
        
        //Tests reading from the database
        ConceptualProduct conceptualproduct2 = BusinessObjectDAO.getInstance().read("conceptualproduct7");
        assertEquals(conceptualproduct1.getId().trim(), conceptualproduct2.getId().trim());
        assertEquals(conceptualproduct1.getProduct_name(), conceptualproduct2.getProduct_name());
        assertEquals(conceptualproduct1.getDescription(), conceptualproduct2.getDescription());
        assertEquals(conceptualproduct1.getManufacturer(), conceptualproduct2.getManufacturer());
        assertTrue(conceptualproduct1.getAvg_cost() - conceptualproduct2.getAvg_cost() < .01);
        assertTrue(conceptualproduct1.getCommissionrate() - conceptualproduct2.getCommissionrate() < .01);
        assertEquals(conceptualproduct1.getSku(), conceptualproduct2.getSku());
        

        conceptualproduct2.setProduct_name("Rabbits");
        conceptualproduct2.setDescription("Very blue.");
        conceptualproduct2.setManufacturer("Minnos.");
        conceptualproduct2.setAvg_cost(29.87f);
        conceptualproduct2.setCommissionrate(.002f);
        conceptualproduct2.setSku("312w");
        conceptualproduct2.save();
        
        Cache.getInstance().clear();
        
        ConceptualProduct conceptualproduct3 = BusinessObjectDAO.getInstance().read("conceptualproduct7");
        //Tests reading from the database
        assertEquals(conceptualproduct3.getId().trim(), conceptualproduct2.getId().trim());
        assertEquals(conceptualproduct3.getProduct_name(), conceptualproduct2.getProduct_name());
        assertEquals(conceptualproduct3.getDescription(), conceptualproduct2.getDescription());
        assertEquals(conceptualproduct3.getManufacturer(), conceptualproduct2.getManufacturer());
        assertTrue(conceptualproduct3.getAvg_cost() - conceptualproduct2.getAvg_cost() < .03);
        assertTrue(conceptualproduct3.getCommissionrate() - conceptualproduct2.getCommissionrate() < .03);
        assertEquals(conceptualproduct3.getSku(), conceptualproduct2.getSku());
        
        // conceptualproduct4 should now have been read from the Cache and be the same object
        ConceptualProduct conceptualproduct4 = BusinessObjectDAO.getInstance().read("conceptualproduct7");
        assertEquals(conceptualproduct3, conceptualproduct4);

     // test deleting
        BusinessObjectDAO.getInstance().delete(conceptualproduct2);
      // Employee tests the search methods, so no need to test them again -Albrecht
    }
    
    
    /** Test the physicalproduct BO */
    @Test
    public void Testphysicalproduct() throws Exception {
        PhysicalProduct physicalproduct1 = BusinessObjectDAO.getInstance().create("PhysicalProduct", "physicalproduct7");
        physicalproduct1.setSerialnumber("#963852");
        physicalproduct1.setShelflocation("a5s9");
        physicalproduct1.setDatepurchased(SDF.parse("2013-2-12"));
        physicalproduct1.setCost(60.89f);
        physicalproduct1.setCommissionrate(.012f);
        physicalproduct1.setConceptualproductid("concepprod2");
        physicalproduct1.setStatus("Sale");
        physicalproduct1.save();
        

        Cache.getInstance().clear();
        
        //Tests reading from the database
        PhysicalProduct physicalproduct2 = BusinessObjectDAO.getInstance().read("physicalproduct7");
        assertEquals(physicalproduct1.getId().trim(), physicalproduct2.getId().trim());
        assertEquals(physicalproduct1.getSerialnumber(), physicalproduct2.getSerialnumber());
        assertEquals(physicalproduct1.getShelflocation(), physicalproduct2.getShelflocation());
        assertEquals(physicalproduct1.getDatepurchased(), physicalproduct2.getDatepurchased());
        assertTrue(physicalproduct1.getCost() - physicalproduct2.getCost() < .01);
        assertTrue(physicalproduct1.getCommissionrate() - physicalproduct2.getCommissionrate() < .01);
        assertEquals(physicalproduct1.getConceptualproductid(), physicalproduct2.getConceptualproductid());
        assertEquals(physicalproduct1.getStatus(), physicalproduct2.getStatus());
        

        physicalproduct2.setSerialnumber("#321547");
        physicalproduct2.setShelflocation("a2s9");
        physicalproduct2.setDatepurchased(SDF.parse("2013-2-3"));
        physicalproduct2.setCost(69.81f);
        physicalproduct2.setCommissionrate(.01f);
        physicalproduct2.setConceptualproductid("concepprod3");
        physicalproduct2.setStatus("Rent");
        physicalproduct2.save();
        
        Cache.getInstance().clear();
        
        PhysicalProduct physicalproduct3 = BusinessObjectDAO.getInstance().read("physicalproduct7");
        //Tests reading from the database
        assertEquals(physicalproduct3.getId().trim(), physicalproduct2.getId().trim());
        assertEquals(physicalproduct3.getSerialnumber(), physicalproduct2.getSerialnumber());
        assertEquals(physicalproduct3.getShelflocation(), physicalproduct2.getShelflocation());
        assertEquals(physicalproduct3.getDatepurchased(), physicalproduct2.getDatepurchased());
        assertTrue(physicalproduct3.getCost() - physicalproduct2.getCost() < .03);
        assertTrue(physicalproduct3.getCommissionrate() - physicalproduct2.getCommissionrate() < .03);
        assertEquals(physicalproduct3.getConceptualproductid(), physicalproduct2.getConceptualproductid());
        assertEquals(physicalproduct3.getStatus(), physicalproduct2.getStatus());
        

        
        // physicalproduct4 should now have been read from the Cache and be the same object
        PhysicalProduct physicalproduct4 = BusinessObjectDAO.getInstance().read("physicalproduct7");
        assertEquals(physicalproduct3, physicalproduct4);

     // test deleting
        BusinessObjectDAO.getInstance().delete(physicalproduct2);
      // Employee tests the search methods, so no need to test them again -Albrecht
    }
    
    
    
    /** Test the Transaction BO */
    @Test
    public void TestTransaction() throws Exception {
    	Txn txn1 = BusinessObjectDAO.getInstance().create("Txn", "txn7");
        txn1.setTxndate(SDF.parse("2012-2-7"));
        txn1.setSubtotal(212.05f);
        txn1.setTax(23.54f);
        txn1.setTotal(235.47f);
        txn1.setEmployeeid("emp1");
        txn1.setCustomerid("cust1");
        txn1.setStoreid("store1");
        txn1.save();
        

        Cache.getInstance().clear();
        
        //Tests reading from the database
        Txn txn2 = BusinessObjectDAO.getInstance().read("txn7");
        assertEquals(txn1.getId().trim(), txn2.getId().trim());
        assertEquals(txn1.getTxndate(), txn2.getTxndate());
        assertTrue(txn1.getSubtotal() - txn2.getSubtotal() < .01);
        assertTrue(txn1.getTax() - txn2.getTax() < .01);
        assertTrue(txn1.getTotal() - txn2.getTotal() < .01);
        assertEquals(txn1.getEmployeeid(), txn2.getEmployeeid());
        assertEquals(txn1.getCustomerid(), txn2.getCustomerid());
        assertEquals(txn1.getStoreid(), txn2.getStoreid());
        

        txn2.setTxndate(SDF.parse("2011-2-6"));
        txn2.setSubtotal(202.55f);
        txn2.setTax(14.50f);
        txn2.setTotal(216.40f);
        txn2.setEmployeeid("emp2");
        txn2.setCustomerid("cust2");
        txn2.setStoreid("store2");
        txn2.save();
        
        Cache.getInstance().clear();
        
        Txn txn3 = BusinessObjectDAO.getInstance().read("txn7");
        //Tests reading from the database
        assertEquals(txn3.getId().trim(), txn2.getId().trim());
        assertEquals(txn3.getTxndate(), txn2.getTxndate());
        assertTrue(txn3.getSubtotal() - txn2.getSubtotal() < .03);
        assertTrue(txn3.getTax() - txn2.getTax() < .03);
        assertTrue(txn3.getTotal() - txn2.getTotal() < .03);
        assertEquals(txn3.getEmployeeid(), txn2.getEmployeeid());
        assertEquals(txn3.getCustomerid(), txn2.getCustomerid());
        assertEquals(txn3.getStoreid(), txn2.getStoreid());
        

        
        // txn4 should now have been read from the Cache and be the same object
        Txn txn4 = BusinessObjectDAO.getInstance().read("txn7");
        assertEquals(txn3, txn4);

     // test deleting
        BusinessObjectDAO.getInstance().delete(txn2);
      // Employee tests the search methods, so no need to test them again -Albrecht
    }
    
    
    /** Test the RevenueSource BO */
    @Test
    public void TestRevenueSource() throws Exception {
    	RevenueSource revenuesource1 = BusinessObjectDAO.getInstance().create("RevenueSource", "revenuesource7");
        revenuesource1.setChargeamount(32.04f);
        revenuesource1.setTypekind("Sale");
        revenuesource1.setTxnid("txn1");
        revenuesource1.save();
        

        Cache.getInstance().clear();
        
        //Tests reading from the database
        RevenueSource revenuesource2 = BusinessObjectDAO.getInstance().read("revenuesource7");
        assertEquals(revenuesource1.getId().trim(), revenuesource2.getId().trim());
        assertTrue(revenuesource1.getChargeamount() - revenuesource2.getChargeamount() < .01);
        assertEquals(revenuesource1.getTypekind(), revenuesource2.getTypekind());
        assertEquals(revenuesource1.getTxnid(), revenuesource2.getTxnid());
        

        revenuesource2.setChargeamount(32.04f);
        revenuesource2.setTypekind("Sale");
        revenuesource2.setTxnid("txn1");
        revenuesource2.save();
        
        Cache.getInstance().clear();
        
        RevenueSource revenuesource3 = BusinessObjectDAO.getInstance().read("revenuesource7");
        //Tests reading from the database
        assertEquals(revenuesource3.getId().trim(), revenuesource2.getId().trim());
        assertTrue(revenuesource3.getChargeamount() - revenuesource2.getChargeamount() < .01);
        assertEquals(revenuesource3.getTypekind(), revenuesource2.getTypekind());
        assertEquals(revenuesource3.getTxnid(), revenuesource2.getTxnid());
        

        
        // revenuesource4 should now have been read from the Cache and be the same object
        RevenueSource revenuesource4 = BusinessObjectDAO.getInstance().read("revenuesource7");
        assertEquals(revenuesource3, revenuesource4);

     // test deleting
        BusinessObjectDAO.getInstance().delete(revenuesource2);
      // Employee tests the search methods, so no need to test them again -Albrecht
    }
    
    
    
    /** Test the Sale BO */
    @Test
    public void TestSale() throws Exception {
    	ConceptualProduct conprod = BusinessObjectDAO.getInstance().create("ConceptualProduct", "concepprod9");
    	
    	Sale sale1 = BusinessObjectDAO.getInstance().create("Sale", "sale7");
        sale1.setQuantity(2);
        sale1.setProdid(conprod);
        sale1.save();
        

        Cache.getInstance().clear();
        
        //Tests reading from the database
        Sale sale2 = BusinessObjectDAO.getInstance().read("sale7");
        assertEquals(sale1.getId().trim(), sale2.getId().trim());
        assertEquals(sale1.getQuantity(), sale2.getQuantity());
        assertEquals(sale1.getProductid(), sale2.getProductid());
        
        ConceptualProduct conprod1 = BusinessObjectDAO.getInstance().create("ConceptualProduct", "concepprod10");
        
        
        sale2.setQuantity(5);
        sale2.setProdid(conprod1);
        sale2.save();
        
        Cache.getInstance().clear();
        
        Sale sale3 = BusinessObjectDAO.getInstance().read("sale7");
        //Tests reading from the database
        assertEquals(sale3.getId().trim(), sale2.getId().trim());
        assertEquals(sale3.getQuantity(), sale2.getQuantity());
        assertEquals(sale3.getProductid(), sale2.getProductid());
        

        
        // sale4 should now have been read from the Cache and be the same object
        Sale sale4 = BusinessObjectDAO.getInstance().read("sale7");
        assertEquals(sale3, sale4);

     // test deleting
        BusinessObjectDAO.getInstance().delete(sale2);
      // Employee tests the search methods, so no need to test them again -Albrecht
    }
    
    
    /** Test the JournalEntry BO */
    @Test
    public void TestJournalEntry() throws Exception {
    	
    	JournalEntry journalentry1 = BusinessObjectDAO.getInstance().create("JournalEntry", "journalentry7");
        journalentry1.setJedate(SDF.parse("2012-2-1"));
        journalentry1.save();
        

        Cache.getInstance().clear();
        
        //Tests reading from the database
        JournalEntry journalentry2 = BusinessObjectDAO.getInstance().read("journalentry7");
        assertEquals(journalentry1.getId().trim(), journalentry2.getId().trim());
        assertEquals(journalentry1.getJedate(), journalentry2.getJedate());
        

        journalentry2.setJedate(SDF.parse("2012-6-18"));
        journalentry2.save();
        
        Cache.getInstance().clear();
        
        JournalEntry journalentry3 = BusinessObjectDAO.getInstance().read("journalentry7");
        //Tests reading from the database
        assertEquals(journalentry3.getId().trim(), journalentry2.getId().trim());
        assertEquals(journalentry3.getJedate(), journalentry2.getJedate());
        

        
        // journalentry4 should now have been read from the Cache and be the same object
        JournalEntry journalentry4 = BusinessObjectDAO.getInstance().read("journalentry7");
        assertEquals(journalentry3, journalentry4);

     // test deleting
        BusinessObjectDAO.getInstance().delete(journalentry2);
      // Employee tests the search methods, so no need to test them again -Albrecht
    }
    
    
    /** Test the DebitCredit BO */
    @Test
    public void TestDebitCredit() throws Exception {
    	DebitCredit debitcredit1 = BusinessObjectDAO.getInstance().create("DebitCredit", "dc7");
        debitcredit1.setDebit(true);
        debitcredit1.setGeneralledgername("Cash");
        debitcredit1.setAmount(321.45f);
        debitcredit1.setJournalentryid("je1");
        debitcredit1.save();
        

        Cache.getInstance().clear();
        
        //Tests reading from the database
        DebitCredit debitcredit2 = BusinessObjectDAO.getInstance().read("dc7");
        assertEquals(debitcredit1.getId().trim(), debitcredit2.getId().trim());
        assertEquals(debitcredit1.getGeneralledgername(), debitcredit2.getGeneralledgername());
        assertTrue(debitcredit1.getAmount() - debitcredit2.getAmount() < .01);
        assertEquals(debitcredit1.getJournalentryid(), debitcredit2.getJournalentryid());
        

        debitcredit2.setDebit(false);
        debitcredit2.setGeneralledgername("Commission Expense");
        debitcredit2.setAmount(51.45f);
        debitcredit2.setJournalentryid("je2");
        debitcredit2.save();
        
        Cache.getInstance().clear();
        
        DebitCredit debitcredit3 = BusinessObjectDAO.getInstance().read("dc7");
        //Tests reading from the database
        assertEquals(debitcredit3.getId().trim(), debitcredit2.getId().trim());
        assertEquals(debitcredit3.getGeneralledgername(), debitcredit2.getGeneralledgername());
        assertTrue(debitcredit3.getAmount() - debitcredit2.getAmount() < .01);
        assertEquals(debitcredit3.getJournalentryid(), debitcredit2.getJournalentryid());
        

        
        // debitcredit4 should now have been read from the Cache and be the same object
        DebitCredit debitcredit4 = BusinessObjectDAO.getInstance().read("dc7");
        assertEquals(debitcredit3, debitcredit4);

     // test deleting
        BusinessObjectDAO.getInstance().delete(debitcredit2);
      // Employee tests the search methods, so no need to test them again -Albrecht
    }
    
    
    /** Test the Commission BO */
    @Test
    public void TestCommission() throws Exception {
    	Commission commission1 = BusinessObjectDAO.getInstance().create("Commission", "commission7");
        commission1.setEmployeeid("emp1");
        commission1.setAmount(65.45f);
        commission1.setCommdate(SDF.parse("2012-5-8"));
        commission1.save();
        

        Cache.getInstance().clear();
        
        //Tests reading from the database
        Commission commission2 = BusinessObjectDAO.getInstance().read("commission7");
        assertEquals(commission1.getId().trim(), commission2.getId().trim());
        assertTrue(commission1.getAmount() - commission2.getAmount() < .01);
        assertEquals(commission1.getEmployeeid(), commission2.getEmployeeid());
        assertEquals(commission1.getCommdate(), commission2.getCommdate());
        

        commission2.setEmployeeid("emp2");
        commission2.setAmount(78.45f);
        commission2.setCommdate(SDF.parse("2012-5-12"));
        commission2.save();
        
        Cache.getInstance().clear();
        
        Commission commission3 = BusinessObjectDAO.getInstance().read("commission7");
        //Tests reading from the database
        assertEquals(commission3.getId().trim(), commission2.getId().trim());
        assertTrue(commission3.getAmount() - commission2.getAmount() < .01);
        assertEquals(commission3.getEmployeeid(), commission2.getEmployeeid());
        assertEquals(commission3.getCommdate(), commission2.getCommdate());
        

        
        // commission4 should now have been read from the Cache and be the same object
        Commission commission4 = BusinessObjectDAO.getInstance().read("commission7");
        assertEquals(commission3, commission4);

     // test deleting
        BusinessObjectDAO.getInstance().delete(commission2);
      // Employee tests the search methods, so no need to test them again -Albrecht
    }
    
    
    /** Test the GeneralLedger BO */
    @Test
    public void TestGeneralLedger() throws Exception {
    	GeneralLedger generalledger1 = BusinessObjectDAO.getInstance().create("GeneralLedger", "generalledger7");
        generalledger1.setAccountname("Sales Tax");
        generalledger1.setBalance(52.47f);
        generalledger1.setTypekind("Debit");
        generalledger1.save();
        

        Cache.getInstance().clear();
        
        //Tests reading from the database
        GeneralLedger generalledger2 = BusinessObjectDAO.getInstance().read("generalledger7");
        assertEquals(generalledger1.getId().trim(), generalledger2.getId().trim());
        assertEquals(generalledger1.getAccountname(), generalledger2.getAccountname());
        assertTrue(generalledger1.getBalance() - generalledger2.getBalance() < .01);
        assertEquals(generalledger1.getTypekind(), generalledger2.getTypekind());
        

        generalledger2.setAccountname("Cash");
        generalledger2.setBalance(520.47f);
        generalledger2.setTypekind("Credit");
        generalledger2.save();
        
        Cache.getInstance().clear();
        
        GeneralLedger generalledger3 = BusinessObjectDAO.getInstance().read("generalledger7");
        //Tests reading from the database
        assertEquals(generalledger3.getId().trim(), generalledger2.getId().trim());
        assertEquals(generalledger3.getAccountname(), generalledger2.getAccountname());
        assertTrue(generalledger3.getBalance() - generalledger2.getBalance() < .01);
        assertEquals(generalledger3.getTypekind(), generalledger2.getTypekind());
        

        
        // generalledger4 should now have been read from the Cache and be the same object
        GeneralLedger generalledger4 = BusinessObjectDAO.getInstance().read("generalledger7");
        assertEquals(generalledger3, generalledger4);

     // test deleting
        BusinessObjectDAO.getInstance().delete(generalledger2);
      // Employee tests the search methods, so no need to test them again -Albrecht
    }
    
    
    /** Test the Payment BO */
    @Test
    public void TestPayment() throws Exception {
    	Payment payment1 = BusinessObjectDAO.getInstance().create("Payment", "payment7");
        payment1.setAmount(26.21f);
        payment1.setChange(5.01f);
        payment1.setTypekind("Cash");
        payment1.save();
        

        Cache.getInstance().clear();
        
        //Tests reading from the database
        Payment payment2 = BusinessObjectDAO.getInstance().read("payment7");
        assertEquals(payment1.getId().trim(), payment2.getId().trim());
        assertTrue(payment1.getAmount() - payment2.getAmount() < .01);
        assertTrue(payment1.getChange() - payment2.getChange() < .01);
        assertEquals(payment1.getTypekind(), payment2.getTypekind());
        

        payment2.setAmount(25.21f);
        payment2.setChange(5.91f);
        payment2.setTypekind("Debit");
        payment2.save();
        
        Cache.getInstance().clear();
        
        Payment payment3 = BusinessObjectDAO.getInstance().read("payment7");
        //Tests reading from the database
        assertEquals(payment3.getId().trim(), payment2.getId().trim());
        assertTrue(payment3.getAmount() - payment2.getAmount() < .01);
        assertTrue(payment3.getChange() - payment2.getChange() < .01);
        assertEquals(payment3.getTypekind(), payment2.getTypekind());
        

        
        // payment4 should now have been read from the Cache and be the same object
        Payment payment4 = BusinessObjectDAO.getInstance().read("payment7");
        assertEquals(payment3, payment4);

     // test deleting
        BusinessObjectDAO.getInstance().delete(payment2);
      // Employee tests the search methods, so no need to test them again -Albrecht
    }
    
    
    
    
    /** Test the Rental BO */
    @Test
    public void TestRental() throws Exception {
    	Rental rental1 = BusinessObjectDAO.getInstance().create("Rental", "rental7");
        rental1.setChargeamount(2.30f);
        
        Calendar c = Calendar.getInstance();
        
        rental1.setDateout(c.getTime());
        
        c.add(Calendar.DAY_OF_MONTH, 3);
        
        rental1.setDatedue(c.getTime());
        rental1.setDatein(c.getTime());
        rental1.setRemindersent(true);
        rental1.setEmployeeid("emp1");
        rental1.setForrentid("forrent1");
        rental1.save();
        

        Cache.getInstance().clear();
        
        //Tests reading from the database
        Rental rental2 = BusinessObjectDAO.getInstance().read("rental7");
        assertEquals(rental1.getId().trim(), rental2.getId().trim());
        assertTrue(rental1.getChargeamount() - rental2.getChargeamount() < .01);
        assertEquals(rental1.getDateout(), rental2.getDateout());
        assertEquals(rental1.getDatein(), rental2.getDatein());
        assertEquals(rental1.getDatedue(), rental2.getDatedue());
        assertEquals(rental1.isRemindersent(), rental2.isRemindersent());
        assertEquals(rental1.getEmployeeid(), rental2.getEmployeeid());
        assertEquals(rental1.getForrentid(), rental2.getForrentid());
        

        rental2.setChargeamount(2.30f);
        
        Calendar c1 = Calendar.getInstance();
        
        rental2.setDateout(c1.getTime());
        
        c1.add(Calendar.DAY_OF_MONTH, 3);
        
        rental2.setDatedue(c1.getTime());
        rental2.setDatein(c1.getTime());
        rental2.setRemindersent(true);
        rental2.setEmployeeid("emp2");
        rental2.setForrentid("forrent2");
        rental2.save();
        
        Cache.getInstance().clear();
        
        Rental rental3 = BusinessObjectDAO.getInstance().read("rental7");
        //Tests reading from the database
        assertEquals(rental3.getId().trim(), rental2.getId().trim());
        assertTrue(rental3.getChargeamount() - rental2.getChargeamount() < .03);
        assertEquals(rental3.getDateout(), rental2.getDateout());
        assertEquals(rental3.getDatein(), rental2.getDatein());
        assertEquals(rental3.getDatedue(), rental2.getDatedue());
        assertEquals(rental3.isRemindersent(), rental2.isRemindersent());
        assertEquals(rental3.getEmployeeid(), rental2.getEmployeeid());
        assertEquals(rental3.getForrentid(), rental2.getForrentid());
        

        
        // rental4 should now have been read from the Cache and be the same object
        Rental rental4 = BusinessObjectDAO.getInstance().read("rental7");
        assertEquals(rental3, rental4);

     // test deleting
        BusinessObjectDAO.getInstance().delete(rental2);
      // Employee tests the search methods, so no need to test them again -Albrecht
    }
    
    /** Test the ForRent BO */
    @Test
    public void TestForRent() throws Exception {
    	ForRent forrent1 = BusinessObjectDAO.getInstance().create("ForRent", "forrent7");
        forrent1.setTimesrented(5);
        forrent1.save();
        

        Cache.getInstance().clear();
        
        //Tests reading from the database
        ForRent forrent2 = BusinessObjectDAO.getInstance().read("forrent7");
        assertEquals(forrent1.getId().trim(), forrent2.getId().trim());
        assertEquals(forrent1.getTimesrented(), forrent2.getTimesrented());
        

        forrent2.setTimesrented(2);
        forrent2.save();
        
        Cache.getInstance().clear();
        
        ForRent forrent3 = BusinessObjectDAO.getInstance().read("forrent7");
        //Tests reading from the database
        assertEquals(forrent3.getId().trim(), forrent2.getId().trim());
        assertEquals(forrent1.getTimesrented(), forrent2.getTimesrented());
        

        
        // forrent4 should now have been read from the Cache and be the same object
        ForRent forrent4 = BusinessObjectDAO.getInstance().read("forrent7");
        assertEquals(forrent3, forrent4);

     // test deleting
        BusinessObjectDAO.getInstance().delete(forrent2);
      // Employee tests the search methods, so no need to test them again -Albrecht
    }
    
    
    /** Test the ForSale BO */
    @Test
    public void TestForSale() throws Exception {
    	ForSale forsale1 = BusinessObjectDAO.getInstance().create("ForSale", "forsale7");
        forsale1.setUsed(true);
        forsale1.save();
        

        Cache.getInstance().clear();
        
        //Tests reading from the database
        ForSale forsale2 = BusinessObjectDAO.getInstance().read("forsale7");
        assertEquals(forsale1.getId().trim(), forsale2.getId().trim());
        assertEquals(forsale1.isUsed(), forsale2.isUsed());
        

        forsale2.setUsed(false);
        forsale2.save();
        
        Cache.getInstance().clear();
        
        ForSale forsale3 = BusinessObjectDAO.getInstance().read("forsale7");
        //Tests reading from the database
        assertEquals(forsale3.getId().trim(), forsale2.getId().trim());
        assertEquals(forsale1.isUsed(), forsale2.isUsed());
        

        
        // forsale4 should now have been read from the Cache and be the same object
        ForSale forsale4 = BusinessObjectDAO.getInstance().read("forsale7");
        assertEquals(forsale3, forsale4);

     // test deleting
        BusinessObjectDAO.getInstance().delete(forsale2);
      // Employee tests the search methods, so no need to test them again -Albrecht
    }
    
    
    /** Test the Membership BO */
    @Test
    public void TestMembership() throws Exception {
    	Membership membership1 = BusinessObjectDAO.getInstance().create("Membership", "membership7");
        membership1.setCcnumber("123456789");
        Calendar c2 = Calendar.getInstance();
        membership1.setCustomerid("cust1");
        membership1.save();
        

        Cache.getInstance().clear();
        
        //Tests reading from the database
        Membership membership2 = BusinessObjectDAO.getInstance().read("membership7");
        assertEquals(membership1.getId().trim(), membership2.getId().trim());
        assertEquals(membership1.getCcnumber(), membership2.getCcnumber());
        assertEquals(membership1.getCustomerid(), membership2.getCustomerid());
        

        membership2.setCcnumber("987654321");
        Calendar c3 = Calendar.getInstance();
        membership2.setCustomerid("cust2");
        membership2.save();
        
        Cache.getInstance().clear();
        
        Membership membership3 = BusinessObjectDAO.getInstance().read("membership7");
        //Tests reading from the database
        assertEquals(membership3.getId().trim(), membership2.getId().trim());
        assertEquals(membership3.getCcnumber(), membership2.getCcnumber());
        assertEquals(membership3.getCustomerid(), membership2.getCustomerid());
        

        
        // membership4 should now have been read from the Cache and be the same object
        Membership membership4 = BusinessObjectDAO.getInstance().read("membership7");
        assertEquals(membership3, membership4);

     // test deleting
        BusinessObjectDAO.getInstance().delete(membership2);
      // Employee tests the search methods, so no need to test them again -Albrecht
    }
    
    
    /** Test the Fee BO */
    @Test
    public void TestFee() throws Exception {
    	Fee fee1 = BusinessObjectDAO.getInstance().create("Fee", "fee7");
        fee1.setAmount(152.30f);
        fee1.setRentalid("rental1");
        fee1.save();
        

        Cache.getInstance().clear();
        
        //Tests reading from the database
        Fee fee2 = BusinessObjectDAO.getInstance().read("fee7");
        assertEquals(fee1.getId().trim(), fee2.getId().trim());
        assertTrue(fee1.getAmount() - fee2.getAmount() < 0.1);
        assertEquals(fee1.getRentalid(), fee2.getRentalid());
        

        fee2.setAmount(12.47f);
        fee2.setRentalid("rental2");
        fee2.save();
        
        Cache.getInstance().clear();
        
        Fee fee3 = BusinessObjectDAO.getInstance().read("fee7");
        //Tests reading from the database
        assertEquals(fee3.getId().trim(), fee2.getId().trim());
        assertTrue(fee3.getAmount() - fee2.getAmount() < 0.1);
        assertEquals(fee3.getRentalid(), fee2.getRentalid());
        

        
        // fee4 should now have been read from the Cache and be the same object
        Fee fee4 = BusinessObjectDAO.getInstance().read("fee7");
        assertEquals(fee3, fee4);

     // test deleting
        BusinessObjectDAO.getInstance().delete(fee2);
      // Employee tests the search methods, so no need to test them again -Albrecht
    }
    
    
    /** Test the ConceptualRental BO */
    @Test
    public void TestConceptualRental() throws Exception {
    	ConceptualRental conceptualrental1 = BusinessObjectDAO.getInstance().create("ConceptualRental", "concepprod7");
        conceptualrental1.setPrice_per_day(12.50f);
        conceptualrental1.setReplacement_price(300.00f);
        conceptualrental1.save();
        

        Cache.getInstance().clear();
        
        //Tests reading from the database
        ConceptualRental conceptualrental2 = BusinessObjectDAO.getInstance().read("conceptualrental7");
        assertEquals(conceptualrental1.getId().trim(), conceptualrental2.getId().trim());
        assertTrue(conceptualrental1.getPrice_per_day() - conceptualrental2.getPrice_per_day() < 0.1);
        assertTrue(conceptualrental1.getReplacement_price() - conceptualrental2.getReplacement_price() < 0.1);
        

        conceptualrental2.setPrice_per_day(214.50f);
        conceptualrental2.setReplacement_price(365.70f);
        conceptualrental2.save();
        
        Cache.getInstance().clear();
        
        ConceptualRental conceptualrental3 = BusinessObjectDAO.getInstance().read("conceptualrental7");
        //Tests reading from the database
        assertEquals(conceptualrental3.getId().trim(), conceptualrental2.getId().trim());
        assertTrue(conceptualrental3.getPrice_per_day() - conceptualrental2.getPrice_per_day() < 0.1);
        assertTrue(conceptualrental3.getReplacement_price() - conceptualrental2.getReplacement_price() < 0.1);
        

        
        // conceptualrental4 should now have been read from the Cache and be the same object
        ConceptualRental conceptualrental4 = BusinessObjectDAO.getInstance().read("conceptualrental7");
        assertEquals(conceptualrental3, conceptualrental4);

     // test deleting
        BusinessObjectDAO.getInstance().delete(conceptualrental2);
      // Employee tests the search methods, so no need to test them again -Albrecht
    }
    

    
    

}