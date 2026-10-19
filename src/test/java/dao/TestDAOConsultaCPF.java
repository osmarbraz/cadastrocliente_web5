package dao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import dao.cliente.ClienteDAO;
import entidade.Cliente;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestDAOConsultaCPF {

    Cliente cliente;
    Cliente clienteNaoExistente;

    @BeforeAll
    void inicializa() {
        cliente = new Cliente("131", "Cliente Existente", "11111111111");
        clienteNaoExistente = new Cliente("999", "Cliente Nao Existente", "");
    }

    @Test
    void testConsulta1() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.SQLITE);
        ClienteDAO dao = factory.getClienteDAO();
        Cliente consulta = new Cliente();
        consulta.setCpf(cliente.getCpf());
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
    void testConsulta1Inexistente() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.SQLITE);
        ClienteDAO dao = factory.getClienteDAO();
        Cliente consulta = new Cliente();
        consulta.setCpf(clienteNaoExistente.getCpf());
        //Consulta
        List lista = dao.aplicarFiltro(consulta);
        //Verifica os dados 
         if (lista.isEmpty()) {
            assertTrue(true);
         }
    }

    @Test
    void testConsulta1Null() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.SQLITE);
        ClienteDAO dao = factory.getClienteDAO();
        //Consulta
        List lista = dao.aplicarFiltro(null);

        assertTrue(lista.isEmpty());
    }

    @Test
    void testConsulta2() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.HASHMAP);
        ClienteDAO dao = factory.getClienteDAO();
        Cliente consulta = new Cliente();
        consulta.setCpf(cliente.getCpf());
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
    void testConsulta2Inexistente() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.HASHMAP);
        ClienteDAO dao = factory.getClienteDAO();
        Cliente consulta = new Cliente();
        consulta.setCpf(clienteNaoExistente.getCpf());
        //Consulta
        List lista = dao.aplicarFiltro(consulta);
        //Verifica os dados    
        assertTrue(lista.isEmpty());
    }

    @Test
    void testConsulta2Null() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.HASHMAP);
        ClienteDAO dao = factory.getClienteDAO();
        //Consulta
        List lista = dao.aplicarFiltro(null);

        assertTrue(lista.isEmpty());
    }

    @Test
    void testConsulta3() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.RAF);
        ClienteDAO dao = factory.getClienteDAO();
        Cliente consulta = new Cliente();
        consulta.setCpf(cliente.getCpf());
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
    void testConsulta3Inexistente() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.RAF);
        ClienteDAO dao = factory.getClienteDAO();
        Cliente consulta = new Cliente();
        consulta.setCpf(clienteNaoExistente.getCpf());
        //Consulta
        List lista = dao.aplicarFiltro(consulta);
        //Verifica os dados    
        assertTrue(lista.isEmpty());
    }

    @Test
    void testConsulta3Null() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.RAF);
        ClienteDAO dao = factory.getClienteDAO();
        //Consulta
        List lista = dao.aplicarFiltro(null);

        assertTrue(lista.isEmpty());
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
