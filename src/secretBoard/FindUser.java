package secretBoard;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import dbConn.UsersDao;
import dbConn.UsersVo;

import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FindUser extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JPanel panel;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JButton btnFind;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindUser frame = new FindUser();
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
	public FindUser() {
		setMinimumSize(new Dimension(550, 370));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getLblNewLabel(), BorderLayout.WEST);
		contentPane.add(getLblNewLabel_1(), BorderLayout.EAST);
		contentPane.add(getLblNewLabel_2(), BorderLayout.NORTH);
		contentPane.add(getLblNewLabel_3(), BorderLayout.SOUTH);
		contentPane.add(getPanel(), BorderLayout.CENTER);
		
		// txtName 포커싱
		txtName.requestFocus();
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
			lblNewLabel_2 = new JLabel("         Find Account");
			lblNewLabel_2.setFont(new Font("Bookman Old Style", lblNewLabel_2.getFont().getStyle() | Font.BOLD | Font.ITALIC, 30));
			lblNewLabel_2.setForeground(Color.WHITE);
			lblNewLabel_2.setPreferredSize(new Dimension(57, 100));
		}
		return lblNewLabel_2;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("");
			lblNewLabel_3.setPreferredSize(new Dimension(57, 50));
		}
		return lblNewLabel_3;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(240, 240, 240), 3, true));
			panel.setBackground(new Color(102, 102, 102));
			panel.setLayout(null);
			panel.add(getTxtName());
			panel.add(getTxtEmail());
			panel.add(getTxtPhone());
			panel.add(getBtnFind());
			panel.add(getBtnCancel());
		}
		return panel;
	}
	private JTextField getTxtName() {
		if (txtName == null) {
			txtName = new JTextField();
			txtName.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					
					// 포커싱되면 블럽처리
					txtName.setSelectionStart(0);
					txtName.setSelectionEnd(txtName.getText().trim().length());
					txtName.setForeground(Color.black);
					
				}
			});
			txtName.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
			txtName.setForeground(Color.GRAY);
			txtName.setText("insert NAME");
			txtName.setBounds(46, 33, 107, 21);
			txtName.setColumns(10);
		}
		return txtName;
	}
	private JTextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new JTextField();
			txtEmail.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					
					// 포커싱되면 블럭처리
					txtEmail.setSelectionStart(0);
					txtEmail.setSelectionEnd(txtEmail.getText().trim().length());
					txtEmail.setForeground(Color.black);
					
				}
			});
			txtEmail.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
			txtEmail.setForeground(Color.GRAY);
			txtEmail.setText("insert E-MAIL");
			txtEmail.setColumns(10);
			txtEmail.setBounds(46, 64, 107, 21);
		}
		return txtEmail;
	}
	private JTextField getTxtPhone() {
		if (txtPhone == null) {
			txtPhone = new JTextField();
			txtPhone.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent ke) {
					
					// 해당 필들 입력 후 엔터치면 FIND 버튼 누름
					if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
						btnFind.doClick();
					}
					
				}
			});
			txtPhone.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					
					// 포커싱되면 블럭처리
					txtPhone.setSelectionStart(0);
					txtPhone.setSelectionEnd(txtPhone.getText().trim().length());
					txtPhone.setForeground(Color.black);
					
				}
			});
			txtPhone.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
			txtPhone.setForeground(Color.GRAY);
			txtPhone.setText("insert PHONE");
			txtPhone.setColumns(10);
			txtPhone.setBounds(46, 95, 107, 21);
		}
		return txtPhone;
	}
	private JButton getBtnFind() {
		if (btnFind == null) {
			btnFind = new JButton("Find");
			btnFind.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					UsersVo vo = new UsersVo();
					vo.setName(txtName.getText().trim());
					vo.setEmail(txtEmail.getText().trim());
					vo.setPhone(txtPhone.getText().trim());
					UsersVo resultVo = new UsersDao().find(vo);
					
					if (resultVo.getId() != null) {
						JOptionPane.showMessageDialog(FindUser.this, "[검색 결과]\n ID : " + resultVo.getId() + "\n PWD : " + resultVo.getPwd(), "Find User", 1);
						
						// FindUser 닫기
						FindUser.this.dispose();
					} else {
						JOptionPane.showMessageDialog(FindUser.this, "찾는 아이디가 없습니다", "Find User", 1);
						
						// txtName 포커싱
						txtName.requestFocus();
					}
					
				}
			});
			btnFind.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
			btnFind.setBounds(197, 95, 97, 21);
		}
		return btnFind;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new Login().setVisible(true);
					FindUser.this.dispose();
				}
			});
			btnCancel.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
			btnCancel.setBounds(197, 130, 97, 21);
		}
		return btnCancel;
	}
}
