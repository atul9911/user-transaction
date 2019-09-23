package pojo;

public class UserPojo {
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserPojo)) return false;

        UserPojo userPojo = (UserPojo) o;

        if (getFirstName() != null ? !getFirstName().equals(userPojo.getFirstName()) : userPojo.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(userPojo.getLastName()) : userPojo.getLastName() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(userPojo.getEmail()) : userPojo.getEmail() != null) return false;
        return getMobile() != null ? getMobile().equals(userPojo.getMobile()) : userPojo.getMobile() == null;
    }

    @Override
    public int hashCode() {
        int result = getFirstName() != null ? getFirstName().hashCode() : 0;
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getMobile() != null ? getMobile().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserPojo{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
