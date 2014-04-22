<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
		<g:layoutHead/>
		<r:layoutResources />
	</head>
	<body>
		<div id="grailsLogo" role="banner"><a href="http://grails.org"><img src="${resource(dir: 'images', file: 'logo.jpg')}" alt="Grails"/></a></div>
		<g:layoutBody/>
		<g:if test="${session.panier != null}">
			<br><br>
			<table>
				<thead>
					<tr>					
						<th>Panier</th>				
					</tr>
				</thead>
				<tbody>
				<g:each in="${session.panier}" status="i" var="livrePanier">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td>${livrePanier.toString() }</td>
						<td>${link(action:'deletePanier', controller:'livre', params : ['targetUri': (request.forwardURI - request.contextPath), 'idLivre' : session.panier[i].getId()]) { 'Supprimer' }}</td>
					</tr>  
				</g:each>
				</tbody>
			</table>	
			<g:if test="${!session.panier.isEmpty()}">
				${link(action:'reservation', controller:'reservation') { 'Valider mon panier' }}
			</g:if>
			
		</g:if>
		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
		<g:javascript library="application"/>
		<r:layoutResources />
	</body>
</html>
