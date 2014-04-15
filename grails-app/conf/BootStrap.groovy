import biblioj.Auteur
import biblioj.Livre
import biblioj.TypeDocument

class BootStrap {

    def init = { servletContext ->
		Auteur a = new Auteur( nom : "Voltaire" , prenom : "d").save()
		TypeDocument t = new TypeDocument (intitule : "Roman").save()
		Livre l = new Livre (titre : "Candide" , nombreExemplaires : 5 , nombreExemplairesDisponibles : 0 , auteurs : a, type : t ).save()
    }
    def destroy = {
    }
}
