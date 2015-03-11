package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

public class Test extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
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
	public Test() {
		final JFrame frame = this;
		setTitle("Amazon Secure Simple Storage Service (S4)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JLabel lblAws = new JLabel("AWS Access Key");
		panel_1.add(lblAws);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JLabel label = new JLabel("AWS Secret Key");
		panel_2.add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel_2.add(textField_1);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		JLabel lblBucketName = new JLabel("Bucket Name");
		panel_3.add(lblBucketName);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		panel_3.add(textField_2);
		
		JButton btnConnect = new JButton("Connect");
		panel.add(btnConnect);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton = new JButton("Choose Existing Key Pair");
		panel_4.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Create New Key Pair");
		panel_4.add(btnNewButton_1);
		
		JTextArea txtrStatus = new JTextArea();
		contentPane.add(txtrStatus, BorderLayout.SOUTH);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
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
	}
}