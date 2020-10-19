
import java.io.IOException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class BackEnd implements IBackEnd {
    private RedBlackTree<AddressBookEntry> tree;

    public BackEnd() {
       try {
            tree = DataAccess.loadData();
       } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR: Can't load data");
        }
    }

    @Override
    public AddressBookEntry getEntry(int entryId) throws NoSuchElementException {
        // Starting at the root of the tree
        RedBlackTree.Node<AddressBookEntry> node = tree.root;

        // Search for the node with id
        while (node != null) {
            if (node.data.getId() == entryId) {
                return node.data;
            }

            // Node's id is greater than root id
            if (node.data.getId() < entryId) {
                // Search right subtree
                node = node.rightChild;
            } else {
                // Search left subtree
                node = node.leftChild;
            }
        }

        throw new NoSuchElementException(String.format("No entry with id = \"%d\"", entryId));
    }

    @Override
    public void showAllEntries() {
        // Use traversal algorithm from RedBlackTree.Node<T>.toString
        LinkedList<RedBlackTree.Node<AddressBookEntry>> q = new LinkedList<>();
        q.add(tree.root);

        while (!q.isEmpty()) {
            RedBlackTree.Node<AddressBookEntry> next = q.removeFirst();

            if (next.leftChild != null)
                q.add(next.leftChild);

            if (next.rightChild != null)
                q.add(next.rightChild);

            System.out.println(next.data);
        }
    }

    @Override
    public void updateEntryName(int entryId, String newName) throws NoSuchElementException, IllegalArgumentException {
        // Get the entry
        AddressBookEntry entry = getEntry(entryId);

        // If the entry wasn't found throw a NoSuchElementException
        if (entry == null) {
            throw new NoSuchElementException();
        }

        // Set the name field to the new name
        entry.setName(newName);

        // Save changes to file
        try {
            DataAccess.saveData(tree);
        } catch (IOException e) {
            System.out.println("ERROR: Changes weren't saved");
        }
    }

    @Override
    public void updateEntryAddress(int entryId, String address, String city, String province, String country)
            throws NoSuchElementException {
        // Get the entry
        AddressBookEntry entry = getEntry(entryId);

        // If the entry wasn't found throw a NoSuchElementException
        if (entry == null) {
            throw new NoSuchElementException();
        }

        // Set the address field to the new address
        entry.setAddress(new Address(address, city, province, country));

        // Save changes to file
        try {
            DataAccess.saveData(tree);
        } catch (IOException e) {
            System.out.println("ERROR: Changes weren't saved");
        }
    }

    @Override
    public void updateEntryEmail(int entryId, String newEmail) throws NoSuchElementException {
        // Get the entry
        AddressBookEntry entry = getEntry(entryId);

        // If the entry wasn't found throw a NoSuchElementException
        if (entry == null) {
            throw new NoSuchElementException();
        }

        // Set the email field to the new email
        entry.setEmail(newEmail);

        // Save changes to file
        try {
            DataAccess.saveData(tree);
        } catch (IOException e) {
            System.out.println("ERROR: Changes weren't saved");
        }
    }

    @Override
    public void updateEntryPhoneNumber(int entryId, String newPhoneNumber) throws NoSuchElementException {
        // Get the entry
        AddressBookEntry entry = getEntry(entryId);

        // If the entry wasn't found throw a NoSuchElementException
        if (entry == null) {
            throw new NoSuchElementException();
        }

        // Set the phone number field to the new phone number
        entry.setPhoneNumber(newPhoneNumber);

        // Save changes to file
        try {
            DataAccess.saveData(tree);
        } catch (IOException e) {
            System.out.println("ERROR: Changes weren't saved");
        }
    }

    @Override
    public void updateEntryBirthday(int entryId, int day, int month, int year) throws NoSuchElementException {
        // Get the entry
        AddressBookEntry entry = getEntry(entryId);

        // If the entry wasn't found throw a NoSuchElementException
        if (entry == null) {
            throw new NoSuchElementException();
        }

        // Set the birthday field to the new date
        entry.setBirthday(new Date(day, month, year));

        // Save changes to file
        try {
            DataAccess.saveData(tree);
        } catch (IOException e) {
            System.out.println("ERROR: Changes weren't saved");
        }
    }

    @Override
    public void addNewEntry(int id, String name, String address, String city, String province, String country,
            String emailAddress, String phoneNumber, int birthdayDay, int birthdayMonth, int birthdayYear)
            throws IllegalArgumentException {
        try {
            // If an entry with id is found
            getEntry(id);
            throw new IllegalArgumentException();
        } catch (NoSuchElementException e) {
            // Working as intended
        }

        // Create Address object for entry
        Address newEntryAddress = new Address(address, city, province, country);

        // Create Date object for entry's birthday
        Date newEntryBirthday = new Date(birthdayDay, birthdayMonth, birthdayYear);

        // Create new entry object
        AddressBookEntry newEntry = new AddressBookEntry(id, name, newEntryAddress, emailAddress, phoneNumber,
                newEntryBirthday);

        // Insert new entry into the tree
        tree.insert(newEntry);

        // Save changes to file
        try {
            DataAccess.saveData(tree);
        } catch (IOException e) {
            System.out.println("ERROR: Changes weren't saved");
        }
    }
}