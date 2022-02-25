<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html>
  <head>
    <title>Study Desk</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/global.css">
    <script src="/js/bootstrap.min.js" ></script>
  </head>
  <body>
    <h1 class="display-3">${headline}</h1>
    <c:forEach items="${list}" var="element">
      <div class="card" style="width: 18rem;">
        <img class="card-img-top" alt=" " src="${'data:image/jpg;base64,'.concat(element.base64String)}">
        <div class="card-body">
          <h5 class="card-title">${element.name}</h5>
          <c:set var="topicLink" value="topics?id=${element.id}"/>
          <c:set var="contentLink" value="content?id=${element.id}"/>
          <c:set var="topicDeleteLink" value="deleteTopic?id=${element.id}" />
          <c:set var="categoryDeleteLink" value="deleteCategory?id=${element.id}" />
          <c:set var="topicEditLink" value="showEditTopicForm?id=${element.id}" />
          <c:set var="categoryEditLink" value="showEditCategoryForm?id=${element.id}" />
          <a href="${isTopic ? contentLink : topicLink}" class="btn btn-primary">${linkText}</a>
          <a href="${isTopic ? topicDeleteLink : categoryDeleteLink }" class="btn btn-primary"
            onclick="return confirm('Are you sure you want to delete \'${element.name}\' and all its content?')">Delete</a>
          <a href="${isTopic ? topicEditLink : categoryEditLink }" class="btn btn-primary">Edit</a>
        </div>
      </div>
    </c:forEach>
    <div class="card" style="width: 18rem;">
      <img class="card-img-top glyphicon glyphicon-plus" src="/images/plus.png">
        <div class="card-body">
          <h5 class="card-title">&nbsp;</h5>
          <c:choose>
            <c:when test="${not empty id}">
              <c:set var="href" value="showTopicAddForm?id=${id}&isTopic=${isTopic}"/>
            </c:when>
            <c:otherwise>
              <c:set var="href" value="showCategoryAddForm?isTopic=${isTopic}"/>
            </c:otherwise>
          </c:choose>
          <c:if test="${isContent}">
            <c:set var="href" value="showPdfAddForm?id=${id}"/>
          </c:if>
          <a href="${href}" class="btn btn-primary">Add new ${addElement}</a>
        </div>
      </div>
  </body>
</html>