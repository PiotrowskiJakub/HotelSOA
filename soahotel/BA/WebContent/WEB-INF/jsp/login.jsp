
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="header.jsp" />
<body>

	<jsp:include page="menu.jsp" />

	<!-- Page Content -->
	<div class="container">
		<form:form method="post" modelAttribute="form" action="login">
			<div class="section">
				<div class="sectionLabel">Login</div>
				<form:errors path="*" cssClass="errorBlock" element="div" />
				<div class="input">
					<div class="formLabel">
						<spring:message code="label.mail" />
					</div>
					<div class="value">
						<form:input path="mail" />
					</div>
				</div>
				<div class="input">
					<div class="formLabel">
						<spring:message code="label.password" />
					</div>
					<div class="value">
						<form:password path="password" />
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
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js" ></script>
</body>
</html>