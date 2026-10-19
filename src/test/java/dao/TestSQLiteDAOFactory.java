package dao;

import dao.cliente.SQLiteClienteDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import org.mockito.MockedStatic;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TestSQLiteDAOFactory {

    /**
     * Testa se o driver não existe.
     */
    @Test
    void testGetConnectionDriver() {
        SQLiteDAOFactory sqlitedaofactory = new SQLiteDAOFactory();
        sqlitedaofactory.setDriverClass("X");
        sqlitedaofactory.setJdbcURL("jdbc:sqlite:cliente.db");
        assertNull(sqlitedaofactory.getConnection());
    }

    /**
     * Testa se a url não existe.
     */
    @Test
    void testGetConnectionURL() {
        SQLiteDAOFactory sqlitedaofactory = new SQLiteDAOFactory();
        sqlitedaofactory.setDriverClass("org.sqlite.JDBC");
        sqlitedaofactory.setJdbcURL("Y");
        assertNull(sqlitedaofactory.getConnection());
    }

    /**
     * Testa se o preparaSQL não existe.
     */
    @Test
    void testPrepareSQL() {
        SQLiteDAOFactory sqlitedaofactory = new SQLiteDAOFactory();
        String prepara = sqlitedaofactory.preparaSQL(null);
        assertEquals("", prepara);
    }

    @Test
    void criarSQLExceptionSQLiteClienteDAO() throws SQLException {
        // Variáveis
        Connection conexao = mock(Connection.class);
        Statement statement = mock(Statement.class);

        // Entrada
        try ( MockedStatic<DriverManager> driverManager  = mockStatic(DriverManager.class)) {
            driverManager.when(()-> DriverManager.getConnection(anyString())) .thenReturn(conexao);
            when(conexao.createStatement()).thenReturn(statement);
            doThrow(new SQLException("Erro simulado")).when(statement).execute(anyString());

            // Processamento
            SQLiteClienteDAO dao = new SQLiteClienteDAO();

            // Saída
            assertNotNull(dao);
            verify(statement).execute(anyString());
        }
    }
}
