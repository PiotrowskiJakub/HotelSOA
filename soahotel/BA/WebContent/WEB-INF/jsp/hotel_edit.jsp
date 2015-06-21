<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="header.jsp" />
<body>


	<jsp:include page="menu.jsp" />
	<jsp:include page="hotel_links.jsp" />

	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h1>
					<spring:message code="label.edit_hotel" />
				</h1>
				<form:form action="editHotel" method="POST" modelAttribute="form">
					<form:errors path="*" cssClass="errorBlock" element="div" />
					<jsp:include page="hotel_form.jsp" />
					<!-- Submit  -->
					<div class="button">
						<input type="submit" />
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<!-- /.container -->
	<!-- jQuery -->
	<script src="js/jquery.js" ></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js" ></script>
</body>
</html>