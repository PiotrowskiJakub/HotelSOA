<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="header.jsp" />
<body>

	<jsp:include page="menu.jsp" />

	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h1>Registration</h1>
				<form:form action="create" method="POST" modelAttribute="account">
					<div class="section">
						<div class="sectionLabel">Temp</div>
						<div class="input">
							<div class="formLabel">
								<spring:message code="label.first_name" />
							</div>
							<div class="value">
								<form:input path="firstName" />
							</div>
						</div>
						<div class="input">
							<div class="formLabel">
								<spring:message code="label.last_name" />
							</div>
							<div class="value">
								<form:input path="lastName" />
							</div>
						</div>
						<div class="input">
							<div class="formLabel">
								<spring:message code="label.last_name" />
							</div>
							<div class="value">
								<form:input path="lastName" />
							</div>
						</div>
					</div>
					
					<div class="section">
						<div class="sectionLabel"><spring:message code="label.address"/></div>
						<div class="input">
							<div class="formLabel">
								<spring:message code="label.street" />
							</div>
							<div class="value">
								<form:input path="address.street" />
							</div>
						</div>
						<div class="input">
							<div class="formLabel">
								<spring:message code="label.house_number" />
							</div>
							<div class="value">
								<form:input path="address.houseNumber" />
							</div>
						</div>
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