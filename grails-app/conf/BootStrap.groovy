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
		Livre livre2 = new Livre (titre:"Hunger Games", nombreExemplaires : 10,
										nombreExemplairesDisponibles: 10, type: livreAdo, auteurs : collins )
		Livre livre3 = new Livre (titre:"La couleur des sentiments", nombreExemplaires : 8,
										nombreExemplairesDisponibles: 8, type: nouveaute, auteurs : stockett)
		Livre livre4 = new Livre (titre:"L'embrasement", nombreExemplaires : 10,
										nombreExemplairesDisponibles: 10, type: livreAdo, auteurs : collins)
		Livre livre5 = new Livre (titre:"Limonov", nombreExemplaires : 5,
										nombreExemplairesDisponibles : 5, type: nouveaute, auteurs : carrere)
		Livre livre6 = new Livre (titre:"1Q84. 1. Avril-juin", nombreExemplaires : 4,
										nombreExemplairesDisponibles: 4, type: nouveaute, auteurs : murakami)
		Livre livre7 = new Livre (titre:"1Q84. 3. Octobre-décembre", nombreExemplaires : 4,
										nombreExemplairesDisponibles: 4, type: nouveaute, auteurs : murakami)
		Livre livre8 = new Livre (titre:"La révolte", nombreExemplaires : 10,
										nombreExemplairesDisponibles: 10, type: livreAdo, auteurs : collins)
		Livre livre9 = new Livre (titre:"La liste de mes envies", nombreExemplaires : 6,
										nombreExemplairesDisponibles: 6, type: livreAdulte, auteurs : delacourt)
		Livre livre10 = new Livre (titre:"1Q84. 2. Juillet-septembre", nombreExemplaires : 4,
										nombreExemplairesDisponibles : 4, type: nouveaute, auteurs : murakami)
		livre1.save()
		livre2.save()
		livre3.save()
		livre4.save()
		livre5.save()
		livre6.save()
		livre7.save()
		livre8.save()
		livre9.save()
		livre10.save()
    }
    def destroy = {
    }
}
