package br.com.spotippos.properties.controller.test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import br.com.spotippos.SpotipposApplication;
import br.com.spotippos.properties.PropertyModel;
import br.com.spotippos.properties.PropertyService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpotipposApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class PropertyControllerTest {
	
	@Autowired
	private PropertyService propertyService;
	
	private PropertyModel jaby;
	private PropertyModel gode;
	private PropertyModel ruja;
	private PropertyModel godyAndRuja;
	private PropertyModel scavy;
	private PropertyModel groola;
	private PropertyModel nova;
	private PropertyModel incorretX;
	private PropertyModel incorretSquareMeter;
	private PropertyModel jabyDuplicated;

	@Before
	public void setup(){
		this.jaby = new PropertyModel("Imóvel em Jaby", 18000L, "Lorem ipsum dolor.", new Short("1300"), new Short("999"), 
				new Short("4"), new Short("3"), new Short("54"));
		this.gode = new PropertyModel("Imóvel em Gode", 18000L, "Lorem ipsum dolor.", new Short("20"), new Short("900"), 
				new Short("4"), new Short("3"), new Short("54"));
		this.ruja = new PropertyModel("Imóvel em Ruja", 18000L, "Lorem ipsum dolor.", new Short("1050"), new Short("1000"), 
				new Short("4"), new Short("3"), new Short("54"));
		this.godyAndRuja = new PropertyModel("Imóvel em Gode e Ruja", 18000L, "Lorem ipsum dolor.", new Short("500"), new Short("900"), 
				new Short("4"), new Short("3"), new Short("54"));
		this.scavy = new PropertyModel("Imóvel em Scavy", 18000L, "Lorem ipsum dolor.", new Short("20"), new Short("20"), 
				new Short("4"), new Short("3"), new Short("54"));
		this.groola = new PropertyModel("Imóvel em Groola", 18000L, "Lorem ipsum dolor.", new Short("700"), new Short("20"), 
				new Short("4"), new Short("3"), new Short("54"));
		this.nova = new PropertyModel("Imóvel em Nova", 18000L, "Lorem ipsum dolor.", new Short("1350"), new Short("20"), 
				new Short("4"), new Short("3"), new Short("54"));
		this.incorretX = new PropertyModel("Imóvel em Nova", 18000L, "Lorem ipsum dolor.", new Short("2000"), new Short("20"), 
				new Short("4"), new Short("3"), new Short("54"));
		this.incorretSquareMeter = new PropertyModel("Imóvel em Nova", 18000L, "Lorem ipsum dolor.", new Short("1000"), new Short("20"), 
				new Short("4"), new Short("3"), new Short("300"));
		this.jabyDuplicated = new PropertyModel("Imóvel em Jaby", 18000L, "Lorem ipsum dolor.", new Short("1300"), new Short("999"), 
				new Short("4"), new Short("3"), new Short("54"));
	}
	
	@Test
	public void shouldSaveNewJabyPropertyModelInJabyProvince(){
		given()
			.header("Accept", "application/json")
			.contentType("application/json")
			.body(this.jaby)
		.expect()
			.statusCode(HttpStatus.CREATED.value())
		.when()
			.post("/properties");
		
		Set<PropertyModel> jabySaved = this.propertyService.findByPoint(this.jaby.getX(), this.jaby.getY());
		
		assertEquals("Imóvel em Jaby", jabySaved.stream().findFirst().get().getTitle());
		assertEquals(new Short("54"),jabySaved.stream().findFirst().get().getSquareMeters());
		assertTrue("Jaby", jabySaved.stream().findFirst().get().getProvinces().contains("Jaby"));
	}
	
	@Test
	public void shouldSaveNewGodePropertyModelInJabyProvince(){
		given()
			.header("Accept", "application/json")
			.contentType("application/json")
			.body(this.gode)
		.expect()
			.statusCode(HttpStatus.CREATED.value())
		.when()
			.post("/properties");
		
		Set<PropertyModel> godeSaved = this.propertyService.findByPoint(this.gode.getX(), this.gode.getY());
		
		assertEquals("Imóvel em Gode", godeSaved.stream().findFirst().get().getTitle());
		assertEquals(new Short("54"),godeSaved.stream().findFirst().get().getSquareMeters());
		assertTrue("Gode", godeSaved.stream().findFirst().get().getProvinces().contains("Gode"));
	}
	
	@Test
	public void shouldSaveNewRujaPropertyModelInJabyProvince(){
		given()
			.header("Accept", "application/json")
			.contentType("application/json")
			.body(this.ruja)
		.expect()
			.statusCode(HttpStatus.CREATED.value())
		.when()
			.post("/properties");

		Set<PropertyModel> godeSaved = this.propertyService.findByPoint(this.ruja.getX(), this.ruja.getY());
		
		assertEquals("Imóvel em Ruja", godeSaved.stream().findFirst().get().getTitle());
		assertEquals(new Short("54"),godeSaved.stream().findFirst().get().getSquareMeters());
		assertTrue("Ruja", godeSaved.stream().findFirst().get().getProvinces().contains("Ruja"));
	}
	
	@Test
	public void shouldSaveNewRujaAndGodePropertyModelInJabyProvince(){
		given()
			.header("Accept", "application/json")
			.contentType("application/json")
			.body(this.godyAndRuja)
		.expect()
			.statusCode(HttpStatus.CREATED.value())
		.when()
			.post("/properties");

		Set<PropertyModel> godeSaved = this.propertyService.findByPoint(this.godyAndRuja.getX(), this.godyAndRuja.getY());
		
		assertEquals("Imóvel em Gode e Ruja", godeSaved.stream().findFirst().get().getTitle());
		assertEquals(new Short("54"),godeSaved.stream().findFirst().get().getSquareMeters());
		assertTrue("Ruja", godeSaved.stream().findFirst().get().getProvinces().contains("Ruja"));
		assertTrue("Gode", godeSaved.stream().findFirst().get().getProvinces().contains("Gode"));
	}
	
	@Test
	public void shouldSaveNewScavyPropertyModelInJabyProvince(){
		given()
			.header("Accept", "application/json")
			.contentType("application/json")
			.body(this.scavy)
		.expect()
			.statusCode(HttpStatus.CREATED.value())
		.when()
			.post("/properties");

		Set<PropertyModel> godeSaved = this.propertyService.findByPoint(this.scavy.getX(), this.scavy.getY());
		
		assertEquals("Imóvel em Scavy", godeSaved.stream().findFirst().get().getTitle());
		assertEquals(new Short("54"),godeSaved.stream().findFirst().get().getSquareMeters());
		assertTrue("Scavy", godeSaved.stream().findFirst().get().getProvinces().contains("Scavy"));
	}
	
	@Test
	public void shouldSaveNewGroolaPropertyModelInJabyProvince(){
		given()
			.header("Accept", "application/json")
			.contentType("application/json")
			.body(this.groola)
		.expect()
			.statusCode(HttpStatus.CREATED.value())
		.when()
			.post("/properties");

		Set<PropertyModel> godeSaved = this.propertyService.findByPoint(this.groola.getX(), this.groola.getY());
		
		assertEquals("Imóvel em Groola", godeSaved.stream().findFirst().get().getTitle());
		assertEquals(new Short("54"),godeSaved.stream().findFirst().get().getSquareMeters());
		assertTrue("Groola", godeSaved.stream().findFirst().get().getProvinces().contains("Groola"));
	}
	
	@Test
	public void shouldSaveNewNovaPropertyModelInJabyProvince(){
		given()
			.header("Accept", "application/json")
			.contentType("application/json")
			.body(this.nova)
		.expect()
			.statusCode(HttpStatus.CREATED.value())
		.when()
			.post("/properties");

		Set<PropertyModel> godeSaved = this.propertyService.findByPoint(this.nova.getX(), this.nova.getY());
		
		assertEquals("Imóvel em Nova", godeSaved.stream().findFirst().get().getTitle());
		assertEquals(new Short("54"),godeSaved.stream().findFirst().get().getSquareMeters());
		assertTrue("Nova", godeSaved.stream().findFirst().get().getProvinces().contains("Nova"));
	}
	
	@Test
	public void shouldReturnAnErrorDueToIncorrectValueForX(){
		  given()
			.header("Accept", "application/json")
			.contentType("application/json")
			.body(this.incorretX)
		.expect()
			.statusCode(HttpStatus.BAD_REQUEST.value())
		.expect()
			.body("errors.message", hasItem("Valor de x não pode ser maior que 1400"))
		.when()
			.post("/properties");
	}
	
	@Test
	public void shouldReturnAnErrorDueToIncorrectValueForSquareMeter(){
		  given()
			.header("Accept", "application/json")
			.contentType("application/json")
			.body(this.incorretSquareMeter)
		.expect()
			.statusCode(HttpStatus.BAD_REQUEST.value())
		.expect()
			.body("errors.message", hasItem("Pode ter no máximo 240 metros quadrados"))
		.when()
			.post("/properties");
	}
	
	@Test
	public void shouldReturnAnErrorDueToAttemptToPersistDuplicateProperty(){
		  given()
			.header("Accept", "application/json")
			.contentType("application/json")
			.body(this.jabyDuplicated)
		.expect()
			.statusCode(HttpStatus.CONFLICT.value())
		.expect()
			.body("errors.message", hasItem("Já existe um imóvel nesta região de Spottipos. Por favor, escolha outra coordenda de X e Y"))
		.when()
			.post("/properties");
	}
	
	@Test
	public void shouldReturnAnErrorDueToAttemptAccessURIThatDoesNotExists(){
		  given()
			.header("Accept", "application/json")
			.contentType("application/json")
			.body(this.jabyDuplicated)
		.expect()
			.statusCode(HttpStatus.NOT_FOUND.value())
		.when()
			.post("/propert");
	}
	
	@Test
	public void shouldReturnAnErrorDueToAttemptSetBiggerValues(){
		  given()
			.header("Accept", "application/json")
			.contentType("application/json")
			.body("{ 'x': 222, 'y': 444, 'title': 'Imóvel código 1, com 5 quartos e 4 banheiros', 'price': 1250000, "
					+ "'description': 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 'beds': 4, 'baths': 3, "
					+ "'squareMeters': 11111111111 }")
		.expect()
			.statusCode(HttpStatus.BAD_REQUEST.value())
		.expect()
			.body("errors.message", hasItem("Por favor, verifique se os dados passados via JSON não estão com falhas, "
				+ "como texto em informação numérica ou valores numéricos altos para valores de X, Y e metros quadrados"))
		.when()
			.post("/properties");
	}
}