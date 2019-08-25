package th.co.grouplease.simple.pm.resource.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import th.co.grouplease.simple.pm.common.BaseAggregateRootEntity;
import th.co.grouplease.simple.pm.common.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ResourceTeam")
@Table(name = "resource_team")
@SQLDelete(sql =
        "UPDATE resource_team " +
                "SET deleted = true " +
                "WHERE id = ?")
@Loader(namedQuery = "findResourceTeamById")
@NamedQuery(name = "findResourceTeamById", query =
        "SELECT r " +
                "FROM ResourceTeam r " +
                "WHERE " +
                "    r.id = ?1 AND " +
                "    r.deleted = false")
@Where(clause = "deleted = false")
public class ResourceTeam extends BaseEntity {

    @NotNull(message = "Name cannot be null")
    private String name;
}
