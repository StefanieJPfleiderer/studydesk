<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
  <head>
    <title>${title}</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/global.css">
    <script src="/js/bootstrap.min.js" ></script>
  </head>
  <body>
    <h1 class="display-3">${headline}</h1>
    <form:form method="post" enctype="multipart/form-data" modelAttribute="element" action="addPdf?topicId=${id}">
      <div class="form-group">
        <form:label path="title">Title</form:label>
        <form:input class="form-control col-xs-4" type="text" path="title"/>
      </div>
      <div class="form-group">
        <form:label path="description">Description</form:label>
        <form:textarea class="form-control" rows="8" name="description" path="description" />
      </div>
      <div class="form-group">
        <form:label path="pdfPath">PDF</form:label>
        <form:input class="form-control col-xs-4" type="text" name="pdf" path="pdfPath"/>
      </div>
      <div class="form-group">
        <input class="btn btn-primary" type="submit" value="Add ${addElement}"/>
      </div>
    </form:form>
  </body>
</html>