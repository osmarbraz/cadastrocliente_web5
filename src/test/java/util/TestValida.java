package util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestValida {

    Valida valida = null;

    /**
     * Instância uma classe de validação.
     */
    @BeforeAll
    void inicializa() {
        valida = new Valida();
    }

    /**
     * Testa CPF válido.
     */
    @Test
    void testValidaCPFValido1() {
        assertTrue(valida.validaCPF("11111111111"));
    }

    /**
     * Testa CPF válido.
     */
    @Test
    void testValidaCPFValido2() {
        assertTrue(valida.validaCPF("84807125206"));
    }

    /**
     * Testa CPF válido.
     */
    @Test
    void testValidaCPFValido3() {
        assertTrue(valida.validaCPF("63883136395"));
    }

    /**
     * Testa CPF válido.
     */
    @Test
    void testValidaCPFValido4() {
        assertTrue(valida.validaCPF("31626333033"));
    }

    /**
     * Testa CPF inválido final 1.
     */
    @Test
    void testValidaCPFInvalido1() {
        assertFalse(valida.validaCPF("94622036011"));
    }

    /**
     * Testa CPF inválido final 2.
     */
    @Test
    void testValidaCPFInvalido2() {
        assertFalse(valida.validaCPF("94622036012"));
    }

    /**
     * Testa CPF com problema na conversão.
     */
    @Test
    void testValidaCPFInvalido3() {
        //CPF com problema na conversão
        assertFalse(valida.validaCPF("0065XAB22050"));
    }

    /**
     * Testa CPF com problema na quantidade de caracteres.
     */
    @Test
    void testValidaCPFInvalidoCurto() {
        //CPF com problema na conversão
        assertFalse(valida.validaCPF("111111"));
    }

    /**
     * Testa o CPF com o primeiro digito zero.
     */
    @Test
    void testCPFValidoComPrimeiroDigitoZero() {
        //CPF com o primeiro digito zero.
        assertTrue(valida.validaCPF("11144477735"));
    }

    /**
     * Testa CPF com 11 caracteres, mas com dígitos verificadores incorretos.
     */
    @Test
    void testCPFComDigitosVerificadoresInvalidos() {
        //CPF com 11 caracteres, mas com dígitos verificadores incorretos.
        assertFalse(valida.validaCPF("52998224700"));
    }

    /**
     * Testa CPF com menos de 11 caracteres.
     */
    @Test
    void testCPFComTamanhoMenorQueOnze() {
        // CPF com menos de 11 caracteres.
        assertFalse(valida.validaCPF("123456789"));
    }

    /**
     * Testa CPF com mais de 11 caracteres.
     */
    @Test
    public void testCPFComTamanhoMaiorQueOnze() {
        // CPF com mais de 11 caracteres.
        assertFalse(valida.validaCPF("529982247250"));
    }

    /**
     * Testa CPF com cálculo do primeiro dígito resulta em zero.
     */
    @Test
    public void testCPFComPrimeiroDigitoVerificadorZero() {
        // O cálculo do primeiro dígito resulta em zero.
        assertFalse(valida.validaCPF("00000000100"));
    }

    /**
     * Testa CPF com o primeiro dígito correto e segundo dígito incorreto.
     */
    @Test
    public void testCPFComSegundoDigitoVerificadorIncorreto() {
        // Primeiro dígito correto e segundo dígito incorreto.
        assertFalse(valida.validaCPF("52998224726"));
    }

    /**
     * Testa CPF com o primeiro dígito incorreto.
     */
    @Test
    public void testCPFComPrimeiroDigitoVerificadorIncorreto() {
        // Primeiro dígito incorreto.
        assertFalse(valida.validaCPF("52998224715"));
    }

    /**
     * Finaliza a classe de validação.
     */
    @AfterAll
    void finaliza() {
        valida = null;
    }
}
