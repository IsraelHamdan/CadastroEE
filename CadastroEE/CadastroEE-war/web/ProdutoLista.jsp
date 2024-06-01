<%@ page import="java.util.List" %>
    <%@ page import="cadastroee.model.Produtos" %>
        <%@ page contentType="text/html; charset=UTF-8" %>

            <!DOCTYPE html>
            <html>
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Lista de Produtos</title>

                <link rel="stylesheet" href="./Produtos.css">
                <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
                <link rel="preconnect" href="https://fonts.googleapis.com">
            </head>
            <body class="container">
                <h1 class="mb-4">Lista de Produtos</h1>
                <div class="mb-3">
                    <a class="btn btn-primary m-2" href="ServletProdutoFC?acao=formIncluir">Cadastrar produto</a>
                </div>
                <table class="table table-striped">
                    <thead class="thead-dark table-dark">
                        <tr>
                            <th class="text-center">Nome</th>
                            <th class="text-center">Quantidade</th>
                            <th class="text-center">Preço</th>
                            <th class="text-center"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <% List<Produtos> produtos = (List<Produtos>) request.getAttribute("produtos");
                                if (produtos != null && !produtos.isEmpty()) {
                                for (Produtos produto : produtos) {
                                %>
                                <tr>
                                    <td class="align-middle text-center">
                                        <%= produto.getNome() %>
                                    </td>
                                    <td class="align-middle text-center">
                                        <%= produto.getQuantidade() %>
                                    </td>
                                    <td class="align-middle text-center">
                                        <%= produto.getPreco() %>
                                    </td>
                                    <td class="align-middle text-center">
                                        <a class="btn btn-primary btn-sm"
                                            href="ServletProdutoFC?acao=formAlterar&id=<%= produto.getIdProduto() %>">Alterar</a>
                                        <a class="btn btn-danger btn-sm"
                                            href="ServletProdutoFC?acao=excluir&id=<%= produto.getIdProduto() %>">Excluir</a>
                                    </td>
                                </tr>
                                <% } } else { %>
                                    <tr>
                                        <td class="text-center" colspan="4">Nenhum produto encontrado</td>
                                    </tr>
                                    <% } %>
                    </tbody>
                </table>



                <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
            </body>
            </html>