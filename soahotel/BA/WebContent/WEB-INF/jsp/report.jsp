
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page trimDirectiveWhitespaces="true" %>
<jsp:include page="header.jsp" />
<body>

	<jsp:include page="menu.jsp" />

	<!-- Page Content -->
	<div class="container">
		<h2>
			<spring:message code="label.hotel_booking_report" />
		</h2>
		<form:form action="generateReport" method="POST" modelAttribute="form">
			<form:errors path="*" cssClass="errorBlock" element="div" />
				<div class="input">
					<div class="formLabel">
						<spring:message code="label.hotel_name" />
					</div>
					<div class="value">
						<form:select path="hotelID">
							<c:forEach var="hotel" items="${hotels}">
								<form:option value="${hotel.id}">${hotel.name}</form:option>
							</c:forEach>
						</form:select>
					</div>
				</div>
			<div class="button">
				<input type="submit" alt="Generate raport"/>
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