package br.com.spotippos.provinces;

import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class BoundariesModel {
	
	@Field("upperLeft")
	private final CoordinateModel upperLeft;
	
	@Field("bottomRight")
	private final CoordinateModel bottomRight;
}
