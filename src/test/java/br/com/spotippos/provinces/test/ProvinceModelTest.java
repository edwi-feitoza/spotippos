package br.com.spotippos.provinces.test;

import org.junit.Before;
import org.junit.Test;
import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.NoPrimitivesRule;
import com.openpojo.validation.rule.impl.NoPublicFieldsRule;
import com.openpojo.validation.test.impl.GetterTester;
import br.com.spotippos.provinces.ProvinceModel;

public class ProvinceModelTest {
	
	private PojoClass provinceModel;
	private Validator validator;
	
	@Before
	public void init(){
		
		this.provinceModel = PojoClassFactory.getPojoClass(ProvinceModel.class);
		this.validator = ValidatorBuilder.create()
				.with(new NoPrimitivesRule())
				.with(new GetterMustExistRule())
				.with(new NoPublicFieldsRule())
				.with(new GetterTester())
				.build();
	}
	
	@Test
	public void testAllProvinceModelFeatures(){
		validator.validate(this.provinceModel);
	}

}
