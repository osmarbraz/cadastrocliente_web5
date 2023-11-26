package dao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import dao.cliente.ClienteDAO;
import entidade.Cliente;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestDAOExclusao {

    Cliente cliente;

    @BeforeAll
    public void inicializa() {
        cliente = new Cliente("131", "Teste", "11111111111");
    }

    @Test
    public void testExclusao1() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.SQLITE);
        ClienteDAO DAO = factory.getClienteDAO();;
        DAO.inserir(cliente);
        List lista = DAO.aplicarFiltro(cliente);

        if (!lista.isEmpty()) {
            Cliente oCliente = (Cliente) lista.iterator().next();
            //Verifica se existe o cliente antes da exclusão
            boolean existeAntesExclusao = oCliente!= null;
            DAO.excluir(oCliente);
            lista = DAO.aplicarFiltro(cliente);
            //Verifica se a lista está vazia depois da exclusão
            boolean naoExisteDepoisExclusao = lista.isEmpty();
            assertTrue(existeAntesExclusao);
            assertTrue(naoExisteDepoisExclusao);
        } else {
            assertFalse(false);
        }
    }

    @Test
    public void testExclusao1Null() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.SQLITE);
        ClienteDAO DAO = factory.getClienteDAO();
        assertEquals(0, DAO.excluir(null));
    }

    @Test
    public void testExclusao2() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.HASHMAP);
        ClienteDAO DAO = factory.getClienteDAO();
        DAO.inserir(cliente);
        List lista = DAO.aplicarFiltro(cliente);

        if (!lista.isEmpty()) {
           Cliente oCliente = (Cliente) lista.iterator().next();
            //Verifica se existe o cliente antes da exclusão
            boolean existeAntesExclusao = oCliente!= null;
            DAO.excluir(oCliente);
            lista = DAO.aplicarFiltro(cliente);
            //Verifica se a lista está vazia depois da exclusão
            boolean naoExisteDepoisExclusao = lista.isEmpty();
            assertTrue(existeAntesExclusao);
            assertTrue(naoExisteDepoisExclusao);
        } else {
            assertFalse(false);
        }
    }

    @Test
    public void testExclusao2Null() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.HASHMAP);
        ClienteDAO DAO = factory.getClienteDAO();
        assertEquals(0, DAO.excluir(null));
    }

    @Test
    public void testExclusao3() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.RAF);
        ClienteDAO DAO = factory.getClienteDAO();
        DAO.inserir(cliente);
        List lista = DAO.aplicarFiltro(cliente);

        if (!lista.isEmpty()) {
            Cliente oCliente = (Cliente) lista.iterator().next();
            //Verifica se existe o cliente antes da exclusão
            boolean existeAntesExclusao = oCliente!= null;
            DAO.excluir(oCliente);
            lista = DAO.aplicarFiltro(cliente);
            //Verifica se a lista está vazia depois da exclusão
            boolean naoExisteDepoisExclusao = lista.isEmpty();
            assertTrue(existeAntesExclusao);
            assertTrue(naoExisteDepoisExclusao);
        } else {
            assertFalse(false);
        }
    }

    @Test
    public void testExclusao3Null() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.RAF);
        ClienteDAO DAO = factory.getClienteDAO();
        assertEquals(0, DAO.excluir(null));
    }

    @AfterAll
    public void Finaliza() throws Exception {
        cliente = null;
    }
}
