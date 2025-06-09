package br.com.gurudoamor.projeto.service;
import br.com.gurudoamor.projeto.dto.MatchResponseDTO;
import br.com.gurudoamor.projeto.entity.Usuario;
import br.com.gurudoamor.projeto.enums.Mensagem;
import br.com.gurudoamor.projeto.enums.Signos;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

@Service
public class MatchService {

    private final MapsService mapsService;
    public MatchService(MapsService mapsService) {
        this.mapsService = mapsService;
    }



    public MatchResponseDTO matchUsuarios(Usuario usuario1, Usuario usuario2) {
        Map<String, Object> resultado = new HashMap<>();
        int compatibilidade = new Random().nextInt(100) + 1;
        String combinacaoSignos = "";
        String mensagem = "";
        String distanciaInfo = mapsService.calcularRota(usuario1.getEndereco(), usuario2.getEndereco());
        if(usuario1.getSignoUsuario() != null &&  usuario2.getSignoUsuario() != null) {
        	combinacaoSignos = usuario1.getSignoUsuario().name() + " + " + usuario2.getSignoUsuario().name();
            mensagem = getMensagem(usuario1.getSignoUsuario(), usuario2.getSignoUsuario(), compatibilidade);
        }
        
        System.out.println("Tamanho da distancia: " + distanciaInfo.length());


        return new MatchResponseDTO(
                usuario1.getNomeUsuario(),
                usuario2.getNomeUsuario(),
                compatibilidade + "%",
                combinacaoSignos,
                mensagem,
                distanciaInfo
        );
    }


    public String getMensagem(Signos signo1, Signos signo2, int compatibilidade) {
        String elemento1 = signo1.getElemento();
        String elemento2 = signo2.getElemento();
        boolean isAmor = compatibilidade >= 50;
        String elementoPrimario = elemento1.compareTo(elemento2) < 0 ? elemento1 : elemento2; //altera a ordem dos elementos
        String elementoSecundario = elemento1.compareTo(elemento2) < 0 ? elemento2 : elemento1;

        return Mensagem.getMensagem(elementoPrimario, elementoSecundario, isAmor);
    }



}
