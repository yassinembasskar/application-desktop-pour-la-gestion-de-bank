package DAO;
import java.sql.SQLException;
import java.sql.ResultSet;
import Model.Person;

public class PersonDAO {
    DBConnection dbConnection = new DBConnection();
    public Person login(String email, String password) {
        try {
            dbConnection.setSt(dbConnection.getCon().createStatement());
            ResultSet res = dbConnection.getSt().executeQuery("select * from person WHERE EMAIL = '"+email+"' and password = '"+password+"';");
            if(res.next()){
                System.out.println("you are logged in successfully");
                return new Person(res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("a problem occured with the login");
        }
        return null;
    }
    public Person signup(String firstName, String lastName, String email, String phoneNumber, String password) {
        try {
            dbConnection.setSt(dbConnection.getCon().createStatement());
            ResultSet res = dbConnection.getSt().executeQuery("SELECT * FROM person WHERE PHONENUMBER = '" + phoneNumber + "' OR EMAIL = '" + email + "' OR (FIRSTNAME = '" + firstName + "' AND LASTNAME = '" + lastName + "')");
            if(res.next()){
                System.out.println("sorry you already have an account");
            } else {
                dbConnection.getSt().executeUpdate("INSERT INTO person (FIRSTNAME, LASTNAME, EMAIL, PHONENUMBER, password) VALUES ('" + firstName + "', '" + lastName + "', '" + email + "', '" + phoneNumber + "', '" + password + "')");
                System.out.println("you signed up successfully");
                return new Person(firstName, lastName, email, phoneNumber, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("a problem occured with the signup");
        }
        return null;
    }
    public int findIdPerson(Person person){
        try {
            dbConnection.setSt(dbConnection.getCon().createStatement());
            ResultSet res = dbConnection.getSt().executeQuery("select * from person WHERE EMAIL = '"+person.getEmail()+"' and password = '"+person.getPassword()+"' and PHONENUMBER = '"+person.getPhoneNumber()+"' and FIRSTNAME = '"+person.getFirstName()+"' and LASTNAME = '"+person.getLastName()+"';");
            if (res.next()) {
                return res.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("a problem occured with finding the id of this person");
        }
        return 0;
    }
    public Person getPersonById(int personId){
         try {
            dbConnection.setSt(dbConnection.getCon().createStatement());
            ResultSet res = dbConnection.getSt().executeQuery("select * from person where ID_PERSON = "+personId+";");
            if (res.next()) {
                return new Person(res.getString(2), res.getString(3), res.getString(4), res.getString(5),res.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("a problem occured with finding this person");
        }
        return null;
    }
    public int findIdPersonByLabel(String email) {
        try {
            dbConnection.setSt(dbConnection.getCon().createStatement());
            ResultSet res = dbConnection.getSt().executeQuery("select * from person where EMAIL = '"+email+"';");
            if (res.next()) {
                return res.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("a problem occured with finding this person");
        }
        return 0;
    }
}
