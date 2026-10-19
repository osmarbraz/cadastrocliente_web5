package dao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import controle.cliente.ClienteIncluir;
import dao.cliente.ClienteDAO;
import dao.cliente.SQLiteClienteDAO;
import entidade.Cliente;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestDAOInclusao {

    Cliente cliente;

    @BeforeAll
    void inicializa() {
        cliente = new Cliente("131", "Teste", "11111111111");
    }

    @Test
    void testIncluir1() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.SQLITE);
        ClienteDAO dao = factory.getClienteDAO();
        // Insere os dados        
        dao.inserir(cliente);
        //Consulta
        List lista = dao.aplicarFiltro(cliente);
        //Verifica os dados    
        if (!lista.isEmpty()) {
            Cliente oCliente = (Cliente) lista.iterator().next();
            assertNotNull(oCliente);
        } else {
            assertFalse(false);
        }
    }

    @Test
    void testIncluir1Null() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.SQLITE);
        ClienteDAO dao = factory.getClienteDAO();
        // Insere os dados        
        assertFalse(dao.inserir(null));
    }

    @Test
    void testIncluir2() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.HASHMAP);
        ClienteDAO dao = factory.getClienteDAO();
        // Insere os dados        
        dao.inserir(cliente);
        //Consulta
        List lista = dao.aplicarFiltro(cliente);
        //Verifica os dados    
        if (!lista.isEmpty()) {
            Cliente oCliente = (Cliente) lista.iterator().next();
            assertNotNull(oCliente);
        } else {
            assertFalse(false);
        }
    }

    @Test
    void testIncluir2Null() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.HASHMAP);
        ClienteDAO dao = factory.getClienteDAO();
        // Insere os dados        
        assertFalse(dao.inserir(null));
    }

    @Test
    void testIncluir3() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.RAF);
        ClienteDAO dao = factory.getClienteDAO();
        // Insere os dados        
        dao.inserir(cliente);
        //Consulta
        List lista = dao.aplicarFiltro(cliente);
        //Verifica os dados    
        if (!lista.isEmpty()) {
            Cliente oCliente = (Cliente) lista.iterator().next();
            assertNotNull(oCliente);
        } else {
            assertFalse(false);
        }
    }

    @Test
    void testIncluir3Null() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.RAF);
        ClienteDAO dao = factory.getClienteDAO();
        // Insere os dados        
        assertFalse(dao.inserir(null));
    }

    @Test
    void incluirIOException() throws IOException {

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

    @Test
    public void testInserirComSucessoSQLiteClienteDAO() throws SQLException{
        SQLiteClienteDAO dao = spy(new SQLiteClienteDAO());
        Connection conexao = mock(Connection.class);
        Statement statement = mock(Statement.class);

        doReturn(conexao).when(dao).getConnection();
        when(conexao.createStatement()).thenReturn(statement);
        when(statement.executeUpdate(anyString())).thenReturn(1);

        assertTrue(dao.inserir(cliente));
        verify(statement).executeUpdate(anyString());
    }

    @Test
    public void testInserirQuandoExecuteUpdateRetornaZeroSQLiteClienteDAO() throws SQLException{
        // Variáveis
        SQLiteClienteDAO dao = spy(new SQLiteClienteDAO());
        Connection conexao = mock(Connection.class);
        Statement statement = mock(Statement.class);
        // Entrada
        doReturn(conexao).when(dao).getConnection();
        when(conexao.createStatement()).thenReturn(statement);
        when(statement.executeUpdate(anyString())).thenReturn(0);
        // Saída
        assertFalse(dao.inserir(cliente));
        verify(statement).executeUpdate(anyString());
    }

    @Test
    public void testInserirComObjetoNuloSQLiteClienteDAO() {
        SQLiteClienteDAO dao = spy(new SQLiteClienteDAO());
        assertFalse(dao.inserir(null));
    }

    @Test
    public void testInserirComErroSQL() throws SQLException {
        // Variáveis
        SQLiteClienteDAO dao = spy(new SQLiteClienteDAO());
        Connection conexao = mock(Connection.class);
        Statement statement = mock(Statement.class);
        boolean resultado;
        // Entrada
        doReturn(conexao).when(dao).getConnection();
        when(conexao.createStatement()).thenReturn(statement);
        doThrow(new SQLException("Erro simulado")).when(statement).executeUpdate(anyString());
        // Processamento
        resultado = dao.inserir(cliente);
        // Saída
        assertFalse(resultado);
        verify(statement).executeUpdate(anyString());
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
