import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {

  public static void main(String[] args) throws Exception {
    //fazer uma conexao http e buscar os top 250 filmes.

    String url = "https://api.nasa.gov/planetary/apod?api key=DEMO KEY1&api secret=DEMO KEY2 &api_version";
    var http = new clienteHttp();
    String json = http.buscaDados(url);

    //extrair os dados que interessam (titulo, poster, classificação).

    var parser = new JsonParser();
    List<Map<String, String>> ListaDeConteudos = parser.parse(json);

    var generator = new StickerGenerator();
    
    for (int i = 0; i < 10; i++) {
      Conteudo conteudo = (Conteudo) ListaDeConteudos.get(i);
      InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
      String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

      generator.cria(inputStream, nomeArquivo);
      System.out.println(conteudo.getTitulo());

      System.out.println();
    }
  }
}
