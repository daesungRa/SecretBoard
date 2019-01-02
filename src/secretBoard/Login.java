package secretBoard;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;

import dbConn.UsersDao;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JPanel panel;
	private JTextField txtId;
	private JPasswordField txtPwd;
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
		
		// 실행시 아이디를 입력하는 텍스트 필드가 자동으로 포커싱됨
		txtId.requestFocus();
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
			lblNewLabel_3 = new JLabel("Secret Board");
			lblNewLabel_3.setFont(new Font("Bookman Old Style", lblNewLabel_3.getFont().getStyle() | Font.BOLD | Font.ITALIC, 40));
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
	
	// 아이디 입력 필드
	private JTextField getTxtId() {
		if (txtId == null) {
			txtId = new JTextField();
			txtId.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
			txtId.addFocusListener(new FocusAdapter() {
				
				// 해당 텍스트필드가 포커싱되면 기존의 전체 텍스트 블럭처리, 기존의 회색 글씨가 검정색으로 변환
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
	
	// 비번 입력 필드
	private JPasswordField getTxtPwd() {
		if (txtPwd == null) {
			txtPwd = new JPasswordField();
			txtPwd.addFocusListener(new FocusAdapter() {
				
				// 해당 텍스트필드가 포커싱되면 기존의 전체 텍스트 블럭처리, 기존의 회색 글씨가 검정색으로 변환
				@Override
				public void focusGained(FocusEvent e) {
					txtPwd.setSelectionStart(0);
					txtPwd.setSelectionEnd(txtPwd.getPassword().length);
					txtPwd.setForeground(Color.BLACK);
				}
				
			});
			txtPwd.addKeyListener(new KeyAdapter() {
				
				// 비번입력 후 엔터 누르면 자동으로 Log in 버튼이 눌림
				@Override
				public void keyReleased(KeyEvent ke) {
					if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
						btnLogin.doClick();
					}
				}
				
			});
			txtPwd.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
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
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String id = txtId.getText().trim();
					// passwordField 는 char[] 로 반환되므로 String 으로 변환
					String pwd = String.valueOf(txtPwd.getPassword());
					
					int result = 0;
					
					// 공백 검사
					if (!id.equals("") && !pwd.equals("")) {
						// 입력된 아이디와 패스워드로 로그인 확인
						UsersDao dao = new UsersDao();
						result = dao.login(id, pwd);
					}
					
					if (result == 1) { // 성공
						Main main = new Main(id);
						main.setVisible(true);
						
						// 로그인 성공했다면 현재 페이지는 닫음
						Login.this.dispose();
					} else if (result == 2) { // 비번 실패
						JOptionPane.showMessageDialog(Login.this, "입력하신 비밀번호가 다릅니다.", "Login Fail", 1);
						
						// 일단 id 로 포커싱되게..
						txtPwd.setText("insert Password");
						txtId.requestFocus();
					} else if (result == 3) { // 아이디 실패
						JOptionPane.showMessageDialog(Login.this, "입력하신 아이디가 존재하지 않습니다.", "Login Fail", 1);
						
						// 각 필드 초기화 후 포커싱
						txtId.setText("insert ID");
						txtPwd.setText("insert Password");
						txtId.requestFocus();
					} else if (result == 0) { // 공백
						JOptionPane.showMessageDialog(Login.this, "아이디 혹은 비밀번호를 입력하세요.", "Login Fail", 1);
						
						// 각 필드 초기화 후 포커싱
						txtId.setText("insert ID");
						txtPwd.setText("insert Password");
						txtId.requestFocus();
					} else { // 기타
						JOptionPane.showMessageDialog(Login.this, "로그인 실패.", "Login Fail", 1);
						
						// 각 필드 초기화 후 포커싱
						txtId.setText("insert ID");
						txtPwd.setText("insert Password");
						txtId.requestFocus();
					}
					
				}
			});
			btnLogin.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
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
					
					Login.this.dispose();
				}
			});
			lblJoin.setForeground(Color.WHITE);
			lblJoin.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 9));
			lblJoin.setHorizontalAlignment(SwingConstants.RIGHT);
			lblJoin.setBounds(122, 207, 57, 15);
		}
		return lblJoin;
	}
	private JLabel getLblFindAccount() {
		if (lblFindAccount == null) {
			lblFindAccount = new JLabel("Forgotten your Account?");
			lblFindAccount.setForeground(Color.WHITE);
			lblFindAccount.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 9));
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
