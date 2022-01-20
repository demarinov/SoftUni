package app.model.entity;

// Helping POJO
public class BooksByAuthor {

    private String firstName;
    private String lastName;
    private long totalCount;

    public BooksByAuthor(String firstName, String lastName, long totalCount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalCount = totalCount;
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

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
