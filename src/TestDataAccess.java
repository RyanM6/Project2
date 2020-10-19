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
public class TestDataAccess {
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
