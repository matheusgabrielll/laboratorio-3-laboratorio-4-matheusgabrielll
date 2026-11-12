import java.time.LocalDate;

public class Visita {

    private String data;

    private String nomeCliente;

    private String comentario;

    public Visita(String nomeCliente, String comentario) {
        this.comentario = comentario;
        this.data = LocalDate.now().toString();
        this.nomeCliente = nomeCliente;
    }

    public String getData() {
        return data;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getComentario() {
        return comentario;
    }

    public String toString() {
        String texto = "Data: " + data + ", Cliente: " + nomeCliente;
        if (comentario != null && !comentario.isEmpty()) {
            texto += ", Comentário: " + comentario;
        }
        return texto;
    }

}