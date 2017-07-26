<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- Modal -->
<div class="modal fade" id="${param.idModal}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">${param.titleModal}</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p class="text-justify">${param.bodyModal}</p>
			</div>
			<div class="modal-footer">
    <a class="btn btn-danger btn-ok ">${param.titleModal}</a>

			</div>
		</div>
	</div>
</div>
<script>
$('#${param.idModal}').on('show.bs.modal', function(e) {
    $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));            
    $('.debug-url').html('Delete URL: <strong>' + $(this).find('.btn-ok').attr('href') + '</strong>');
});
</script>
