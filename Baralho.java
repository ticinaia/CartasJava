package BlackJack;
import java.util.*;

public class Baralho {
    private List<Carta> cartas;

    public Baralho() {
        cartas = new ArrayList<>();
        String[] naipes = {"Copas", "Ouros", "Espadas", "Paus"};
        String[] valores = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
        int[] pontos = {2,3,4,5,6,7,8,9,10,10,10,10,11};

        for(int i = 0; i < valores.length; i++) {
            for(String naipe : naipes) {
                cartas.add(new Carta(valores[i], naipe, pontos[i]));
            }
        }
    }

    public void embaralhar() {
        Collections.shuffle(cartas);
    }

    public Carta comprarCarta() {
        if (!cartas.isEmpty()) return cartas.remove(0);
        return null;
    }
}
