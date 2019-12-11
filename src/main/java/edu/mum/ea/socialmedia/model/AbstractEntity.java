package edu.mum.ea.socialmedia.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractEntity {
    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    @PrePersist
    public void onPrePersist() {
        setCreatedDate(LocalDateTime.now());
    }

    @PreUpdate
    public void onPreUpdate() {
        setModifiedDate(LocalDateTime.now());
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime created) {
        this.createdDate = created;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
