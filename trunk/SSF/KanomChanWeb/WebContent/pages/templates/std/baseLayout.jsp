<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html lang="en">
<head>
<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<title><tiles:getAsString name="title" /></title>
<jsp:include page="/pages/common/projectInclude.jsp" />
</head>
<body>
	<div class="container">
		<div class="row">

			<div class="col-md-12">


				<nav class="navbar navbar-inverse" role="navigation">
					<div class="container-fluid">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed"
								data-toggle="collapse" data-target="#navbar"
								aria-expanded="false" aria-controls="navbar">
								<span class="sr-only">Toggle navigation</span> <span
									class="icon-bar"></span> <span class="icon-bar"></span> <span
									class="icon-bar"></span>
							</button>
							<a class="navbar-brand" href="#">Project name</a>
						</div>
						<div id="navbar" class="navbar-collapse collapse">
							<ul class="nav navbar-nav navbar-right">
								<li><a href="#">Dashboard</a></li>
								<li><a href="#">Settings222</a></li>
								<li><a href="#">Profile</a></li>
								<li><a href="#">Help</a></li>
							</ul>
							<form class="navbar-form navbar-right">
								<input type="text" class="form-control" placeholder="Search...">
							</form>
						</div>
					</div>
				</nav>
			</div>
		</div>
		<div class="row">
			<div class=" col-md-2">
				<tiles:insertAttribute name="menu" ignore="true" />
			</div>
			<div class=" col-md-10 ">
				<tiles:insertAttribute name="body" ignore="true" />
			</div>
		</div>
	</div>
</body>
</html>
