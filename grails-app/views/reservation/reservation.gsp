
<%@ page import="biblioj.Reservation" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'reservation.label', default: 'Reservation')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-reservation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-reservation" class="content scaffold-list" role="main">
			
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:if test="${!listeLivreDisponible.isEmpty()}">
			<g:if test="${indisponible == true}">
			
				<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="titre" title="${message(code: 'livre.titre.label', default: 'Titre')}" />
					
						<th><g:message code="livre.auteurs.label" default="Auteurs" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${listeLivreDisponible}" status="i" var="livreInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${livreInstance.id}">${fieldValue(bean: livreInstance, field: "titre")}</g:link></td>
					
						<td>${fieldValue(bean: livreInstance, field: "auteurs")}</td>

					</tr>  
				</g:each>
				</tbody>
			</table>
			
				<p>
 					Seules les livres ci dessus sont disponibles. Voulez-vous valider la commande avec les livres disponibles ou annuler?<br>
 					${link(action:'confirmerReservation', controller:'reservation') { 'Valider' }}<br>
 					${link(action:'annulerReservation', controller:'reservation') { 'Annuler' }}<br>
 				</p>
			</g:if>
			<g:else>
 				<p>La reservation a été validée<br>
 					Votre code de reservation est : ${session.code}<br>
 					La date limite de retrait est : ${session.dateLimite}<br></p>
 			</g:else>
 			</g:if>
 			<g:else>
 				<p>Plus aucun des livres n'est disponible</p>
 			</g:else>
		</div>
	</body>
</html>
