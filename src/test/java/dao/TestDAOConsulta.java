package dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.spy;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import controle.cliente.ClienteConsultar;
import dao.cliente.ClienteDAO;
import dao.cliente.SQLiteClienteDAO;
import entidade.Cliente;
import java.sql.Connection;
import java.sql.SQLException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestDAOConsulta {

    Cliente cliente;

    /**
     * Instância um cliente para os testes.
     */
    @BeforeAll
    void inicializa() {
        cliente = new Cliente("131", "Cliente Existente", "11111111111");
    }

    /**
     * Testa um cliente existente no SQLite.
     */
    @Test
    void testConsulta1() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.SQLITE);
        ClienteDAO DAO = factory.getClienteDAO();
        // Insere os dados da consulta
        DAO.inserir(cliente);
        //Consulta
        List lista = DAO.getLista();
        assertNotEquals(0, lista.size());
    }

    /**
     * Testa um cliente existente no Hashmap.
     */
    @Test
    void testConsulta2() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.HASHMAP);
        ClienteDAO DAO = factory.getClienteDAO();
        // Insere os dados da consulta
        DAO.inserir(cliente);
        //Consulta
        List lista = DAO.getLista();
        assertNotEquals(0, lista.size());
    }

    /**
     * Testa um cliente existente no RAF.
     */
    @Test
    void testConsulta3() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.RAF);
        ClienteDAO DAO = factory.getClienteDAO();
        // Insere os dados da consulta
        DAO.inserir(cliente);
        //Consulta
        List lista = DAO.getLista();
        assertNotEquals(0, lista.size());
    }

    @Test
    void consultarCatchIOException() throws Exception {

        // Variáveis
        ClienteConsultar servlet = new ClienteConsultar();

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
    void tratarSQLExceptionNoSelectSQLiteClienteDAO() throws Exception {
        // Variáveis
        SQLiteClienteDAO dao = spy(new SQLiteClienteDAO());
        Connection conexao = mock(Connection.class);

        // Entrada
        doReturn(conexao).when(dao).getConnection();
        doThrow(new SQLException("Erro simulado")).when(conexao).createStatement();

        // Processamento
        List<Cliente> resultado = dao.getLista();

        // Saída
        assertTrue(resultado.isEmpty());
    }

    /**
     * Exclui os clientes usados nos testes.
     *
     * @throws java.lang.Exception
     */
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
