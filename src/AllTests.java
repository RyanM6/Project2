// --== CS400 File Header Information ==--
// Name: Ryan McBrayer
// Email: rmcbrayer@wisc.edu
// Team: ED
// TA: Keren
// Role: Test Engineer
// Lecturer: Florian
// Notes to Grader: After running the tests, you must open the data.txt file and delete the two
// new entries(the last two lines) so when the tests run again you are not adding duplicates
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")

@RunWith(Suite.class)
@SuiteClasses({AllTests.class})
public class AllTests {

  private static BackEnd test;
  @BeforeAll
  public static void setupBackEnd() {
     test = new BackEnd();
  }


  /*
   * Front End Tests
   */
  @Test
  // Compares expected results of FrontEnd.isValidDate with actual results
  void testValidDate() {
    // test with invalid day of month (35th of June)
    if (IFrontEnd.isValidDate(35, 6, 2000) != false) {
      fail("isValidDate() returns true with invalid parameters");
    }
    // test with invalid date
    if (IFrontEnd.isValidDate(-1, 0, -1) != false) {
      fail("isValidDate() returns true with invalid parameters");
    }
    // test with valid date (3rd of June 2000)
    if (IFrontEnd.isValidDate(3, 6, 2000) != true) {
      fail("isValidDate() returns false with valid paramaters");
    }
    // test with another valid date (19th of October 2010)
    if (IFrontEnd.isValidDate(19, 10, 2010) != true) {
      fail("isValidDate() returns false with valid paramaters");
    }
  }

  @Test
  // Compares expected results of FrontEnd.isValidEmail with actual results
  void testValidEmail() {
    // test with null
    if (IFrontEnd.isValidEmail(null) != false) {
      fail("isValidEmail() fails with null param");
    }
    // test with invalid input
    if (IFrontEnd.isValidEmail("Rick Astley") != false) {
      fail("isValidEmail() returns true with invalid input");
    }
    // test proper email
    if (IFrontEnd.isValidEmail("rick@astley.com") != true) {
      fail("isValidEmail() returns false for valid input");
    }
    // test with whitespace
    if (IFrontEnd.isValidEmail("    rick@astley.com   ") != true) {
      fail("isValidEmail() returns false for valid input(w/ whitespace)");
    }
    // test with missing parts
    if (IFrontEnd.isValidEmail("@astley.com") != false) {
      fail("isValidEmail() returns true for invalid input");
    }
    // test again with next missing part
    if (IFrontEnd.isValidEmail("rick@.com") != false) {
      fail("isValidEmail() returns true for invalid input");
    }
    // test again with final missing part
    if (IFrontEnd.isValidEmail("rick@astley.") != false) {
      fail("isValidEmail() returns true for invalid input");
    }
  }

  /*
   * Back End Tests
   */
  @Test
  // Compares expected results of BackEnd.getEntry with actual results to confirm the method is
  // functioning properly
  void testGetEntry() {
    // create a BackEnd object to call methods
    //BackEnd test = new BackEnd();
    // attempt to get and Entry that is known to exist
    try {
      test.getEntry(6);
    } catch (Exception e) {
      e.printStackTrace();
      fail("getEntry fails for known entry");
    }
  }

  @Test
  // Checks that BackEnd.updateEntryName works as intended
  void testUpdateEntryName() {
  //create a BackEnd object to call methods
    //BackEnd test = new BackEnd();
    try {
      test.addNewEntry(15, "Rick Astley", "7777 Astley Drive", "MALIBU", "California", "U.S.",
          "rick@astley.com", "7648309", 6, 3, 1970);
    }
    catch(NoSuchElementException e) {
      //catch NoSuchElementException and fail
      fail("adding valid entry failed, check for invalid inputs or duplicate input");
    }
    catch(Exception e) {
     //catch any general exceptions and fail
      e.printStackTrace();
      fail("Exception when trying to add valid entry");
    }
    
    //use get to check if entry has indeed been added
    try {
      test.getEntry(15);  
    }
    catch(NoSuchElementException e) {
      //catch exception if entry cannot be found and fail
      fail("Cannot find entry after it has been added");
    }
    catch(Exception e) {
      //catch any misc. exceptions
      e.printStackTrace();
      fail("Exception thrown when getting an entry");
    }
  }

  @Test
  // Checks that BackEnd.updateEntryAddress works as intended
  void testUpdateEntryAddress() {
  //create a BackEnd object to call methods
    //BackEnd test = new BackEnd();
    //try and update address of existing id
    try {
      test.updateEntryAddress(6, "1000 Sasquatch Lane", "Aberdeen", "Washington", "U.S.");
    }
    catch(NoSuchElementException e) {
      //catch exception if id cannot be found
      fail("Failed to update known entry address");
    }
    catch(Exception e) {
      //catch any misc. exceptions
      e.printStackTrace();
      fail("Exception thrown when updating address");
    }
  //then test if Address is properly changed
    if(test.getEntry(6).getAddress().compareTo(new Address("1000 Sasquatch Lane", "Aberdeen", "Washington", "U.S.")) != 0) {
      fail("Address was not changed properly");
    }
    
  }

  @Test
  // Checks that BackEnd.updateEntryEmail works as intended
  void testUpdateEntryEmail() {
  //create a BackEnd object to call methods
    //BackEnd test = new BackEnd();
    //try and update Email of known entry
    try {
      test.updateEntryEmail(4, "sasquatch@isreal.com");
    }
    catch(NoSuchElementException e) {
      //catch exception and fail
      fail("failed to update email");
    }
    //then test if email is updated correctly
    if(!test.getEntry(4).getEmail().equals("sasquatch@isreal.com")) {
      fail("Email failed to update");
    }
  }

  @Test
  // Checks that BackEnd.updateEntryPhoneNumber works as intended
  void testUpdateEntryPhoneNumber() {
    //create a BackEnd object to call methods
    //BackEnd test = new BackEnd();
    //try and update phone number of known entry
    try {
      test.updateEntryPhoneNumber(10, "5018762");
    }
    catch(NoSuchElementException e) {
      //catch exception if update fails
      fail("Failed to update phone number");
    }
    //test to see if new phone number matches
    if(!test.getEntry(10).getPhoneNumber().equals("5018762")) {
      fail("Failed toupdate phone number");
    }
  }

  @Test
  // Checks that BackEnd.updateEntryBirthday works as intended
  void testUpdateEntryBirthday() {
    //create a BackEnd object to call methods
    //BackEnd test = new BackEnd();
    //try and change the birth day of a known entry
    try {
      test.updateEntryBirthday(3, 3, 6, 2000);
    }
    catch(NoSuchElementException e) {
      //if updating birthday fails 
      fail("Failed to update Birthday");
    }
    //test to see if new birthday matches
    if(test.getEntry(3).getBirthday().compareTo(new Date(3, 6, 2000)) != 0) {
      fail("Failed to update Birthday");
    }
  }

  @Test
  // Checks that the BackEnd.addNewEntry method successfully adds an entry into the tree
  void testAddNewEntry() {
    //create a BackEnd object to call methods
    //BackEnd test = new BackEnd();
    //try to add a new entry
    try {
      test.addNewEntry(11, "Rick Sasquatch", "5555 Astley Drive", "Sedona", "Arizona", "U.S.",
          "rick@sasquatch.com", "7628309", 5, 3, 1971);
    }
    catch(NoSuchElementException e) {
      //if NoSuchElement exception fail, may be due to duplicate
      fail("Failed to add a new entry with ID: 11");
    }
    catch(Exception e) {
      //catch any general Exceptions
      e.printStackTrace();
      fail("Failed to add a new entry with ID: 16");
    }
    //test if new fields are properly updated
    if(test.getEntry(11).getAddress().compareTo(new Address("5555 Astley Drive", "Sedona", "Arizona", "U.S.")) != 0) {
      fail("Address of new entry does not match");
    }
    if(test.getEntry(11).getBirthday().compareTo(new Date(5,3,1971)) != 0) {
      fail("Birthday of new entry does not match");
    }
    if(test.getEntry(11).getEmail().compareTo("rick@sasquatch.com") != 0) {
      fail("Email of new entry does not match");
    }
    if(test.getEntry(11).getName().compareTo("Rick Sasquatch") != 0) {
      fail("Name of new entry does not match");
    }
    if(test.getEntry(11).getPhoneNumber().compareTo("7628309") != 0) {
      fail("Phone Number of new entry does not match");
    }
  }

  /*
   * Data Access Tests
   */
  @Test
  // Confirms that DataAccess.getData doesn’t cause an error
  void testCanGetData() {
    //attempt to load data
    try {
      DataAccess.loadData();
    }
    //fail if exception
    catch(Exception e) {
      fail("Failed to load data");
    }
  }

  @Test
  // Confirms that DataAccess.setData doesn’t cause an error
  void testCanSetData() {
    //attempt to save data
    try {
      RedBlackTree<AddressBookEntry> testSet = DataAccess.loadData();
      DataAccess.saveData(testSet);
    }
    catch(Exception e) {
      fail("Failed to save data");
    }
  }

  @Test
  // Confirms that both getData and setData actually get and update data
  void testChangesData() {
    //create a red black tree to insert new data
    RedBlackTree<AddressBookEntry> testSet1 = null;
    //add new entry and load data
    try {
    testSet1 = DataAccess.loadData();
    AddressBookEntry entry = new AddressBookEntry(20, "Test Set", new Address("5525 Astley Drive",
        "Sully", "Arizona", "U.S."), "rick@astley.com", "7648309", new Date(1,1,2000));
    }
    catch(Exception e) {
      fail("Failed to add new entry");
    }
    //write data
    try {
  DataAccess.saveData(testSet1);
  }
   catch(Exception e) {
     fail("Failed to save new entry");
   }
    //test to see if data is saved
    System.out.println(testSet1.toString());
    if(testSet1.root.data.compareTo(new AddressBookEntry(6, "Gurleen Franco",
        new Address("1000 Sasquatch Lane",
        "Aberdeen", "Washington", "U.S."), "riyadmadrid03f@lolibox.ml", "4108313936", new Date(4,3,1963))) != 0) {
      fail("Data failed to update");
    }
  }
}

