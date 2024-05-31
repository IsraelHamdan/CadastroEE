/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cadastroee.controller;

import cadastroee.model.PessoaFisicas;
import jakarta.ejb.Local;
import java.util.List;

/**
 *
 * @author Cleyton
 */
@Local
public interface PessoaFisicasFacadeLocal {

    void create(PessoaFisicas pessoaFisicas);

    void edit(PessoaFisicas pessoaFisicas);

    void remove(PessoaFisicas pessoaFisicas);

    PessoaFisicas find(Object id);

    List<PessoaFisicas> findAll();

    List<PessoaFisicas> findRange(int[] range);

    int count();
    
}
