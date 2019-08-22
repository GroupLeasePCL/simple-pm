package th.co.grouplease.simple.pm.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import th.co.grouplease.simple.pm.AuditableEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Resource extends AuditableEntity {
    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_team_id")
    private ResourceTeam resourceTeam;

    @NotNull(message = "Username can't be null")
    private String username;

    @NotNull(message = "First name can't be null")
    private String firstName;

    @NotNull(message = "Last name can't be null")
    private String lastName;

    @JsonIgnore
    @NotNull(message = "Employee id can't be null")
    private String employeeId;

    @NotNull(message = "Employee start date can't be null")
    private LocalDate employeeStartDate;

    private LocalDate employeeEndDate;

    public static Resource create(String username, String firstName, String lastName, String employeeId, LocalDate employeeStartDate){
        var resource = new Resource();
        resource.setUsername(username);
        resource.setFirstName(firstName);
        resource.setLastName(lastName);
        resource.setEmployeeId(employeeId);
        resource.setEmployeeStartDate(employeeStartDate);
        return resource;
    }

    public Resource withTeam(ResourceTeam team){
        this.setResourceTeam(team);
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResourceTeam getResourceTeam() {
        return resourceTeam;
    }

    public void setResourceTeam(ResourceTeam resourceTeam) {
        this.resourceTeam = resourceTeam;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getEmployeeStartDate() {
        return employeeStartDate;
    }

    public void setEmployeeStartDate(LocalDate employeeStartDate) {
        this.employeeStartDate = employeeStartDate;
    }

    public LocalDate getEmployeeEndDate() {
        return employeeEndDate;
    }

    public void setEmployeeEndDate(LocalDate employeeEndDate) {
        this.employeeEndDate = employeeEndDate;
    }
}
