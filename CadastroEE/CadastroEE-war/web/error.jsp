<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Erro</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body class="container">
    <h1 class="mt-5">Ocorreu um erro</h1>
    <div class="alert alert-danger mt-3">
        <p><%= request.getAttribute("errorMessage") %></p>
    </div>
    <a href="ServletProdutoFC" class="btn btn-primary mt-3">Voltar à lista de produtos</a>
</body>
</html>
