<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
	<h2 style="text-align: center;">Please login</h2>
	<form method="POST" action="${context }/loginHandler" >
	   <table style="width:300px;margin:0 auto;">
	    <tr>
	        <c:if test="${sessionScope.SPRING_SECURITY_LAST_EXCEPTION != null }">
      		<td colspan="2" class="errors">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message }</td>
      		</c:if>
	    </tr>
	    <tr>
	        <td><label for="j_username">Login</label></td>
	        <td><input type="text"  name="j_username" /> </td>
	    </tr>
	    <tr>
	        <td><label for="j_password">Password</label></td>
	        <td><input type="password" name="j_password"/> </td>
	    </tr>
	    <tr>
	        <td><label for="idRole">Role</label></td>
	        <td>
	        	<select name="idRole">
	        		<option value="0">--- Select ---</option>
	        		<c:forEach var="role" items="${roles }">
	        			<option value="${role.idRole }">${role.name }</option>
	        		</c:forEach>
	        	</select>
	        </td>
	    </tr>
	    <tr>
	        <td colspan="2" style="text-align:center;padding-top:20px;">
	            <input type="checkbox" value="true" name="_spring_security_remember_me" >Remember me
	        </td>
	    </tr>
	    <tr>
	        <td colspan="2" style="text-align:center;padding-top:20px;">
	            <input type="submit" value="Login"/>
	        </td>
	    </tr>  
	</table>  
	</form>
</div>