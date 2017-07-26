<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- https://cdn.datatables.net/v/bs-3.3.7/dt-1.10.15/datatables.min.js --%>
<script src="<spring:url value="/resources/js/datatables.min.js"/>"></script>
<%--  https://cdn.datatables.net/1.10.15/js/dataTables.bootstrap4.min.js --%>
<script src="<spring:url value="/resources/js/dataTables.bootstrap4.min.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {

	    $('#datatable').DataTable(
	    		 
	    
	    		<c:if test="${pageContext.response.locale.language == 'es'}">
				{
    	        "language": {
    	            "sProcessing":     "Procesando...",
    	            "sLengthMenu":     "Mostrar _MENU_ registros",
    	            "sZeroRecords":    "No se encontraron resultados",
    	            "sEmptyTable":     "Ningún dato disponible en esta tabla",
    	            "sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
    	            "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
    	            "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
    	            "sInfoPostFix":    "",
    	            "sSearch":         "Buscar:",
    	            "sUrl":            "",
    	            "sInfoThousands":  ",",
    	            "sLoadingRecords": "Cargando...",
    	            "oPaginate": {
    	                "sFirst":    "Primero",
    	                "sLast":     "Último",
    	                "sNext":     ">>",
    	                "sPrevious": "<<"
    	            },
    	            "oAria": {
    	                "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
    	                "sSortDescending": ": Activar para ordenar la columna de manera descendente"
    	            }
    	        }
    	    }
				</c:if>
	    
	    );
	} );
	</script>
