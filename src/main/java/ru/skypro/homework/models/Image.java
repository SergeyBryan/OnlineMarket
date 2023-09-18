package ru.skypro.homework.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long fileSize;
    private String mediaType;

    private byte[] data;
    @ManyToOne
    @JoinColumn(name = "ad_id")
    private Ad ad;
}
