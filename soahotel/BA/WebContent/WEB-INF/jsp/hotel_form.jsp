<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

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
</div>
<div class="section">
	<div class="sectionLabel">
		<spring:message code="label.address" />
	</div>
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
	<div class="sectionLabel">
		<spring:message code="label.contact" />
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
</div> 
	
<div class="hidden">
	<form:input path="id" />
</div>