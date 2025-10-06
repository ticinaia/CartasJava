package BlackJack;
import java.util.*;

public class Jogador {
    private List<Carta> mao;
    private int pontos;

    public Jogador() {
        mao = new ArrayList<>();
        pontos = 0;
    }

    public void receberCarta(Carta carta) {
        mao.add(carta);
        pontos += carta.getPontos();

        // Lógica para Ás (11 ou 1)
        if (pontos > 21) {
            for (Carta c : mao) {
                if (c.getValor().equals("A") && pontos > 21) {
                    pontos -= 10;
                }
            }
        }
    }

    public int getPontos() { return pontos; }

    public String mostrarMao() {
        StringBuilder sb = new StringBuilder();
        for(Carta c : mao) {
            sb.append(c.toString()).append("\n");
        }
        sb.append("Total de pontos: ").append(pontos);
        return sb.toString();
    }
}
