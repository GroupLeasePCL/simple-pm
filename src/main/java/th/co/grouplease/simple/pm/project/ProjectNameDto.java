package th.co.grouplease.simple.pm.project;

import lombok.Data;

@Data
public class ProjectNameDto {

    private Long id;
    private String name;

    public ProjectNameDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
