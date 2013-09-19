<%
/*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>Search</title>
</head>
<body>

<div id="form_search_with_results">
    <h1>Distributed Index Search</h1>
    <form action="/search/search" method="get">
        <label for="q">Search:</label><input type="text" id="q" size="50" name="q" value="<c:out value="${query}"/>"/>
        <label for="booleanOr">OR query:</label><input type="checkbox" id="booleanOr" name="booleanOr" value="true" <c:if test="${booleanOr != null}">checked="true"</c:if> />
        <input type="submit" value="search">
    </form>
</div>

<c:if test="${errorMsg != null}">
    <span class="errorMessage"><c:out value="${errorMsg}"/></span>
</c:if>

<c:if test="${result != null}">
    <p/>
    <div id=results>

        <span class="totalresult">Your search returned <c:out value="${fn:length(result)}"/> result(s)!</span>

        <p>
        <c:forEach items="${result}" var="document">
            <a href="/search/documentViewer?doc=<c:out value="${document}"/>" target="_blank"><c:out value="${document}"/></a> &nbsp; &nbsp;
        </c:forEach>
        </p>
        <p class="time">Query executed in <c:out value="${time}"/> millisecond(s)</p>
    </div>
</c:if>
</body>
</html>