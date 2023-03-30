import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        
        // API apiIMDB = API.IMDB_TOP_MOVIES;
        // String url =
        // "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        // String url = apiIMDB.getUrl();
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        // API apiNASA = API.NASA;
        // String url = "https://api.nasa.gov/planetary/apod?api_key=wah98P8EUNkD6aWXHomTAAzV5sF4GSfyS0PiJq7f&start_date=2022-06-12&end_date=2022-06-14";
        // String url = apiNASA.getUrl();
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        API api = API.IMDB_TOP_MOVIES;
        String url = api.getUrl();
        ExtratorDeConteudo extrator = api.getExtrator();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // exibir e manupular os dados

        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        var diretorio = new File("saida/");
        diretorio.mkdir();

        System.out.println();
        System.out.println("Conteúdos: ");
        System.out.println();

        for (int i = 0; i < conteudos.size(); i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.titulo() + ".png";

            geradora.cria(inputStream, nomeArquivo.replace(":", "-") /* , textoFigurinha, imagemJose */);

            System.out.println(conteudo.titulo());
            System.out.println();

        }

    }
}

// int numeroEstrela = 1;

// String textoFigurinha;

// InputStream imagemJose;

// if (numeroEstrela == 1) {
// textoFigurinha = "TOPZERA";
// imagemJose = new FileInputStream(
// new
// File("C:/Serrateco/ImersaoJava/Aula1/alura-stickers/sobreposicao/topzera.jpg"));
// } else {
// textoFigurinha = "HMMMMMM...";
// imagemJose = new FileInputStream(
// new
// File("C:/Serrateco/ImersaoJava/Aula1/alura-stickers/sobreposicao/hmm.jpg"));
// }

// // var geradora = new GeradoraDeFigurinhas();
// geradora.cria(inputStream, nomeArquivo, textoFigurinha, imagemJose);

// System.out.println(
// "\u001b[1mRank:\u001b[m " + " \u001b[31;1m" + "\u001b[4m" +
// conteudo.get("rank") + "\u001b[m");
// System.out.println(
// "\u001b[1mTitle:\u001b[m " + " \u001b[32;1m" + "\u001b[4m" +
// conteudo.get("title") + "\u001b[m");
// System.out.println(
// "\u001b[1mYear:\u001b[m " + " \u001b[32;1m" + "\u001b[4m" +
// conteudo.get("year") + "\u001b[m");
// // System.out.println(""+conteudo.get("image"));
// System.out.print("\u001b[1mIMDB Rating:\u001b[m" + "\u001b[37;1m
// \u001b[33;1m" + "\u001b[4m"
// + conteudo.get("imDbRating") + "\u001b[m");

// for (int n = 1; n <= numeroEstrela; n++) {
// System.out.print(" ⭐ ");
// }
// System.out.println("\n");
