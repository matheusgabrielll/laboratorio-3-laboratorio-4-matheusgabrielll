import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Restaurante {

    private String nome;

    private String prato;

    private double PRECO;

    private ArrayList<Visita> livroDeVisitas;

    private HashSet<Cliente> clientesUnicos;

    private int totalVotos;

    public Restaurante(String nome, String prato) {

    }

    public void registrarVisita(Cliente cliente, String comentario) {

    }

    public void adicionaVoto() {

    }

    public boolean jaFoiVisitadoPor(String emailCliente) {

    }

    public String getNome() {

    }

    public String getPrato() {

    }

    public int getTotalVotos() {

    }

    public List<Visita> getLivroDeVisitas() {

    }

    public List<Cliente> getClientesUnicos() {

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Restaurante that = (Restaurante) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nome);
    }


}