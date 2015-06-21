<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp" />
<body>


	<jsp:include page="menu.jsp" />
	<jsp:include page="hotel_links.jsp" />

	<!-- Page Content -->
	<div class="container">
		<h1>
			<spring:message code="label.add_room_section" />
		</h1>
		<form:form action="createRoom?id=${hotelID}" method="POST" modelAttribute="form">
			<form:errors path="*" cssClass="errorBlock" element="div" />
			<div class="section">
				<!-- <div class="sectionLabel">Basic information</div> -->
				<div class="input">
					<div class="formLabel">
						<spring:message code="label.floor" />
					</div>
					<div class="value">
						<form:input path="floor" />
					</div>
				</div>
				<div class="input">
					<div class="formLabel">
						<spring:message code="label.number" />
					</div>
					<div class="value">
						<form:input path="number" />
					</div>
				</div>
				<div class="input">
					<div class="formLabel">
						<spring:message code="label.size" />
					</div>
					<div class="value">
						<form:input path="size" />
					</div>
				</div>
				<div class="input">
					<div class="formLabel">
						<spring:message code="label.room_type" />
					</div>
					<div class="value">
						<form:select path="roomTypeID">
							<c:forEach var="roomType" items="${roomTypes}">
								<form:option value="${roomType.id}">${roomType.name}</form:option>
							</c:forEach>
						</form:select>
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
	<script src="js/jquery.js" ></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js" ></script>
</body>
</html>