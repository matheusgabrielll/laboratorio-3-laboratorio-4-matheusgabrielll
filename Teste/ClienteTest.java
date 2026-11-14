import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    void preparaCliente() {
        this.cliente = new Cliente("matheus", "matheus@gmail");
    }

    @Test
    void testAdicionarGasto() {
        this.cliente.adicionarGasto(89.90);
        assertEquals(89.90, this.cliente.getTotalGasto(), 0.001, "Esperando gasto de 89.90");
    }

    @Test
    void testAdicionarGastoVariasVezes() {
        this.cliente.adicionarGasto(89.90);
        this.cliente.adicionarGasto(89.90);
        this.cliente.adicionarGasto(89.90);
        assertEquals(269.70, this.cliente.getTotalGasto(), 0.001, "Esperando gasto de 269.70");
    }

    @Test
    void testSetPratoPreferido() {
        this.cliente.setPratoPreferido("Feijoada");
        assertEquals("Feijoada", this.cliente.getPratoPreferido(), "Esperando prato Feijoada");
    }

    @Test
    void testMudarPratoPreferidoVariasVezes() {
        this.cliente.setPratoPreferido("Feijoada");
        this.cliente.setPratoPreferido("Pizza");
        assertEquals("Pizza", this.cliente.getPratoPreferido(), "Esperando prato Pizza");
    }

    @Test
    void testEqualsMesmoObjeto() {
        assertTrue(this.cliente.equals(this.cliente), "Esperando cliente igual a ele mesmo");
    }

    @Test
    void testEqualsMesmoEmail() {
        Cliente outro = new Cliente("ana", "matheus@gmail");
        assertTrue(this.cliente.equals(outro), "Esperando clientes iguais com o mesmo email");
    }

    @Test
    void testEqualsEmailDiferente() {
        Cliente outro = new Cliente("matheus", "ana@gmail");
        assertFalse(this.cliente.equals(outro), "Esperando clientes diferentes com email diferente");
    }

    @Test
    void testEqualsNull() {
        assertFalse(this.cliente.equals(null), "Esperando cliente diferente de null");
    }

    @Test
    void testEqualsOutroTipo() {
        assertFalse(this.cliente.equals("matheus@gmail"), "Esperando cliente diferente de uma String");
    }

    @Test
    void testHashCodeMesmoEmail() {
        Cliente outro = new Cliente("ana", "matheus@gmail");
        assertEquals(this.cliente.hashCode(), outro.hashCode(), "Esperando o mesmo hashCode");
    }
}