package Model;

public class Person {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    
    public Person(String firstName, String lastName, String email, String phoneNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    @Override
    public String toString() {
        return "firstname: " + firstName + ", lastname: " + lastName + ", email: " + email + ", phonenumber: " + phoneNumber;
    }
    public boolean equals(Person person){
        if (person == null) {
            return false;
        }
        return this.firstName.equals(person.getFirstName()) && this.lastName.equals(person.getLastName()) && this.password.equals(person.getPassword()) && this.email.equals(person.getEmail()) && this.phoneNumber.equals(person.getPhoneNumber());
    }
}