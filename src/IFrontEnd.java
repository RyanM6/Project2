// --== CS400 File Header Information ==--
// Name: Laura Dinh
// Email: lmdinh@wisc.edu
// Team: ED
// Role: Front End Developer
// TA: Keren Chen
// Lecturer: Florian Heimerl

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface IFrontEnd {
  static final Pattern p = Pattern.compile("[0-9a-z%+\\._\\-]+@[a-z\\-\\.]+\\.[a-z]{2,}", Pattern.CASE_INSENSITIVE);

  /**
   * Checks if date is valid
   * 
   * @param day   of birth
   * @param month of birth
   * @param year  of birth
   * @return true if date is valid, false otherwise
   */
  public static boolean isValidDate(int day, int month, int year) {
    String validingDate = month + "-" + day + "-" + year;
    SimpleDateFormat date = new SimpleDateFormat("MM-dd-yyyy");
    date.setLenient(false);
    try {
      date.parse(validingDate);
    } catch (ParseException e) {
      return false;
    }
    return true;
  }

  /**
   * Checks if email is valid
   * 
   * @param email of AddressBookEntry
   * @return true if email is valid, false otherwise
   */
  public static boolean isValidEmail(String email) {
    if (email == null)
      return false;

    Matcher m = p.matcher(email.strip());
    return m.matches();
  }

}