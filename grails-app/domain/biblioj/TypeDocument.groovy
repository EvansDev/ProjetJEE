package biblioj

class TypeDocument {
	
	String intitule

    static constraints = {
		intitule blank : false, unique : true
    }
	
	String toString() {
		return intitule
	}
}
