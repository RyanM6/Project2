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
public class TestFrontEnd {
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
}
