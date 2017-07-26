<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>

	<title>Laboratorio Medico</title>
	
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    	
	<!-- Bootstrap core CSS -->
	<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
	<link href="${bootstrapCss}" rel="stylesheet" />
	
	<!-- Custom styles for this template -->
	<spring:url value="/resources/css/signin.css" var="signinCss" />
	<link href="${signinCss}" rel="stylesheet" />
	
</head>

<spring:url value="/" var="urlHome" />
<spring:url value="/admins/add" var="urlAddAdmin" />

<nav class="navbar navbar-toggleable-md navbar-inverse bg-primary">

  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  
  <a class="navbar-brand" href="${urlHome}">Laboratorio Medico</a>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="${urlAddAdmin}">Agregar Administrador <span class="sr-only">(current)</span></a>
      </li>      
    </ul>    
  </div>
</nav>