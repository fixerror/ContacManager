<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="container">
	<div class="row" id="header">
		<div class="span12">
			<h2 style="text-align: center;">Contacts</h2>
		</div>
	</div>
	<div class="row" id="content">
		<div class="span3 sidebar">
			<h3 style="text-align: center;">Menu</h3>
			<a href="${context}/logout"  class="btn btn-mini btn-danger btn-block">Exit</a>
		</div>                                                          
		<div class="span9">
			<c:if test="${!empty admintList}">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th><span class="glyphicon glyphicon-th-list"></span> №</th>
							<th>Name</th>
							<th>Email</th>
							<th>Login</th>
							<th>Password</th>
							<th>Surname</th>
							<th>Active</th>
							<th>Role</th>
							<th>&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${admintList}" var="contactadmin">
							<tr>
								<td>${contactadmin.idAccount}</td>
								<td>${contactadmin.name}</td>
								<td>${contactadmin.email}</td>
								<td>${contactadmin.login}</td>
								<td>${contactadmin.password}</td>
								<td>${contactadmin.surname}</td>
								<td>${contactadmin.active}</td>
								<td></td>
								<c:if
									test="${contactadmin.idAccount!=1&&contactadmin.active==true}">
									<td><a href="deactivate/${contactadmin.idAccount}"
										class="btn btn-mini btn-success btn-block">Deactivate</a></td>
								</c:if>
								<c:if
									test="${contactadmin.idAccount!=1&&contactadmin.active==false}">
									<td><a href="active/${contactadmin.idAccount}"
										class="btn btn-mini btn-danger btn-block"> Аctivate</a></td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</div>
	<div class="row" id="footer">
		<div class="span12">
			&#169; Radchenko Aleksandr </br> radchenkoaas@gmail.com
		</div>
	</div>
</div>
