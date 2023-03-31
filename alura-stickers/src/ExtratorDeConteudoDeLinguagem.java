import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDeLinguagem implements ExtratorDeConteudo {

    public List<Conteudo> extraiConteudos(String json) {

        // extrair só os dados que interessam (titulo, poster, classificação)
        JsonParcer parser = new JsonParcer();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        return listaDeAtributos.stream()
                .map(atributos -> new Conteudo(atributos.get("title"), atributos.get("image"), atributos.get("ranking"))).toList();

    }

}
