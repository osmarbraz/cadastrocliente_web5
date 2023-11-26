package controle.cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import entidade.Cliente;

class TestClienteAlterar {
    
    /**
     * Testa parametrizado do filtro para clienteid, nome e cpf em RAF inexistente.
     */
    @ParameterizedTest
    @CsvSource({
        "131, Teste, 11111111111, 131, Cliente Existente, 11111111111, Altera&ccedil;&atilde;o realizada com sucesso.",
        "131, Teste, 11111111111, 131, CPF Inválido, 111, CPF Inv&aacute;lido!",
        "131, Teste, 11111111111, 132, Cliente Não Existente, 11111111111, Altera&ccedil;&atilde;o n&atilde;o realizada."        
    })
    void testAlteracaoParametrizado(String clienteId, String nome, String CPF, String clienteIdAlterado, String nomeAlterado, String CPFAlterado, String resultadoEsperado) throws IOException, ServletException {
                
       // Dados da alteração        
        Cliente cliente = new Cliente(clienteId, nome, CPF);
        Cliente clienteAlterado = new Cliente(clienteIdAlterado, nomeAlterado, CPFAlterado);
        // Insere os dados para serem alterados
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

        //Parâmetros da alteração
        when(mockedRequest.getParameter("ClienteId")).thenReturn(clienteAlterado.getClienteId());
        when(mockedRequest.getParameter("Nome")).thenReturn(clienteAlterado.getNome());
        when(mockedRequest.getParameter("CPF")).thenReturn(clienteAlterado.getCpf());
        when(mockedRequest.getSession()).thenReturn(mockedSession);

        //Servlet Alteração
        ClienteAlterar clienteAlterar = new ClienteAlterar();
        clienteAlterar.doPost(mockedRequest, mockedResponse);

        //Resultado do servlet
        String resultado = stringWriter.toString();
        assertTrue(resultado.contains(resultadoEsperado));

        //Exclui os dados da consulta
        cliente.excluir();
     }
}
