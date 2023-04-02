import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class clienteHttp {

 public String buscaDados(String Url) {
    try {
      URI endereco = URI.create(Url);
      var client = HttpClient.newHttpClient();
      var request = HttpRequest.newBuilder(endereco).GET().build();
      HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
      String body = response.body();
      return body;
    } catch (IOException |InterruptedException ex) {
      throw new RuntimeException(ex);
    }
  }
}
