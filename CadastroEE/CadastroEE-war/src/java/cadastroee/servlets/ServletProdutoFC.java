package cadastroee.servlets;

import cadastroee.controller.ProdutosFacadeLocal;
import cadastroee.model.Produtos;

import jakarta.ejb.EJB;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ServletProdutoFC", urlPatterns = {"/ServletProdutoFC"})
public class ServletProdutoFC extends HttpServlet {
    @EJB 
    private ProdutosFacadeLocal facade;

    private static final Logger LOGGER = Logger.getLogger(ServletProdutoFC.class.getName());

    private void dispatchRequest(HttpServletRequest req, HttpServletResponse res, String destino) throws ServletException, IOException {
        try {
            req.getRequestDispatcher(destino).forward(req, res);
        } catch (ServletException | IOException e) {
            LOGGER.log(Level.SEVERE, "Erro no dispatcher", e);
            throw e;
        }
    }

    private String handleGetAction(String acao, HttpServletRequest req) {
        LOGGER.info("Ação GET: " + acao);
        try {
            switch (acao) {
                case "formIncluir":
                    return "ProdutoDados.jsp";
                case "excluir":
                    return handleDeleteItem(req);
                case "formAlterar":
                    return handleFormModifyItem(req);
                default:
                    return handleListProducts(req);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro no handleGetAction", e);
            req.setAttribute("errorMessage", "Erro ao processar a ação GET: " + e.getMessage());
            return "error.jsp";
        }
    }

    private String handlePostAction(String acao, HttpServletRequest req) {
        LOGGER.info("Ação POST: " + acao);
        try {
            switch (acao) {
                case "incluir":
                    return handleIncludeItem(req);
                case "alterar":
                    return handleModifyItem(req);
                default:
                    return handleListProducts(req);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro no handlePostAction", e);
            req.setAttribute("errorMessage", "Erro ao processar a ação POST: " + e.getMessage());
            return "error.jsp";
        }
    }

    private String handleListProducts(HttpServletRequest req) {
        try {
            List<Produtos> produtos = facade.findAll();
            req.setAttribute("produtos", produtos);
            return "ProdutoLista.jsp";
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao listar produtos", e);
            req.setAttribute("errorMessage", "Erro ao listar produtos: " + e.getMessage());
            return "error.jsp";
        }
    }

    private String handleDeleteItem(HttpServletRequest req) {
        try {
            int idDel = Integer.parseInt(req.getParameter("id"));
            facade.remove(facade.find(idDel));
            return handleListProducts(req);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao excluir produto", e);
            req.setAttribute("errorMessage", "Erro ao excluir produto: " + e.getMessage());
            return "error.jsp";
        }
    }

    private String handleFormModifyItem(HttpServletRequest req) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Produtos produto = facade.find(id);
            req.setAttribute("produto", produto);
            return "ProdutoDados.jsp";
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao preparar formulário de alteração", e);
            req.setAttribute("errorMessage", "Erro ao preparar formulário de alteração: " + e.getMessage());
            return "error.jsp";
        }
    }

    private String handleIncludeItem(HttpServletRequest req) {
        try {
            Produtos newProduct = new Produtos();
            String name = req.getParameter("nome");
            int quantity = Integer.parseInt(req.getParameter("quantidade"));
            Float price = Float.parseFloat(req.getParameter("preco")) ;
            
            newProduct.setNome(name);
            newProduct.setQuantidade(quantity);
            newProduct.setPreco(price);

            facade.create(newProduct);
            return handleListProducts(req);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao incluir produto", e);
            req.setAttribute("errorMessage", "Erro ao incluir produto: " + e.getMessage());
            return "error.jsp";
        }
    }

    private String handleModifyItem(HttpServletRequest req) {
        try {
            int idModify = Integer.parseInt(req.getParameter("id"));
            Produtos modifyProduct = facade.find(idModify);
            
            String modifyName = req.getParameter("nome");
            int modifyQuantity = Integer.parseInt(req.getParameter("quantidade")) ;
            Float modifyPride = Float.parseFloat(req.getParameter("preco"));
            
            modifyProduct.setNome(modifyName);
            modifyProduct.setQuantidade(modifyQuantity);
            modifyProduct.setPreco(modifyPride);

            facade.edit(modifyProduct);
            return handleListProducts(req);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao modificar produto", e);
            req.setAttribute("errorMessage", "Erro ao modificar produto: " + e.getMessage());
            return "error.jsp";
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            String acao = req.getParameter("acao");
            String destino = handleGetAction(acao, req);
            dispatchRequest(req, res, destino);
        } catch (ServletException | IOException e) {
            LOGGER.log(Level.SEVERE, "Erro no doGet", e);
            req.setAttribute("errorMessage", "Erro no doGet: " + e.getMessage());
            dispatchRequest(req, res, "error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            String acao = req.getParameter("acao");
            String destino = handlePostAction(acao, req);
            dispatchRequest(req, res, destino);
        } catch (ServletException | IOException e) {
            LOGGER.log(Level.SEVERE, "Erro no doPost", e);
            req.setAttribute("errorMessage", "Erro no doPost: " + e.getMessage());
            dispatchRequest(req, res, "error.jsp");
        }
    }
}
