<%--
  Created by IntelliJ IDEA.
  User: valisoa
  Date: 18/04/2023
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <title>Formulaire</title>
</head>
<body>
<br><br>
    <div class="container w-50 shadow p-4 rounded-3 ">
        <h1>Ajouter Employé</h1>
        <form action="save-emp" method="post">
            <div class="mb-3">
                <label for="exampleInputEmail1" class="form-label">Nom</label>
                <input type="text" name="nom" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
            </div>
            <div class="mb-3">
                <label for="exampleInputPassword1" class="form-label">Salaire</label>
                <input type="number" name="salaire" class="form-control" id="exampleInputPassword1">
            </div>
            <input type="submit" class="btn btn-primary" value="Enregistrer">
        </form>
    </div>

</body>
</html>
