package dao.cliente;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.io.File;
import java.util.List;

import entidade.Cliente;

class TestRAFClienteDAO {

    /**
     * Testa se o arquivo não existe.
     *
     */
    @Test
    void testAbrirArquivo() {
        RAFClienteDAO rafclientedao = new RAFClienteDAO();

        assertFalse(rafclientedao.abrirArquivo("tes\\te//.txt"));
    }

    /**
     * Testa uma inclusão de cliente em arquivo inexistente no RAF.
     */
    @Test
    void testInclusaoRAF() {
        String NOMEARQUIVO = "cliente.dat";

        Cliente cliente = new Cliente("-1", "Cliente Existente", "11111111111");

        RAFClienteDAO rafClienteDAO = new RAFClienteDAO();
        try {
            rafClienteDAO.fecharArquivo();
        } catch (IOException e) {
            System.out.println("Problema em fechar o arquivo!");
        }

        //Apaga o arquivo para gerar exceção
        File file = new File(NOMEARQUIVO);
        file.delete();

        assertFalse(rafClienteDAO.inserir(cliente));
    }

    /**
     * Testa uma exclusão de cliente em arquivo inexistente no RAF.
     */
    @Test
    void testExclusaoRAF() {
        String NOMEARQUIVO = "cliente.dat";

        Cliente cliente = new Cliente("-1", "Cliente Existente", "11111111111");

        RAFClienteDAO rafClienteDAO = new RAFClienteDAO();
        try {
            rafClienteDAO.fecharArquivo();
        } catch (IOException e) {
            System.out.println("Problema em fechar o arquivo!");
        }

        //Apaga o arquivo para gerar exceção
        File file = new File(NOMEARQUIVO);
        file.delete();

        assertEquals(0, rafClienteDAO.excluir(cliente));
    }

    /**
     * Testa uma exclusão de registro de cliente em arquivo inexistente no RAF.
     */
    @Test
    void testExclusaoRegistoRAF() {
        String NOMEARQUIVO = "cliente.dat";

        RAFRegistroCliente registro = new RAFRegistroCliente();

        long pos = 1;

        RAFClienteDAO rafClienteDAO = new RAFClienteDAO();
        try {
            rafClienteDAO.fecharArquivo();
        } catch (IOException e) {
            System.out.println("Problema em fechar o arquivo!");
        }

        //Apaga o arquivo para gerar exceção
        File file = new File(NOMEARQUIVO);
        file.delete();

        assertEquals(0, rafClienteDAO.excluirRegistro(registro, pos));
    }

    /**
     * Testa uma exclusão de cliente em arquivo inexistente no RAF.
     */
    @Test
    void testAlteracaRAF() {
        String NOMEARQUIVO = "cliente.dat";

        Cliente cliente = new Cliente("-1", "Cliente Existente", "11111111111");

        RAFClienteDAO rafClienteDAO = new RAFClienteDAO();
        try {
            rafClienteDAO.fecharArquivo();
        } catch (IOException e) {
            System.out.println("Problema em fechar o arquivo!");
        }

        //Apaga o arquivo para gerar exceção
        File file = new File(NOMEARQUIVO);
        file.delete();

        assertEquals(0, rafClienteDAO.alterar(cliente));
    }

    /**
     * Testa uma alteração de registro de cliente em arquivo inexistente no RAF.
     */
    @Test
    void testAlteracaRegistoRAF() {
        String NOMEARQUIVO = "cliente.dat";

        Cliente cliente = new Cliente("-1", "Cliente Existente", "11111111111");
        RAFRegistroCliente registro = new RAFRegistroCliente();

        int pos = 1;

        RAFClienteDAO rafClienteDAO = new RAFClienteDAO();
        try {
            rafClienteDAO.fecharArquivo();
        } catch (IOException e) {
            System.out.println("Problema em fechar o arquivo!");
        }

        //Apaga o arquivo para gerar exceção
        File file = new File(NOMEARQUIVO);
        file.delete();

        assertEquals(0, rafClienteDAO.alterarRegistro(registro, cliente, pos));
    }

    /**
     * Testa o getLista em arquivo inexistente no RAF.
     */
    @Test
    void testGetListaRAF() {
        String NOMEARQUIVO = "cliente.dat";

        RAFClienteDAO rafClienteDAO = new RAFClienteDAO();
        try {
            rafClienteDAO.fecharArquivo();
        } catch (IOException e) {
            System.out.println("Problema em fechar o arquivo!");
        }

        //Apaga o arquivo para gerar exceção
        File file = new File(NOMEARQUIVO);
        file.delete();

        //Consulta
        List lista = rafClienteDAO.getLista();

        assertEquals(0, lista.size());
    }
        
    /**
     * Testa parametrizado do filtro para clienteid, nome e cpf em RAF inexistente.
     */
    @ParameterizedTest
    @CsvSource({"131, , ",
        ", Nome, ",
        ", , 111"})
    void testAplicarFiltrParametrizadoRAF(String clienteId, String nome, String CPF) {
        
        Cliente cliente = new Cliente(clienteId, nome, CPF);
        String NOMEARQUIVO = "cliente.dat";

        RAFClienteDAO rafClienteDAO = new RAFClienteDAO();
        try {
            rafClienteDAO.fecharArquivo();
        } catch (IOException e) {
            System.out.println("Problema em fechar o arquivo!");
        }

        //Apaga o arquivo para gerar exceção
        File file = new File(NOMEARQUIVO);
        file.delete();

        //Consulta
        List lista = rafClienteDAO.aplicarFiltro(cliente);

        assertEquals(0, lista.size());
    }  
}