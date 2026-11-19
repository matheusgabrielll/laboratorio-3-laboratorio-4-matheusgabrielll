import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * Representa um restaurante do sistema.
 * Cada restaurante tem um nome, um prato principal, um livro de visitas,
 * a lista de clientes que já passaram por lá e a contagem de votos que recebeu.
 */
public class Restaurante {

    /** Nome do restaurante.
     */
    private String nome;

    /** Prato princpal do restaurante.
     */
    private String prato;

    /** Preço cobrado de cada cliente a cada visita.
     */
    private double PRECO = 89.90;

    /** Lista com todas as visitas feitas ao restaurante, na ordem em que aconteceram.
     */
    private ArrayList<Visita> livroDeVisitas;

    /** Conjunto de clientes que já visitaram o restaurante (sem repetir cliente).
     */
    private HashSet<Cliente> clientesUnicos;

    /** Quantidade de votos que o prato do restaurante ja recebeu.
     */
    private int totalVotos;

    private HashMap<String, Integer> visitasPorCliente;

    /**
     * Construtor do restaurante.
     * Define o nome e o prato, e começa com o livro de visitas e
     * a lista de clientes vazios, e com zero votos.
     *
     * @param nome  nome do restaurante
     * @param prato prato principal do restaurante
     */
    public Restaurante(String nome, String prato) {
        this.nome = nome;
        this.prato = prato;
        this.livroDeVisitas = new ArrayList<>();
        this.clientesUnicos = new HashSet<>();
        this.totalVotos = 0;
        this.visitasPorCliente = new HashMap<>();
    }

    /**
     * Registra uma visita de um cliente ao restaurante.
     * O cliente é guardado na lista de clientes únicos, a visita é
     * anotada no livro de visitas e o preço do prato é somado ao gasto do cliente.
     *
     * @param cliente    cliente que visitou o restaurante
     * @param comentario comentário que o cliente deixou (pode ser vazio)
     */
    public void registrarVisita(Cliente cliente, String comentario) {
        clientesUnicos.add(cliente);
        livroDeVisitas.add(new Visita(cliente.getNome(), comentario));
        cliente.adicionarGasto(PRECO);
        visitasPorCliente.put(cliente.getEmail(), getVisitasDoCliente(cliente.getEmail()) + 1);
    }

    /**
     * Soma um voto ao total de votos do restaurante.
     */
    public void adicionaVoto() {
        totalVotos++;

    }

    /**
     * Verifica se um cliente já visitou o restaurante.
     * Percorre os clientes únicos comparando o email de cada um.
     *
     * @param emailCliente email do cliente que se quer verificar
     * @return true se o cliente já visitou o restaurante, false caso contrário
     */
    public boolean jaFoiVisitadoPor(String emailCliente) {
        for(Cliente c : clientesUnicos) {
            if (c.getEmail().equals(emailCliente)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna o nome do restaurante.
     *
     * @return nome do restaurante
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o prato principal do restaurante.
     *
     * @return prato do restaurante
     */
    public String getPrato() {
        return prato;
    }

    /**
     * Retorna quantos votos o restaurante já recebeu.
     *
     * @return total de votos
     */
    public int getTotalVotos() {
        return totalVotos;
    }

    public int getTotalVisitas() {
        return livroDeVisitas.size();
    }

    public int getTotalClientes() {
        return clientesUnicos.size();
    }

    public double getReceita() {
        return livroDeVisitas.size() * PRECO;
    }

    public int getVisitasDoCliente(String emailCliente) {
        return visitasPorCliente.getOrDefault(emailCliente, 0);
    }

    /**
     * Retorna uma cópia da lista de visitas do restaurante.
     * Por ser uma cópia, mexer na lista retornada não altera o livro original.
     *
     * @return lista com as visitas do restaurante
     */
    public List<Visita> getLivroDeVisitas() {
        return new ArrayList<>(livroDeVisitas);
    }

    /**
     * Retorna uma cópia da lista de clientes que ja visitaram o restaurante.
     * Por ser uma cópia, mexer na lista retornada não altera os dados originais.
     *
     * @return lista com os clientes únicos do restaurante
     */
    public List<Cliente> getClientesUnicos() {
        return new ArrayList<>(clientesUnicos);
    }

    /**
     * Compara esse restaurante com outro objeto.
     * Dois restaurantes são considerados iguais se tiverem o mesmo nome.
     *
     * @param o objeto que será comparado com este restaurante
     * @return true se for um restaurante com o mesmo nome, false caso contrário
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Restaurante that = (Restaurante) o;
        return Objects.equals(nome, that.nome);
    }

    /**
     * Gera o codigo hash do restaurante, calculado a partir do nome.
     * Combina com o equals: restaurantes com o mesmo nome tem o mesmo hash.
     *
     * @return código hash do restaurante
     */

    @Override
    public int hashCode() {
        return Objects.hashCode(nome);
    }
}