<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<html>
<head>
<jsp:include page="fragments/title.jsp" />
<jsp:include page="fragments/css/bootstrap.jsp" />
<jsp:include page="fragments/css/signin.jsp" />
<jsp:include page="fragments/css/footer.jsp" />
<jsp:include page="fragments/css/fontAwesome.jsp" />
<jsp:include page="fragments/css/animate.jsp" />
<style>
footer{
color: white;
}
html, body {
	height: 100%;
	margin: 0;
	background-color: #E9E9E9;
}

.bg-inverse {
	background-color: #0b3e71 !important;
}

#section-1 {
	background: linear-gradient(-45deg, #0b3e71 65%, transparent 0%),
		linear-gradient(135deg, #0b3e71 30%, transparent 0%),
		linear-gradient(-45deg, #0d64b0 70%, transparent 0%);
	color: white;
}

#section-3 {
	background: linear-gradient(to right, #0b3e71, #0d64b0);
	color: white;
}

#section-5 {
	background: linear-gradient(-45deg, #0b3e71 40%, transparent 0%),
		linear-gradient(135deg, #0b3e71 55%, transparent 0%),
		linear-gradient(-45deg, #0d64b0 70%, transparent 0%);
}

.divider-section {
	padding-top: 65px;
	background: #0d64b0;
}

#section-2 {
	background: linear-gradient(-45deg, #292b2c 70%, transparent 0%),
		linear-gradient(0deg, #A6C14B 100%, transparent 0%);
	color: white;
}

.fixed-top {
	position: relative;
}

@media ( min-width : 768px) AND ( min-height: 400px ) {
	.fixed-top {
		position: fixed;
	}
	section {
		padding-top: 65px;
	}
	#section-1 {
		height: 100vh;
	}
	#section-5 {
		height: 100vh;
	}
}

.carousel-item>.card, .carousel-caption.card, .section-3.card {
	border: none;
	background-color: rgba(0, 0, 0, 0.24);
}
</style>
</head>
<body>
	<nav class="navbar fixed-top navbar-toggleable-sm navbar-inverse bg-inverse " style="background-color: #e3f2fd;">
		<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<a class="navbar-brand" href="#section-1"><i class="fa fa-stethoscope fa-2x " aria-hidden="true"></i></a>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false"> <c:if test="${pageContext.response.locale.language == 'es'}">
			         ES
			      </c:if> <c:if test="${pageContext.response.locale.language == 'en'}">
			         EN
			      </c:if> <i class="fa fa-language" aria-hidden="true"></i></a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="?lang=es"> <img src="<c:url value="/resources/images/spanish-icon.png"/>" class="img-fluid" alt="Responsive image" width="30" height="30"> <spring:message code="spanish" /></a> <a class="dropdown-item" href="?lang=en"><img src="<c:url value="/resources/images/english-icon.png"/>" class="img-fluid" alt="Responsive image" width="30" height="30"> <spring:message code="english" /></a>
					</div></li>
			</ul>
			<a class="btn btn-outline-secondary my-2 my-sm-0 hidden-sm-down" href="#section-5"><i class="fa fa-sign-in fa-fw" aria-hidden="true"></i> <spring:message code="login" /></a>
		</div>
	</nav>
	<section id="section-1" class="d-flex align-items-center  justify-content-center ">
		<div class="container">
			<div class="row">
				<div class="col py-2 justify-content-center d-flex align-items-center hidden-sm-down">
					<img src="<c:url value="/resources/images/logo.svg"/>" alt="Card image cap" width="350px">
				</div>
				<div class="col py-2 text-center d-flex flex-column">
					<h2 class="display-4">
						<spring:message code="website.title" />
					</h2>
					<div class="row justify-content-center">
						<div class="col-12 col-sm-10 text-justify">
							<spring:message code="index.presentation" />
						</div>
					</div>
					<br />
					<p class="hidden-md-up">
						<a class="btn btn-outline-secondary my-2 my-sm-0" href="#section-5"><i class="fa fa-sign-in fa-fw" aria-hidden="true"></i> <spring:message code="login" /></a>
					</p>
					<br />
					<div class="row  py-2 align-items-center justify-content-center">
						<div class="col  text-center">
							<img src="<c:url value="/resources/images/GTST.svg"/>" class="img-fluid rounded" alt="Responsive image" width="100px">
						</div>
						<div class="col text-center">
							<img src="<c:url value="/resources/images/LU.svg"/>" class="img-fluid rounded" alt="Responsive image" width="100px">
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</section>
	<div class="divider-section"></div>
	<section id="section-5" class="d-flex align-items-center  justify-content-center ">
		<div class="row justify-content-center py-4 ">
			<div class="row card">
				<form class="form-signin ${param.error != null ? 'has-danger' : ''}" name='loginForm' action="<c:url value='/auth/login_check' />" method='POST'>
					<h2 class="form-signin-heading text-center">
						<spring:message code="login" />
					</h2> <label for="username" class="sr-only">Usuario:</label> <input type="number" id="username" name='username' class="form-control" placeholder="<spring:message code="login.username.placeholder" />" ${param.error != null ? 'autofocus="autofocus"' : ''} required> <label for="inputPassword" class="sr-only">Contraseña</label> <input type="password" id="inputPassword" name="password" class="form-control" placeholder="<spring:message code="login.password.placeholder" />" required>
					<button class="btn btn-lg btn-primary btn-block" name="submit" type="submit" value="submit">
						<i class="fa fa-sign-in fa-fw" aria-hidden="true"></i>
						<spring:message code="login" />
					</button>
					<div class="checkbox">
						<label><input type="checkbox" id="rememberme" name="remember-me"> <spring:message code="login.remember-me" /></label>
					</div> <spring:url value="/re/resetPassword" var="RecoverPasswordUrl" /> <a href="${RecoverPasswordUrl}"><span class="text-primary"><spring:message code="login.recovery-password" /></span></a> <c:if test="${param.error != null}">
						<div class="form-control-feedback text-center">
							<spring:message code="login.error" />
						</div>
					</c:if> <c:if test="${param.logout != null}">
						<div class="form-control-feedback text-center">
							<spring:message code="login.closed-sesion" />
						</div>
					</c:if> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
			</div>
		</div>
	</section>
	<jsp:include page="fragments/footer.jsp" />
	<jsp:include page="fragments/js/jquery.jsp" />
	<jsp:include page="fragments/js/bootstrap.jsp" />
	<c:if test="${not empty notifyMesage}">
		<jsp:include page="fragments/js/notify.jsp">
			<jsp:param name="notifyMesage" value="${notifyMesage}" />
			<jsp:param name="notifyType" value="${notifyType}" />
		</jsp:include>
	</c:if>
	<script src="<spring:url value="/resources/js/scrolling.js"/>"></script>
	
</body>
</html>