import java.awt.Color;
import java.awt.Font;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class StickerGenerator {
  public void cria(InputStream inputStream, String nomeArquivo) throws IOException {
    java.awt.image.BufferedImage originalImage = ImageIO.read(inputStream);
    
//cria nova imagem em memoria com transparencia e tamanho novo.
    int largura = originalImage.getWidth();
    int altura = originalImage.getHeight();
    int novaAltura = altura + 200;
    BufferedImage novaImagem = new BufferedImage
    (largura, novaAltura, BufferedImage.TRANSLUCENT);

    //copiar a imagem original para a nova imagem em memoria.
    Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
    graphics.drawImage(originalImage, 0, 0, null);

    //configurar a fonte.
    var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
    graphics.setColor(Color.MAGENTA);
    graphics.setFont(fonte);

    //escrever a nova imagem em um arquivo.
    ImageIO.write((RenderedImage) novaImagem,
     "png",new File(nomeArquivo));
    
  }
}
