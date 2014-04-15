import biblioj.Auteur
import biblioj.Livre
import biblioj.TypeDocument

class BootStrap {

    def init = { servletContext ->
		TypeDocument nouveaute = new TypeDocument (intitule : "Nouveauté")
		TypeDocument livreAdo = new TypeDocument (intitule : "Livre Ado")
		TypeDocument livreAdulte = new TypeDocument(intitule : "Livre Adulte")
		nouveaute.save()
		livreAdo.save()
		livreAdulte.save()
		
		Auteur vigian = new Auteur(nom : "Vigian", prenom : "Delphine de")
		Auteur collins = new Auteur(nom : "Collins", prenom : "Suzanne")
		Auteur stockett = new Auteur (nom : "Stockett", prenom : "Kathryn")
		Auteur carrere = new Auteur (nom : "Carrere", prenom : "Emmanuel")
		Auteur murakami = new Auteur (nom : "Murakami", prenom :"Hiraki")
		Auteur delacourt = new Auteur (nom : "Delacourt", prenom : "Gregoire")
		vigian.save()
		collins.save()
		stockett.save()
		carrere.save()
		murakami.save()
		delacourt.save()
		
		Livre livre1 = new Livre (titre:"Rien ne s'oppose à la nuit : roman", nombreExemplaires : 2,
										nombreExemplairesDisponibles: 2, type: nouveaute, auteurs : vigian)
		Livre livre2 = new Livre (titre:"Hunger Games", nombreExemplaire : 10,
										nombreExemplaireDisponible: 10, type: livreAdo, auteur : collins )
		livre1.save()
		livre2.save()
    }
    def destroy = {
    }
}
