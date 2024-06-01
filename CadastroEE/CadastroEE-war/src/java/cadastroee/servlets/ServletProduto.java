
package cadastroee.servlets;

import cadastroee.model.Produtos;
import cadastroee.controller.ProdutosFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletProduto", urlPatterns = {"/ServletProduto"})
public class ServletProduto extends HttpServlet {

    @EJB
    private ProdutosFacadeLocal facade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        List<Produtos> listaDeProdutos = facade.findAll();
        String htmlResponse = buildHtmlResponse(listaDeProdutos);

        try (PrintWriter out = response.getWriter()) {
            out.println(htmlResponse);
        }
    }

    private String buildHtmlResponse(List<Produtos> produtos) {
        StringBuilder htmlBuilder = new StringBuilder();
        
        
        htmlBuilder.append("<!DOCTYPE html>")
            .append("<html>")
            .append("<head>")
            .append("<title>Lista de Produtos</title>")
            .append("</head>")
            .append("<body>")
            .append("<h1>Lista de Produtos</h1>");
            
            produtos.forEach(produto -> htmlBuilder.append("<li>"+ produto.getNome() + "</li>"));
            htmlBuilder.append("</body>")
            .append("</html>");

        return htmlBuilder.toString();
    }

    @Override
    public String getServletInfo() {
        return "Servlet para listagem de produtos";
    }
}
