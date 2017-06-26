package br.com.spotippos.provinces;

import java.util.Set;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Repositório responsável por interagir com collection de {@link ProvinceModel}
 * @author edwi
 *
 */
public interface ProvinceRepository extends MongoRepository<ProvinceModel, String> {
	
	/**
	 * Recupera um set de províncias localizadas por meio de coordenadas geográficas. <br>
	 * @param x Valor a ser pesquisado no eixo X
	 * @param y Valor a ser pesquisado no eixo Y
	 * @return O Set com os documentos localizados no MongoDB
	 */
	@Query("{ $and: [{boundaries.bottomRight.x : {$gte: ?0 }}, {boundaries.upperLeft.x : {$lte: ?0 }}, {boundaries.upperLeft.y : {$gte: ?1 }}, {boundaries.bottomRight.y : {$lte: ?1 }}]}")
	Set<ProvinceModel> findByPoint(Short x, Short y);
}
