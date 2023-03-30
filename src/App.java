import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        //fazer uma conexao http e buscar os top 250 filmes.
        String url = "https://www.imdb.com/chart/top/?ref_=nv_mv_250";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        
//extrair os dados que interessam (titulo, poster, classificação).
        
        var parser = new JsonParser();
        List<Map<String, String>> ListaDeFilmes = parser.parse(body);
        
        //exibir e manipular os dados.
        for (Map<String, String> filme : ListaDeFilmes) {
            String urlImagem = filme.get("imagem");
            InputStream inputStream = new URL(urlImagem).openStream();
            var generator = new StickerGenerator();
            generator.cria(inputStream, urlImagem);
            System.out.println(filme.get("title"));
            System.out.println(filme.get("imdbRating"));
            System.out.println();
    }
    }
}

