package br.com.spotippos.properties.test;

import org.junit.Before;
import org.junit.Test;
import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.BusinessKeyMustExistRule;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.NoPrimitivesRule;
import com.openpojo.validation.rule.impl.NoPublicFieldsRule;
import com.openpojo.validation.test.impl.GetterTester;
import br.com.spotippos.properties.PropertyModel;

public class PropertyModelTest {

	private PojoClass propertyModel;
	private Validator validator;
	
	@Before
	public void init(){
		this.propertyModel = PojoClassFactory.getPojoClass(PropertyModel.class);
		this.validator = ValidatorBuilder.create()
				.with(new NoPrimitivesRule())
				.with(new GetterMustExistRule())
				.with(new NoPublicFieldsRule())
				.with(new GetterTester())
				.with(new BusinessKeyMustExistRule())
				.build();
	}
	
	@Test
	public void testAllPropertyModelFeatures(){
		validator.validate(this.propertyModel);
	}
}
