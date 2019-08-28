package th.co.grouplease.simple.pm.ui.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BaseModel<T> implements Serializable {
    private T id;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    @JsonIgnore
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    @JsonProperty
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @JsonIgnore
    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    @JsonProperty
    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
