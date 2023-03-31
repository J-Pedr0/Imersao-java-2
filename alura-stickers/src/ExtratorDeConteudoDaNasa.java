import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDaNasa implements ExtratorDeConteudo {

    public List<Conteudo> extraiConteudos(String json) {

        // extrair só os dados que interessam (titulo, poster, classificação)
        JsonParcer parser = new JsonParcer();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        return listaDeAtributos.stream()
            .map(atributos -> new Conteudo(atributos.get("title"), atributos.get("url"), atributos.get("date"))).toList();

        // List<Conteudo> conteudos = new ArrayList<>();

        // //popular a lista de conteudos
        // for (Map<String, String> atributos : listaDeAtributos) {

        //     String titulo = atributos.get("title");
        //     String urlImagem = atributos.get("url");

        //     var conteudo = new Conteudo(titulo, urlImagem);

        //     conteudos.add(conteudo);
            
        // }

        // return conteudos;
    }

}
