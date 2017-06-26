package br.com.spotippos.properties;

import java.util.Set;

import br.com.spotippos.properties.controller.PropertyController;

public interface PropertyService {
	
	/**
	 * Salva uma propriedade informada por meio do REST Controller no método {@link PropertyController#persistNewProperty(PropertyModel)}
	 * @param propertyModel O documento de {@link PropertyModel} a ser persistido no banco de dados.
	 */
	void save(PropertyModel propertyModel);

	/**
	 * Localiza uma propriedade em Spotippos por meio do ID da mesma
	 * @param id O ID a ser pesquisado
	 * @return Uma instância de {@link PropertyModel} correspondente ao ID informado
	 */
	PropertyModel findById(Integer id);

	/**
	 * Invoca o método {@link PropertyRepository#findByCoordinates(Short, Short, Short, Short)} para buscar <br>
	 * propriedades por coordenadas geográficas.
	 * @param ax O valor de X para o canto superior esquerdo de Spotippos
	 * @param ay O valor de Y para o canto superior esquerdo de Spotippos
	 * @param bx O valor de X para o canto inferior direito de Spotippos
	 * @param by O valor de X para o canto inferior direito de Spotippos
	 * @return Um Set contendo todas as propriedades localizadas, se houver.
	 */
	PropertiesByCoordinatesModel findByCoordinates(Short ax, Short ay, Short bx, Short by);
	
	/**
	 * Localiza uma propriedade em Spottipos por meio das coordenadas geográficas da mesma
	 * @param x O valor do eixo X da propriedade
	 * @param y O valor do eixo Y da propriedade
	 * @return Um Set com as propriedades localizadas, se houver
	 */
	Set<PropertyModel> findByPoint(Short x, Short y);
}
