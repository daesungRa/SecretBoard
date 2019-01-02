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
import dbConn.UsersVo;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Join extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JPanel panel;
	private JTextField txtId;
	private JPasswordField txtPwd01;
	private JButton btnLogin;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JPasswordField txtPwd02;
	private JLabel lblPwd02;
	private JLabel lblPwd01;
	private JButton btnCancel;

	// 이메일 검증을 위한 포맷
	private Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + 
			"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	private Matcher matcher;
	
	// 이메일 검증 메서드
	public boolean emailValidate(final String email) {
		matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Join frame = new Join();
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
	public Join() {
		setMinimumSize(new Dimension(550, 500));
		setPreferredSize(new Dimension(500, 800));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
			lblNewLabel.setPreferredSize(new Dimension(95, 15));
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setPreferredSize(new Dimension(95, 15));
		}
		return lblNewLabel_1;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("");
			lblNewLabel_2.setPreferredSize(new Dimension(57, 50));
		}
		return lblNewLabel_2;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("Join to Secret Board");
			lblNewLabel_3.setFont(new Font("Bookman Old Style", lblNewLabel_3.getFont().getStyle() | Font.BOLD | Font.ITALIC, 36));
			lblNewLabel_3.setForeground(Color.WHITE);
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_3.setPreferredSize(new Dimension(57, 140));
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
			panel.add(getTxtPwd01());
			panel.add(getBtnLogin());
			panel.add(getTxtName());
			panel.add(getTxtPhone());
			panel.add(getTxtEmail());
			panel.add(getTxtPwd02());
			panel.add(getLblPwd02());
			panel.add(getLblPwd01());
			panel.add(getBtnCancel());
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
			txtId.setBounds(40, 25, 116, 21);
			txtId.setColumns(10);
		}
		return txtId;
	}
	
	// 비번 입력 필드 1
	private JPasswordField getTxtPwd01() {
		if (txtPwd01 == null) {
			txtPwd01 = new JPasswordField();
			txtPwd01.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
			txtPwd01.addFocusListener(new FocusAdapter() {

				// 포커싱 되면 블럭지정
				@Override
				public void focusGained(FocusEvent e) {
					txtPwd01.setSelectionStart(0);
					txtPwd01.setSelectionEnd(txtPwd01.getPassword().length);
				}
				
				// 포커스 아웃될때 해당 필드가 비어있으면 화면에 비번입력 요청 메시지 출력
				@Override
				public void focusLost(FocusEvent fe) {
					String pwd01 = String.valueOf(txtPwd01.getPassword());
					
					if (pwd01.equals("")) {
						lblPwd01.setText("plz input PASSWORD");
						txtPwd01.requestFocus();
					} else {
						lblPwd01.setText("");
						lblPwd02.setText("input PASSWORD again");
						txtPwd02.setText("");
					}
				}
				
			});
			txtPwd01.setForeground(Color.GRAY);
			txtPwd01.setColumns(10);
			txtPwd01.setBounds(40, 87, 116, 21);
		}
		return txtPwd01;
	}
	private JButton getBtnLogin() {
		if (btnLogin == null) {
			btnLogin = new JButton("Join In");
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					// === 입력된 정보 검증 === 
					// 아이디 - PK
					String id = txtId.getText().trim();
					// 이름 - NN
					String name = txtName.getText().trim();
					// 비번 - NN
					// passwordField 의 반환형은 char[] 이므로 String 으로 변환
					String pwd = String.valueOf(txtPwd02.getPassword());
					// 이메일 - UK
					String email = txtEmail.getText().trim();
					// 폰 - UK
					String phone = txtPhone.getText().trim();
					
					if (id.equals("")) {
						JOptionPane.showMessageDialog(Join.this, "아이디를 입력하세요", "Join Fail", 1);
						txtId.setText("insert ID");
						txtId.requestFocus();
						
						return;
					} else if (name.equals("")) {
						JOptionPane.showMessageDialog(Join.this, "이름을 입력하세요", "Join Fail", 1);
						txtName.setText("insert NAME");
						txtName.requestFocus();
						
						return;
					} else if (pwd.equals("")) {
						JOptionPane.showMessageDialog(Join.this, "비밀번호를 입력하세요", "Join Fail", 1);
						txtPwd01.requestFocus();
						
						return;
					} else if (!emailValidate(email)) {
						JOptionPane.showMessageDialog(Join.this, "이메일 형식이 맞지 않습니다", "Join Fail", 1);
						txtEmail.requestFocus();
						
						return;
					} else if (phone.equals("")) {
						JOptionPane.showMessageDialog(Join.this, "전화번호를 입력하세요", "Join Fail", 1);
						txtPhone.setText("insert PHONE");
						txtPhone.requestFocus();
						
						return;
					}
					
					// 검증된 정보를 기반으로 vo 객체 생성
					UsersVo vo = new UsersVo(id, name, pwd, email, phone);
					
					// 입력된 정보를 전송하여 DB 에 저장
					UsersDao dao = new UsersDao();
					boolean result = dao.insert(vo);
					
					if (result) {
						JOptionPane.showMessageDialog(Join.this, "회원가입 완료", "Join Success", 1);
						Login login = new Login();
						login.setVisible(true);
						Join.this.dispose();
					} else {
						JOptionPane.showMessageDialog(Join.this, "회원가입 실패", "Join Fail", 1);
						// DB 에 정보입력 실패 시 각 필드 초기화 후 포커싱
						txtId.setText("insert ID");
						txtId.setForeground(Color.GRAY);
						txtName.setText("insert NAME");
						txtName.setForeground(Color.GRAY);
						txtPwd01.setText("");
						txtPwd02.setText("");
						txtEmail.setText("insert E-MAIL");
						txtEmail.setForeground(Color.GRAY);
						txtPhone.setText("insert PHONE");
						txtPhone.setForeground(Color.GRAY);
						txtId.requestFocus();
					}
				}
			});
			btnLogin.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
			btnLogin.setBounds(200, 180, 100, 21);
		}
		return btnLogin;
	}
	private JTextField getTxtName() {
		if (txtName == null) {
			txtName = new JTextField();
			txtName.addFocusListener(new FocusAdapter() {
				
				// 해당 텍스트필드가 포커싱되면 기존의 전체 텍스트 블럭처리, 기존의 회색 글씨가 검정색으로 변환
				@Override
				public void focusGained(FocusEvent e) {
					txtName.setSelectionStart(0);
					txtName.setSelectionEnd(txtName.getText().length());
					txtName.setForeground(Color.BLACK);
				}
				
			});
			txtName.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
			txtName.setForeground(Color.GRAY);
			txtName.setText("insert NAME");
			txtName.setBounds(40, 56, 116, 21);
			txtName.setColumns(10);
		}
		return txtName;
	}
	private JTextField getTxtPhone() {
		if (txtPhone == null) {
			txtPhone = new JTextField();
			txtPhone.addKeyListener(new KeyAdapter() {
				
				// 비번입력 후 엔터 누르면 자동으로 Log in 버튼이 눌림
				@Override
				public void keyReleased(KeyEvent ke) {
					if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
						btnLogin.doClick();
					}
				}
				
			});
			txtPhone.addFocusListener(new FocusAdapter() {

				// 해당 텍스트필드가 포커싱되면 기존의 전체 텍스트 블럭처리, 기존의 회색 글씨가 검정색으로 변환
				@Override
				public void focusGained(FocusEvent e) {
					txtPhone.setSelectionStart(0);
					txtPhone.setSelectionEnd(txtPhone.getText().length());
					txtPhone.setForeground(Color.BLACK);
				}
				
			});
			txtPhone.setForeground(Color.GRAY);
			txtPhone.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
			txtPhone.setText("insert PHONE");
			txtPhone.setBounds(40, 180, 116, 21);
			txtPhone.setColumns(10);
		}
		return txtPhone;
	}
	private JTextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new JTextField();
			txtEmail.addFocusListener(new FocusAdapter() {

				// 해당 텍스트필드가 포커싱되면 기존의 전체 텍스트 블럭처리, 기존의 회색 글씨가 검정색으로 변환
				@Override
				public void focusGained(FocusEvent e) {
					txtEmail.setSelectionStart(0);
					txtEmail.setSelectionEnd(txtEmail.getText().length());
					txtEmail.setForeground(Color.BLACK);
				}
				
			});
			txtEmail.setForeground(Color.GRAY);
			txtEmail.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
			txtEmail.setText("insert E-MAIL");
			txtEmail.setBounds(40, 149, 116, 21);
			txtEmail.setColumns(10);
		}
		return txtEmail;
	}
	
	// 비번 입력 필드 2
	private JPasswordField getTxtPwd02() {
		if (txtPwd02 == null) {
			txtPwd02 = new JPasswordField();
			txtPwd02.addFocusListener(new FocusAdapter() {

				// 포커스 아웃될때 해당 필드가 비어있으면 화면에 비번입력 요청 메시지 출력
				@Override
				public void focusLost(FocusEvent fe) {
					String pwd01 = String.valueOf(txtPwd01.getPassword());
					String pwd02 = String.valueOf(txtPwd02.getPassword());
					
					
					if (!pwd01.equals("") && pwd02.equals("")) {
						lblPwd02.setText("plz input PASSWORD again");
						txtPwd02.requestFocus();
					} else if (!pwd01.equals("") && !pwd02.equals("") && !pwd01.equals(pwd02)) {
						lblPwd02.setText("Both passwords are different");
						txtPwd02.requestFocus();
						txtPwd02.setSelectionStart(0);
						txtPwd02.setSelectionEnd(pwd02.length());
					} else {
						lblPwd02.setText("");
					}
					
				}
				
			});
			txtPwd02.setText("");
			txtPwd02.setForeground(Color.GRAY);
			txtPwd02.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
			txtPwd02.setColumns(10);
			txtPwd02.setBounds(40, 118, 116, 21);
		}
		return txtPwd02;
	}
	private JLabel getLblPwd02() {
		if (lblPwd02 == null) {
			lblPwd02 = new JLabel("");
			lblPwd02.setForeground(Color.DARK_GRAY);
			lblPwd02.setFont(new Font("Bookman Old Style", Font.BOLD, 10));
			lblPwd02.setBounds(168, 124, 164, 15);
		}
		return lblPwd02;
	}
	private JLabel getLblPwd01() {
		if (lblPwd01 == null) {
			lblPwd01 = new JLabel("insert PASSWORD");
			lblPwd01.setForeground(Color.DARK_GRAY);
			lblPwd01.setFont(new Font("Bookman Old Style", Font.BOLD, 10));
			lblPwd01.setBounds(168, 91, 145, 15);
		}
		return lblPwd01;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new Login().setVisible(true);
					Join.this.dispose();
				}
			});
			btnCancel.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
			btnCancel.setBounds(200, 211, 100, 21);
		}
		return btnCancel;
	}
}
