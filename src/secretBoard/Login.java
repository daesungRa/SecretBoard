package secretBoard;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JPanel panel;
	private JTextField txtId;
	private JTextField txtPwd;
	private JButton btnLogin;
	private JLabel lblJoin;
	private JLabel lblFindAccount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setMinimumSize(new Dimension(500, 600));
		setPreferredSize(new Dimension(500, 800));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getLblNewLabel(), BorderLayout.WEST);
		contentPane.add(getLblNewLabel_1(), BorderLayout.EAST);
		contentPane.add(getLblNewLabel_2(), BorderLayout.SOUTH);
		contentPane.add(getLblNewLabel_3(), BorderLayout.NORTH);
		contentPane.add(getPanel_1(), BorderLayout.CENTER);
		
		txtId.requestFocus();
		txtId.setSelectionStart(0);
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setPreferredSize(new Dimension(115, 15));
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setPreferredSize(new Dimension(115, 15));
		}
		return lblNewLabel_1;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("");
			lblNewLabel_2.setPreferredSize(new Dimension(57, 95));
		}
		return lblNewLabel_2;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("Secret Diary");
			lblNewLabel_3.setFont(new Font("Andalus", lblNewLabel_3.getFont().getStyle() | Font.BOLD | Font.ITALIC, 40));
			lblNewLabel_3.setForeground(Color.WHITE);
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_3.setPreferredSize(new Dimension(57, 200));
		}
		return lblNewLabel_3;
	}
	private JPanel getPanel_1() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(192, 192, 192), 3, true));
			panel.setBackground(new Color(102, 102, 102));
			panel.setLayout(null);
			panel.add(getTxtId());
			panel.add(getTxtPwd());
			panel.add(getBtnLogin());
			panel.add(getLblJoin());
			panel.add(getLblFindAccount());
		}
		return panel;
	}
	private JTextField getTxtId() {
		if (txtId == null) {
			txtId = new JTextField();
			txtId.setFont(new Font("Arial Black", Font.PLAIN, 12));
			txtId.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					txtId.setSelectionStart(0);
					txtId.setSelectionEnd(txtId.getText().length());
					txtId.setForeground(Color.BLACK);
				}
			});
			txtId.setForeground(Color.GRAY);
			txtId.setText("insert ID");
			txtId.setBounds(63, 79, 116, 21);
			txtId.setColumns(10);
		}
		return txtId;
	}
	private JTextField getTxtPwd() {
		if (txtPwd == null) {
			txtPwd = new JTextField();
			txtPwd.setFont(new Font("Arial Black", Font.PLAIN, 12));
			txtPwd.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					txtPwd.setSelectionStart(0);
					txtPwd.setSelectionEnd(txtPwd.getText().length());
					txtPwd.setForeground(Color.BLACK);
				}
			});
			txtPwd.setForeground(Color.GRAY);
			txtPwd.setText("insert Password");
			txtPwd.setColumns(10);
			txtPwd.setBounds(63, 110, 116, 21);
		}
		return txtPwd;
	}
	private JButton getBtnLogin() {
		if (btnLogin == null) {
			btnLogin = new JButton("Log In");
			btnLogin.setFont(new Font("Arial Black", Font.BOLD, 14));
			btnLogin.setBounds(63, 159, 116, 30);
		}
		return btnLogin;
	}
	private JLabel getLblJoin() {
		if (lblJoin == null) {
			lblJoin = new JLabel("Join");
			lblJoin.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent me) {
					Join join = new Join();
					join.setVisible(true);
				}
			});
			lblJoin.setForeground(Color.WHITE);
			lblJoin.setFont(new Font("Andalus", Font.BOLD | Font.ITALIC, 10));
			lblJoin.setHorizontalAlignment(SwingConstants.RIGHT);
			lblJoin.setBounds(122, 207, 57, 15);
		}
		return lblJoin;
	}
	private JLabel getLblFindAccount() {
		if (lblFindAccount == null) {
			lblFindAccount = new JLabel("Forgotten your Account?");
			lblFindAccount.setForeground(Color.WHITE);
			lblFindAccount.setFont(new Font("Andalus", Font.BOLD | Font.ITALIC, 10));
			lblFindAccount.setHorizontalAlignment(SwingConstants.RIGHT);
			lblFindAccount.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent me) {
					FindUser fu = new FindUser();
					fu.setVisible(true);
				}
			});
			lblFindAccount.setBounds(43, 220, 137, 15);
		}
		return lblFindAccount;
	}
}
