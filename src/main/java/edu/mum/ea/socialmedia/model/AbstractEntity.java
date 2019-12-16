package edu.mum.ea.socialmedia.model;

import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractEntity {
    private LocalDateTime createdDate;

    private String createdBy;

    private LocalDateTime modifiedDate;

    private String modifiedBy;

    @PrePersist
    public void onPrePersist() {
        setCreatedDate(LocalDateTime.now());
        setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @PreUpdate
    public void onPreUpdate() {
        setModifiedDate(LocalDateTime.now());
        setModifiedBy(SecurityContextHolder.getContext().getAuthentication().getName());
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
