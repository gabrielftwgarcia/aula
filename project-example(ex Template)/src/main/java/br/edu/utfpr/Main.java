package br.edu.utfpr;

import br.edu.utfpr.dao.ClienteDAO;
import br.edu.utfpr.dao.PaisDAO;
import br.edu.utfpr.dto.ClienteDTO;
import br.edu.utfpr.dto.PaisDTO;
import br.edu.utfpr.excecao.*;
import br.edu.utfpr.negocio.ClienteNegocio;
import br.edu.utfpr.negocio.PaisNegocio;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        PaisDAO pDAO = new PaisDAO();
        ClienteDAO cDAO = new ClienteDAO();

        ClienteDTO cliente = new ClienteDTO();
        PaisDTO pais = new PaisDTO();

        ClienteNegocio cNeg = new ClienteNegocio();
        PaisNegocio pNeg = new PaisNegocio();

        // Testando Pais
        try {
            pais.setNome("Brasil");
            pais.setSigla("Br");
            pais.setCodigoTelefone(55);
            pNeg.incluir(pais);
        } catch (NomePaisNuloException e) {
            e.printStackTrace();
        } catch (NomePaisJaExisteException e) {
            e.printStackTrace();
        }

        // Testando cliente
        try {
            cliente.setNome("Gabriel Garcia");
            cliente.setIdade(21);
            cliente.setTelefone("5543996696318");
            cliente.setPais(pDAO.getPaisByNomeSigla("Brasil", "Br"));

            cliente.validarLimiteCredito();
            cNeg.incluir(cliente);
        } catch (NomeClienteMenor5CaracteresException e) {
            e.printStackTrace();
        } catch (NomePaisNuloException e) {
            e.printStackTrace();
        } catch (IdadeClienteInvalidaException e) {
            e.printStackTrace();
        } catch (NomeClienteJaExisteException e) {
            e.printStackTrace();
        }

        // Listando
        System.out.println(pDAO.listarTodos());
        System.out.println(cDAO.listar());
    }
}





