package com.vallete.portfolio.backendjava.model;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data //@Data to replace all of @Getter@Setter@RequiredArgsConstructor@ToString@EqualsAndHashCode 
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_user")
public class User {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	private String name;

	private String email;

	private String password;	
	
	private LocalDateTime creationDate;

	private String role;

}
