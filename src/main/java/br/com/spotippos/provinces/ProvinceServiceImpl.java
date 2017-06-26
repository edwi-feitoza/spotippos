package br.com.spotippos.provinces;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinceServiceImpl implements ProvinceService {

	@Autowired
	private ProvinceRepository provinceRepository; 
	
	@Override
	public Set<ProvinceModel> findByPoint(Short x, Short y) {
		return this.provinceRepository.findByPoint(x, y);
	}

	@Override
	public Set<String> findProvincesNames(Short x, Short y) {
		return this.findByPoint(x, y)
			.stream()
			.map(ProvinceModel::getProvince)
			.collect(Collectors.toSet());
	}
}