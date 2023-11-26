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
    void testIncluir1() throws Exception {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.SQLITE);
        ClienteDAO DAO = factory.getClienteDAO();
        // Insere os dados        
        DAO.inserir(cliente);
        // Repete a inserção dos dados
        assertFalse(DAO.inserir(cliente));
    }

    @Test
    void testIncluir2() throws Exception {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.HASHMAP);
        ClienteDAO DAO = factory.getClienteDAO();
        // Insere os dados                           
        DAO.inserir(cliente);
        // Repete a inserção dos dados
        assertFalse(DAO.inserir(cliente));
    }

    @Test
    void testIncluir3() throws Exception {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.RAF);
        ClienteDAO DAO = factory.getClienteDAO();
        // Insere os dados        
        DAO.inserir(cliente);
        // Repete a inserção dos dados
        assertFalse(DAO.inserir(cliente));
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
