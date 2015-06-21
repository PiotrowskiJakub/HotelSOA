<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		</div>
	</div>

	<!-- /.container -->
	<!-- jQuery -->
	<script src="js/jquery.js" />

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js" />
</body>
</html>