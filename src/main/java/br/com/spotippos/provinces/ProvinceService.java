package br.com.spotippos.provinces;

import java.util.Set;
import br.com.spotippos.properties.PropertyModel;

/**
 * Service responsável por interagir com a collection de Provincias
 * @author edwi
 *
 */
public interface ProvinceService {
	
	/**
	 * Retorna uma instância de {@link ProvinceModel} dado um ponto geográfico (eixo X e eixo X)
	 * @param x O ponto a ser localizado no eixo X
	 * @param y O ponto a ser localizado no eixo Y
	 * @return Um set de províncias. Retorna uma colletion porque existem situações onde um ponto <br>
	 * pode estar contido simultaneamente em duas províncias 
	 */
	Set<ProvinceModel> findByPoint(Short x, Short y);
	
	/**
	 * Sobrescreve o método {@link this#findByPoint(Short, Short)}, retornando apenas um set com os nomes da províncias. <br>
	 * Essa informação é usada para ser setada em {@link PropertyModel}
	 * @param x O ponto a ser localizado no eixo X
	 * @param y O ponto a ser localizado no eixo Y
	 * @return Um set de Strings contendo os nomes da províncias localizadas
	 */
	Set<String> findProvincesNames(Short x, Short y);
}
