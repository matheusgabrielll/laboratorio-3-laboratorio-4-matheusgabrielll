import java.time.LocalDate;

/**
 * Representa uma visita de um cliente a um restaurante.
 * Cada visita guarda a data em que aconteceu, o nome do cliente
 * e o comentário que ele deixou (se deixou algum).
 */

public class Visita {

    /** Data da visita, guardada como texto (a data do dia em que a visita foi criada).
     */
    private String data;

    /** Nome do cliente que fez a visita.
     */
    private String nomeCliente;

    /** Comentário que o cliente deixou sobre a visita (pode ser vazio).
     */
    private String comentario;

    /**
     * Construtor da visita.
     * Guarda o nome do cliente e o comentário, e define a data
     * automaticamente como a data de hoje.
     *
     * @param nomeCliente nome do cliente que visitou
     * @param comentario  comentário que o cliente deixou (pode ser vazio)
     */

    public Visita(String nomeCliente, String comentario) {
        this.comentario = comentario;
        this.data = LocalDate.now().toString();
        this.nomeCliente = nomeCliente;
    }

    /**
     * Retorna a data da visita.
     *
     * @return data da visita em formato de texto
     */

    public String getData() {
        return data;
    }

    /**
     * Retorna o nome do cliente que fez a visita.
     *
     * @return nome do cliente
     */
    public String getNomeCliente() {
        return nomeCliente;
    }

    /**
     * Retorna o comentário deixado na visita.
     *
     * @return comentário da visita
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Transforma a visita em texto.
     * O texto mostra a data e o nome do cliente. O comentário só aparece
     * se ele existir e não estiver vazio.
     *
     * @return texto com as informações da visita
     */

    public String toString() {
        String texto = "Data: " + data + ", Cliente: " + nomeCliente;

        if (comentario != null && !comentario.isEmpty()) {
            texto += ", Comentário: " + comentario;
        }
        return texto;
    }
}