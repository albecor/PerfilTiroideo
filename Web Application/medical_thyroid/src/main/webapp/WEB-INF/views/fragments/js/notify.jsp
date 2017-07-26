<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%--https://github.com/mouse0270/bootstrap-notify --%>
<script src="<spring:url value="/resources/js/bootstrap-notify.min.js"/>"></script>
<script>
	$(document).ready(function() {
		$.notify({
			icon : 'fa fa-star',
			message : "<spring:message code="${notifyMesage}" />"
		}, {
			type : '${notifyType}',
			placement : {
				align : 'center'
			}
		});
	});
</script>
