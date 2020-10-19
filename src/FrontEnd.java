// --== CS400 File Header Information ==--
// Name: Laura Dinh
// Email: lmdinh@wisc.edu
// Team: ED
// Role: Front End Developer
// TA: Keren Chen
// Lecturer: Florian Heimerl

import java.util.NoSuchElementException;
import java.util.Scanner;

public class FrontEnd implements IFrontEnd {

  /**
   * Parse's through the user's input
   * 
   * @param input from user
   * @return array of data
   */
  private static String[] parseCommand(String input) {
    input = input.strip().toLowerCase().replaceAll("\\s", "");
    ;
    String[] commands = input.split(",");
    if (commands[0].contains("a") || commands[0].contains("e") || commands[0].contains("h")
        || commands[0].contains("s") || commands[0].contains("q")) {
      try {
        commands[0] = String.valueOf(commands[0].charAt(0));
      } catch (StringIndexOutOfBoundsException e) {

      }
    }
    return commands;
  }

  public static void main(String[] args) {
    BackEnd backend = new BackEnd();

    Scanner scnr = new Scanner(System.in);
    System.out.println("________________________");
    System.out.println("Welcome to Address Book");
    String command;
    String arguments;
    do {
      System.out.println("Please enter a command. To see a list of commands, please enter \"help\"");
      String userInputCommand = scnr.nextLine();
      String[] userCommand = parseCommand(userInputCommand);
      command = userCommand[0];
      int commandLength = userCommand.length;
      String userInputArguments = "";
      String[] userArguments;

      switch (command) {
        case "a": // adds new entry [id, name, address, city, province, country, emailAddress,
              // phoneNumber, birthdayDay, birthdayMonth, birthdayYear]
          System.out.println(
              "Please enter id, name, address, city, province, country, email address, phone number, day of birth, month of birthday, and year of birth for contact (separated by commas). To see list of commands regarding add, please enter \"help\"");
          userInputArguments = scnr.nextLine();
          userArguments = parseCommand(userInputArguments);

          if (userArguments.length != 11) {
            System.out.println(
                "Insufficient information. To see list of commands regarding add, please enter \"help\"");
            break;
          }

          if (!IFrontEnd.isValidEmail(userArguments[6])) { // validates the email of entry
            System.out.println("Email is invalid. To see list of commands, please enter \"help\"");
            break;
          }
          int day; // validates the dob of entry
          int month;
          int year;
          try {
            day = Integer.parseInt(userArguments[8]);
            month = Integer.parseInt(userArguments[9]);
            year = Integer.parseInt(userArguments[10]);
            if (!IFrontEnd.isValidDate(day, month, year)) {
              System.out.println("Invalid calendar date. To see list of commands, please enter \"help\"");
              break;
            }
            // adds entry
            backend.addNewEntry(Integer.parseInt(userArguments[0]), userArguments[1], userArguments[2],
                userArguments[3], userArguments[4], userArguments[5], userArguments[6],
                userArguments[7], day, month, year);
          } catch (NumberFormatException e) {
            System.out.println(
                "birthday is in incorrect format. To see list of commands, please enter \"help\"");
            break;
          } catch (IllegalArgumentException e) {
            System.out.println("ID already exists in address book.");
            break;
          }

          System.out.println("Entry successfully added");
          break;
        case "s": // searches for entry
          System.out.println("Please enter contact ID. If you want all entries, please enter \"all\"");
          userInputArguments = scnr.nextLine();
          userArguments = parseCommand(userInputArguments);
          if (userArguments[0].equals("a")) {
            backend.showAllEntries();
            break;
          }
          try {
            int id = Integer.parseInt(userArguments[0]);
            System.out.println(backend.getEntry(id));
          } catch (NumberFormatException e) {
            System.out.println("Invalid input for ID");
            break;
          } catch (NoSuchElementException e) {
            System.out.println("There is no contact with this ID");
            break;
          }
          break;
        case "e": // edits new entry
          System.out.println(
              "Please enter contact ID and information that needs to edited (separated by commas)");
          userInputArguments = scnr.nextLine();
          userArguments = parseCommand(userInputArguments);
          int id;
          String updateInputInfo = ""; // holds info needed to update entry
          String[] updateInfo;
          try {
            id = Integer.parseInt(userArguments[0]);
            switch (userArguments[1]) {
              case "name":
                System.out.println("Please enter new name");
                updateInputInfo = scnr.nextLine();
                try {
                  backend.updateEntryName(id, updateInputInfo);
                } catch (NoSuchElementException e) {
                  System.out.println("There is no contact with this ID");
                  break;
                } catch (IllegalArgumentException e) {
                  System.out.println("Incorrect information entered");
                  break;
                }
                System.out.println("Entry name updated");
                break;
              case "address":
                System.out.println(
                    "Please enter the new address <address, city, province, country> (separated by commas)");
                updateInputInfo = scnr.nextLine();
                updateInfo = parseCommand(updateInputInfo);
                if (updateInfo.length != 4) {
                  System.out.println(
                      "Wrong number of arguments. To see list of commands, please enter \"help\"");
                  break;
                }
                try {
                  backend.updateEntryAddress(id, updateInfo[0], updateInfo[1], updateInfo[2],
                      updateInfo[3]);
                } catch (NoSuchElementException e) {
                  System.out.println("There is no contact with this ID");
                  break;
                }
                System.out.println("Entry address updated");
                break;
              case "email":
                System.out.println("Please enter the new email");
                updateInputInfo = scnr.nextLine();

                if (!IFrontEnd.isValidEmail(updateInputInfo)) {
                  System.out.println("Email is not valid");
                  break;
                }
                try {
                  backend.updateEntryEmail(id, updateInputInfo);
                } catch (NoSuchElementException e) {
                  System.out.println("There is no contact with this ID");
                  break;
                }
                System.out.println("Entry email updated");
                break;
              case "phonenumber":
                System.out.println("Please enter the new phone number");
                updateInputInfo = scnr.nextLine();

                try {
                  backend.updateEntryPhoneNumber(id, updateInputInfo);
                } catch (NoSuchElementException e) {
                  System.out.println("There is no contact with this ID");
                  break;
                }
                System.out.println("Entry phone number updated");
                break;
              case "birthday":
                System.out.println(
                    "Please enter the new birthday <birth day, birth month, birth year> (separated by commas)");
                updateInputInfo = scnr.nextLine();
                updateInfo = parseCommand(updateInputInfo);

                if (updateInfo.length != 3) {
                  System.out.println(
                      "Wrong number of arguments. To see list of commands, please enter \"help\"");
                  break;
                }
                try {
                  day = Integer.parseInt(updateInfo[8]);
                  month = Integer.parseInt(updateInfo[9]);
                  year = Integer.parseInt(updateInfo[10]);
                  if (!IFrontEnd.isValidDate(day, month, year)) {
                    System.out.println(
                        "Invalid calendar date. To see list of commands, please enter \"help\"");
                    break;
                  }
                  backend.updateEntryBirthday(id, day, month, year);
                } catch (NumberFormatException e) {
                  System.out.println(
                      "birthday is in incorrect format. To see list of commands, please enter \"help\"");
                  break;
                } catch (NoSuchElementException e) {
                  System.out.println("There is no contact with this ID");
                  break;
                }
                System.out.println("Entry birthday has been updated");
                break;
            }

          } catch (NumberFormatException e) {
            System.out.println("Invalid input for ID");
            break;
          } catch (ArrayIndexOutOfBoundsException e) {
            System.out.printf(
                "%sERROR: Please include the field you wish to change.\n\ne.g. to change name of entry with id 1:\n\n> e\nPlease enter ID and information that needs to be edited (separated by commas)\n> 1, name%s",
                "\n".repeat(3), "\n".repeat(3));
          }
          break;
        case "h": // prints help menu
          System.out.println("__________________________________________________________");
          System.out.println("Type one of the following commands: add, search, edit, quit");
          System.out.println("The command may prompt the user for additional information\n");
          System.out.println("All information is separated by commands");
          System.out.println("[a]dd - adds a new entry");
          System.out.println(
              " add prompts user for [id, name, address, city, province, country, emailAddress, phoneNumber, birthday day, birthday month, birthday year]");
          System.out.println("  Ex. add");
          System.out.println(
              " 23, Hannah Smith, 123 Goodman Ln, Madison, Wisconsin, US, hmsmith@gmail.com, 608-455-6578, 06, 06, 2000");
          System.out.println("[s]earch - searches for specific entry or shows all entries");
          System.out.println("  search prompts user for [id] or [all]");
          System.out.println("  Ex. search");
          System.out.println("  4");
          System.out.println("[e]dit - edits an existing entry's information");
          System.out.println(
              " The following fields can be updated [name, address, phone number, email, birthday");
          System.out.println("  edit prompts user for [id, <field that needs to be updated>]");
          System.out.println("  user can then type the updated information for the field");
          System.out.println("  Ex. edit");
          System.out.println("  4, birthday");
          System.out.println("  29, 03, 1998");
          System.out.println("[q]uit - quits address book");
          System.out.println("__________________________________________________________");

          break;
        case "q":
          break;
        default:
          System.out.println("Invalid command. Use \"help\" to see all commands");
          System.out.println();
      }
    } while (!command.equals("q"));
    System.out.println("Thank you for using address book");
  }

}