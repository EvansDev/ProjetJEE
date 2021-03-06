package biblioj

import org.springframework.dao.DataIntegrityViolationException

class LivreController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "recherche", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [livreInstanceList: Livre.list(params), livreInstanceTotal: Livre.count()]
    }

    def create() {
        [livreInstance: new Livre(params)]
    }
	
	def recherche() {
		 
	}
	
	def resultat_recherche(String titre, String nom, String type, String idLivre) {
		if(session['panier'] == null){
			session['panier'] = []
	   }
		
		if (titre==null) {
			titre = session['livre']
			nom = session ['nom']
			type = session ['type']
		}
		else {
			session['livre'] = titre
			session ['nom'] = nom
			session ['type'] = type
		}
		
		if (idLivre!=null && idLivre !=0) {
			if (Livre.findById(idLivre).nombreExemplairesDisponibles > 0 && !session.panier.id.contains(Livre.findById(idLivre).id)){
				session['panier'].add(Livre.findById(idLivre))
		   }
		}
		
		def idType = Integer.parseInt(type.replaceAll("[^\\d-]", "")) // On r�cup�re juste le num�ro de l'id
			
		def c = Livre.createCriteria()
		def result = c.listDistinct {
			auteurs {
				like("nom","%"+nom+"%")
			}
			like("titre","%"+titre+"%")
			'in' ("type", TypeDocument.get(idType))
		}
		
		
		//result.take(params.max)
		[livreInstanceList: result, livreInstanceTotal: result.size()]
	}

	def deletePanier(Long idLivre) {
		int compteur = 0;
		if (session['panier']!=null) {
			for ( Livre l : session['panier']) {
				if (l.id==idLivre) {
					session['panier'].remove(compteur);
					break;
				}
				compteur++;
			}
		}
		def targetUri = params.targetUri ?: "/"
		redirect(uri: targetUri)
	}
	
    def save() {
        def livreInstance = new Livre(params)
        if (!livreInstance.save(flush: true)) {
            render(view: "create", model: [livreInstance: livreInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'livre.label', default: 'Livre'), livreInstance.id])
        redirect(action: "show", id: livreInstance.id)
    }

    def show(Long id) {
        def livreInstance = Livre.get(id)
        if (!livreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'livre.label', default: 'Livre'), id])
            redirect(action: "list")
            return
        }

        [livreInstance: livreInstance]
    }

    def edit(Long id) {
        def livreInstance = Livre.get(id)
        if (!livreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'livre.label', default: 'Livre'), id])
            redirect(action: "list")
            return
        }

        [livreInstance: livreInstance]
    }

    def update(Long id, Long version) {
        def livreInstance = Livre.get(id)
        if (!livreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'livre.label', default: 'Livre'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (livreInstance.version > version) {
                livreInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'livre.label', default: 'Livre')] as Object[],
                          "Another user has updated this Livre while you were editing")
                render(view: "edit", model: [livreInstance: livreInstance])
                return
            }
        }

        livreInstance.properties = params

        if (!livreInstance.save(flush: true)) {
            render(view: "edit", model: [livreInstance: livreInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'livre.label', default: 'Livre'), livreInstance.id])
        redirect(action: "show", id: livreInstance.id)
    }

    def delete(Long id) {
        def livreInstance = Livre.get(id)
        if (!livreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'livre.label', default: 'Livre'), id])
            redirect(action: "list")
            return
        }

        try {
            livreInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'livre.label', default: 'Livre'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'livre.label', default: 'Livre'), id])
            redirect(action: "show", id: id)
        }
    }
}
