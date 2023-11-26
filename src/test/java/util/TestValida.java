package util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestValida {

    private Valida valida = null;

    /**
     * Instância uma classe de validação.
     */
    @BeforeAll
    public void inicializa() {
        valida = new Valida();
    }

    /**
     * Testa CPF válido.
     */
    @Test
    public void testValidaCPFValido1() {
        assertTrue(valida.validaCPF("11111111111"));
    }

    /**
     * Testa CPF válido.
     */
    @Test
    public void testValidaCPFValido2() {
        assertTrue(valida.validaCPF("84807125206"));
    }

    /**
     * Testa CPF válido.
     */
    @Test
    public void testValidaCPFValido3() {
        assertTrue(valida.validaCPF("63883136395"));
    }

    /**
     * Testa CPF válido.
     */
    @Test
    public void testValidaCPFValido4() {
        assertTrue(valida.validaCPF("31626333033"));
    }

    /**
     * Testa CPF inválido final 1.
     */
    @Test
    public void testValidaCPFInvalido1() {
        assertFalse(valida.validaCPF("94622036011"));
    }
    
    /**
     * Testa CPF inválido final 2.
     */
    @Test
    public void testValidaCPFInvalido2() {
        assertFalse(valida.validaCPF("94622036012"));
    }

    /**
     * Testa CPF com problema na conversão.
     */
    @Test
    public void testValidaCPFInvalido3() {
        //CPF com problema na conversão
        assertFalse(valida.validaCPF("0065XAB22050"));
    }

    /**
     * Testa CPF com problema na quantidade de caracteres.
     */
    @Test
    public void testValidaCPFInvalidoCurto() {
        //CPF com problema na conversão
        assertFalse(valida.validaCPF("111111"));
    }

    /**
     * Finaliza a classe de validação.
     */
    @AfterAll
    public void finaliza() {
        valida = null;
    }
}
