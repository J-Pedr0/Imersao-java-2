import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;


public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // pegar só os dados que interessam (titulo, poster, classificação)
        JsonParcer parser = new JsonParcer();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manupular os dados

        System.out.println("Filmes: ");

        for (int i = 0; i < listaDeFilmes.size(); i++) {
            Map<String,String> filme = listaDeFilmes.get(i); 
            System.out.println("\u001b[1mRank:\u001b[m "+ "  \u001b[31;1m" + "\u001b[4m" + filme.get("rank") + "\u001b[m");
            System.out.println("\u001b[1mTitle:\u001b[m "+ " \u001b[32;1m" + "\u001b[4m" + filme.get("title") + "\u001b[m");
            System.out.println("\u001b[1mYear:\u001b[m " + " \u001b[32;1m" + "\u001b[4m" + filme.get("year") + "\u001b[m");
            // System.out.println(""+filme.get("image"));
            System.out.print("\u001b[1mIMDB Rating:\u001b[m" + "\u001b[37;1m \u001b[33;1m" + "\u001b[4m" + filme.get("imDbRating") + "\u001b[m");

            double classificação = Double.parseDouble(filme.get("imDbRating"));
            int numeroEstrela = (int) classificação;

            for (int n = 1; n <= numeroEstrela; n++) {
                System.out.print(" ⭐ ");    
            }
            System.out.println("\n");

        }

    }
}
