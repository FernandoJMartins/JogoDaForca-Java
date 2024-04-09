package program;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.InputMismatchException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Window {

	private JFrame frame;
	private JButton button;

	private JogoDaForca jogo;
	private ArrayList<Integer> ocorrencias; // posicoes adivinhadas
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel pa;
	private JTextField textField;
	private JButton advinhar;
	private final JLabel acertosS = new JLabel("Acertos: ");
	private JLabel Erros;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private String letraDigitada;
	private int ocsize = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Jogo da Forca - the Game");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		button = new JButton("Iniciar Jogo");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					button.setVisible(false);

					jogo = new JogoDaForca();
					jogo.iniciar();

					pa.setText(jogo.getPalavraAdivinhada());

					label.setVisible(true);
					label_1.setVisible(true);
					textField.setVisible(true);
					advinhar.setVisible(true);
					Erros.setVisible(true);
					acertosS.setVisible(true);
					label_2.setText(jogo.getDica());
					label_3.setText(String.valueOf(jogo.getTamanho()));
					label_4.setVisible(true);
					label_5.setVisible(true);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		button.setBounds(125, 99, 146, 55);
		frame.getContentPane().add(button);

		label = new JLabel("Dica: ");
		label.setBounds(21, 236, 46, 14);
		label.setVisible(false);
		frame.getContentPane().add(label);

		label_1 = new JLabel("Tamanho:");
		label_1.setBounds(21, 217, 126, 14);
		label_1.setVisible(false);
		frame.getContentPane().add(label_1);

		label_2 = new JLabel("");
		label_2.setBounds(147, 236, 287, 14);
		label_2.setVisible(true);
		frame.getContentPane().add(label_2);

		label_3 = new JLabel("");
		label_3.setBounds(147, 217, 67, 14);
		label_3.setVisible(true);
		frame.getContentPane().add(label_3);

		pa = new JLabel("");
		pa.setHorizontalAlignment(SwingConstants.CENTER);
		pa.setFont(new Font("Serif", Font.PLAIN, 17));
		pa.setBounds(123, 28, 168, 27);
		frame.getContentPane().add(pa);

		textField = new JTextField();
		textField.setVisible(false);
		textField.setFont(new Font("Serif", Font.PLAIN, 15));
		textField.setBounds(72, 66, 116, 35);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		advinhar = new JButton("advinhar");
		advinhar.setBounds(218, 66, 116, 35);
		frame.getContentPane().add(advinhar);
		advinhar.setVisible(false);

		acertosS.setBounds(21, 198, 101, 14);
		frame.getContentPane().add(acertosS);
		acertosS.setVisible(false);

		Erros = new JLabel("Penalidades:");
		Erros.setBounds(21, 179, 101, 14);
		frame.getContentPane().add(Erros);
		Erros.setVisible(false);

		label_4 = new JLabel("0");
		label_4.setBounds(147, 198, 46, 14);
		frame.getContentPane().add(label_4);
		label_4.setVisible(false);

		label_5 = new JLabel("0 - sem penalidades");
		label_5.setBounds(146, 180, 278, 14);
		frame.getContentPane().add(label_5);
		label_5.setVisible(false);

		label_6 = new JLabel("");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		label_6.setBounds(82, 118, 233, 36);
		frame.getContentPane().add(label_6);

		advinhar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				letraDigitada = textField.getText();
				textField.setText("");

				try {
					ocorrencias = jogo.getOcorrencias(letraDigitada);

					if (ocorrencias.isEmpty())
						label_6.setText("Você Errou ( " + letraDigitada + " )");
					else {
						if (ocorrencias.size() > ocsize) {
							label_6.setText("Você Acertou ( " + letraDigitada + " )");
							ocsize = ocorrencias.size();
						} else if (ocorrencias.size() <= ocsize)
							label_6.setText("Você Errou ( " + letraDigitada + " )");
					}

					textField.setText("");
					pa.setText(jogo.getPalavraAdivinhada());
					label_5.setText(String
							.valueOf(jogo.getNumeroPenalidade() + " - " + String.valueOf(jogo.getNomePenalidade())));
					label_4.setText(String.valueOf(jogo.getAcertos()));

					if (jogo.terminou()) {
						label_6.setText(jogo.getResultado());
						pa.setText(jogo.getSorted());
						textField.setVisible(false);
						advinhar.setVisible(false);
					}

				} catch (InputMismatchException ex) {
					// Exibe uma caixa de diálogo com a mensagem de exceção

					label_6.setText(ex.getMessage());
				} catch (Exception ex) {
					// Lidar com outras exceções, se necessário
					System.out.println(ex.getMessage());
				}

			}
		});
	}
}
