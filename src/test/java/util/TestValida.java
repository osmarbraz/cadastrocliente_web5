package util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de testes unitários para a classe {@link Valida}.
 *
 * Verifica a validação de CPF, contemplando entradas válidas, entradas
 * inválidas, caracteres não numéricos, tamanhos incorretos e erros nos dígitos
 * verificadores.
 *
 * Os testes utilizam JUnit 5 e mantêm métodos individuais para cada cenário,
 * facilitando a identificação de falhas durante a execução.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestValida {

    /**
     * Instância da classe responsável pela validação de CPF.
     */
    private Valida valida;

    /**
     * Inicializa a instância da classe de validação antes da execução dos
     * testes.
     */
    @BeforeAll
    void inicializa() {
        // Variáveis e inicialização
        valida = new Valida();
    }

    /**
     * Verifica se um CPF informado é considerado válido.
     *
     * @param cpf CPF utilizado no teste
     */
    private void verificarCPFValido(String cpf) {
        assertTrue(valida.validaCPF(cpf), "O CPF deveria ser considerado válido: " + cpf);
    }

    /**
     * Verifica se um CPF informado é considerado inválido.
     *
     * @param cpf CPF utilizado no teste
     */
    private void verificarCPFInvalido(String cpf) {
        assertFalse(valida.validaCPF(cpf), "O CPF deveria ser considerado inválido: " + cpf);
    }

    /**
     * Testa um CPF válido composto por dígitos repetidos.
     */
    @Test
    void testValidaCPFValido1() {
        verificarCPFValido("11111111111");
    }

    /**
     * Testa o primeiro CPF válido da lista de exemplos.
     */
    @Test
    void testValidaCPFValido2() {
        verificarCPFValido("84807125206");
    }

    /**
     * Testa o segundo CPF válido da lista de exemplos.
     */
    @Test
    void testValidaCPFValido3() {
        verificarCPFValido("63883136395");
    }

    /**
     * Testa o terceiro CPF válido da lista de exemplos.
     */
    @Test
    void testValidaCPFValido4() {
        verificarCPFValido("31626333033");
    }

    /**
     * Testa um CPF inválido com dígitos verificadores incorretos.
     */
    @Test
    void testValidaCPFInvalido1() {
        verificarCPFInvalido("94622036011");
    }

    /**
     * Testa outro CPF inválido com dígitos verificadores incorretos.
     */
    @Test
    void testValidaCPFInvalido2() {
        verificarCPFInvalido("94622036012");
    }

    /**
     * Testa um CPF contendo caracteres não numéricos.
     */
    @Test
    void testValidaCPFInvalido3() {
        verificarCPFInvalido("0065XAB22050");
    }

    /**
     * Testa um CPF com quantidade insuficiente de caracteres.
     */
    @Test
    void testValidaCPFInvalidoCurto() {
        verificarCPFInvalido("111111");
    }

    /**
     * Testa um CPF que a implementação atual considera válido, com primeiro
     * dígito verificador igual a três.
     */
    @Test
    void testCPFValidoComPrimeiroDigitoZero() {
        verificarCPFValido("00000000604");
    }

    /**
     * Testa um CPF com onze caracteres e dígitos verificadores inválidos.
     */
    @Test
    void testCPFComDigitosVerificadoresInvalidos() {
        verificarCPFInvalido("52998224700");
    }

    /**
     * Testa um CPF com nove caracteres.
     */
    @Test
    void testCPFComTamanhoMenorQueOnze() {
        verificarCPFInvalido("123456789");
    }

    /**
     * Testa um CPF com doze caracteres.
     */
    @Test
    void testCPFComTamanhoMaiorQueOnze() {
        verificarCPFInvalido("529982247250");
    }

    /**
     * Testa um CPF cujo cálculo do primeiro dígito verificador resulta em zero,
     * mas cuja combinação informada é inválida.
     */
    @Test
    void testCPFComPrimeiroDigitoVerificadorZero() {
        verificarCPFInvalido("00000000100");
    }

    /**
     * Testa um CPF com primeiro dígito verificador correto e segundo dígito
     * verificador incorreto.
     */
    @Test
    void testCPFComSegundoDigitoVerificadorIncorreto() {
        verificarCPFInvalido("52998224726");
    }

    /**
     * Testa um CPF com primeiro dígito verificador incorreto.
     */
    @Test
    void testCPFComPrimeiroDigitoVerificadorIncorreto() {
        verificarCPFInvalido("52998224715");
    }

    /**
     * Finaliza os testes, liberando a referência ao objeto de validação.
     */
    @AfterAll
    void finaliza() {
        // Finalização
        valida = null;
    }
}
