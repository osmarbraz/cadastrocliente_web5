package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

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
        assertEquals("",prepara);
    }
}
