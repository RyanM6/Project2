import java.util.NoSuchElementException;

public interface IBackEnd {
        public AddressBookEntry getEntry(int entryId) throws NoSuchElementException;

        public void showAllEntries();

        public void updateEntryName(int entryId, String newName)
                        throws NoSuchElementException, IllegalArgumentException;

        public void updateEntryAddress(int entryId, String address, String city, String province, String country)
                        throws NoSuchElementException;

        public void updateEntryEmail(int entryId, String newEmail) throws NoSuchElementException;

        public void updateEntryPhoneNumber(int entryId, String newPhoneNumber) throws NoSuchElementException;

        public void updateEntryBirthday(int entryId, int day, int month, int year) throws NoSuchElementException;

        public void addNewEntry(int id, String name, String address, String city, String province, String country,
                        String emailAddress, String phoneNumber, int birthdayDay, int birthdayMonth, int birthdayYear);
}