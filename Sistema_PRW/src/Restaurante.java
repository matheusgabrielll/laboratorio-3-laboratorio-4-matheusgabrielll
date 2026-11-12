import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Restaurante {

    private String nome;

    private String prato;

    private double PRECO = 89.90;

    private ArrayList<Visita> livroDeVisitas;

    private HashSet<Cliente> clientesUnicos;

    private int totalVotos;

    public Restaurante(String nome, String prato) {
        this.nome = nome;
        this.prato = prato;
        this.livroDeVisitas = new ArrayList<>();
        this.clientesUnicos = new HashSet<>();
        this.totalVotos = 0;
    }

    public void registrarVisita(Cliente cliente, String comentario) {
        clientesUnicos.add(cliente);
        livroDeVisitas.add(new Visita(cliente.getNome(), comentario));
        cliente.adicionarGasto(PRECO);
    }

    public void adicionaVoto() {
        totalVotos++;

    }

    public boolean jaFoiVisitadoPor(String emailCliente) {
        for(Cliente c : clientesUnicos) {
            if (c.getEmail().equals(emailCliente)) {
                return true;
            }
        }
        return false;
    }

    public String getNome() {
        return nome;
    }

    public String getPrato() {
        return prato;
    }

    public int getTotalVotos() {
        return totalVotos;
    }

    public List<Visita> getLivroDeVisitas() {
        return new ArrayList<>(livroDeVisitas);
    }

    public List<Cliente> getClientesUnicos() {
        return new ArrayList<>(clientesUnicos);
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