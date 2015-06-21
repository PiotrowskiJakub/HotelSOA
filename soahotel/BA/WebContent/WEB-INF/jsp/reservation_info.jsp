<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="header.jsp" />
<body>


	<jsp:include page="menu.jsp" />
	<jsp:include page="hotel_links.jsp" />

	<!-- Page Content -->
	<div class="container">
		<div class="section">
			<div class="sectionLabel">
				<spring:message code="label.customer_information" />
			</div>
			<div class="output">
				<div class="tableLeft">
					<spring:message code="label.first_name" />
				</div>
				<div class="tableRight">
					<c:out value="${reservation.account.firstName}" />
				</div>
			</div>
			<div class="clear"></div>
			<div class="output">
				<div class="tableLeft">
					<spring:message code="label.last_name" />
				</div>
				<div class="tableRight">
					<c:out value="${reservation.account.lastName}" />
				</div>
			</div>
			<div class="clear"></div>
			<div class="output">
				<div class="tableLeft">
					<spring:message code="label.mail" />
				</div>
				<div class="tableRight">
					<c:out value="${reservation.account.contact.mail}" />
				</div>
			</div>
			<div class="clear"></div>
		</div>

		<div class="section">
			<div class="sectionLabel">
				<spring:message code="label.reservation_information" />
			</div>
			<div class="output">
				<div class="tableLeft">
					<spring:message code="label.hotel_name" />
				</div>
				<div class="tableRight">
					<c:out value="${reservation.room.hotel.name}" />
				</div>
			</div>
			<div class="clear"></div>
			<div class="output">
				<div class="tableLeft">
					<spring:message code="label.number" />
				</div>
				<div class="tableRight">
					<c:out value="${reservation.room.number}" />
				</div>
			</div>
			<div class="clear"></div>
			<div class="output">
				<div class="tableLeft">
					<spring:message code="label.floor" />
				</div>
				<div class="tableRight">
					<c:out value="${reservation.room.floor}" />
				</div>
			</div>
			<div class="clear"></div>
			<div class="output">
				<div class="tableLeft">
					<spring:message code="label.size" />
				</div>
				<div class="tableRight">
					<c:out value="${reservation.room.size}" />
				</div>
			</div>
			<div class="clear"></div>
			<div class="output">
				<div class="tableLeft">
					<spring:message code="label.room_type" />
				</div>
				<div class="tableRight">
					<c:out value="${reservation.room.roomType.name}" />
				</div>
			</div>
			<div class="clear"></div>
			<div class="output">
				<div class="tableLeft">
					<spring:message code="label.start_date" />
				</div>
				<div class="tableRight">
					<fmt:formatDate type="date" value="${reservation.startDate}" />
				</div>
			</div>
			<div class="clear"></div>
			<div class="output">
				<div class="tableLeft">
					<spring:message code="label.end_date" />
				</div>
				<div class="tableRight">
					<fmt:formatDate type="date" value="${reservation.endDate}" />
				</div>
			</div>
			<div class="clear"></div>
			<div class="output">
				<div class="tableLeft">
					<spring:message code="label.is_paid" />
				</div>
				<div class="tableRight">
					<c:if test="${reservation.paid == true}">
						<span id="removeButton" class="glyphicon glyphicon-ok"
							aria-hidden="true"></span>
					</c:if>
					<c:if test="${reservation.paid == false}">
						<span id="removeButton" class="glyphicon glyphicon-remove"
							aria-hidden="true"></span>
					</c:if>
				</div>
			</div>
			<div class="clear"></div>
		</div>

		<div class="section">
			<div class="sectionLabel">
				<spring:message code="label.complaint" />
			</div>
			<div class="output">
				<c:out value="${complaint.desc}" />
			</div>
			<div class="clear"></div>
			<div class="tableRight">
				<span id="responseButton" class="glyphicon glyphicon-envelope"
					aria-hidden="true"></span>
				<spring:message code="label.response" />
			</div>
			<div class="clear"></div>

			<div id="complaint_response">
				<form:form action="sendMessage" modelAttribute="form" method="POST">
					<form:textarea path="message" />
					<div class="tableRight">
						<input type="submit" />
					</div>
				</form:form>

			</div>
		</div>
	</div>

	<!-- /.container -->
	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<script src="js/main.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>