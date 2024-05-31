<%@page import="java.util.List" %>
    <%@page import="cadastroee.model.Produtos" %>
        <%@page contentType="text/html" pageEncoding="UTF-8" %>

            <!DOCTYPE html>
            <html>
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Listagem de Produtos</title>
                <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
                <link rel="stylesheet" href="./Produtos.css">
            </head>
            <body class="container">
                <h1 class="mb-4">Listagem de Produtos</h1>
                <div class="mb-3">
                    <a class="btn btn-primary" href="ServletProdutoFC?acao=formIncluir">Novo Produto</a>
                </div>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>Quantidade</th>
                            <th>Preço</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% List<Produtos> produtos = (List<Produtos>) request.getAttribute("produtos");
                                if (produtos != null) {
                                for (Produtos produto : produtos) {
                                %>
                                <tr>
                                    <td>
                                        <%= produto.getIdProduto() %>
                                    </td>
                                    <td>
                                        <%= produto.getNome() %>
                                    </td>
                                    <td>
                                        <%= produto.getQuantidade() %>
                                    </td>
                                    <td>
                                        <%= produto.getPreco() %>
                                    </td>
                                    <td>
                                        <a class="btn btn-warning btn-sm btn-action"
                                            href="ServletProdutoFC?acao=alterar&id=<%= produto.getIdProduto() %>">Alterar</a>
                                        <a class="btn btn-danger btn-sm btn-action"
                                            href="ServletProdutoFC?acao=excluir&id=<%= produto.getIdProduto() %>">Excluir</a>
                                    </td>
                                </tr>
                                <% } } %>
                    </tbody>
                </table>


                <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
                <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
            </body>
            </html>