<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../fragments/title.jsp" />
<jsp:include page="../fragments/css/bootstrap.jsp" />
<jsp:include page="../fragments/css/footer.jsp" />
<jsp:include page="../fragments/fontAwesome.jsp" />
</head>

<body>
	<jsp:include page="navbar.jsp" />
	<br />
	<jsp:include page="../fragments/alert.jsp" />
	<br />

	<div class="container">
		<div class="card">
		<c:if test="${not empty exams}">
		<table class="table table-bordered table-sm">
			<thead>
				<tr>
					<th>Orden</th>
					<th>Estudio</th>
					<th>Paciente</th>
					<th>Acción</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="exam" items="${exams}">
					<tr>
						<th scope="row">${exam.order}</th>
						<td>${exam.displayCode}</td>
						<td>${exam.displaySubject}</td>
						<td><spring:url
								value="/lab/${exam.order}/${exam.referenceSubject}/RealizarOrden"
								var="RealizarOrdenUrl" /> <a href="${RealizarOrdenUrl}"
							class="btn btn-sm btn-primary">Realizar</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	</div>
	</div>
	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>