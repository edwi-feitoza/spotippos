package br.com.spotippos.properties;

import java.util.Set;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.openpojo.business.annotation.BusinessKey;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "properties")
@RequiredArgsConstructor
@Getter
@ToString
@JsonIgnoreProperties(value = {"mongoId", "id", "provinces"}, allowGetters = true)
public class PropertyModel {

	@Id
	private String mongoId;
	
	@Field
	@Setter(AccessLevel.PACKAGE)
	@BusinessKey
	private Integer id;
	
	@Field
	@NotNull(message = "O campo 'title' não pode ficar vazio")
	private final String title;
	
	@Field
	@NotNull(message = "O campo 'price' não pode ficar vazio")
	private final Long price;
	
	@Field
	@NotNull(message = "O campo 'Descrição' não pode ficar vazio")
	private final String description;
	
	@Field
	@NotNull(message = "O campo 'x' não pode ficar vazio")
	@Min(value = 0, message = "Valor de x não pode ser menor que 0")
	@Max(value = 1400, message = "Valor de x não pode ser maior que 1400")
	private final Short x;
	
	@Field
	@NotNull(message = "O campo 'y' não pode ficar vazio")
	@Min(value = 0, message = "Valor de y não pode ser menor que 0")
	@Max(value = 1000, message = "Valor de y não pode ser maior que 1000")
	private final Short y;
	
	@Field
	@NotNull(message = "O campo 'beds' não pode ficar vazio")
	@Min(value = 1, message = "Deve ter no mínimo um quarto")
	@Max(value = 5, message = "Pode ter no máximo 5 quartos")
	private final Short beds;
	
	@Field
	@NotNull(message = "O campo 'baths' não pode ficar vazio")
	@Min(value = 1, message = "Deve ter no mínimo um banheiro")
	@Max(value = 4, message = "Pode ter no máximo quatro banheiros")
	private final Short baths;
	
	@Field
	@NotNull(message = "O campo 'squareMeters' não pode ficar vazio")
	@Min(value = 20, message = "Deve ter no mínimo 20 metros quadrados")
	@Max(value = 240, message = "Pode ter no máximo 240 metros quadrados")
	private final Short squareMeters;
	
	@Field
	@Setter(AccessLevel.PACKAGE)
	private Set<String> provinces;
}