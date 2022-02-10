<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
  <head>
    <title>${pdf.title}</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/global.css">
    <script src="/js/bootstrap.min.js" ></script>
  </head>
  <body>
    <div class="pdf-overview-section" style="width: 50%; margin: 0 auto; text-align: center">
      <h1>${pdf.title}</h1>
      <p>${pdf.description}</p>
      <a href="showPdf?elementId=${pdf.id}" class="btn btn-primary">Open PDF</a>
    </div>
  </body>
</html>