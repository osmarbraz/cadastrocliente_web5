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

class TestClienteExcluir {

    /**
     * Teste exclusão cliente existente.
     *
     * @throws IOException
     * @throws ServletException
     */
    @Test
    void testDoPost1() throws IOException, ServletException {

        //Insere os dados da exclusão        
        Cliente cliente = new Cliente("131", "Teste", "11111111111");
        cliente.inserir();

        //Servlet
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
        ServletContext mockedServletContext = mock(ServletContext.class);
        HttpSession mockedSession = mock(HttpSession.class);
        doReturn(mockedServletContext).when(mockedRequest).getServletContext();

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(mockedResponse.getWriter()).thenReturn(writer);

        //Parâmetros da exclusão
        when(mockedRequest.getParameter("ClienteId")).thenReturn("131");
        when(mockedRequest.getSession()).thenReturn(mockedSession);

        //Servlet Exclusão
        ClienteExcluir clienteExcluir = new ClienteExcluir();
        clienteExcluir.doPost(mockedRequest, mockedResponse);

        //Resultado do servlet
        String resultado = stringWriter.toString();
        assertTrue(resultado.contains("Exclus&atilde;o realizada com sucesso."));
    }

    /**
     * Teste exclusão cliente não existente.
     *
     * @throws IOException
     * @throws ServletException
     */
    @Test
    void testDoPost2() throws IOException, ServletException {

        //Insere os dados da exclusão        
        Cliente cliente = new Cliente("131", "Teste", "11111111111");
        cliente.inserir();

        //Servlet
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
        ServletContext mockedServletContext = mock(ServletContext.class);
        HttpSession mockedSession = mock(HttpSession.class);
        doReturn(mockedServletContext).when(mockedRequest).getServletContext();

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(mockedResponse.getWriter()).thenReturn(writer);

        //Parâmetros da exclusão
        when(mockedRequest.getParameter("ClienteId")).thenReturn("133");
        when(mockedRequest.getSession()).thenReturn(mockedSession);

        //Servlet Exclusão
        ClienteExcluir clienteExcluir = new ClienteExcluir();
        clienteExcluir.doPost(mockedRequest, mockedResponse);

        //Resultado do servlet
        String resultado = stringWriter.toString();
        assertTrue(resultado.contains("Exclus&atilde;o n&atilde;o realizada."));
    }
}
