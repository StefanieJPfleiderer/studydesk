<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<html>
  <head>
    <title>Content</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/global.css">
    <script src="/js/bootstrap.min.js" ></script>
  </head>
  <body>
    <jsp:include page="breadcrumb.jsp">
      <jsp:param name="navItems" value="${navItems}" />
    </jsp:include>

    <h1>Content</h1>
    <c:forEach items="${list}" var="element">
      <div class="card" style="width: 18rem;">
        <div class="card-body">
          <h5 class="card-title">${element.title}</h5>
          <a href="readMore?elementId=${element.id}" class="btn btn-primary">Read more</a>
        </div>
      </div>
    </c:forEach>
    <div class="card" style="width: 18rem;">
      <div class="card-body">
        <h5 class="card-title">&nbsp;</h5>
        <a href="showPdfAddForm?id=${id}" class="btn btn-primary">Add new PDF</a>
        </div>
      </div>
  </body>
</html>
