package biblioj



import grails.test.mixin.*

import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Reservation)
class ReservationTests {

	TypeDocument type 
	Livre livre 
	Reservation res 
	Reservation res2
	@Before
	void setUp(){
		type = new TypeDocument( intitule : "Livre Ado" )
		livre = new Livre( titre : "testLivre", type : type, nombreExemplaires : 2, nombreExemplairesDisponibles : 2 )
		res = new Reservation( code : 1, dateReservation : new Date("24/04/2014") )
		res2 = new Reservation( code : "", dateReservation: "" )
	}
	
	@After
	void tearsDown(){
		type = null
		livre = null
		res = null
	}
	
    void testCreation() {
		assert res != null
		assert res.save() != null
	}

	void testMauvaiseCreation() {
		assert res2 != null
		assert res2.save() == null
	}

	void testToString() {
		assert res.toString() == "1 - Fri Dec 04 00:00:00 CET 2015"
	}
}
