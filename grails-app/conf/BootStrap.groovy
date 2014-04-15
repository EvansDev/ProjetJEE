import biblioj.Auteur
import biblioj.Livre
import biblioj.TypeDocument

class BootStrap {

    def init = { servletContext ->
		TypeDocument nouveaute = new TypeDocument (intitule : "Nouveauté")
		nouveaute.save()
		Auteur vigian = new Auteur(nom : "Vigian", prenom : "Delphine de")
		vigian.save()
		Livre livre1 = new Livre (titre:"Rien ne s'oppose à la nuit : roman", nombreExemplaires : 2,
										nombreExemplairesDisponibles: 2, type: nouveaute, auteur : vigian)
		livre1.save()
		vigian.addToLivres(livre1)
		
		
    }
    def destroy = {
    }
}
