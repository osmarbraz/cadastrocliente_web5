package cliente;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import entidade.Cliente;

class TestCliente {

    /**
     * Testa o construtor sem argumentos do cliente.
     */
    @Test
    void testCliente() {
        Cliente instancia = new Cliente();
        assertTrue("".equals(instancia.getClienteId()) && "".equals(instancia.getNome()) && "".equals(instancia.getCpf()));
    }

    @Test
    void testClienteIdInt() {
        Cliente instancia = new Cliente();
        instancia.setClienteId(1);
        assertTrue("1".equals(instancia.getClienteId()) && "".equals(instancia.getNome()) && "".equals(instancia.getCpf()));
    }

    @Test
    void testParaString() {
        Cliente instancia = new Cliente();
        String esperado = "clienteId: - Nome : - CPF :";
        assertEquals(esperado, instancia.toString());
    }

    @Test
    void testInserir() {
        Cliente cliente = new Cliente("131", "Teste", "11111111111");
        assertTrue(cliente.inserir());
        cliente.excluir();
    }

    @Test
    void testExcluir() {
        Cliente cliente = new Cliente("131", "Teste", "11111111111");
        cliente.inserir();
        assertEquals(1, cliente.excluir());
    }

    @Test
    void testAlterar() {
        Cliente cliente = new Cliente("131", "Teste", "11111111111");
        cliente.inserir();
        String nomeAlteracao = "Alterado";
        cliente.setNome(nomeAlteracao);
        //Altera o objeto
        cliente.alterar();
        cliente.abrir();
        //Compara a alteração com o dado do objeto
        assertEquals(nomeAlteracao, cliente.getNome());
        cliente.excluir();
    }

    @Test
    void testAplicarFiltro() {
        Cliente cliente = new Cliente("131", "Teste", "11111111111");
        cliente.inserir();
        List lista = cliente.aplicarFiltro();

        //Verifica os dados    
        if (!lista.isEmpty()) {
            Cliente oCliente = (Cliente) lista.iterator().next();
            assertNotNull(oCliente);
        } else {
            assertFalse(false);
        }
        cliente.excluir();
    }

    @Test
    void testGetLista() {
        Cliente cliente = new Cliente("131", "Teste", "11111111111");
        cliente.inserir();
        List lista = cliente.getLista();

        //Verifica os dados    
        if (!lista.isEmpty()) {
            Cliente oCliente = (Cliente) lista.iterator().next();
            assertNotNull(oCliente);
        } else {
            assertFalse(false);
        }
        cliente.excluir();
    }

    @Test
    void testAbrir() {
        //Testa um cliente que não existe
        Cliente cliente = new Cliente("-1", "Teste", "11111111111");
        assertFalse(cliente.abrir());
    }
}
