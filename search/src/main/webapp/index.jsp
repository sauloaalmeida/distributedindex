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
    <form action="/search/search" method="get">
        <label for="q">Search:</label><input type="text" id="q" name="q" value="<c:out value="${query}"/>"/>
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

        <c:forEach items="${result}" var="document">
            <p><a href="/search/download?doc=<c:out value="${document}"/>" target="_blank"><c:out value="${document}"/></a></p>
        </c:forEach>

        <p class="time">Query executed in <c:out value="${time}"/> millisecond(s)</p>
    </div>
</c:if>
</body>
</html>