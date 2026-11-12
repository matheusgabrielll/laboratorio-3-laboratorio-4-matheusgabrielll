import java.util.Objects;

public class Cliente {

    private String nome;

    private String email;

    private double totalGasto;

    private String pratoPreferido;

    public Cliente(String nome, String email) {
        this.email = email;
        this.nome = nome;
        this.pratoPreferido = "Prato preferido não definido";
        this.totalGasto =0;
    }

    public void adicionarGasto(double valor) {
        this.totalGasto += valor;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public double getTotalGasto() {
        return totalGasto;
    }

    public String getPratoPreferido() {
        return pratoPreferido;
    }

    public void setPratoPreferido(String novoPrato) {
        this.pratoPreferido = novoPrato;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(email, cliente.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

}