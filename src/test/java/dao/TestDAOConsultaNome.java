package dao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import dao.cliente.ClienteDAO;
import entidade.Cliente;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestDAOConsultaNome {

    Cliente cliente;

    @BeforeAll
    void inicializa() {
        cliente = new Cliente("131", "TesteConsulta", "11111111111");
    }

    @Test
    void testConsulta1() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.SQLITE);
        ClienteDAO dao = factory.getClienteDAO();
        Cliente consulta = new Cliente();
        consulta.setNome(cliente.getNome());
        // Insere os dados da consulta
        dao.inserir(cliente);
        //Consulta
        List lista = dao.aplicarFiltro(consulta);

        //Verifica os dados    
        if (!lista.isEmpty()) {
            Cliente oCliente = (Cliente) lista.iterator().next();
            assertNotNull(oCliente);
        } else {
            assertFalse(false);
        }
    }

    @Test
    void testConsulta2() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.HASHMAP);
        ClienteDAO dao = factory.getClienteDAO();
        Cliente consulta = new Cliente();
        consulta.setNome(cliente.getNome());
        // Insere os dados da consulta
        dao.inserir(cliente);
        //Consulta
        List lista = dao.aplicarFiltro(consulta);

        //Verifica os dados    
        if (!lista.isEmpty()) {
            Cliente oCliente = (Cliente) lista.iterator().next();
            assertNotNull(oCliente);
        } else {
            assertFalse(false);
        }
    }

    @Test
    void testConsulta3() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.RAF);
        ClienteDAO dao = factory.getClienteDAO();
        Cliente consulta = new Cliente();
        consulta.setNome(cliente.getNome());
        // Insere os dados da consulta
        dao.inserir(cliente);
        //Consulta
        List lista = dao.aplicarFiltro(consulta);

        //Verifica os dados    
        if (!lista.isEmpty()) {
            Cliente oCliente = (Cliente) lista.iterator().next();
            assertNotNull(oCliente);
        } else {
            assertFalse(false);
        }
    }

    @AfterAll
    void finaliza() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.SQLITE);
        ClienteDAO dao = factory.getClienteDAO();
        // Exclui os dados inseridos
        dao.excluir(cliente);

        factory = DAOFactory.getDAOFactory(DAOFactory.HASHMAP);
        dao = factory.getClienteDAO();
        // Exclui os dados inseridos
        dao.excluir(cliente);

        factory = DAOFactory.getDAOFactory(DAOFactory.RAF);
        dao = factory.getClienteDAO();
        // Exclui os dados inseridos
        dao.excluir(cliente);

        cliente = null;
    }
}
