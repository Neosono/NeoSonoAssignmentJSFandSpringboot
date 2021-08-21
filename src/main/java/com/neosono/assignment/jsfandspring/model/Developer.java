
package com.neosono.assignment.jsfandspring.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name="developers")
public class Developer implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "developer_firstname")
  private String fName;

  @Column(name = "developer_lastname")
  private String lName;

  @Column(name = "developer_email")
  private String email;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "devId")
  private List<DeveloperSkills> skills;

  public Developer() {
  }

  public Developer(String fName, String lName, String email) {
    this.fName = fName;
    this.lName = lName;
    this.email = email;

  }

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  public String getfName() {
    return fName;
  }

  public void setfName(String fName) {
    this.fName = fName;
  }

  public String getlName() {
    return lName;
  }

  public void setlName(String lName) {
    this.lName = lName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<DeveloperSkills> getDeveloperSkills() {
    return skills;
  }

  public void setDeveloperSkills(List<DeveloperSkills> developerSkills) {
    this.skills = developerSkills;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Developer)) return false;
    Developer developer = (Developer) o;
    return getId().equals(developer.getId()) && getfName().equals(developer.getfName()) && getlName().equals(developer.getlName()) && getEmail().equals(developer.getEmail()) && Objects.equals(getDeveloperSkills(), developer.getDeveloperSkills());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getfName(), getlName(), getEmail(), getDeveloperSkills());
  }

  @Override
  public String toString() {
    return "Developer{" +
            "id=" + id +
            ", fName='" + fName + '\'' +
            ", lName='" + lName + '\'' +
            ", email='" + email + '\'' +
            ", skills=" + skills +
            '}';
  }
}

