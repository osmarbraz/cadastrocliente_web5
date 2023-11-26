package dao.cliente;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestRAFRegistroCliente {

    @Test
    public void RAFRegistroCliente() {
        RAFRegistroCliente instancia = new RAFRegistroCliente("0", "", "");
        assertTrue("0".equals(instancia.getClienteId()) && "".equals(instancia.getNome()) && "".equals(instancia.getCpf()));
    }
}
