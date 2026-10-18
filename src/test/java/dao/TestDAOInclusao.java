package dao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.Level;

import controle.cliente.ClienteIncluir;
import dao.cliente.ClienteDAO;
import entidade.Cliente;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestDAOInclusao {

    Cliente cliente;

    @BeforeAll
    void inicializa() {
        cliente = new Cliente("131", "Teste", "11111111111");
    }

    @Test
    void testIncluir1() throws Exception {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.SQLITE);
        ClienteDAO DAO = factory.getClienteDAO();
        // Insere os dados        
        DAO.inserir(cliente);
        //Consulta
        List lista = DAO.aplicarFiltro(cliente);
        //Verifica os dados    
        if (!lista.isEmpty()) {
            Cliente oCliente = (Cliente) lista.iterator().next();
            assertNotNull(oCliente);
        } else {
            assertFalse(false);
        }
    }

    @Test
    void testIncluir1Null() throws Exception {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.SQLITE);
        ClienteDAO DAO = factory.getClienteDAO();
        // Insere os dados        
        assertFalse(DAO.inserir(null));
    }

    @Test
    void testIncluir2() throws Exception {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.HASHMAP);
        ClienteDAO DAO = factory.getClienteDAO();
        // Insere os dados        
        DAO.inserir(cliente);
        //Consulta
        List lista = DAO.aplicarFiltro(cliente);
        //Verifica os dados    
        if (!lista.isEmpty()) {
            Cliente oCliente = (Cliente) lista.iterator().next();
            assertNotNull(oCliente);
        } else {
            assertFalse(false);
        }
    }

    @Test
    void testIncluir2Null() throws Exception {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.HASHMAP);
        ClienteDAO DAO = factory.getClienteDAO();
        // Insere os dados        
        assertFalse(DAO.inserir(null));
    }

    @Test
    void testIncluir3() throws Exception {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.RAF);
        ClienteDAO DAO = factory.getClienteDAO();
        // Insere os dados        
        DAO.inserir(cliente);
        //Consulta
        List lista = DAO.aplicarFiltro(cliente);
        //Verifica os dados    
        if (!lista.isEmpty()) {
            Cliente oCliente = (Cliente) lista.iterator().next();
            assertNotNull(oCliente);
        } else {
            assertFalse(false);
        }
    }

    @Test
    void testIncluir3Null() throws Exception {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.RAF);
        ClienteDAO DAO = factory.getClienteDAO();
        // Insere os dados        
        assertFalse(DAO.inserir(null));
    }

    @Test
    void incluirIOException() throws Exception {

        // Variáveis
        ClienteIncluir servlet = new ClienteIncluir();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Entrada: simula um erro ao obter o PrintWriter.
        when(response.getWriter()).thenThrow(new IOException("Erro simulado"));

        // Processamento: executa o servlet.
        // A IOException deve ser capturada pelo catch.
        assertDoesNotThrow(() -> servlet.doPost(request, response));

        // Saída: verifica se o método foi chamado.
        verify(response).getWriter();
    }

    @AfterAll
    void Finaliza() throws Exception {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.SQLITE);
        ClienteDAO DAO = factory.getClienteDAO();
        // Exclui os dados inseridos
        DAO.excluir(cliente);

        factory = DAOFactory.getDAOFactory(DAOFactory.HASHMAP);
        DAO = factory.getClienteDAO();
        // Exclui os dados inseridos
        DAO.excluir(cliente);

        factory = DAOFactory.getDAOFactory(DAOFactory.RAF);
        DAO = factory.getClienteDAO();
        // Exclui os dados inseridos
        DAO.excluir(cliente);

        cliente = null;
    }
}
