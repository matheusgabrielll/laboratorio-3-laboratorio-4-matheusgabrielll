import java.util.HashMap;
import java.util.List;

/**
 * Classe principal do sistema de restaurantes.
 * Ela guarda os restaurantes e os clientes cadastrados e permite
 * registrar visitas, votar no prato preferido e consultar informações.
 */
public class Sistema_PRW {

    /** Guarda os restaurantes cadastrados. A chave é o nome do restaurante. */
    private HashMap<String, Restaurante> restaurantes;

    /** Guarda os clientes cadastrados. A chave é o email do cliente. */
    private HashMap<String, Cliente> clientes;


    /**
     * Construtor do sistema.
     * Cria os dois mapas (restaurantes e clientes) vazios.
     */
    public Sistema_PRW() {
        this.restaurantes = new HashMap<>();
        this.clientes = new HashMap<>();
    }

    /**
     * Adiciona um novo restaurante no sistema.
     * Não adiciona se ja existir um restaurante com o mesmo nome
     * ou se ja tiver 50 restaurantes cadastrados.
     *
     * @param nome  nome do restaurante
     * @param prato prato principal do restaurante
     * @return true se o restaurante foi adicionado, false caso contrário
     */
    public boolean adicionarRestaurante(String nome, String prato) {
        if (restaurantes.containsKey(nome) || restaurantes.size() >= 50) {
            return false;
        } else {
            restaurantes.put(nome, new Restaurante(nome, prato));
            return true;
        }
    }


    /**
     * Adiciona um novo cliente no sistema.
     * Não adiciona se ja existir um cliente com o mesmo email
     * ou se já tiver 100 clientes cadastrados.
     *
     * @param nome  nome do cliente
     * @param email email do cliente (usado como identificador)
     * @return true se o cliente foi adicionado, false caso contrário
     */

    public boolean adicionarCliente(String nome, String email) {
        if (clientes.containsKey(email) || clientes.size() >= 100) {
            return false;
        }
        clientes.put(email, new Cliente(nome, email));
        return true;
    }
//pode ter ou nao um comenatrio
    /**
     * Registra a visita de um cliente a um restaurante, sem comentário.
     * Chama a outra versão do metodo passando um comentário vazio.
     *
     * @param emailCliente    email do cliente que visitou
     * @param nomeRestaurante nome do restaurante visitado
     */
    public void visitarRestaurante(String emailCliente, String nomeRestaurante) {
        visitarRestaurante(emailCliente,nomeRestaurante, "");
    }

    /**
     * Registra a visita de um cliente a um restaurante, com comentário.
     *
     * @param emailCliente    email do cliente que visitou
     * @param nomeRestaurante nome do restaurante visitado
     * @param comentario      comentário que o cliente deixou sobre a visita
     * @throws IllegalArgumentException se o cliente ou o restaurante não existirem
     */
    public void visitarRestaurante(String emailCliente, String nomeRestaurante, String comentario) {
        Cliente cliente = buscarCliente(emailCliente);
        Restaurante restaurante = buscarRestaurante(nomeRestaurante);

        restaurante.registrarVisita(cliente, comentario);
    }

    /**
     * Retorna quanto um cliente já gastou no total.
     *
     * @param emailCliente email do cliente
     * @return o valor total gasto pelo cliente
     * @throws IllegalArgumentException se o cliente não existir
     */

    public double obtemGastoCliente(String emailCliente) {
        Cliente cliente = buscarCliente(emailCliente);
        return cliente.getTotalGasto();
    }

    /**
     * Lista os clientes (sem repetição) que visitaram um restaurante.
     * Se o prato preferido do cliente for o prato do restaurante,
     * acrescenta o texto "Prato preferido da casa".
     *
     * @param nomeRestaurante nome do restaurante
     * @return vetor de textos, um para cada cliente
     * @throws IllegalArgumentException se o restaurante não existir
     */
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
    /**
     * Registra o voto de um cliente no prato de um restaurante.
     * O voto só vale se o cliente já tiver visitado o restaurante.
     * Se o voto for aceito, o prato do restaurante vira o prato preferido do cliente.
     *
     * @param emailCliente    email do cliente que está votando
     * @param nomeRestaurante nome do restaurante votado
     * @return "Voto registrado com sucesso!" se deu certo,
     *         ou "Voto não registrado" se o cliente nunca visitou o restaurante
     * @throws IllegalArgumentException se o cliente ou o restaurante não existirem
     */
    public String votarPratoPreferido(String emailCliente, String nomeRestaurante) {
        Cliente cliente = buscarCliente(emailCliente);
        Restaurante restaurante = buscarRestaurante(nomeRestaurante);

        if (!restaurante.jaFoiVisitadoPor(emailCliente)){
            return "Voto não registrado";
        }

        restaurante.adicionaVoto();
        cliente.setPratoPreferido(restaurante.getPrato());

        return "Voto registrado com sucesso!";
    }

    /**
     * Descobre qual é o melhor prato, ou seja, o do restaurante com mais votos.
     *
     * @return um texto com o melhor prato e o nome do restaurante,
     *         ou "Nenhum restaurante cadastrado" se não houver restaurantes
     */
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

    /**
     * Lista todas as visitas do livro de visitas de um restaurante.
     *
     * @param nomeRestaurante nome do restaurante
     * @return vetor com o texto de cada visita
     * @throws IllegalArgumentException se o restaurante não existir
     */
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
    /**
     * Lista só as visitas mais recentes do livro de visitas de um restaurante.
     * Se pedir mais visitas do que existem, retorna todas as que existem.
     *
     * @param nomeRestaurante nome do restaurante
     * @param num_recentes    quantidade de visitas recentes que se quer ver
     * @return vetor com o texto das visitas mais recentes
     * @throws IllegalArgumentException se num_recentes for negativo
     *                                  ou se o restaurante não existir
     */
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
    /**
     * Procura um cliente pelo emqil.
     * Metodo auxiliar, usado apenas dentro dessa classe.
     *
     * @param email email do cliente procurado
     * @return o cliente encontrado
     * @throws IllegalArgumentException se não existir cliente com esse email
     */
    private Cliente buscarCliente(String email) {
        Cliente cliente = clientes.get(email);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        return cliente;
    }

    /**
     * Procura um restaurante pelo nome.
     * Metodo auxiliar, usado so dentro dessa classe.
     *
     * @param nome nome do restaurante procurado
     * @return o restaurante encontrado
     * @throws IllegalArgumentException se não existir restaurante com esse nome
     */
    private Restaurante buscarRestaurante(String nome) {
        Restaurante restaurante = restaurantes.get(nome);
        if (restaurante == null) {
            throw new IllegalArgumentException("Restaurante não encontrado");
        }

        return restaurante;
    }
}