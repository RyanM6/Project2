// --== CS400 File Header Information ==--
// Name: Letong Dai
// Email: ldai29@wisc.edu
// Team: ED
// Role: Data Wrangler
// TA: Keren Chen
// Lecturer: Florian Heimerl

public class AddressBookEntry implements Comparable<AddressBookEntry> {
    private String name;
    private Address address;
    private String email;
    private String phoneNumber;
    private Date birthday;
    private int id;

    public AddressBookEntry() {
    }

    public AddressBookEntry(int id, String name, Address address, String email, String phoneNumber, Date birthday) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }

    /*
     * This constructor receive a String which contains all date information
     * 
     * @param date - format: name, address - city - province - country, email, phone
     * number, month / day / year
     */
    public AddressBookEntry(String entry) {
        if(entry == null) {
          return;
        }
        String[] info = entry.split(", ");
        this.id = Integer.valueOf(info[0]);
        this.name = info[1];
        this.address = new Address(info[2]);
        this.email = info[3];
        this.phoneNumber = info[4];
        this.birthday = new Date(info[5]);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int newId) {
        id = newId;
    }

    /*
     * @return format: name, address - city - province - country, email, phone
     * number, month / day / year
     */
    @Override
    public String toString() {
        return "[" + id + ", " + name + ", " + address.toString() + ", " + email + ", " + phoneNumber + ", "
                + birthday.toString() + "]";
    }

    @Override
    public int compareTo(AddressBookEntry other) {
        return id - other.getId();
    }
}
