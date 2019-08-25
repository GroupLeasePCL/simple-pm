package th.co.grouplease.simple.pm.resource.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import th.co.grouplease.simple.pm.common.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Resource")
@Table(name = "resource")
@SQLDelete(sql =
        "UPDATE resource " +
                "SET deleted = true " +
                "WHERE id = ?")
@Loader(namedQuery = "findResourceById")
@NamedQuery(name = "findResourceById", query =
        "SELECT r " +
                "FROM Resource r " +
                "WHERE " +
                "    r.id = ?1 AND " +
                "    r.deleted = false")
@Where(clause = "deleted = false")
public class Resource extends BaseEntity {
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
}
