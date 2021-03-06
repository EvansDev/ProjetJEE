package biblioj



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Auteur)
class AuteurTests {

	Auteur auteur1
	Auteur auteur2
	@Before
	public void setUp(){
		auteur1 = new Auteur(nom : "Pierre", prenom : "Jacques")
		auteur2 = new Auteur(nom : "", prenom : "")
	}
	
	@After
	public void tearsDown(){
		auteur1 = null
		auteur2 = null
	}
	
	@Test
    void testCreationAuteur() {
     	assertNotNull(auteur1)
    }
	
	@Test
	void testNomAuteur(){
		assertEquals(auteur1.getNom(), "Pierre")
	}
	
	@Test
	void testPrenomAuteur(){
		assertEquals(auteur1.getPrenom(), "Jacques")
	}
	
	@Test 
	void testAuteurNonValide(){
		assert auteur2 != null
		assert auteur2.save() == null
	}
	
	@Test
	void testToString(){
		assertEquals(auteur1.toString(), "Pierre Jacques")
	}
	
}
