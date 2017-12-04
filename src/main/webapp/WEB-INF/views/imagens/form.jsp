<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<html>

<head>

<meta charset="UTF-8">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />	

<title>Gestor Multimídia</title>

<c:url value="/" var="contextPath" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/layout/stylesheets/vendors.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/layout/stylesheets/algaworks.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/static/layout/stylesheets/application.css"/>

<link rel="stylesheet" type="text/css" href="${contextPath}resources/static/stylesheets/estagio.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}resources/static/stylesheets/vendors/upload.min.css"/>
<script type="text/javascript"	src="${pageContext.request.contextPath}/resources/static/javascripts/jquery-2.2.3.js"></script>

</head>

<body>
<div class="aw-layout-page">

	<nav class="navbar  navbar-fixed-top  navbar-default  js-sticky-reference" id="main-navbar">
	  <div class="container-fluid">

		<div class="navbar-header">
	      <a class="navbar-brand  hidden-xs" href="#">
	        <img alt="Bem-Vindo" src="${pageContext.request.contextPath}/resources/static/layout/images/logoNovo.png"/>
	      </a>

	      <ul class="nav  navbar-nav">
	        <li>
	          <a href="#" class="js-sidebar-toggle">  <i class="fa  fa-bars"></i>    </a>
	        </li>
	      </ul>
		</div>

	    <ul class="nav navbar-nav  navbar-right">
	      <li>
	        <a href="#"><em class="fa  fa-sign-out"></em></a>
	      </li> 
	    </ul>

	  </div>
	</nav>
	
		<aside class="aw-layout-sidebar  js-sidebar">
		<div class="aw-layout-sidebar__content">

	    	<nav class="aw-menu  js-menu">
	      		<ul class="aw-menu__list">
	
	        		<li class="aw-menu__item">
	         			 <a href="dashboard.html"><i class="fa  fa-fw  fa-home"></i><span>Dashboard</span></a>
	        		</li>
	
	        		<li class="aw-menu__item  is-active is-expanded">
	         			<a href="#">
	            			<i class="fa  fa-fw  fa-file-text"></i><span>Opções</span>
	            			<i class="aw-menu__navigation-icon  fa"></i>
	          			</a>
	      
	          			<ul class="aw-menu__list  aw-menu__list--sublist">
	            			<li class="aw-menu__item  aw-menu__item--link"><a href="form">Cadastro de Imagem</a></li>
	            			<li class="aw-menu__item  aw-menu__item--link  is-active"><a href="pesquisar">Pesquisa de Imagem</a></li>
	         		 	</ul>
	        		</li>
	
	        		<li class="aw-menu__item  is-expanded">
	         			<a href="#">
	            			<i class="fa  fa-fw  fa-file-text"></i><span>Páginas comuns</span>
	            			<i class="aw-menu__navigation-icon  fa"></i>
	          			</a>
	
	         			<ul class="aw-menu__list  aw-menu__list--sublist">
	            			<li class="aw-menu__item  aw-menu__item--link"><a href="pagina-vazia.html">Página vazia</a></li>
	            			<li class="aw-menu__item  aw-menu__item--link"><a href="login.html">Login</a></li>
	            			<li class="aw-menu__item  aw-menu__item--link"><a href="esqueceu-a-senha.html">Esqueceu a senha</a></li>
	            			<li class="aw-menu__item  aw-menu__item--link"><a href="403.html">403</a></li>
	            			<li class="aw-menu__item  aw-menu__item--link"><a href="404.html">404</a></li>
	            			<li class="aw-menu__item  aw-menu__item--link"><a href="500.html">500</a></li>
	          			</ul>
	        		</li>
	
	      		</ul>
			</nav>
		
		</div>
	</aside>
	
	<section class="aw-layout-content  js-content">

		<div class="page-header">
			<div class="container-fluid">
				<div class="row">
					<div class="col-sm-10">
						<h1>
							Cadastro de Imagem
						</h1>
					</div>
					<div class="col-sm-2">
						<div class="aw-page-header-controls">
							<a class="btn btn-default" href="/gestormultimidia/imagens/pesquisar">
								<i class="glyphicon glyphicon-plus=sign"></i> <span class="hidden-xs hidden-sm">Pesquisa</span>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	
		<div class="container-fluid">
	
		<form:form method="POST" action="${s:mvcUrl('IC#gravar').build() }"	commandName="imagem" enctype="multipart/form-data" class="form-vertical  js-form-loading">
			<div class="form-group">
				<label>Título</label>
				<input type="text" name="titulo" class="form-control">
				<form:errors path="titulo" />
			</div>
			<div class="form-group">
				<label>Descrição</label>
				<textarea row="10" cols="20" name="descricao" class="form-control"></textarea>
				<form:errors path="descricao" />
			</div>
			<div class="row">
				<div class="form-group	col-sm-12">
					<input type="hidden"	name="nomeFoto"	value="${nomeFoto }">
					<input type="hidden"	name="contentType"	value="${contentType }">
					<input type="hidden"	name="sumarioPath" value="${nomeFoto }">
					
					<label class="control-label">Foto</label>
					
					<div class="js-container-foto-cerveja">
						<div id="upload-drop" class="bw-upload">
							<i class="glyphicon glyphicon-cloud-upload"></i>
							<span>Arraste a foto aqui ou </span>
							<a class="bw-upload-form-file">selecione 
								<input id="upload-select" type="file" name="sumario" accept=".jpg,.jpeg,.png" />
							</a>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<button class="but btn-primary" type="submit">Cadastrar</button>
			</div>
		</form:form>
		</div>
		
		<c:import url="/WEB-INF/views/handlebars/FotoImagem.jsp"/>
		
	</section>
	
	<footer class="aw-layout-footer  js-content">
		<div class="container-fluid">
			<span class="aw-footer-disclaimer">&copy; 2016 UFPI. Todos os direitos reservados.</span>
		</div>
	</footer>
</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/javascripts/imagemUpload.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/javascripts/vendors/uikit.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/javascripts/vendors/upload.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/javascripts/vendors/handlebars.min.js"></script>

</body>
</html>