package br.com.spotippos.properties;

import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
public class PropertiesByCoordinatesModel {

	private final Integer foundProperties;
	private final Set<PropertyModel> properties;
}
