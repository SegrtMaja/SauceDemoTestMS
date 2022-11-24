package sauce.demo.models;

public class Employee extends User {

    public String firstName;
    public String lastName;

    public Employee(String userName, String password, String firstName, String lastName) {
        super(userName, password);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }




}
