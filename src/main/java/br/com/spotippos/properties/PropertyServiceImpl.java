package br.com.spotippos.properties;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.spotippos.errors.DuplicatePropertyException;
import br.com.spotippos.infra.CounterRepositoryCustom;
import br.com.spotippos.provinces.ProvinceService;

@Service
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	private PropertyRepository propertyRepository;
	
	@Autowired
	private CounterRepositoryCustom counterRepositoryCustom;
	
	@Autowired
	private ProvinceService provinceService;
	
	@Override
	public void save(PropertyModel propertyModel) {
		Set<PropertyModel> properties = this.propertyRepository.findByPoint(propertyModel.getX(), propertyModel.getY());
		if(properties.size() > 0) throw new DuplicatePropertyException("Já existe um imóvel nesta região de Spottipos. Por favor, escolha outra coordenda de X e Y");
		propertyModel.setId(this.counterRepositoryCustom.getNextSequence("property_id"));
		propertyModel.setProvinces(this.provinceService.findProvincesNames(propertyModel.getX(), propertyModel.getY()));
		this.propertyRepository.save(propertyModel);
	}

	@Override
	public PropertyModel findById(Integer id) {
		return this.propertyRepository.findById(id);
	}

	@Override
	public PropertiesByCoordinatesModel findByCoordinates(Short ax, Short ay, Short bx, Short by) {
		Set<PropertyModel> properties =  this.propertyRepository.findByCoordinates(ax, ay, bx, by);
		return new PropertiesByCoordinatesModel(properties.size(), properties);
	}

	@Override
	public Set<PropertyModel> findByPoint(Short x, Short y) {
		return this.propertyRepository.findByPoint(x, y);
	}
}