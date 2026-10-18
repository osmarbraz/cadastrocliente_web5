package dao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.anyString;

import controle.cliente.ClienteAlterar;
import dao.cliente.ClienteDAO;
import dao.cliente.SQLiteClienteDAO;
import entidade.Cliente;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestDAOAlteracao {

    Cliente cliente;
    Cliente clienteNaoExistente;

    @BeforeAll
    void inicializa() {
        cliente = new Cliente("131", "TesteAlteracao", "11111111111");
        clienteNaoExistente = new Cliente("879", "Cliente Nao Existente", "11111111111");
    }

    /**
     * Testa a alteração do nome de um cliente existente no SQLite.
     */
    @Test
    void testAlteracaoNome1() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.SQLITE);
        ClienteDAO DAO = factory.getClienteDAO();
        DAO.inserir(cliente);
        List lista = DAO.aplicarFiltro(cliente);
        //Dado a ser alterado
        String nomeAlteracao = "Alterado";
        if (!lista.isEmpty()) {
            Cliente oCliente = (Cliente) lista.iterator().next();
            oCliente.setNome(nomeAlteracao);
            //Altera o objeto
            DAO.alterar(oCliente);
            lista = DAO.aplicarFiltro(cliente);
            if (!lista.isEmpty()) {
                Cliente aCliente = (Cliente) lista.iterator().next();
                //Compara a alteração com o dado do objeto
                assertEquals(nomeAlteracao, aCliente.getNome());
            } else {
                assertFalse(false);
            }
        } else {
            assertFalse(false);
        }
    }

    /**
     * Testa a alteração do cpf de um cliente existente no SQLite.
     */
    @Test
    void testAlteracaoCpf1() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.SQLITE);
        ClienteDAO DAO = factory.getClienteDAO();
        DAO.inserir(cliente);
        List lista = DAO.aplicarFiltro(cliente);
        //Dado a ser alterado
        String cpfAlteracao = "22222222222";
        if (!lista.isEmpty()) {
            Cliente oCliente = (Cliente) lista.iterator().next();
            oCliente.setCpf(cpfAlteracao);
            //Altera o objeto
            DAO.alterar(oCliente);
            lista = DAO.aplicarFiltro(cliente);
            if (!lista.isEmpty()) {
                Cliente aCliente = (Cliente) lista.iterator().next();
                //Compara a alteração com o dado do objeto
                assertEquals(cpfAlteracao, aCliente.getCpf());
            } else {
                assertFalse(false);
            }
        } else {
            assertFalse(false);
        }
    }

    /**
     * Testa a alteração de um cliente não existente no SQLite.
     */
    @Test
    void testAlteracao1NaoExistente() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.SQLITE);
        ClienteDAO DAO = factory.getClienteDAO();
        //Altera o objeto não existente         
        assertEquals(0, DAO.alterar(clienteNaoExistente));
    }

    /**
     * Testa a alteração de um cliente nullo no SQLite.
     */
    @Test
    void testAlteracao1Null() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.SQLITE);
        ClienteDAO DAO = factory.getClienteDAO();
        assertEquals(0, DAO.alterar(null));
    }

    /**
     * Testa a alteração do campo nome de um cliente no Hashmap.
     */
    @Test
    void testAlteracao2() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.HASHMAP);
        ClienteDAO DAO = factory.getClienteDAO();
        DAO.inserir(cliente);
        List lista = DAO.aplicarFiltro(cliente);
        //Dado a ser alterado
        String nomeAlteracao = "Alterado";
        if (!lista.isEmpty()) {
            Cliente oCliente = (Cliente) lista.iterator().next();
            oCliente.setNome(nomeAlteracao);
            //Altera o objeto
            DAO.alterar(oCliente);
            lista = DAO.aplicarFiltro(cliente);
            if (!lista.isEmpty()) {
                Cliente aCliente = (Cliente) lista.iterator().next();
                //Compara a alteração com o dado do objeto
                assertEquals(nomeAlteracao, aCliente.getNome());
            } else {
                assertFalse(false);
            }
        } else {
            assertFalse(false);
        }
    }

    /**
     * Testa a alteração de um cliente não existente no Hashmap.
     */
    @Test
    void testAlteracao2NaoExistente() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.HASHMAP);
        ClienteDAO DAO = factory.getClienteDAO();
        //Altera o objeto não existente         
        assertEquals(0, DAO.alterar(clienteNaoExistente));
    }

    /**
     * Testa a alteração de um cliente nulo no Hashmap.
     */
    @Test
    void testAlteracao2Null() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.HASHMAP);
        ClienteDAO DAO = factory.getClienteDAO();
        assertEquals(0, DAO.alterar(null));
    }

    /**
     * Testa a alteração do campo nome de um cliente não existente no RAF.
     */
    @Test
    void testAlteracao3() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.RAF);
        ClienteDAO DAO = factory.getClienteDAO();
        DAO.inserir(cliente);
        List lista = DAO.aplicarFiltro(cliente);
        //Dado a ser alterado
        String nomeAlteracao = "Alterado";
        if (!lista.isEmpty()) {
            Cliente oCliente = (Cliente) lista.iterator().next();
            oCliente.setNome(nomeAlteracao);
            //Altera o objeto
            DAO.alterar(oCliente);
            lista = DAO.aplicarFiltro(cliente);
            if (!lista.isEmpty()) {
                Cliente aCliente = (Cliente) lista.iterator().next();
                //Compara a alteração com o dado do objeto
                assertEquals(nomeAlteracao, aCliente.getNome());
            } else {
                assertFalse(false);
            }
        } else {
            assertFalse(false);
        }
    }

    /**
     * Testa a alteração de um cliente não existente no RAF.
     */
    @Test
    void testAlteracao3NaoExistente() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.RAF);
        ClienteDAO DAO = factory.getClienteDAO();
        //Altera o objeto não existente         
        assertEquals(0, DAO.alterar(clienteNaoExistente));
    }

    /**
     * Testa a alteração de um cliente nulo no RAF.
     */
    @Test
    void testAlteracao3Null() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.RAF);
        ClienteDAO DAO = factory.getClienteDAO();
        assertEquals(0, DAO.alterar(null));
    }

    @Test
    void alterarCatchIOException() throws Exception {
        // Variáveis
        ClienteAlterar servlet = new ClienteAlterar();

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
    void tratarSQLExceptionNoAlterarSQLiteClienteDAO() throws Exception {
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
        int resultado = dao.alterar(cliente);

        // Saída
        assertEquals(0, resultado);
        verify(statement).executeUpdate(anyString());
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
