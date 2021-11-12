<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@page import="java.io.InputStream" %>
<%@page import="java.util.Properties" %>
<!DOCTYPE HTML>
<html>
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <!-- Bootstrap -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
      <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
      <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
      <!--[if lt IE 9]>
         <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/
            html5shiv.js"></script>
         <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/
            respond.min.js"></script>
      <![endif] -->
    <style type="text/css">
   
    </style>
</head>

<body>
	<div class="container">
	<div class="row">
	  <div class="col-md-8"><h1>Test Converter</h1></div>
	  <%
    InputStream stream = getServletContext().getResourceAsStream("/META-INF/MANIFEST.MF");
    Properties props = new Properties();
    props.load(stream);
     %>
 <div class="col-md-4">Version <%= props.getProperty("Specification-Version")%></div>
	</div>
	
	<div class="row">
	<div class="col-md-12">
	<% String errorMsg = (String) session.getAttribute("ERROR_MSG");
	   String successMsg = (String) session.getAttribute("SUCCESS_MSG"); 
	  if (errorMsg != null) {  %>
	  <div class="alert alert-danger">
	   <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
	    <strong>Danger!</strong> <%=errorMsg%>
	  </div>
	<% } else if (successMsg != null) { %>
	  <div class="alert alert-success">
	   <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
	    <strong>Success!</strong> <%=successMsg%>
	  </div>
	<% } %>
	</div>
	</div>
	
	<% String tab = (String) request.getParameter("tab"); %>
	<% if (tab == null) { %>
<!-- 	<ul class="nav nav-tabs">
	  <li role="presentation" class="active"><a href="index.jsp?tab=ws2">Invoke EJB</a></li>
	  <li role="presentation"><a href="index.jsp?tab=ws1">Tab 2</a></li>
	 
	</ul> -->
	<% } else if (tab.equals("ws1")) { %>
	<ul class="nav nav-tabs">
	  <li role="presentation" ><a href="index.jsp?tab=ws2">Invoke EJB</a></li>
	  <li role="presentation" class="active"><a href="index.jsp?tab=ws1">Tab 2</a></li>
	
	</ul>
	<% } else if (tab.equals("ws2")) {%>
	<ul class="nav nav-tabs">
	  <li role="presentation" class="active"><a href="index.jsp?tab=ws2">Invoke EJB</a></li>
	 <!-- <li role="presentation"><a href="index.jsp?tab=ws1">Tab 2</a></li> -->
	</ul>
	<% } %>	
	<% if (tab == null || tab.equals("ws2")) { %>
	<p></p>
	<div class="col-md-8">
	<form class="form-horizontal" role="form" action="TestServlet" method="post" >
	  <div class="form-group">
	    <label for="depositID" class="control-label col-xs-2">Temperature:</label>
	     <div class="col-xs-4">
	    <input type="text" class="form-control" name="depositID">
	    </div>
	  </div>
	  <button type="submit" class="btn btn-success">Submit</button>
	</form>
	</div><!-- 
	<% } else if (tab.equals("ws1")) { %>
	<p> Reserved for future use...</p>
	
	<% } %>
	--></div>
</body>
</html>