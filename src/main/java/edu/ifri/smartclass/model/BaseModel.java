package edu.ifri.smartclass.model;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@JsonIgnoreProperties(value = {"isActivated", "isDeleted", "createdAt", "updatedAt"}, allowGetters = true)

public abstract class BaseModel {
    @JsonIgnore
    @Column(name = "is_activated", nullable = false, columnDefinition = "BOOLEAN default TRUE")
    private boolean isActivated;

    @JsonIgnore
    @Column(name = "is_deleted", nullable = false, columnDefinition = "BOOLEAN default FALSE")
    private boolean isDeleted;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    private LocalDate createdAt;

    @JsonIgnore
    @UpdateTimestamp
    @LastModifiedDate
    @Column(name = "updated_at", nullable = true, columnDefinition = "TIMESTAMP")
    private LocalDate updatedAt;
}