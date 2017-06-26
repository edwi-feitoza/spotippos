package br.com.spotippos.infra.test;

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
import br.com.spotippos.infra.CountersModel;

public class CountersModelTest {

	private PojoClass countersModel;
	private Validator validator;
	
	@Before
	public void init(){
		this.countersModel = PojoClassFactory.getPojoClass(CountersModel.class);
		this.validator = ValidatorBuilder.create()
				.with(new NoPrimitivesRule())
				.with(new GetterMustExistRule())
				.with(new NoPublicFieldsRule())
				.with(new GetterTester())
				.build();
	}
	
	@Test
	public void testAllCountersModelFeatures(){
		validator.validate(this.countersModel);
	}
}
