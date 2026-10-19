package dao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;

import entidade.Cliente;
import dao.cliente.ClienteDAO;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestDAOInclusaoChavePrimaria {

    Cliente cliente;

    @BeforeAll
    void inicializa() {
        cliente = new Cliente("123", "Teste Chave Primaria", "11111111111");
    }

    @Test
    void testIncluir1() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.SQLITE);
        ClienteDAO dao = factory.getClienteDAO();
        // Insere os dados        
        dao.inserir(cliente);
        // Repete a inserção dos dados
        assertFalse(dao.inserir(cliente));
    }

    @Test
    void testIncluir2() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.HASHMAP);
        ClienteDAO dao = factory.getClienteDAO();
        // Insere os dados                           
        dao.inserir(cliente);
        // Repete a inserção dos dados
        assertFalse(dao.inserir(cliente));
    }

    @Test
    void testIncluir3() {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.RAF);
        ClienteDAO dao = factory.getClienteDAO();
        // Insere os dados        
        dao.inserir(cliente);
        // Repete a inserção dos dados
        assertFalse(dao.inserir(cliente));
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
