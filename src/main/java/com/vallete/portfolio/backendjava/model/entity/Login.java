package com.vallete.portfolio.backendjava.model.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
//avoid use the entity as User for reserved word reasons
//@Setter
//@ToString
//@EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor @Builder
//or @Data to replace all of @Getter@Setter@RequiredArgsConstructor@ToString@EqualsAndHashCode 
//@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Login {

	@Id
	// @Column(name = "id") //use @Column if some name change is needed
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private String name;

	private String email;

	private String password;

//	public Login(String email, String password) {
//		email = this.email;
//		password = this.password;
//	}
//	
//	public Login(String name, String email, String password) {
//		name = this.name;
//		email = this.email;
//		password = this.password;
//	}
	

}
