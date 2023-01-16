package com.vallete.portfolio.backendjava.comment.model;

import com.vallete.portfolio.backendjava.post.model.PostModel;
import com.vallete.portfolio.backendjava.user.model.UserModel;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    @NonNull
    private String body;
    @Builder.Default
    private LocalDateTime creationDate = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    @ManyToOne
    @NonNull
    private UserModel user;
    @ManyToOne
    @NonNull
    private PostModel post;
}
