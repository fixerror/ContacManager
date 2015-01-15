<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
	<div class="row" id="header">
		<div class="span12">
			<h2 style="text-align: center;">Contact Manager</h2>
		</div>
	</div>
	<div class="row" id="content">
		<div class="span3 sidebar">
			<h3 style="text-align: center;">Menu</h3>
			<a href="${context}/logout"  class="btn btn-mini btn-danger btn-block">Exit</a>
		</div>
		<div class="span9 contentformtable" >
			<form:form method="post" action="add.html" commandName="contact" class="form-horizontal">
				<form:label path="firstname"> <spring:message code="label.firstname" /></form:label>
				<form:input type="text" placeholder="First Name" path="firstname" />
				<form:label path="lastname"><spring:message code="label.lastname" /></form:label>
				<form:input type="text" placeholder="Last Name" path="lastname" />
				<form:label path="email"><spring:message code="label.email" /></form:label>
				<form:input type="text" id="inputEmail" placeholder="Email" path="email" />
				<form:label path="telephone"><spring:message code="label.telephone" /></form:label>
				<form:input type="text" placeholder="Telephone" path="telephone" />
				<input type="submit" class="btn btn-primary" value="<spring:message code="label.addcontact"/>">
			</form:form>
			<h3 style="text-align: center;">Contacts</h3>
			<c:if test="${!empty contactList}">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>Name</th>
							<th>Email</th>
							<th>Telephone</th>
							<th>&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${contactList}" var="contact">
							<tr>
								<td>${contact.lastname},${contact.firstname}</td>
								<td>${contact.email}</td>
								<td>${contact.telephone}</td>
								<td><a href="delete/${contact.id}" class="btn btn-danger">delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
			</div>
			</div>
		<div class="row" id="footer">
			<div class="span12">
				<h5>
					&#169; Radchenko Aleksandr </br>
					 radchenkoaas@gmail.com
				</h5>
			</div>
		</div>
		</div>





















<%-- <div class="container">
	<div class="row">
		<div class="col-md-12 form-block">
			<h2>Contact Manager</h2>

			<form:form method="post" action="add.html" commandName="contact"
				class="form-horizontal">

				<table>
					<tr>
						<td><form:label path="firstname">
								<spring:message code="label.firstname" />
							</form:label></td>
						<td><form:input type="text" placeholder="First Name"
								path="firstname" /></td>
					</tr>
					<tr>
						<td><form:label path="lastname">
								<spring:message code="label.lastname" />
							</form:label></td>
						<td><form:input type="text" placeholder="Last Name"
								path="lastname" /></td>
					</tr>
					<tr>
						<td><form:label class="control-label" for="inputEmail"
								path="email">
								<spring:message code="label.email" />
							</form:label></td>
						<td><form:input type="text" id="inputEmail"
								placeholder="Email" path="email" /></td>
					</tr>
					<tr>
						<td><form:label path="telephone">
								<spring:message code="label.telephone" />
							</form:label></td>
						<td><form:input type="text" placeholder="Telephone"
								path="telephone" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" class="btn btn-primary"
							value="<spring:message code="label.addcontact"/>"></td>
					</tr>
				</table>
			</form:form>
		</div>
		<div class="col-md-12 contacts-block">
			<h3>Contacts</h3>
			<c:if test="${!empty contactList}">
				<table class="table table-bordered">
					<tr>
						<th>Name</th>
						<th>Email</th>
						<th>Telephone</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${contactList}" var="contact">
						<tr>
							<td>${contact.lastname},${contact.firstname}</td>
							<td>${contact.email}</td>
							<td>${contact.telephone}</td>
							<td><a href="delete/${contact.id}" class="btn btn-danger"><i
									class="icon-trash"> </i> delete</a></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</div>
</div> --%>