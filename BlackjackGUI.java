package BlackJack;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class BlackjackGUI extends JFrame {
    private Baralho baralho;
    private Jogador jogador, dealer;
    private JTextArea display;
    private JButton pedirBtn, pararBtn;

    public BlackjackGUI() {
        setTitle("Blackjack Simplificado");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextArea();
        display.setEditable(false);
        add(new JScrollPane(display), BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        pedirBtn = new JButton("Pedir Carta");
        pararBtn = new JButton("Parar");
        painelBotoes.add(pedirBtn);
        painelBotoes.add(pararBtn);
        add(painelBotoes, BorderLayout.SOUTH);

        iniciarJogo();

        pedirBtn.addActionListener(e -> {
            jogador.receberCarta(baralho.comprarCarta());
            display.setText("Sua mão:\n" + jogador.mostrarMao() + "\n\nCarta visível do dealer:\n" + dealer.mostrarMao().split("\n")[0]);
            if(jogador.getPontos() > 21) {
                JOptionPane.showMessageDialog(null, "Você estourou! Dealer vence.");
                resetarJogo();
            }
        });

        pararBtn.addActionListener(e -> {
            while(dealer.getPontos() < 17) {
                dealer.receberCarta(baralho.comprarCarta());
            }
            display.setText("Sua mão:\n" + jogador.mostrarMao() + "\n\nMão do dealer:\n" + dealer.mostrarMao());
            if(dealer.getPontos() > 21 || jogador.getPontos() > dealer.getPontos()) {
                JOptionPane.showMessageDialog(null, "Você venceu!");
            } else if(jogador.getPontos() == dealer.getPontos()) {
                JOptionPane.showMessageDialog(null, "Empate!");
            } else {
                JOptionPane.showMessageDialog(null, "Dealer venceu!");
            }
            resetarJogo();
        });
    }

    private void iniciarJogo() {
        baralho = new Baralho();
        baralho.embaralhar();
        jogador = new Jogador();
        dealer = new Jogador();

        jogador.receberCarta(baralho.comprarCarta());
        jogador.receberCarta(baralho.comprarCarta());
        dealer.receberCarta(baralho.comprarCarta());
        dealer.receberCarta(baralho.comprarCarta());

        display.setText("Sua mão:\n" + jogador.mostrarMao() + "\n\nCarta visível do dealer:\n" + dealer.mostrarMao().split("\n")[0]);
    }

    private void resetarJogo() {
        int resposta = JOptionPane.showConfirmDialog(this, "Deseja jogar novamente?", "Novo Jogo", JOptionPane.YES_NO_OPTION);
        if(resposta == JOptionPane.YES_OPTION) {
            iniciarJogo();
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BlackjackGUI().setVisible(true));
    }
}
