package ru.skypro.homework.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.Role;
import tools.TimeOfCreation;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@Data
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String text;
    private Long createdAt;

    public Comment(int ID, String text, User commentAuthor) {
        this.ID = ID;
        this.text = text;
        this.createdAt = createdAt;
        this.commentAuthor = commentAuthor;
        createdAt = TimeOfCreation.TimeOfCreation();
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User commentAuthor;

    @ManyToOne
    @JoinColumn(name = "ad_id")
    private Ad ad;

}
