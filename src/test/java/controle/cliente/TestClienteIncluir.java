package controle.cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import entidade.Cliente;

public class TestClienteIncluir {

    /**
     * Teste de incluão de novo cliente.
     *
     * @throws IOException
     * @throws ServletException
     */
    @Test
    public void testDoPost1() throws IOException, ServletException {

        // Dados da inclusão
        Cliente cliente = new Cliente("140", "Cliente Existente", "11111111111");

        // Servlet
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
        ServletContext mockedServletContext = mock(ServletContext.class);
        HttpSession mockedSession = mock(HttpSession.class);
        doReturn(mockedServletContext).when(mockedRequest).getServletContext();

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(mockedResponse.getWriter()).thenReturn(writer);

        //Parâmetros da inclusão
        when(mockedRequest.getParameter("ClienteId")).thenReturn(cliente.getClienteId());
        when(mockedRequest.getParameter("Nome")).thenReturn(cliente.getNome());
        when(mockedRequest.getParameter("CPF")).thenReturn(cliente.getCpf());
        when(mockedRequest.getSession()).thenReturn(mockedSession);

        //Servlet Consulta
        ClienteIncluir clienteIncluir = new ClienteIncluir();
        clienteIncluir.doPost(mockedRequest, mockedResponse);

        //Resultado do servlet
        String resultado = stringWriter.toString();
        assertTrue(resultado.contains("Inclus&atilde;o realizada com sucesso."));

        //Exclui os dados da inclusão
        cliente.excluir();
    }

    /**
     * Teste de incluão de cliente existente.
     *
     * @throws IOException
     * @throws ServletException
     */
    @Test
    public void testDoPost2() throws IOException, ServletException {

        // Dados da inclusão
        Cliente cliente1 = new Cliente("135", "Cliente Existente", "11111111111");

        Cliente cliente2 = new Cliente("135", "Cliente Existente", "11111111111");
        cliente2.inserir();

        // Servlet
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
        ServletContext mockedServletContext = mock(ServletContext.class);
        HttpSession mockedSession = mock(HttpSession.class);
        doReturn(mockedServletContext).when(mockedRequest).getServletContext();

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(mockedResponse.getWriter()).thenReturn(writer);

        //Parâmetros da inclusão
        when(mockedRequest.getParameter("ClienteId")).thenReturn(cliente1.getClienteId());
        when(mockedRequest.getParameter("Nome")).thenReturn(cliente1.getNome());
        when(mockedRequest.getParameter("CPF")).thenReturn(cliente1.getCpf());
        when(mockedRequest.getSession()).thenReturn(mockedSession);

        //Servlet Consulta
        ClienteIncluir clienteIncluir = new ClienteIncluir();
        clienteIncluir.doPost(mockedRequest, mockedResponse);

        //Resultado do servlet
        String resultado = stringWriter.toString();
        assertTrue(resultado.contains("Inclus&atilde;o n&atilde;o realizada."));

        //Exclui os dados da inclusão
        cliente1.excluir();
        cliente2.excluir();
    }

    /**
     * Teste de incluão de novo cliente com cpf inválido.
     *
     * @throws IOException
     * @throws ServletException
     */
    @Test
    public void testDoPost3() throws IOException, ServletException {

        // Dados da inclusão
        Cliente cliente = new Cliente("137", "Cliente Existente", "111");

        // Servlet
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
        ServletContext mockedServletContext = mock(ServletContext.class);
        HttpSession mockedSession = mock(HttpSession.class);
        doReturn(mockedServletContext).when(mockedRequest).getServletContext();

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(mockedResponse.getWriter()).thenReturn(writer);

        //Parâmetros da inclusão
        when(mockedRequest.getParameter("ClienteId")).thenReturn(cliente.getClienteId());
        when(mockedRequest.getParameter("Nome")).thenReturn(cliente.getNome());
        when(mockedRequest.getParameter("CPF")).thenReturn(cliente.getCpf());
        when(mockedRequest.getSession()).thenReturn(mockedSession);

        //Servlet Consulta
        ClienteIncluir clienteIncluir = new ClienteIncluir();
        clienteIncluir.doPost(mockedRequest, mockedResponse);

        //Resultado do servlet
        String resultado = stringWriter.toString();
        assertTrue(resultado.contains("CPF Inv&aacute;lido!"));

        //Exclui os dados da inclusão
        cliente.excluir();
    }
}
