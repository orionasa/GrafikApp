import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Grafik_App extends JFrame {

	// Nesnelerimizi oluþturuyoruz
	private static final long serialVersionUID = -6972101192183069214L;
	private JButton ciz;
	private JTextField en, boy;
	private JComboBox<String> cizim_sayisi;
	private JRadioButton dikdortgen, cizgi, oval, kare;
	public int x = 200, y = 150, yukseklik = 0, genislik = 0, cizgi_Y = 125;
	public int sayi = 0;
	public String sekil;
	public static String cizimler[] = { "1", "2", "3", "4", "5" };

	public Grafik_App() {
		super("Grafik App");
		setLayout(new BorderLayout());
		// Panel1 BorderLayout.NORTH ile konumlaniyor
		final panel1 panel1 = new panel1();
		panel1.setPreferredSize(new Dimension(0, 300));
		add(panel1, BorderLayout.NORTH);

		// Panel2 GridLayout 4x2 oluþturuyoruz ve nesneleri konumlandýrýyoruz
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(4, 2, 0, 10));
		panel2.setPreferredSize(new Dimension(0, 200));
		// Hücre1 de Labelimiz ve ComboBoxýmýz var seçilen sayýnýn indexi+1 bize
		// deðeri //yolluyor
		JPanel hucre1 = new JPanel();
		hucre1.setLayout(new GridLayout(1, 2));
		JLabel label1 = new JLabel("Sayý");
		cizim_sayisi = new JComboBox<String>(cizimler);
		cizim_sayisi.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					sayi = Integer.parseInt(cizim_sayisi.getItemAt(cizim_sayisi
							.getSelectedIndex()));
					System.out.println();
				}
			}
		});
		hucre1.add(label1);
		hucre1.add(cizim_sayisi);

		// Hücre2 de label ve textfield kullanýyoruz yükseklik tutmak için
		// Textfield týklandýðýnda içi seçiliyor, rahat kullaným için
		JPanel hucre2 = new JPanel();
		hucre2.setLayout(new GridLayout(1, 2));
		JLabel label2 = new JLabel("Yükseklik");
		boy = new JTextField();
		boy.setText("0");
		boy.setPreferredSize(new Dimension(120, 20));
		boy.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				boy.selectAll();
			}
		});
		hucre2.add(label2);
		hucre2.add(boy);

		// Hücre3 de label ve textfield kullanýyoruz geniþlik tutmak için
		// Textfield týklandýðýnda içi seçiliyor, rahat kullaným için
		JPanel hucre3 = new JPanel();
		hucre3.setLayout(new GridLayout(1, 2));
		JLabel label3 = new JLabel("Geniþlik/Uzunluk");
		en = new JTextField();
		en.setText("0");
		en.setPreferredSize(new Dimension(120, 20));
		en.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				
				en.selectAll();
			}
		});
		hucre3.add(label3);
		hucre3.add(en);

		// Hücre4 çizdirme butonumuza sahip ve boþ label ile sola hizalýyoruz
		JPanel hucre4 = new JPanel();
		hucre4.setLayout(new GridLayout(1, 2));
		JButton kayit = new JButton("Kaydet");
		kayit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				BufferedImage bi = new BufferedImage(panel1.getWidth(), panel1
						.getHeight(), BufferedImage.TYPE_INT_RGB);
				Graphics2D g = bi.createGraphics();
				panel1.paintComponent(g);
				try {
					ImageIO.write(bi, "JPEG", new File("image.jpg"));
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		});
		ciz = new JButton("Çiz");
		// Buton deðiþkenleri alýyor harf girilmesinden oluþan hatalar tutuluyor
		ciz.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {

					cizgi_Y = 125;
					genislik = Integer.parseInt(en.getText());
					yukseklik = Integer.parseInt(boy.getText());
					if (genislik > 400) {
						genislik = 400;
						en.setText("400");
					}
					if (yukseklik > 300) {
						yukseklik = 300;
						boy.setText("300");
					}

					if (sayi == 0 || sayi == 1) {
						sayi = 1;
					}
					repaint();
				} catch (NumberFormatException ex) {
					en.setText("Sayi Gir");
					boy.setText("Sayi Gir");
				}
			}
		});

		hucre4.add(ciz);
		hucre4.add(kayit);

		// RadioButtonlar oluþturup seçimi deðiþkene atýyoruz

		dikdortgen = new JRadioButton("Dikdörtgen", true);
		dikdortgen.isSelected();
		cizgi = new JRadioButton("Çizgi", false);
		oval = new JRadioButton("Daire", false);
		kare = new JRadioButton("Kare", false);

		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(dikdortgen);
		bgroup.add(cizgi);
		bgroup.add(oval);
		bgroup.add(kare);

		ActionListener radio = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				sekil = event.getActionCommand();
			}

		};
		dikdortgen.addActionListener(radio);
		cizgi.addActionListener(radio);
		oval.addActionListener(radio);
		kare.addActionListener(radio);

		panel2.add(hucre1);
		panel2.add(dikdortgen);
		panel2.add(hucre2);
		panel2.add(cizgi);
		panel2.add(hucre3);
		panel2.add(oval);
		panel2.add(hucre4);
		panel2.add(kare);

		add(panel2, BorderLayout.SOUTH);

	}

	private class panel1 extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		// Çizim kontroller yapýlýp panele aktarýlýyor
		public void paintComponent(Graphics g) {

			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 400, 300);
			g.setColor(Color.RED);
			g.drawLine(200, 0, 200, 300);
			g.setColor(Color.RED);
			g.drawLine(0, 150, 400, 150);
			g.setColor(Color.BLACK);
			g.drawString("Max Yükseklik: 300", 20, 50);
			g.setColor(Color.BLACK);
			g.drawString("Max Geniþlik: 400", 20, 80);
			// Þekil Çizgi Olunca
			if (sekil == "Çizgi") {
				boy.setText("0");
				g.setColor(Color.BLUE);
				for (int i = 0; i < sayi; i++) {
					g.drawLine(x - (genislik / 2), cizgi_Y, x + (genislik / 2),
							cizgi_Y);
					cizgi_Y += ((cizgi_Y * 10) / 100);
					genislik -= ((genislik * 10) / 100);
				}
			}

			// Sekil dikdörtgen olunca
			if (sekil == "Dikdörtgen") {
				g.setColor(Color.BLUE);
				for (int i = 0; i < sayi; i++) {
					g.drawRect(x - (genislik / 2), y - (yukseklik / 2),
							genislik, yukseklik);
					genislik -= ((genislik * 10) / 100);
					yukseklik -= ((yukseklik * 10) / 100);
				}
			}

			// Sekil daire olunca
			if (sekil == "Daire") {
				g.setColor(Color.BLUE);
				for (int i = 0; i < sayi; i++) {
					g.drawOval(x - (genislik / 2), y - (yukseklik / 2),
							genislik, yukseklik);
					genislik -= ((genislik * 10) / 100);
					yukseklik -= ((yukseklik * 10) / 100);
				}
			}

			// Sekil Kare Olunca
			if (sekil == "Kare") {
				yukseklik = genislik;
				boy.setText(Integer.toString(yukseklik));
				g.setColor(Color.BLUE);
				for (int i = 0; i < sayi; i++) {
					g.drawRect(x - (genislik / 2), y - (yukseklik / 2),
							genislik, yukseklik);
					genislik -= ((genislik * 10) / 100);
					yukseklik -= ((yukseklik * 10) / 100);
				}
			}

		}
	}

}
