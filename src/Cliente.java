import java.util.Objects;

/**
 * Representa um cliente do sistema.
 * Cada cliente tem nome, email, o total que já gastou nos restaurantes
 * e o prato que escolheu como preferido.
 */
public class Cliente {

    /** Nome do cliente. */
    private String nome;

    /** Email do cliente (é o que identifica cada cliente no sistema). */
    private String email;

    /** Soma de tudo que o cliente já gastou nas visitas. */
    private double totalGasto;

    /** Prato preferido do cliente, definido quando ele vota em um restaurante. */
    private String pratoPreferido;

    /**
     * Construtor do cliente.
     * Guarda o nome e o email, começa com gasto zero e com o prato
     * preferido ainda não definido.
     *
     * @param nome  nome do cliente
     * @param email email do cliente
     */
    public Cliente(String nome, String email) {
        this.email = email;
        this.nome = nome;
        this.pratoPreferido = "Prato preferido não definido";
        this.totalGasto =0;
    }

    /**
     * Soma um valor ao total gasto pelo cliente.
     *
     * @param valor valor que será somado ao gasto
     */
    public void adicionarGasto(double valor) {
        this.totalGasto += valor;
    }

    /**
     * Retorna o nome do cliente.
     *
     * @return nome do cliente
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o email do cliente.
     *
     * @return email do cliente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retorna quanto o cliente ja gastou no total.
     *
     * @return total gasto pelo cliente
     */
    public double getTotalGasto() {
        return totalGasto;
    }

    /**
     * Retorna o prato preferido do cliente.
     * Se o cliente ainda não votou em nenhum prato, retorna
     * "Prato preferido não definido".
     *
     * @return prato preferido do cliente
     */
    public String getPratoPreferido() {
        return pratoPreferido;
    }

    /**
     * Muda o prato preferido do cliente.
     *
     * @param novoPrato nome do novo prato preferido
     */
    public void setPratoPreferido(String novoPrato) {
        this.pratoPreferido = novoPrato;
    }

    /**
     * Compara este cliente com outro objeto.
     * Dois clientes são considerados iguais se tiverem o mesmo email.
     *
     *
     * @param o objeto que será comparado com este cliente
     * @return true se for um cliente com o mesmo email, false caso contrário
     */

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(email, cliente.email);
    }

    /**
     * Gera o código hash do cliente, calculado a partir do email.
     * Combina com o equals: clientes com o mesmo email têm o mesmo hash.
     *
     * @return código hash do cliente
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}