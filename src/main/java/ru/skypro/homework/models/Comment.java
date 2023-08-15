package ru.skypro.homework.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import tools.TimeOfCreation;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@Data
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;
    private Long createdAt;
    private String author_image;
    private String author_first_name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User commentAuthor;

    @ManyToOne
    @JoinColumn(name = "ad_id")
    private Ad ad;

    public Comment(int id, String text, String author_image, String author_first_name, User commentAuthor, Ad ad) {
        this.id = id;
        this.text = text;
        this.createdAt = TimeOfCreation.TimeOfCreation();
        this.author_image = author_image;
        this.author_first_name = author_first_name;
        this.commentAuthor = commentAuthor;
        this.ad = ad;
    }
}
