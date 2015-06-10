<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="header.jsp" />
<body>


	<jsp:include page="menu.jsp" />
	<jsp:include page="hotel_links.jsp"/>

	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h1>Create hotel</h1>
				<form:form action="createHotel" method="POST" modelAttribute="form">
				<form:errors path="*" cssClass="errorBlock" element="div"/>
					<div class="section">
						<div class="sectionLabel">Basic information</div>
						<div class="input">
							<div class="formLabel">
								<spring:message code="label.hotel_name" />
							</div>
							<div class="value">
								<form:input path="name" />
							</div>
						</div>
						<div class="input">
							<div class="formLabel">
								<spring:message code="label.hotel_desc" />
							</div>
							<div class="value">
								<form:input path="desc" />
							</div>
						</div>
					
					<div class="section">
						<div class="sectionLabel"><spring:message code="label.address"/></div>
						<div class="input">
							<div class="formLabel">
								<spring:message code="label.street" />
							</div>
							<div class="value">
								<form:input path="street" />
							</div>
						</div>
						<div class="input">
							<div class="formLabel">
								<spring:message code="label.house_number" />
							</div>
							<div class="value">
								<form:input path="houseNumber" />
							</div>
						</div>
						<div class="input">
							<div class="formLabel">
								<spring:message code="label.country" />
							</div>
							<div class="value">
								<form:input path="country" />
							</div>
						</div>
						<div class="input">
							<div class="formLabel">
								<spring:message code="label.postal_code" />
							</div>
							<div class="value">
								<form:input path="postalCode" />
							</div>
						</div>
						<div class="input">
							<div class="formLabel">
								<spring:message code="label.city" />
							</div>
							<div class="value">
								<form:input path="city" />
							</div>
						</div>
					</div>
					
					<!-- Contact info -->
					<div class="section">
						<div class="sectionLabel"><spring:message code="label.contact"/>
						<div class="input">
							<div class="formLabel">
								<spring:message code="label.phone_number" />
							</div>
							<div class="value">
								<form:input path="phone" />
							</div>
						</div>
						<div class="input">
							<div class="formLabel">
								<spring:message code="label.mail" />
							</div>
							<div class="value">
								<form:input path="mail" />
							</div>
						</div>
					</div>
					
					<!-- Submit  -->
					<div class="button">
					<input type="submit"/>
					<%-- <spring:message code="label.submit"/> --%>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<!-- /.container -->
	<!-- jQuery -->
	<script src="js/jquery.js" />

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js" />
</body>
</html>