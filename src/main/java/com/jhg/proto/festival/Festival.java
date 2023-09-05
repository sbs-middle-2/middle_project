package com.jhg.proto.festival;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Festival {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "regDate")
    private LocalDateTime regDate;

    @Column(name = "updateDate")
    private LocalDateTime updateDate;

    @Column(name = "picture")
    private String picture;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "body", columnDefinition = "TEXT")
    private String body;

    @Column(name = "startDate")
    private LocalDateTime startDate;

    @Column(name = "endDate")
    private LocalDateTime endDate;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "inquiryCall", columnDefinition = "TEXT")
    private String inquiryCall;

    @Column(name = "siteLink", columnDefinition = "TEXT")
    private String siteLink;

    @Column(name = "classification", columnDefinition = "TEXT")
    private String classification;
}