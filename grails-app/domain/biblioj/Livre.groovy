package biblioj

class Livre {
	
	String titre
	int nombreExemplaires
	int nombreExemplairesDisponibles
	TypeDocument type // type du livre
	static hasMany = [auteurs : Auteur , reservations : Reservation] // Un livre peut être écrit par plusieurs documents
	//static belongsToAuteur = Auteur
	

    static constraints = {
		titre blank : false
		nombreExemplaires blank : false
		nombreExemplairesDisponibles blank : false
    }
	
	String toString() {
		return titre;		
	}
	
}
