<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"> <img src="images/reception.png"
				width="100px" height="auto" alt=""></a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<c:if test="${!empty sessionScope.TOKEN}">
					<li><a href="">Hello ${account.firstName}</a></li>
					<li><a href="logout">LogOut</a></li>
					<li><a href=""></a></li>
				</c:if>

				<c:if test="${empty sessionScope.TOKEN}">
					<li><a href="registration">Registration</a></li>
					<li><a href="login">LogIn</a></li>
				</c:if>
				<c:if test="${!empty sessionScope.TOKEN}">
					<li><a href="hotel_management">Hotel management</a></li>
					<li><a href="report">Reports</a></li>
					<li><a href="invoice">Invoices</a></li>
				</c:if>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container -->
</nav>
