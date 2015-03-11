package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.FlowLayout;

import javax.swing.JTabbedPane;

import java.awt.Panel;

import javax.swing.JList;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Properties;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;

import javax.swing.SwingConstants;

import utils.AwS3Conn;
import utils.ConfigManager;

import javax.swing.JCheckBox;

public class MainGui extends JFrame {

	private JPanel contentPane;
	private JTextField textField_2;
	private JTextField textField_3;
	private static JTextArea txtrStatus;
	private AwS3Conn awsS4 = null;
	private CardLayout navLayout;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui frame = new MainGui();
					frame.setSize(640, 480);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGui() {
		final JFrame frame = this;
		setTitle("Amazon Secure Simple Storage Service (S4)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		txtrStatus = new JTextArea();
		contentPane.add(txtrStatus, BorderLayout.SOUTH);

		final JPanel navPanel = new JPanel();
		contentPane.add(navPanel, BorderLayout.WEST);
		navLayout = new CardLayout();
		navPanel.setLayout(navLayout);

		JPanel accessPanel = new JPanel();
		navPanel.add(accessPanel, "accessPanel");

		JPanel s3Connect = new JPanel();
		accessPanel.add(s3Connect);

		JButton defaultConnBtn = new JButton("Connect Using Default Keys");
		defaultConnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		s3Connect.setLayout(new BorderLayout(0, 0));

		s3Connect.add(defaultConnBtn, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		s3Connect.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);

		JLabel lblNewLabel_1 = new JLabel("Access Key");
		panel_2.add(lblNewLabel_1);

		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(10);

		JPanel panel_9 = new JPanel();
		panel_1.add(panel_9);

		JLabel lblSKey = new JLabel("Secret Key");
		panel_9.add(lblSKey);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel_9.add(textField_1);

		JTextArea txtrStatus = new JTextArea();
		contentPane.add(txtrStatus, BorderLayout.SOUTH);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Set As Default");
		panel_1.add(chckbxNewCheckBox);

		JButton btnNewButton_6 = new JButton("Connect Using New Keys");
		s3Connect.add(btnNewButton_6, BorderLayout.SOUTH);

		JPanel s4Panel = new JPanel();
		navPanel.add(s4Panel, "s4Panel");
		s4Panel.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		s4Panel.add(tabbedPane);

		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Upload", null, panel_5, null);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));

		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.X_AXIS));

		JButton btnNewButton_2 = new JButton("Select File");

		panel_7.add(btnNewButton_2);

		final JLabel lblNewLabel = new JLabel("No File Selected");
		panel_7.add(lblNewLabel);

		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser uploadFile = new JFileChooser();
				uploadFile.showOpenDialog(frame);
				upload(uploadFile.getSelectedFile());

			}

			private void upload(File selectedFile) {
				// TODO Auto-generated method stub
				lblNewLabel.setText(selectedFile.getName());
			}
		});

		JButton btnU = new JButton("Upload");
		panel_5.add(btnU);

		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Download", null, panel_6, null);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));

		Panel panel_8 = new Panel();
		panel_6.add(panel_8);
		panel_8.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		textField_3 = new JTextField();
		panel_8.add(textField_3);
		textField_3.setColumns(10);

		JButton btnNewButton_3 = new JButton("Search");
		panel_8.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Download");
		panel_8.add(btnNewButton_4);

		JList list = new JList();
		panel_6.add(list);

		JPanel panel = new JPanel();
		s4Panel.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));

		JLabel lblBucketName = new JLabel("Bucket Name");
		panel_3.add(lblBucketName);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		panel_3.add(textField_2);

		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));

		JButton btnNewButton = new JButton("Choose Existing Key Pair");
		panel_4.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Create New Key Pair");
		panel_4.add(btnNewButton_1);

		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser newRsa_pubKey = new JFileChooser();
				FileNameExtensionFilter pubkey_filter = new FileNameExtensionFilter(
						"Pubkey filter", "pubs4");
				newRsa_pubKey.setFileFilter(pubkey_filter);

				JFileChooser newRsa_privKey = new JFileChooser();
				FileNameExtensionFilter privkey_filter = new FileNameExtensionFilter(
						"Privkey filter", "privs4");
				newRsa_privKey.setFileFilter(privkey_filter);
				newRsa_pubKey
						.setDialogTitle("Enter the location to open the public key ");
				newRsa_pubKey.showOpenDialog(frame);
				newRsa_privKey
						.setDialogTitle("Enter the location to open the private key ");
				newRsa_privKey.showOpenDialog(frame);

				try {
					KeyPair useKeys = open_keys(
							newRsa_pubKey.getSelectedFile(),
							newRsa_privKey.getSelectedFile());

					KeyGenerator keygen = KeyGenerator.getInstance("AES");
					keygen.init(128);
					SecretKey aes_key = keygen.generateKey();

					File temp = new File("/tmp/encrypt.temps4");
					encrypt(aes_key, useKeys);
					// upload encrypted file i.e. temp to
					decrypt(aes_key, useKeys);
					temp.delete();

				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidKeySpecException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser newRsa_pubKey = new JFileChooser();
				FileNameExtensionFilter pubkey_filter = new FileNameExtensionFilter(
						"Pubkey filter", "pubs4");
				newRsa_pubKey.setFileFilter(pubkey_filter);

				JFileChooser newRsa_privKey = new JFileChooser();
				FileNameExtensionFilter privkey_filter = new FileNameExtensionFilter(
						"Privkey filter", "privs4");
				newRsa_privKey.setFileFilter(privkey_filter);
				newRsa_pubKey
						.setDialogTitle("Enter the location to store the public key ");
				newRsa_pubKey.showSaveDialog(frame);
				newRsa_privKey
						.setDialogTitle("Enter the location to store the private key ");
				newRsa_privKey.showSaveDialog(frame);

				try {

					generate_rsa_keys(newRsa_pubKey.getSelectedFile(),
							newRsa_privKey.getSelectedFile());

					KeyPair useKeys = open_keys(
							newRsa_pubKey.getSelectedFile(),
							newRsa_privKey.getSelectedFile());

				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidKeySpecException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		defaultConnBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				log("Using Default Keys to connect to AWS S3");
				Properties prop = ConfigManager.getProperties();
				if (prop != null) {
					awsS4 = new AwS3Conn(prop.getProperty("access-key"), prop
							.getProperty("secret-access-key"));
					log("Connected to AWS S3");
					if (awsS4 != null) {
						navLayout.show(navPanel, "s4Panel");
					}
				}

			}
		});
	}

	protected KeyPair open_keys(File pub, File priv) throws IOException,
			NoSuchAlgorithmException, InvalidKeySpecException {

		KeyFactory keyfac = KeyFactory.getInstance("RSA");

		FileInputStream pubstream = new FileInputStream(pub);
		long pub_size = pub.length();
		byte[] pubKeybytes = new byte[(int) pub_size];
		pubstream.read(pubKeybytes);
		pubstream.close();

		X509EncodedKeySpec pubkey = new X509EncodedKeySpec(pubKeybytes);
		PublicKey pubKey = keyfac.generatePublic(pubkey);

		// Loaded the public key

		FileInputStream privstream = new FileInputStream(priv);
		long priv_size = priv.length();
		byte[] privKeybytes = new byte[(int) priv_size];
		privstream.read(privKeybytes);
		privstream.close();

		PKCS8EncodedKeySpec privkey = new PKCS8EncodedKeySpec(privKeybytes);
		PrivateKey privKey = keyfac.generatePrivate(privkey);

		// Loaded the private key

		KeyPair keypair = new KeyPair(pubKey, privKey);

		return keypair;
	}

	public static void log(String msg) {
		txtrStatus.append(msg + "\n");
	}

	protected void generate_rsa_keys(File pubFile,File privFile ) throws NoSuchAlgorithmException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Entered function:");
		
		/*FileInputStream pubstream = new FileInputStream(pubFile);
		long pub_size = pubFile.length();
		byte[] pubkey_bytes = new byte[(int) pub_size];
		pubstream.read(pubkey_bytes);
		pubstream.close();
		
		FileInputStream privstream = new FileInputStream(privFile);
		long priv_size = privFile.length();
		byte[] privkey_bytes = new byte[(int) priv_size];
		privstream.read(privkey_bytes);
		privstream.close();
		*/
		
		KeyPairGenerator rsaPair = KeyPairGenerator.getInstance("RSA");
		rsaPair.initialize(2048);
		KeyPair rP = rsaPair.genKeyPair();
		
		byte[] pubkey_bytes = rP.getPublic().getEncoded();
		byte[] privkey_bytes = rP.getPrivate().getEncoded();
		
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(pubkey_bytes);
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privkey_bytes);
		
		FileOutputStream pub_key = new FileOutputStream(pubFile);
		pub_key.write(x509EncodedKeySpec.getEncoded());
		pub_key.close();
		
		FileOutputStream priv_key = new FileOutputStream(privFile);
		priv_key.write(pkcs8EncodedKeySpec.getEncoded());
		priv_key.close();
		
		/*	To print the public and private keys and store them as hex in files
		 */ 
		/*String temp="";
		String pubkey = "";
		System.out.println("Generating public key...");
		float percent = (float) 0.00 ;
		 
		for (int i = 0; i < pubkey_bytes.length; i++) {
			temp = Integer.toString((pubkey_bytes[i] & 0xff) + 0x100, 16) ;
			pubkey += temp.substring(1);
			percent = (float) (((float)i*100.00)/(float)pubkey_bytes.length) ;
			System.out.println(percent);
		}
		
		temp="";
		String privkey = "";
		System.out.println("Generating private key...");
		percent=(float) 0.00;
		for (int i = 0; i < privkey_bytes.length; i++) {
			temp = Integer.toString((privkey_bytes[i] & 0xff) + 0x100, 16);
			privkey += temp.substring(1);
			percent = (float) (((float)i*100.00)/(float)privkey_bytes.length) ;
			System.out.println(percent);
		}
		
		//System.out.println(pubkey);
		PrintWriter pub_key = new PrintWriter(new FileOutputStream(pubFile));
		pub_key.println(pubkey);
		pub_key.close();
		//System.out.println("Writing private key");
		System.out.println(privkey);
		PrintWriter priv_key = new PrintWriter(new FileOutputStream(privFile));
		priv_key.println(privkey);
		priv_key.close();*/
	}

	private static void encrypt(SecretKey key, KeyPair rP) throws Exception {
		// TODO Auto-generated method stub
		FileInputStream rawFile = new FileInputStream(
				"/home/master/assignment4/input_data");

		FileOutputStream encFile = new FileOutputStream("/tmp/encrypt.temps4");

		Cipher rsa = Cipher.getInstance("RSA");
		rsa.init(Cipher.ENCRYPT_MODE, rP.getPublic());
		byte[] encKey = rsa.doFinal(key.getEncoded());
		System.out.println(encKey.length);

		encFile.write(encKey);

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, key);

		byte[] input = new byte[64];
		int bytesRead;

		while ((bytesRead = rawFile.read(input)) != -1) {
			byte[] output = cipher.update(input, 0, bytesRead);
			if (output != null) {
				encFile.write(output);
			}
		}

		byte[] output = cipher.doFinal();
		if (output != null) {
			encFile.write(output);
		}

		rawFile.close();
		encFile.flush();
		encFile.close();
	}

	private static void decrypt(SecretKey key, KeyPair rP) throws Exception {
		// TODO Auto-generated method stub
		FileOutputStream decFile = new FileOutputStream(
				"/home/master/assignment4/output_data");

		FileInputStream encFile = new FileInputStream("/tmp/encrypt.temps4");

		Cipher rsa = Cipher.getInstance("RSA");
		rsa.init(Cipher.DECRYPT_MODE, rP.getPrivate());
		byte[] encKey = new byte[256];
		encFile.read(encKey);
		byte[] decKey = rsa.doFinal(encKey);

		SecretKey k = new SecretKeySpec(decKey, 0, decKey.length, "AES");

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, k);

		byte[] in = new byte[64];
		int read;
		while ((read = encFile.read(in)) != -1) {
			byte[] output = cipher.update(in, 0, read);
			if (output != null)
				decFile.write(output);
		}

		byte[] output = cipher.doFinal();
		if (output != null)
			decFile.write(output);

		encFile.close();
		decFile.flush();
		decFile.close();

	}

}
