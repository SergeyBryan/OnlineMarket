package ru.skypro.homework.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import tools.TimeOfCreation;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @CreatedDate
    private Long createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User commentAuthor;

    @ManyToOne
    @JoinColumn(name = "ad_id")
    private Ad ad;

    public Comment(Long id, String text, User commentAuthor, Ad ad) {
        this.id = id;
        this.text = text;
        this.createdAt = TimeOfCreation.TimeOfCreation();
        this.commentAuthor = commentAuthor;
        this.ad = ad;
    }
}
