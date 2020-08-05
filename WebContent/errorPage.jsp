<%@ page import="java.io.*" isErrorPage="true" info="handles errors"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Error Page JSP</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="stylesheet" href="styles/main.css" type="text/css" />
</head>
<body>
	<div class="container" style="margin-top:50px">
		<h1>Java Error</h1>
		<p>Sorry, Java has thrown an exception.</p>
		<p>To continue, click the Back button.</p>
	
		<h2>Details</h2>
		<p style="">
			<%
				if (exception != null) {
					exception.printStackTrace(new PrintWriter(out));
				}
			%>
		</p>
	</div>
	<br><br>
	<jsp:include page="/footer.html" />
</body>
</html>