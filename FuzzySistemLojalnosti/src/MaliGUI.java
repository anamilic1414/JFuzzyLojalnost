import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import javax.swing.JRadioButton;

public class MaliGUI {

	private JFrame frame;
	private JTextField txtPotrosnja;
	private JTextField txtUcestalost;
	private JTextField txtRedovnost;
	private JTextField txtReklamacije;
	private JTextField txtKomunikacija;
	private JTextField txtVremenski;
	private JTextField txtPreporuke;
	public Proba pr = new Proba();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MaliGUI window = new MaliGUI();
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
	public MaliGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 720, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 704, 0 };
		gridBagLayout.rowHeights = new int[] { 35, 285, 150, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		JLabel lblNaslov = new JLabel("Fuzzy-Logic sistem lojalnosti kupaca");
		lblNaslov.setHorizontalAlignment(SwingConstants.CENTER);
		lblNaslov.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 16));
		GridBagConstraints gbc_lblNaslov = new GridBagConstraints();
		gbc_lblNaslov.insets = new Insets(0, 0, 5, 0);
		gbc_lblNaslov.gridx = 0;
		gbc_lblNaslov.gridy = 0;
		frame.getContentPane().add(lblNaslov, gbc_lblNaslov);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 0);
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 1;
		frame.getContentPane().add(tabbedPane, gbc_tabbedPane);

		JPanel panelRucno = new JPanel();
		tabbedPane.addTab("Ru\u010Dno", null, panelRucno, null);
		panelRucno.setLayout(null);

		JLabel lblPotronja = new JLabel("Potro\u0161nja:");
		lblPotronja.setBounds(10, 11, 139, 31);
		panelRucno.add(lblPotronja);

		JLabel lblUcestalost = new JLabel("U\u010Destalost kupovine:");
		lblUcestalost.setBounds(10, 41, 139, 31);
		panelRucno.add(lblUcestalost);

		JLabel lblRedovnostPlaanja = new JLabel("Redovnost pla\u0107anja:");
		lblRedovnostPlaanja.setBounds(10, 71, 139, 31);
		panelRucno.add(lblRedovnostPlaanja);

		JLabel lblReklamacije = new JLabel("Reklamacije:");
		lblReklamacije.setBounds(10, 101, 139, 31);
		panelRucno.add(lblReklamacije);

		JLabel lblKominkacija = new JLabel("Kominkacija:");
		lblKominkacija.setBounds(10, 131, 139, 31);
		panelRucno.add(lblKominkacija);

		JLabel lblVremenskiKupac = new JLabel("Vremenski kupac:");
		lblVremenskiKupac.setBounds(10, 161, 139, 31);
		panelRucno.add(lblVremenskiKupac);

		JLabel lblBrojPreporuka = new JLabel("Broj preporuka:");
		lblBrojPreporuka.setBounds(10, 191, 139, 31);
		panelRucno.add(lblBrojPreporuka);

		txtPotrosnja = new JTextField();
		txtPotrosnja.setText("5500000");
		txtPotrosnja.setToolTipText("0-1000000");
		txtPotrosnja.setBounds(159, 16, 86, 20);
		panelRucno.add(txtPotrosnja);
		txtPotrosnja.setColumns(10);

		txtUcestalost = new JTextField();
		txtUcestalost.setText("18");
		txtUcestalost.setToolTipText("0-20");
		txtUcestalost.setColumns(10);
		txtUcestalost.setBounds(159, 46, 86, 20);
		panelRucno.add(txtUcestalost);

		txtRedovnost = new JTextField();
		txtRedovnost.setText("0.8");
		txtRedovnost.setToolTipText("0-1");
		txtRedovnost.setColumns(10);
		txtRedovnost.setBounds(159, 76, 86, 20);
		panelRucno.add(txtRedovnost);

		txtReklamacije = new JTextField();
		txtReklamacije.setText("1");
		txtReklamacije.setToolTipText("0-1");
		txtReklamacije.setColumns(10);
		txtReklamacije.setBounds(159, 106, 86, 20);
		panelRucno.add(txtReklamacije);

		txtKomunikacija = new JTextField();
		txtKomunikacija.setText("1");
		txtKomunikacija.setToolTipText("0-1");
		txtKomunikacija.setColumns(10);
		txtKomunikacija.setBounds(159, 136, 86, 20);
		panelRucno.add(txtKomunikacija);

		txtVremenski = new JTextField();
		txtVremenski.setText("6");
		txtVremenski.setToolTipText("0-7");
		txtVremenski.setColumns(10);
		txtVremenski.setBounds(159, 166, 86, 20);
		panelRucno.add(txtVremenski);

		txtPreporuke = new JTextField();
		txtPreporuke.setText("7");
		txtPreporuke.setToolTipText("0-7");
		txtPreporuke.setColumns(10);
		txtPreporuke.setBounds(159, 196, 86, 20);
		panelRucno.add(txtPreporuke);

		JTextArea txtRules = new JTextArea();
		// frame.getContentPane().add(txtRules);

		txtRules.setAutoscrolls(true);
		JScrollPane js = new JScrollPane(txtRules, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		GridBagConstraints gbc_js = new GridBagConstraints();
		gbc_js.fill = GridBagConstraints.BOTH;
		gbc_js.gridheight = 2;
		gbc_js.gridx = 0;
		gbc_js.gridy = 2;
		frame.getContentPane().add(js, gbc_js);
		txtRules.setBounds(0, 460, 704, 56);

		JRadioButton rdbtnSvi = new JRadioButton("Svi grafici");
		rdbtnSvi.setSelected(true);
		rdbtnSvi.setBounds(286, 45, 160, 23);
		panelRucno.add(rdbtnSvi);

		JRadioButton rdbtnGrafikLojalnosti = new JRadioButton("Grafik lojalnosti");
		rdbtnGrafikLojalnosti.setSelected(true);
		rdbtnGrafikLojalnosti.setBounds(286, 75, 160, 23);
		panelRucno.add(rdbtnGrafikLojalnosti);

		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(rdbtnSvi);
		bgroup.add(rdbtnGrafikLojalnosti);

		JPanel panelBaza = new JPanel();
		tabbedPane.addTab("Baza", null, panelBaza, null);
		panelBaza.setLayout(null);
		
		JButton btnGenerisi = new JButton("Generisi");
		btnGenerisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pr.GenerisiTreningPodatke();
			}
		});
		btnGenerisi.setBounds(56, 30, 89, 23);
		panelBaza.add(btnGenerisi);

		JButton btnGenerii = new JButton("Generi\u0161i");
		btnGenerii.setAutoscrolls(true);
		btnGenerii.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pr.SetovanjeVrednosti(Double.valueOf(txtPotrosnja.getText()), Integer.valueOf(txtUcestalost.getText()),
						Double.valueOf(txtRedovnost.getText()), Double.valueOf(txtReklamacije.getText()),
						Integer.valueOf(txtKomunikacija.getText()), Integer.valueOf(txtVremenski.getText()),
						Integer.valueOf(txtPreporuke.getText()));
				if (rdbtnSvi.isSelected()) {
					try {
						pr.PrikaziSveGrafike();
					} catch (Exception e) {
						// TODO: handle exception
					}
				} else {
					pr.PrikaziGrafikLojalnost();
				}

				txtRules.setText(pr.VratiPravila());
			}
		});
		btnGenerii.setBounds(286, 122, 109, 31);
		panelRucno.add(btnGenerii);

		txtRules.setEnabled(true);
		txtRules.setMaximumSize(new Dimension(2147483647, 100));
		txtRules.setEditable(false);
		txtRules.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		txtRules.setLineWrap(true);

	}
}
