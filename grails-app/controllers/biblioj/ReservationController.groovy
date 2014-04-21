package biblioj

import javax.servlet.SessionCookieConfig;

import org.springframework.dao.DataIntegrityViolationException

class ReservationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }
	
	
	def reservation() {
		
		def compteur = 0
		def code = 0
		def listeReservation = []
		def livreIndisponible = false
		
		
		// Creation de la liste des livres disponibles
		for (Livre l : session.panier) {
			if (Livre.findById(session.panier[compteur].id).getNombreExemplairesDisponibles()>0 && Livre.findById(session.panier[compteur].id).getNombreExemplairesDisponibles()!=null) {
				Livre livre = Livre.findById(session.panier[compteur].id)
				livre.setNombreExemplairesDisponibles(livre.getNombreExemplairesDisponibles()-1)
				listeReservation.add(livre)
			}
			else {
				System.out.println("INDISPO")
				livreIndisponible = true
			}
			compteur++
		}
		
		// Création du code de réservation
		while (code==0 || !Reservation.findAllByCode(code).isEmpty()) {
			code = (int)(Math.random() * 1000)
		}
		session['code'] = code
		
		// On crée la reservation 
		session['reservation'] = new Reservation(code : code, livres : listeReservation, dateReservation : new Date());
		
		
		if (livreIndisponible || listeReservation.isEmpty()) {
			
		}
		else {
			session['panier']=[]
			session['reservation'].save()
			Calendar dateLimite = Calendar.getInstance()
			dateLimite.setTime(new Date())
			dateLimite.add(Calendar.HOUR, 24)
			session['dateLimite'] = dateLimite.getTime().toString()
		}		
		[listeLivreDisponible : listeReservation, indisponible : livreIndisponible]
	}
	
	def confirmerReservation() {
		session['panier']=[]
		session['reservation'].save()
		Calendar dateLimite = Calendar.getInstance()
		dateLimite.setTime(new Date())
		dateLimite.add(Calendar.HOUR, 24)
		session['dateLimite'] = dateLimite.getTime().toString()
	}
	
	def annulerReservation() {
		
	}

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [reservationInstanceList: Reservation.list(params), reservationInstanceTotal: Reservation.count()]
    }

    def create() {
        [reservationInstance: new Reservation(params)]
    }

    def save() {
        def reservationInstance = new Reservation(params)
        if (!reservationInstance.save(flush: true)) {
            render(view: "create", model: [reservationInstance: reservationInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'reservation.label', default: 'Reservation'), reservationInstance.id])
        redirect(action: "show", id: reservationInstance.id)
    }

    def show(Long id) {
        def reservationInstance = Reservation.get(id)
        if (!reservationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
            redirect(action: "list")
            return
        }

        [reservationInstance: reservationInstance]
    }

    def edit(Long id) {
        def reservationInstance = Reservation.get(id)
        if (!reservationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
            redirect(action: "list")
            return
        }

        [reservationInstance: reservationInstance]
    }

    def update(Long id, Long version) {
        def reservationInstance = Reservation.get(id)
        if (!reservationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (reservationInstance.version > version) {
                reservationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'reservation.label', default: 'Reservation')] as Object[],
                          "Another user has updated this Reservation while you were editing")
                render(view: "edit", model: [reservationInstance: reservationInstance])
                return
            }
        }

        reservationInstance.properties = params

        if (!reservationInstance.save(flush: true)) {
            render(view: "edit", model: [reservationInstance: reservationInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'reservation.label', default: 'Reservation'), reservationInstance.id])
        redirect(action: "show", id: reservationInstance.id)
    }

    def delete(Long id) {
        def reservationInstance = Reservation.get(id)
        if (!reservationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
            redirect(action: "list")
            return
        }

        try {
            reservationInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
            redirect(action: "show", id: id)
        }
    }
}
