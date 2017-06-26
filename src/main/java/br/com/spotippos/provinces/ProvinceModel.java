package br.com.spotippos.provinces;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
@Document(collection = "provinces")
public class ProvinceModel {

	@Id
	private final String id;

	@Field("province")
	private final String province;
	
	@Field("boundaries")
	private final BoundariesModel boundaries;
}