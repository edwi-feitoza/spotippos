package br.com.spotippos.properties.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.spotippos.properties.PropertiesByCoordinatesModel;
import br.com.spotippos.properties.PropertyModel;
import br.com.spotippos.properties.PropertyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "/properties")
@RequestMapping(path = "/properties", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PropertyController {
	
	@Autowired
	private PropertyService propertyService;
	
	@ApiOperation(value = "Salva uma nova propriedade em Spotippos", notes = "Salva uma nova propriedade em Spotippos", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PostMapping
	public ResponseEntity<String> persistNewProperty(@Valid @RequestBody PropertyModel propertyModel){
		this.propertyService.save(propertyModel);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Busca uma propriedade em Spotippos por ID", notes = "Busca uma propriedade em Spotippos por ID", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@GetMapping(value = "/{id}")
	public ResponseEntity<PropertyModel> findById(@PathVariable(value = "id") final Integer id){
		PropertyModel model = this.propertyService.findById(id);
		if(model == null) return new ResponseEntity<PropertyModel>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<PropertyModel>(model, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "Busca propriedades em Spotippos por coordenadas geográficas", notes = "Busca uma propriedade em Spotippos por ID", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@GetMapping
	public ResponseEntity<PropertiesByCoordinatesModel> findByCoordinates(
			@RequestParam("ax") final Short ax,
			@RequestParam("ay") final Short ay,
			@RequestParam("bx") final Short bx,
			@RequestParam("by") final Short by) {
		PropertiesByCoordinatesModel model =  this.propertyService.findByCoordinates(ax, ay, bx, by);
		if(model.getFoundProperties() == 0) return new ResponseEntity<PropertiesByCoordinatesModel>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<PropertiesByCoordinatesModel>(model, HttpStatus.OK);
	}
}