import java.util.Objects;

public class Cliente {

    private String nome;

    private String email;

    private double totalGasto;

    private String pratoPreferido;

    public Cliente(String nome, String email) {

    }

    public void adicionarGasto(double valor) {

    }

    public String getNome() {

    }

    public String getEmail() {

    }

    public double getTotalGasto() {

    }

    public String getPratoPreferido() {

    }

    public void setPratoPreferido(String novoPrato) {

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