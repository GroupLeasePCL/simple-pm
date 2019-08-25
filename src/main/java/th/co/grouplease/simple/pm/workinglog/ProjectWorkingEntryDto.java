package th.co.grouplease.simple.pm.workinglog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import th.co.grouplease.simple.pm.workinglog.domain.model.TypeOfWork;

import java.time.LocalDate;

public interface ProjectWorkingEntryDto {
    Long getId();
    TypeOfWork getTypeOfWork();
    LightResource getResource();
    LightProject getProject();
    LocalDate getWorkingDate();

    interface LightProject {
        String getId();
        String getName();
    }

    interface LightResource {
        Long getId();
        @JsonIgnore
        String getFirstName();
        @JsonIgnore
        String getLastName();
        default String getFullName(){
            return getFirstName().concat(" ").concat(getLastName());
        }
    }
}
