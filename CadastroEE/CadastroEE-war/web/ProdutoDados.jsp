<%@page import="cadastroee.model.Produtos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cadastro de Produtos</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Mukta:wght@200;300;400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="./Produtos.css">
</head>
<body class="container">
    <%
        // Recuperar a entidade produto enviada pelo Servlet
        Produtos produto = (Produtos) request.getAttribute("produto");
        
        // Definir a variável acao
        String acao = (produto != null) ? "alterar" : "incluir";
    %>

    <div class="header-section">
        <h1><%= acao.equals("alterar") ? "Alteração" : "Cadastro" %> de Produto</h1>
    </div>

    <% if (acao.equals("alterar") && produto != null) { %>
        <div class="card-product    ">
            <h2>Dados Atuais do Produto</h2>
            <p><strong>Nome:</strong> <%= produto.getNome() %></p>
            <p><strong>Quantidade:</strong> <%= produto.getQuantidade() %></p>
            <p><strong>Preço de Venda:</strong> <%= produto.getPreco() %></p>
        </div>
    <% } %>

    <div class="form-container">
        <form action="ServletProdutoFC" method="post">
            <!-- Campo hidden para envio do valor de acao -->
            <input type="hidden" name="acao" value="<%= acao %>">

            <!-- Campo hidden para envio do id, apenas quando acao for alterar -->
            <% if (produto != null) { %>
                <input type="hidden" name="id" value="<%= produto.getIdProduto() %>">
            <% } %>

            <!-- Campo para Nome -->
            <div class="form-group">
                <label for="nome" class="form-label">Nome:</label>
                <input type="text" id="nome" name="nome" class="form-control" value="<%= produto != null ? produto.getNome() : "" %>" required>
            </div>

            <!-- Campo para Quantidade -->
            <div class="form-group">
                <label for="quantidade" class="form-label">Quantidade:</label>
                <input type="number" id="quantidade" name="quantidade" class="form-control" value="<%= produto != null ? produto.getQuantidade() : "" %>" required>
            </div>

            <!-- Campo para Preço de Venda -->
            <div class="form-group">
                <label for="preco" class="form-label">Preço de Venda:</label>
                <input type="text" id="preco" name="preco" class="form-control" value="<%= produto != null ? produto.getPreco() : "" %>" required>
            </div>

            <!-- Botão de envio -->
            <div class="form-group">
                <button type="submit" class="btn btn-primary"><%= acao.equals("incluir") ? "Cadastrar" : "Alterar" %></button>
            </div>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>