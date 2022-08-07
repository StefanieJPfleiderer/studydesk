<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <c:forEach items="${navItems}" var="navItem">
      <li class="breadcrumb-item"><a href="${navItem.href}">${navItem.linkText}</a></li>
    </c:forEach>
  </ol>
</nav>