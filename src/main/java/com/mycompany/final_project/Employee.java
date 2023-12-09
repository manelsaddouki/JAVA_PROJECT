public class Employee extends Person {
    private String role;
    private String password;

    public Employee(int empId, String empName, String password, String role) {
        super(empId, empName);
        this.password = password;
        this.role = role;
    }
    public String getRole() {
        return role;
    }
    
    public boolean authenticate(String enteredName, String enteredPassword) {
        return getPersonName().equals(enteredName) && this.password.equals(enteredPassword);
    }

    
    public boolean hasAuthorization(String requiredRole) {
        return this.role.equals(requiredRole);
    }
    public Object getPassword() {
        return null;
    }
}
