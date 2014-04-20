package biblioj



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Livre)
class LivreTests {

	TypeDocument nouveaute
	Auteur vigian 
	
	Livre livre1 
	Livre livre2
	
	@Before
	void setUp (){
		nouveaute = new TypeDocument (intitule : "Nouveauté")
		vigian = new Auteur(nom : "Vigian", prenom : "Delphine de")
		livre1 = new Livre (titre:"Rien ne s'oppose à la nuit : roman", nombreExemplaires : 2,
			nombreExemplairesDisponibles: 2, type: nouveaute, auteurs : vigian)
		livre2 = new Livre (titre:"", nombreExemplaires : 2,
			nombreExemplairesDisponibles: 2, type: nouveaute, auteurs : vigian)
	}
	
	@After
	void tearsDown() {
		nouveaute = null
		vigian = null
		livre1 = null
		livre2 = null
	}
	
	@Test
    void testCreationValide() {
		assertEquals ("Rien ne s'oppose à la nuit : roman", livre1.getTitre())
    }
	
	@Test
	void testMauvaiseCreation (){
		assert livre2 != null
		assert livre2.save() == null
	}
}
