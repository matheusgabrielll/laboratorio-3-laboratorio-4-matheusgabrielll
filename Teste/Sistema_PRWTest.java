import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Sistema_PRWTest {

    private Sistema_PRW sistema;

    @BeforeEach
    void preparaSistema() {
        this.sistema = new Sistema_PRW();
        this.sistema.adicionarRestaurante("Sabor", "Feijoada");
        this.sistema.adicionarRestaurante("Cantina", "Pizza");
        this.sistema.adicionarCliente("matheus", "matheus@gmail");
        this.sistema.adicionarCliente("ana", "ana@gmail");
    }

    @Test
    void testAdicionarRestaurante() {
        assertTrue(this.sistema.adicionarRestaurante("Padaria", "Tapioca"), "Esperando restaurante adicionado");
        assertFalse(this.sistema.adicionarRestaurante("Sabor", "Pizza"), "Esperando false para nome repetido");
    }

    @Test
    void testAdicionarCliente() {
        assertTrue(this.sistema.adicionarCliente("joao", "joao@gmail"), "Esperando cliente adicionado");
        assertFalse(this.sistema.adicionarCliente("outro", "matheus@gmail"), "Esperando false para email repetido");
    }

    @Test
    void testVisitarRestauranteSemComentario() {
        this.sistema.visitarRestaurante("matheus@gmail", "Sabor");
        assertEquals(89.90, this.sistema.obtemGastoCliente("matheus@gmail"), 0.001, "Esperando gasto de 89.90");
        assertFalse(this.sistema.listarLivroVisitas("Sabor")[0].contains("Comentário"),
                "Esperando visita sem comentário");
    }

    @Test
    void testVisitarRestauranteComComentario() {
        this.sistema.visitarRestaurante("matheus@gmail", "Sabor", "Comida boa");
        String[] livro = this.sistema.listarLivroVisitas("Sabor");
        assertTrue(livro[0].contains("matheus"), "Esperando o nome matheus na visita");
        assertTrue(livro[0].contains("Comida boa"), "Esperando o comentário na visita");
    }

    @Test
    void testVisitarMesmoRestauranteDuasVezes() {
        this.sistema.visitarRestaurante("matheus@gmail", "Sabor");
        this.sistema.visitarRestaurante("matheus@gmail", "Sabor");
        assertEquals(179.80, this.sistema.obtemGastoCliente("matheus@gmail"), 0.001, "Esperando gasto de 179.80");
        assertEquals(1, this.sistema.listarClientesRestaurante("Sabor").length, "Esperando 1 cliente só");
        assertEquals(2, this.sistema.listarLivroVisitas("Sabor").length, "Esperando 2 visitas no livro");
    }

    @Test
    void testVisitarInexistentes() {
        assertThrows(IllegalArgumentException.class, () -> this.sistema.visitarRestaurante("joao@gmail", "Sabor"),
                "Esperando exceção para cliente inexistente");
        assertThrows(IllegalArgumentException.class, () -> this.sistema.visitarRestaurante("matheus@gmail", "Padaria"),
                "Esperando exceção para restaurante inexistente");
    }

    @Test
    void testListarClientesRestaurante() {
        this.sistema.visitarRestaurante("matheus@gmail", "Sabor");
        assertEquals("Cliente: matheus", this.sistema.listarClientesRestaurante("Sabor")[0],
                "Esperando só o nome do cliente");
        this.sistema.votarPratoPreferido("matheus@gmail", "Sabor");
        assertEquals("Cliente: matheus, Prato preferido da casa", this.sistema.listarClientesRestaurante("Sabor")[0],
                "Esperando a mensagem de prato da casa");
    }

    @Test
    void testVotarSemTerVisitado() {
        assertEquals("Voto não registrado", this.sistema.votarPratoPreferido("matheus@gmail", "Sabor"),
                "Esperando voto não registrado");
    }

    @Test
    void testVotarVariasVezes() {
        this.sistema.visitarRestaurante("matheus@gmail", "Sabor");
        this.sistema.visitarRestaurante("ana@gmail", "Cantina");
        assertEquals("Voto registrado com sucesso!", this.sistema.votarPratoPreferido("matheus@gmail", "Sabor"),
                "Esperando primeiro voto registrado");
        assertEquals("Voto registrado com sucesso!", this.sistema.votarPratoPreferido("matheus@gmail", "Sabor"),
                "Esperando segundo voto registrado");
        this.sistema.votarPratoPreferido("ana@gmail", "Cantina");
        assertEquals("Melhor prato: Feijoada Restaurante: Sabor", this.sistema.melhorPrato(),
                "Esperando Sabor com 2 votos na frente");
    }

    @Test
    void testVotarInexistentes() {
        assertThrows(IllegalArgumentException.class, () -> this.sistema.votarPratoPreferido("joao@gmail", "Sabor"),
                "Esperando exceção para cliente inexistente");
        assertThrows(IllegalArgumentException.class, () -> this.sistema.votarPratoPreferido("matheus@gmail", "Padaria"),
                "Esperando exceção para restaurante inexistente");
    }

    @Test
    void testMelhorPratoSemRestaurantes() {
        Sistema_PRW vazio = new Sistema_PRW();
        assertEquals("Nenhum restaurante cadastrado", vazio.melhorPrato(), "Esperando nenhum restaurante cadastrado");
    }

    @Test
    void testListarLivroVisitasEmOrdem() {
        this.sistema.visitarRestaurante("matheus@gmail", "Sabor");
        this.sistema.visitarRestaurante("ana@gmail", "Sabor");
        String[] livro = this.sistema.listarLivroVisitas("Sabor");
        assertEquals(2, livro.length, "Esperando 2 visitas");
        assertTrue(livro[0].contains("matheus"), "Esperando matheus primeiro");
        assertTrue(livro[1].contains("ana"), "Esperando ana depois");
    }

    @Test
    void testListarLivroVisitasRecentes() {
        this.sistema.visitarRestaurante("matheus@gmail", "Sabor", "primeira");
        this.sistema.visitarRestaurante("ana@gmail", "Sabor", "segunda");
        this.sistema.visitarRestaurante("matheus@gmail", "Sabor", "terceira");
        String[] livro = this.sistema.listarLivroVisitas("Sabor", 2);
        assertEquals(2, livro.length, "Esperando 2 visitas");
        assertTrue(livro[0].contains("segunda"), "Esperando a segunda visita primeiro");
        assertTrue(livro[1].contains("terceira"), "Esperando a terceira visita depois");
        assertEquals(3, this.sistema.listarLivroVisitas("Sabor", 10).length, "Esperando todas as 3 visitas");
    }

    @Test
    void testListarLivroVisitasRecentesNegativo() {
        assertThrows(IllegalArgumentException.class, () -> this.sistema.listarLivroVisitas("Sabor", -1),
                "Esperando exceção para quantidade negativa");
    }
}
