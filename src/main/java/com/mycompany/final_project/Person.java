public class Person {
    private int personId;
    private String personName;

    // Constructor
    public Person(int personId, String personName) {
        this.personId = personId;
        this.personName = personName;
    }

    // Getters and setters for attributes
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

}

