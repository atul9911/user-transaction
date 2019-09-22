package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "User", uniqueConstraints = {@UniqueConstraint(columnNames = "EMAIL")})
public class User extends BaseModel {

  @Column(name = "EMAIL", unique = true, nullable = false, length = 1000)
  private String email;

  @Column(name = "FIRST_NAME", unique = false, nullable = false, length = 100)
  private String firstName;

  @Column(name = "LAST_NAME", unique = false, nullable = false, length = 100)
  private String lastName;

  @Column(name = "mobile", unique = false, nullable = false, length = 100)
  private String mobile;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
}
