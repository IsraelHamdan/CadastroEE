
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
import jakarta.servlet.RequestDispatcher;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.RequestDispatcher;


@WebServlet(name = "ServletProdutoFC", urlPatterns = {"/ServletProdutoFC"})
public class ServletProdutoFC extends HttpServlet {
    @EJB 
    private ProdutosFacadeLocal facade;
    

    
    private static final Logger LOGGER = Logger.getLogger(ServletProdutoFC.class.getName());
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param req servlet request
     * @param res servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = res.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServTeste</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServTeste at " + req.getContextPath() + "</h1>");

            out.println("</body>");
            out.println("</html>");
        } 
    }
    
    private void dispatchRequest(HttpServletRequest req, HttpServletResponse res, String destino ) throws ServletException, IOException {
        try {
            RequestDispatcher dispatcher = req.getRequestDispatcher(destino);
            dispatcher.forward(req, res);
        } catch (ServletException e) {
            LOGGER.log(Level.SEVERE, "erro no dispathcer" + e);
            
        }
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param req servlet request
     * @param res servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   private String handleGetAction(String acao, HttpServletRequest req) {
        System.out.println("Ação GET: " + acao);
        switch(acao) {
            case "formIncluir":
                return "ProdutoDados.jsp";
            case "excluir": 
                return handleDeleteItem(req);
            case "formAlterar" :
                return handleFormModifyItem(req);
            default:  
                return handleListProducts(req);
        }
    }

    private String handlePostAction(String acao, HttpServletRequest req) {
       switch (acao) {
           case "incluir" : 
               return handleIncludeItem(req);
           case "alterar" : 
               return handleModifyItem(req);
           default: 
               return handleListProducts(req);
       }
   }

    private String handleListProducts(HttpServletRequest req) {
        List<Produtos> produtos = facade.findAll();
        req.setAttribute("produtos", produtos);
        return "ProdutoLista.jsp";
    }
  
    
    private String handleDeleteItem(HttpServletRequest req) {
        int idDel = Integer.parseInt(req.getParameter("id"));
        facade.remove(facade.find(idDel));
        return handleListProducts(req);
    }
    
    private String handleFormModifyItem(HttpServletRequest req) {
       int id = Integer.parseInt(req.getParameter("id"));
       Produtos produto = facade.find(id);
       req.setAttribute("produto", produto);
       return "ProdutoDados.jsp";
   }

    private String handleIncludeItem(HttpServletRequest req) {
        String nome = req.getParameter("nome"); 
        int quantity = Integer.parseInt(req.getParameter("quantidade"));
        Float preco = Float.valueOf(req.getParameter("preco"));
        
        Produtos newProduct = new Produtos();
        newProduct.setNome(nome);
        newProduct.setQuantidade(quantity);
        newProduct.setPreco(preco);
        
        facade.create(newProduct);
        return handleListProducts(req);
    }
    
    private String handleModifyItem(HttpServletRequest req) {
        int idModify = Integer.valueOf(req.getParameter("id"));
        Produtos modifyProduct = facade.find(idModify);

        String modifyName = req.getParameter("nome");
        int modifyQuantity = Integer.parseInt(req.getParameter("quantidade"));
        Float modifyPrice = Float.valueOf(req.getParameter("preco"));

        modifyProduct.setNome(modifyName);
        modifyProduct.setQuantidade(modifyQuantity);
        modifyProduct.setPreco(modifyPrice);

        facade.edit(modifyProduct);

        return handleListProducts(req);
    }

 
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param req servlet request
     * @param res servlet res
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            String acao = req.getParameter("acao");
            String destino = handleGetAction(acao, req);
            dispatchRequest(req, res, destino);
        } catch (ServletException e ) {
            LOGGER.log(Level.SEVERE, "erro no doGet" + e);
            
        }
      
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param req servlet req
     * @param res servlet res
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            String acao = req.getParameter("acao");
            acao = acao == null || acao.isEmpty() ? "" : acao;
            String destino = handlePostAction(acao, req);
            dispatchRequest(req, res, destino);
        } catch (ServletException e) {
            LOGGER.log(Level.SEVERE, "erro no doPost" + e);
        }

     
    }
    
}