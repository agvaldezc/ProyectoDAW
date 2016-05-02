<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <% if (request.getSession().getAttribute("maestro") == null && request.getSession().getAttribute("alumno") == null) {%>
      <a class="navbar-brand" href="/ProyectoFinal/index.jsp">Inscripciones</a>
      <%} else { %>
      <a class="navbar-brand" href="/ProyectoFinal/menu.jsp">Inscripciones</a>
      <% } %>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>