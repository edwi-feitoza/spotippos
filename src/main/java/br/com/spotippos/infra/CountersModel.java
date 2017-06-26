package br.com.spotippos.infra;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Document(collection = "counters")
public class CountersModel {

	@Id
	private String id;
	
	@Field
	private Integer seq;
}
