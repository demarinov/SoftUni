
// Simple builder pattern ..
public class Address {

    private String firstName;
    private String lastName;
    private String email;
    private String line1;
    private String line2;
    private String phoneNumber;
    private String company;
    private String postCode;

    public Address() {
    }

    public Address setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Address setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Address setEmail(String email) {
        this.email = email;
        return this;
    }

    public Address setLine1(String line1) {
        this.line1 = line1;
        return this;
    }

    public Address setLine2(String line2) {
        this.line2 = line2;
        return this;
    }

    public Address setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Address setCompany(String company) {
        this.company = company;
        return this;
    }

    public Address setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }
}
