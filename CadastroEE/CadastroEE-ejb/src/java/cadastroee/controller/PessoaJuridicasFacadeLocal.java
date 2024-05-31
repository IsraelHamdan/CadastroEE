/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cadastroee.controller;

import cadastroee.model.PessoaJuridicas;
import jakarta.ejb.Local;
import java.util.List;

/**
 *
 * @author Cleyton
 */
@Local
public interface PessoaJuridicasFacadeLocal {

    void create(PessoaJuridicas pessoaJuridicas);

    void edit(PessoaJuridicas pessoaJuridicas);

    void remove(PessoaJuridicas pessoaJuridicas);

    PessoaJuridicas find(Object id);

    List<PessoaJuridicas> findAll();

    List<PessoaJuridicas> findRange(int[] range);

    int count();
    
}
