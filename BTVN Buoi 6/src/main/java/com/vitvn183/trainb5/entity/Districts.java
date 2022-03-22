package com.vitvn183.trainb5.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "districts")
public class Districts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long districtId;

    @Nationalized
    private String name;

    private String type;
    private String slug;

    @Nationalized
    @Column(name = "name_with_type")
    private String nameWithType;

    @Nationalized
    private String path;

    @Nationalized
    @Column(name = "path_with_type")
    private String pathWithType;

    private String code;

    @Column(name = "parent_code")
    private Long parentCode;

    @CreationTimestamp
    private Timestamp created;

    @UpdateTimestamp
    private Timestamp updated;

    private Boolean status = Boolean.TRUE;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "province_id")
    private Provinces province;
}
