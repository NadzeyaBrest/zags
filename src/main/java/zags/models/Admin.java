package zags.models;

public class Admin {
    private String lastName;
    private String firstName;
    private String middleName;
    private String phone;
    private String passportNumber;
    private String birthDate;

    public Admin() {
    }
    public Admin(String lastName, String firstName, String middleName, String phone, String passportNumber, String birthDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.phone = phone;
        this.passportNumber = passportNumber;
        this.birthDate = birthDate;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public Admin lastName(String v) { this.lastName = v; return this; }
    public Admin firstName(String v) { this.firstName = v; return this; }
    public Admin middleName(String v) { this.middleName = v; return this; }
    public Admin phone(String v) { this.phone = v; return this; }
    public Admin passportNumber(String v) { this.passportNumber = v; return this; }
    public Admin birthDate(String v) { this.birthDate = v; return this; }


}
