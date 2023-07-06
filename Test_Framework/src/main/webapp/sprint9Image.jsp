<%--
  Created by IntelliJ IDEA.
  User: valisoa
  Date: 21/06/2023
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <title>Sprint8</title>
</head>
<body>
<br><br>
<div class="container w-50 shadow p-4 rounded-3">
    <h1>Ajouter un document</h1>
    <form action="save-emp" method="post" enctype="multipart/form-data">
        <div class="input-group mb-3">
            <input type="file" name="profil" class="form-control" id="inputGroupFile01">
        </div>
        <div class="row">
            <div class="form-check col">
                <input class="form-check-input" type="radio" name="flexRadioDefault" value="image" id="flexRadioDefault1">
                <label class="form-check-label" for="flexRadioDefault1">
                    Image
                </label>
            </div>
            <div class="form-check col">
                <input class="form-check-input" type="radio" name="flexRadioDefault" value="document" id="flexRadioDefault2"
                       checked>
                <label class="form-check-label" for="flexRadioDefault2">
                    Document
                </label>
            </div>
        </div>
        <div class="form-check form-switch">
            <input class="form-check-input" type="checkbox" role="switch" id="document">
            <label class="form-check-label" for="document">Document certifié</label>
        </div>
        <div class="form-check form-switch">
            <input class="form-check-input" type="checkbox" role="switch" id="condition">
            <label class="form-check-label" for="condition">Accepter les conditions d'utilisation</label>
        </div>
        <input type="submit" class="btn btn-primary" value="Enregistrer">
    </form>
</div>
</body>
</html>
