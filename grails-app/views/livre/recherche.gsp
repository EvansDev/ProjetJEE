
<%@ page import="biblioj.Livre" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'livre.label', default: 'Livre')}" />
		<title><g:message code="default.recherche.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#recherche-livre" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				<li><g:link class="list" action="recherche"><g:message code="Recherche" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		
		<form action="resultat_recherche" method="post">
    		<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'titre', 'error')} required">
				<label for="titre">
					<g:message code="livre.titre.label" default="Titre" />
					<span class="required-indicator">*</span>
				</label>
				<g:textField name="titre" required="" value="${livreInstance?.titre}"/>
			</div>
			
			<div class="fieldcontain ${hasErrors(bean: auteurInstance, field: 'nom', 'error')} required">
				<label for="titre">
					<g:message code="auteur.nom.label" default="Nom" />
					<span class="required-indicator">*</span>
				</label>
				<g:textField name="nom" required="" value="${auteurInstance?.nom}"/>
			</div>
			
			<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'type', 'error')} required">
				<label for="type">
					<g:message code="livre.type.label" default="Type" />
					<span class="required-indicator">*</span>
				</label>
				<g:select id="type" name="type.id" from="${biblioj.TypeDocument.list()}" optionKey="id" required="" value="${livreInstance?.type?.id}" class="many-to-one"/>
			</div>

    		
    		<g:submitButton name="create" class="save" value="Rechercher" />
    
		</form>
		
	</body>
</html>
