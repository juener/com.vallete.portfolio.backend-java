package com.vallete.portfolio.backendjava.comment.model;

import com.vallete.portfolio.backendjava.post.model.PostModel;
import com.vallete.portfolio.backendjava.user.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_comment")
public class CommentModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String body;

    private LocalDateTime creationDate;

    @ManyToOne
    private UserModel user;

    @ManyToOne
    private PostModel post;
}
