package th.co.grouplease.simple.pm.resource;

import th.co.grouplease.simple.pm.AuditableEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ResourceTeam extends AuditableEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public static ResourceTeam create(String name){
        var resourceTeam = new ResourceTeam();
        resourceTeam.setName(name);
        return resourceTeam;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
