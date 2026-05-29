package com.semcon.sgu.modules.systems.entity;

import com.semcon.sgu.modules.users.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "external_system_users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "externalSystemId", nullable = false)
    private System system;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "externalRoleId", nullable = false)
    private SystemRole role;
}
