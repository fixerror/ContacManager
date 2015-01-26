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
			<!-- Button to trigger modal  -->
			<a href="#myModal" role="button" class="btn btn-mini btn-primary btn-block" data-toggle="modal">Create Contact</a>
		</div>
		<div class="span9 contentformtable">
			<div class="modal hide fade" id="myModal"  data-backdrop="false" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h3 id="myModalLabel">Create Contact</h3>
				</div>
				<div class="modal-body">
			<form:form method="post" action="${context}/tutor/add" modelAttribute="contact" class="form-horizontal">
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
			</div>
				<div class="modal-footer"></div>
			</div>
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