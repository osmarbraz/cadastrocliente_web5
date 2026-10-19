package util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;

import entidade.Cliente;

/**
 * Testes unitários da classe RequestCliente.
 *
 * Verifica a recuperação e a codificação dos dados recebidos por meio de uma
 * requisição HTTP.
 */
public class TestRequestCliente {

    /** 
     * Testa a recuperação dos dados do cliente.
     */
    @Test
    void testRecuperaCliente() {
        // Variáveis
        HttpServletRequest request = mock(HttpServletRequest.class);
        Cliente cliente;

        // Entrada
        when(request.getParameter("ClienteId")).thenReturn("10");
        when(request.getParameter("Nome")).thenReturn("Joao");
        when(request.getParameter("CPF")).thenReturn("12345678900");

        // Processamento
        cliente = RequestCliente.recuperaCliente(request);

        // Saída
        assertEquals("10", cliente.getClienteId());
        assertEquals("Joao", cliente.getNome());
        assertEquals("12345678900", cliente.getCpf());
    }

    /** 
     * Testa a recuperação dos dados do cliente com caracteres HTML.
     */
    @Test
    void testRecuperaClienteComCaracteresHTML() {
        // Variáveis
        HttpServletRequest request = mock(HttpServletRequest.class);
        Cliente cliente;

        // Entrada
        when(request.getParameter("ClienteId")).thenReturn("10");
        when(request.getParameter("Nome")).thenReturn("<script>alert('XSS')</script>");
        when(request.getParameter("CPF")).thenReturn("12345678900");

        // Processamento
        cliente = RequestCliente.recuperaCliente(request);

        // Saída
        assertEquals("10", cliente.getClienteId());
        assertEquals("&lt;script&gt;alert(&#39;XSS&#39;)&lt;/script&gt;", cliente.getNome());
        assertEquals("12345678900", cliente.getCpf());
    }

    /** 
     * Testa a recuperação dos dados do cliente com valores nulos.
     */
    @Test
    void testRecuperaClienteComParametrosNulos() {

        // Variáveis
        HttpServletRequest request = mock(HttpServletRequest.class);
        Cliente cliente;

        // Entrada
        when(request.getParameter("ClienteId")).thenReturn(null);
        when(request.getParameter("Nome")).thenReturn(null);
        when(request.getParameter("CPF")).thenReturn(null);

        // Processamento
        cliente = RequestCliente.recuperaCliente(request);

        // Saída
        assertEquals("null", cliente.getClienteId());
        assertEquals("null", cliente.getNome());
        assertEquals("null", cliente.getCpf());
    }
}
