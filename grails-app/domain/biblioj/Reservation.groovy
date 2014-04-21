package biblioj

class Reservation {

	int code 
	Date dateReservation
	static hasMany = [livres : Livre]
	//static belongsTo = Livre
	
    static constraints = {
		code blank : false, unique : true
		dateReservation blank : false, min : new Date()
    }
	
	String toString() {
		return code + " - " + dateReservation		
	}
	
}
