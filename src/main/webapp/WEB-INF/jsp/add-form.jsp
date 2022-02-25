<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
  <head>
    <title>${title}</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/global.css">
    <script src="/js/bootstrap.min.js" ></script>
    <script src="/js/jQuery.js"></script>
    <script src="/js/fileUpload.js"></script>
  </head>
  <body>
    <h1 class="display-3">${headline}</h1>
    <form:form method="post" enctype="multipart/form-data" modelAttribute="element" action="${action}">
        <div class="form-group">
          <form:label path="name">Name</form:label>
          <form:input class="form-control col-xs-4" type="text" path="name" value="${name}"/>
        </div>
        <div class="form-group custom-file">
          <form:label id="file-label" class="custom-file-label" path="image" value="${image}">${image eq "" ? "Image" : image}</form:label>
          <form:input id="file-upload" class="custom-file-input col-xs-4" type="file" name="image" path="image"/>
        </div>
        <br />
        <div class="form-group">
          <input class="btn btn-primary" type="submit" value="${addElement}"/>
        </div>
    </form:form>
  </body>
</html>