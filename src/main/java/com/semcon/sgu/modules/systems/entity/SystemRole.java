package com.semcon.sgu.modules.systems.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "external_system_roles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "externalSystemId", nullable = false)
    private System system;

    @Column(name = "createdAt", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updatedAt", nullable = false)
    private OffsetDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = OffsetDateTime.now();
        updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = OffsetDateTime.now();
    }
}
