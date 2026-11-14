import java.util.HashMap;
import java.util.List;

public class Sistema_PRW {

    private HashMap<String, Restaurante> restaurantes;
    private HashMap<String, Cliente> clientes;

    public Sistema_PRW() {
        this.restaurantes = new HashMap<>();
        this.clientes = new HashMap<>();
    }

    public boolean adicionarRestaurante(String nome, String prato) {
        if (restaurantes.containsKey(nome) || restaurantes.size() >= 50) {
            return false;
        } else {
            restaurantes.put(nome, new Restaurante(nome, prato));
            return true;
        }
    }

    public boolean adicionarCliente(String nome, String email) {
        if (clientes.containsKey(email) || clientes.size() >= 100) {
            return false;
        }
        clientes.put(email, new Cliente(nome, email));
        return true;
    }
//pode ter ou nao um comenatrio
    public void visitarRestaurante(String emailCliente, String nomeRestaurante) {
        visitarRestaurante(emailCliente,nomeRestaurante, "");
    }

    public void visitarRestaurante(String emailCliente, String nomeRestaurante, String comentario) {
        Cliente cliente = buscarCliente(emailCliente);
        Restaurante restaurante = buscarRestaurante(nomeRestaurante);

        restaurante.registrarVisita(cliente, comentario);
    }

    public double obtemGastoCliente(String emailCliente) {
        Cliente cliente = buscarCliente(emailCliente);
        return cliente.getTotalGasto();
    }

//essa parte tava dando erro e eu tava com difiultdade em resolver, usei ia apenas para consultar o erro
    public String[] listarClientesRestaurante(String nomeRestaurante) {
        Restaurante restaurante = buscarRestaurante(nomeRestaurante);
        List<Cliente> lista = restaurante.getClientesUnicos();
        String[] resultado = new String[lista.size()];

        for (int i = 0; i < lista.size(); i++) {
            Cliente c = lista.get(i);
            String texto = "Cliente: " + c.getNome();
            if (c.getPratoPreferido().equals(restaurante.getPrato())) {
                texto += ", Prato preferido da casa";
            }
            resultado[i] = texto;
        }
        return resultado;
    }
// um colega me disse que a professora livia disse "essa parte vai ser que nem o BBB, voto livre"
    public String votarPratoPreferido(String emailCliente, String nomeRestaurante) {
        Cliente cliente = buscarCliente(emailCliente);
        Restaurante restaurante = buscarRestaurante(nomeRestaurante);

        if (!restaurante.jaFoiVisitadoPor(emailCliente)) {
            return "Voto não registrado";
        }

        restaurante.adicionaVoto();
        cliente.setPratoPreferido(restaurante.getPrato());
        return "Voto registrado com sucesso!";
    }

    public String melhorPrato() {
        Restaurante melhor = null;

        for (Restaurante r : restaurantes.values()) {
            if (melhor == null || r.getTotalVotos() > melhor.getTotalVotos()) {
                melhor = r;
            }
        }

        if (melhor == null) {
            return "Nenhum restaurante cadastrado";
        }
        return "Melhor prato: " + melhor.getPrato() + " Restaurante: " + melhor.getNome();
    }

    public String[] listarLivroVisitas(String nomeRestaurante) {
        Restaurante restaurante = buscarRestaurante(nomeRestaurante);
        List<Visita> visitas = restaurante.getLivroDeVisitas();
        String[] resultado = new String[visitas.size()];

        for (int i = 0; i < visitas.size(); i++) {
            resultado[i] = visitas.get(i).toString();
        }
        return resultado;
    }

    // essa parte do livro de visitas tava dando muito erro, usei ia para me """"ajudar"""" a ajeitar
    public String[] listarLivroVisitas(String nomeRestaurante, int num_recentes) {
        if (num_recentes < 0) {
            throw new IllegalArgumentException("Quantidade inválida");
        }

        Restaurante restaurante = buscarRestaurante(nomeRestaurante);
        List<Visita> visitas = restaurante.getLivroDeVisitas();

        if (num_recentes > visitas.size()) {
            num_recentes = visitas.size();
        }

        String[] resultado = new String[num_recentes];
        int inicio = visitas.size() - num_recentes;

        for (int i = 0; i < num_recentes; i++) {
            resultado[i] = visitas.get(inicio + i).toString();
        }
        return resultado;
    }

//ajuda de leve
    private Cliente buscarCliente(String email) {
        Cliente cliente = clientes.get(email);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        return cliente;
    }


    private Restaurante buscarRestaurante(String nome) {
        Restaurante restaurante = restaurantes.get(nome);
        if (restaurante == null) {
            throw new IllegalArgumentException("Restaurante não encontrado");
        }

        return restaurante;
    }

}