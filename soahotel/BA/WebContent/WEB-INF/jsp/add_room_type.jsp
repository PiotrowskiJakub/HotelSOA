<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="header.jsp" />
<body>


	<jsp:include page="menu.jsp" />
	<jsp:include page="hotel_links.jsp" />

	<!-- Page Content -->
	<div class="container">
		<h1>
			<spring:message code="label.add_room_type" />
		</h1>
		<form:form action="createRoomType" method="POST" modelAttribute="form">
			<form:errors path="*" cssClass="errorBlock" element="div" />
			<div class="section">
				<!-- <div class="sectionLabel">Basic information</div> -->
				<div class="input">
					<div class="formLabel">
						<spring:message code="label.room_type_name" />
					</div>
					<div class="value">
						<form:input path="name" />
					</div>
				</div>
				<div class="input">
					<div class="formLabel">
						<spring:message code="label.room_type_desc" />
					</div>
					<div class="value">
						<form:input path="description" />
					</div>
				</div>
				<div class="input">
					<div class="formLabel">
						<spring:message code="label.price" />
					</div>
					<div class="value">
						<form:input path="price" />
					</div>
				</div>
			</div>
			<div class="button">
				<input type="submit" />
			</div>

		</form:form>
	</div>
	<!-- /.container -->
	<!-- jQuery -->
	<script src="js/jquery.js" />

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js" />
</body>
</html>