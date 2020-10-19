// --== CS400 File Header Information ==--
// Name: Letong Dai
// Email: ldai29@wisc.edu
// Team: ED
// Role: Data Wrangler
// TA: Keren Chen
// Lecturer: Florian Heimerl

public class Address implements Comparable<Address>{
    private String address;
    private String city;
    private String province;
    private String country;

    public Address() {
    }

    public Address(String address, String city, String province, String country) {
        this.address = address;
        this.city = city;
        this.province = province;
        this.country = country;
    }

    /*
     * This constructor receive a String which contains all address information
     * 
     * @param address - format: address, city, province, country
     */
    public Address(String address) {
        String[] info = address.split(" - ");
        this.address = info[0];
        this.city = info[1];
        this.province = info[2];
        this.country = info[3];
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /*
     * @return format: address - city - province - country
     */
    @Override
    public String toString() {
        return address + " - " + city + " - " + province + " - " + country;
    }

    @Override
    public int compareTo(Address arg0) {
        int c = this.country.compareTo(arg0.country);
        if (c==0) {
            c = this.province.compareTo(arg0.province);
            if (c==0) {
                c = this.city.compareTo(arg0.city);
                if(c==0) {
                    c = this.address.compareTo(arg0.address);
                    if (c==0) {
                        return 0;
                    } else {
                        return c;
                    }
                } else {
                    return c;
                }

            } else {
                return c;
            }
        } else {
            return c;
        }
    }
}