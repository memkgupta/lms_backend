package com.lms.organisation_service.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Organisation {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String description;
    private String orgCode;
    private String location;
    private String logo;
    private String banner;
    private boolean isVerified;
    @Embedded
    private ContactDetails contactDetails;
    @ManyToOne
    private Employee admin;
    private String ownerId;
}
