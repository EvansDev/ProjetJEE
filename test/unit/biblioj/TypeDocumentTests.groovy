package biblioj



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(TypeDocument)
class TypeDocumentTests {
	
	TypeDocument nouveaute 
	TypeDocument mauvaisType

	@Before
	void setUp(){
		nouveaute = new TypeDocument (intitule : "Nouveauté")
		mauvaisType = new TypeDocument (intitule : "")
	}
	
	@After
	void tearsDown(){
		nouveaute = null
		mauvaisType = null
	}
	
	@Test
    void testCreationTypeDocument() {
       	assertEquals("Nouveauté", nouveaute.getIntitule())
    }
	
	@Test
	void testMauvaisTypeVide() {
		assert mauvaisType != null
		assert mauvaisType.save() == null
	}
}
