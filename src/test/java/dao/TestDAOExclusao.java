package dao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.spy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.cliente.ClienteExcluir;
import dao.cliente.ClienteDAO;
import dao.cliente.HashMapClienteDAO;
import dao.cliente.SQLiteClienteDAO;
import entidade.Cliente;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestDAOExclusao {

    Cliente cliente;

    @BeforeAll
    void inicializa() {
        cliente = new Cliente("131", "Teste", "11111111111");
    }

    @Test
    void testExclusao1() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.SQLITE);
        ClienteDAO DAO = factory.getClienteDAO();;
        DAO.inserir(cliente);
        List lista = DAO.aplicarFiltro(cliente);

        if (!lista.isEmpty()) {
            Cliente oCliente = (Cliente) lista.iterator().next();
            //Verifica se existe o cliente antes da exclusão
            boolean existeAntesExclusao = oCliente != null;
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
    void testExclusao1Null() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.SQLITE);
        ClienteDAO DAO = factory.getClienteDAO();
        assertEquals(0, DAO.excluir(null));
    }

    @Test
    void testExclusao2() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.HASHMAP);
        ClienteDAO DAO = factory.getClienteDAO();
        DAO.inserir(cliente);
        List lista = DAO.aplicarFiltro(cliente);

        if (!lista.isEmpty()) {
            Cliente oCliente = (Cliente) lista.iterator().next();
            //Verifica se existe o cliente antes da exclusão
            boolean existeAntesExclusao = oCliente != null;
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
    void testExclusao2Null() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.HASHMAP);
        ClienteDAO DAO = factory.getClienteDAO();
        assertEquals(0, DAO.excluir(null));
    }

    @Test
    void testExclusao3() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.RAF);
        ClienteDAO DAO = factory.getClienteDAO();
        DAO.inserir(cliente);
        List lista = DAO.aplicarFiltro(cliente);

        if (!lista.isEmpty()) {
            Cliente oCliente = (Cliente) lista.iterator().next();
            //Verifica se existe o cliente antes da exclusão
            boolean existeAntesExclusao = oCliente != null;
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
    void excluirClienteInexistenteHashMapClienteDAO() {

        // Variáveis
        HashMapClienteDAO dao = new HashMapClienteDAO();
        Cliente cliente = new Cliente();
        cliente.setClienteId("TESTE_EXCLUSAO_INEXISTENTE");

        // Entrada: garante que o cliente não esteja cadastrado.
        dao.excluir(cliente);

        // Processamento: tenta excluir novamente o cliente.
        // Como ele não existe, o bloco else deve ser executado.
        int resultado = dao.excluir(cliente);

        // Saída: verifica o resultado da exclusão.
        assertEquals(0, resultado);
    }

    @Test
    void excluirCatchIOException() throws Exception {

        // Variáveis
        ClienteExcluir servlet = new ClienteExcluir();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Entrada: simula um erro ao obter o PrintWriter.
        when(response.getWriter()).thenThrow(new IOException("Erro simulado"));

        // Processamento: executa o servlet.
        // A IOException deve ser capturada pelo catch.
        servlet.doPost(request, response);

        // Saída: verifica se o método foi chamado.
        verify(response).getWriter();
    }

    @Test
    void tratarSQLExceptionNoExcluirSQLiteClienteDAO() throws Exception {
        // Variáveis
        SQLiteClienteDAO dao = spy(new SQLiteClienteDAO());
        Connection conexao = mock(Connection.class);
        Statement statement = mock(Statement.class);

        Cliente cliente = new Cliente();
        cliente.setClienteId("1");
        cliente.setNome("Cliente Teste");
        cliente.setCpf("12345678900");

        // Entrada
        doReturn(conexao).when(dao).getConnection();
        when(conexao.createStatement()).thenReturn(statement);

        doThrow(new SQLException("Erro simulado")).when(statement).executeUpdate(anyString());

        // Processamento
        int resultado = dao.excluir(cliente);

        // Saída
        assertEquals(0, resultado);
        verify(statement).executeUpdate(anyString());
    }

    @Test
    void testExclusao3Null() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.RAF);
        ClienteDAO DAO = factory.getClienteDAO();
        assertEquals(0, DAO.excluir(null));
    }

    @AfterAll
    void Finaliza() throws Exception {
        cliente = null;
    }
}
