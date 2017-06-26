package br.com.spotippos.properties;

import java.util.Set;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.com.spotippos.errors.DuplicatePropertyException;

/**
 * Repositório responsável por interagir com a collection de {@link PropertyModel}
 * @author edwi
 *
 */
public interface PropertyRepository extends MongoRepository<PropertyModel, String> {

	/**
	 * Busca uma propriedade por meio do campo id. Não se trata do campo '_id' setado por default pelo MongoDB. <br>
	 * Trata-se de um campo numérico incremental criado para atender aos requisitos do desafio. 
	 * @param id O id pelo qual deve ser feita a busca da propriedade.
	 * @return A instância de {@link PropertyModel} correspondente ao ID
	 */
	@Query("{id: ?0}")
	PropertyModel findById(Integer id);
	
	/**
	 * Busca um imóvel em Spotippos por meio de coordenadas geográficas. Este método é usado por <br>
	 * {@link PropertyService#save(PropertyModel)}. Caso encontre uma propriedade vai lançar uma <br>
	 * {@link DuplicatePropertyException} informando que não pode criar uma propriedade onde já existe uma.
	 * @param x O valor a ser pesquisado no eixo X
	 * @param y O valor a ser pesquisado no eixo Y
	 * @return As propriedades encontradas neste ponto, se houver.
	 */
	@Query("{$and :[{x: ?0}, {y : ?1}]}")
	Set<PropertyModel> findByPoint(Short x, Short y);
	
	/**
	 * Busca todas as propriedades em Spotippos delimitadas pelas coordenadas informadas.
	 * @param ax O valor de X para o canto superior esquerdo de Spotippos
	 * @param ay O valor de Y para o canto superior esquerdo de Spotippos
	 * @param bx O valor de X para o canto inferior direito de Spotippos
	 * @param by O valor de X para o canto inferior direito de Spotippos
	 * @return Um Set contendo todas as propriedades localizadas, se houver.
	 */
	@Query("{ $and: [{x : {$gte: ?0 }}, {x : {$lte: ?2 }}, {y : {$lte: ?1 }}, {y : {$gte: ?3 }}]}")
	Set<PropertyModel> findByCoordinates(Short ax, Short ay, Short bx, Short by);
}