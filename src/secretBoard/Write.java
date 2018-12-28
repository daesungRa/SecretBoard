package secretBoard;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import dbConn.SecretBoardDao;
import dbConn.SecretBoardVo;
import dbConn.UsersDao;
import dbConn.UsersVo;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Write extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JPanel panel;
	
	private JPanel panel_1;
	private JPanel panel_2;
	private JButton btnWrite;
	private JLabel lblNewLabel_2;
	private JPanel panel_3;
	private JLabel lblWelcom;
	private JLabel lblUserInfo;

	// 접속한 유저의 아이디 저장
	private String userId;
	
	// 테이블 모델 정의
	String[] header = {"글번호", "제목", "아이디", "날짜", "공개유무"};
	DefaultTableModel tModel = new DefaultTableModel(header, 0);
	private JLabel lblLogout;
	private JPanel mainPane;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_6;
	private JPanel panel_5;
	private JTextField txtSubject;
	private JTextField txtTags;
	private JScrollPane scrollPane;
	private JTextArea mainTextArea;
	private JButton btnCancel;
	private JPanel panel_4;
	private JCheckBox ckIsPublic;
	private JLabel lblNewLabel_5;
	
	/*
	 * create method
	 */

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Write frame = new Write();
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
	public Write() {
		setMinimumSize(new Dimension(800, 900));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getLblNewLabel(), BorderLayout.WEST);
		contentPane.add(getLblNewLabel_1(), BorderLayout.EAST);
		contentPane.add(getPanel(), BorderLayout.NORTH);
		contentPane.add(getPanel_1(), BorderLayout.SOUTH);
		contentPane.add(getMainPane(), BorderLayout.CENTER);
	}
	
	/*
	 * constructor
	 */
	public Write(String id) {
		this();
		// 전달받은 아이디 세팅
		this.userId = id;
		
		// 메인화면 상단에 유저명과 메시지를 출력하기 위해 현재 아이디로 getUserInfo 메서드를 실행함
		UsersVo uvo = new UsersDao().getUserInfo(userId);
		String welcomMsg = " " + uvo.getName() + " 님, 환영합니다";
		lblWelcom.setText(welcomMsg);
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setPreferredSize(new Dimension(50, 15));
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setPreferredSize(new Dimension(50, 15));
		}
		return lblNewLabel_1;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setPreferredSize(new Dimension(10, 80));
			panel.setBackground(new Color(51, 51, 51));
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getLblNewLabel_2(), BorderLayout.WEST);
			panel.add(getPanel_3(), BorderLayout.EAST);
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(new Color(51, 51, 51));
			panel_1.setPreferredSize(new Dimension(10, 60));
			panel_1.setLayout(new BorderLayout(0, 0));
			panel_1.add(getPanel_2(), BorderLayout.EAST);
		}
		return panel_1;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setPreferredSize(new Dimension(270, 10));
			panel_2.setBackground(new Color(51, 51, 51));
			panel_2.add(getBtnWrite());
			panel_2.add(getBtnCancel());
		}
		return panel_2;
	}
	private JButton getBtnWrite() {
		if (btnWrite == null) {
			btnWrite = new JButton("등 록");
			btnWrite.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					// 입력된 게시글 데이터를 DAO 로 DB 에 저장하고 완료 메시지 출력 후 메인 페이지로 넘어감
					// 글 제목과 내용의 무결성 체크
					String subject = txtSubject.getText();
					String tags = txtTags.getText().trim();
					String content = mainTextArea.getText();
					String id = userId; // id 가 null 인가 체크
					boolean b = ckIsPublic.isSelected(); // toggle 버튼이 클릭되면 true, 아니면 false
					
					// vo 객체 생성
					SecretBoardVo vo = new SecretBoardVo(subject, content, id, b, tags);
					
					// write 의 실행결과 반환 (boolean)
					b = new SecretBoardDao().write(vo);
					
					if (b) {
						JOptionPane.showMessageDialog(Write.this, "새 글 등록 성공", "Write Success", 1);
						
						new Main(id).setVisible(true);
						Write.this.dispose();
					} else {
						JOptionPane.showMessageDialog(Write.this, "새 글 등록 실패", "Write Fail", 2);
						
						// 실패 이유 설명 > 사실 상단 무결성 체크 부분에서 걸러짐
					}
					
				}
			});
			btnWrite.setFont(new Font("맑은 고딕", Font.BOLD, 12));
			btnWrite.setPreferredSize(new Dimension(70, 30));
		}
		return btnWrite;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("Secret Board ");
			lblNewLabel_2.setForeground(Color.WHITE);
			lblNewLabel_2.setFont(new Font("Bookman Old Style", lblNewLabel_2.getFont().getStyle() | Font.BOLD | Font.ITALIC, 24));
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_2.setPreferredSize(new Dimension(210, 15));
		}
		return lblNewLabel_2;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setBackground(new Color(51, 51, 51));
			panel_3.setPreferredSize(new Dimension(290, 10));
			panel_3.setLayout(new BorderLayout(0, 0));
			panel_3.add(getLblWelcom(), BorderLayout.WEST);
			panel_3.add(getLblUserInfo(), BorderLayout.EAST);
			panel_3.add(getLblLogout(), BorderLayout.CENTER);
		}
		return panel_3;
	}
	private JLabel getLblWelcom() {
		if (lblWelcom == null) {
			lblWelcom = new JLabel();
			lblWelcom.setPreferredSize(new Dimension(140, 0));
			lblWelcom.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			lblWelcom.setForeground(Color.WHITE);
		}
		return lblWelcom;
	}
	private JLabel getLblUserInfo() {
		if (lblUserInfo == null) {
			lblUserInfo = new JLabel("[회원정보]");
			lblUserInfo.setPreferredSize(new Dimension(100, 15));
			lblUserInfo.setFont(new Font("맑은 고딕", Font.BOLD, 9));
			lblUserInfo.setForeground(Color.WHITE);
		}
		return lblUserInfo;
	}
	private JLabel getLblLogout() {
		if (lblLogout == null) {
			lblLogout = new JLabel("[로그아웃]");
			lblLogout.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					int confirm = JOptionPane.showConfirmDialog(Write.this, "로그아웃 하시겠습니까?");
					
					if (confirm == 0) {
						// 창닫고 로그인 페이지로 넘어감
						new Login().setVisible(true);
						Write.this.dispose();
					}
					
				}
			});
			lblLogout.setFont(new Font("맑은 고딕", Font.BOLD, 9));
			lblLogout.setForeground(Color.WHITE);
		}
		return lblLogout;
	}
	private JPanel getMainPane() {
		if (mainPane == null) {
			mainPane = new JPanel();
			mainPane.setBackground(new Color(169, 169, 169));
			mainPane.setLayout(new BorderLayout(0, 0));
			mainPane.add(getLblNewLabel_3(), BorderLayout.WEST);
			mainPane.add(getLblNewLabel_4(), BorderLayout.EAST);
			mainPane.add(getLblNewLabel_6(), BorderLayout.NORTH);
			mainPane.add(getPanel_5(), BorderLayout.CENTER);
			mainPane.add(getPanel_4_1(), BorderLayout.SOUTH);
		}
		return mainPane;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("");
			lblNewLabel_3.setPreferredSize(new Dimension(30, 0));
		}
		return lblNewLabel_3;
	}
	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("");
			lblNewLabel_4.setPreferredSize(new Dimension(30, 0));
		}
		return lblNewLabel_4;
	}
	private JLabel getLblNewLabel_6() {
		if (lblNewLabel_6 == null) {
			lblNewLabel_6 = new JLabel("     새 글 쓰기");
			lblNewLabel_6.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 16));
			lblNewLabel_6.setPreferredSize(new Dimension(76, 50));
		}
		return lblNewLabel_6;
	}
	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.setLayout(new BorderLayout(0, 0));
			panel_5.add(getTxtSubject(), BorderLayout.NORTH);
			panel_5.add(getTxtTags(), BorderLayout.CENTER);
			panel_5.add(getScrollPane(), BorderLayout.SOUTH);
		}
		return panel_5;
	}
	private JTextField getTxtSubject() {
		if (txtSubject == null) {
			txtSubject = new JTextField();
			txtSubject.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					if (txtSubject.getText().trim().equals("제목을 입력하세요")) {
						txtSubject.setText("");
						txtSubject.setForeground(new Color(0, 0, 0));
					}
				}
				@Override
				public void focusLost(FocusEvent e) {
					if (txtSubject.getText().trim().equals("")) {
						txtSubject.setForeground(new Color(128, 128, 128));
						txtSubject.setText("제목을 입력하세요");
					}
				}
			});
			txtSubject.setForeground(new Color(128, 128, 128));
			txtSubject.setBackground(new Color(210, 210, 210));
			txtSubject.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			txtSubject.setText("제목을 입력하세요");
			txtSubject.setPreferredSize(new Dimension(6, 32));
			txtSubject.setColumns(10);
		}
		return txtSubject;
	}
	private JTextField getTxtTags() {
		if (txtTags == null) {
			txtTags = new JTextField();
			txtTags.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					if (txtTags.getText().trim().equals("태그를 입력하세요")) {
						txtTags.setText("");
						txtTags.setForeground(new Color(0, 0, 0));
					}
				}
				@Override
				public void focusLost(FocusEvent e) {
					if (txtTags.getText().trim().equals("")) {
						txtTags.setForeground(new Color(128, 128, 128));
						txtTags.setText("태그를 입력하세요");
					}
				}
			});
			txtTags.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			txtTags.setForeground(new Color(128, 128, 128));
			txtTags.setBackground(new Color(210, 210, 210));
			txtTags.setText("태그를 입력하세요");
			txtTags.setPreferredSize(new Dimension(6, 30));
			txtTags.setColumns(10);
		}
		return txtTags;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(2, 550));
			scrollPane.setViewportView(getMainTextArea());
		}
		return scrollPane;
	}
	private JTextArea getMainTextArea() {
		if (mainTextArea == null) {
			mainTextArea = new JTextArea();
			mainTextArea.setBackground(new Color(210, 210, 210));
		}
		return mainTextArea;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("취 소");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					if (JOptionPane.showConfirmDialog(Write.this, "정말 취소하시겠습니까?") == 0) {
						Main main = new Main(userId);
						main.setVisible(true);
						
						Write.this.dispose();
					};
				}
			});
			btnCancel.setPreferredSize(new Dimension(70, 30));
			btnCancel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		}
		return btnCancel;
	}
	private JPanel getPanel_4_1() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setPreferredSize(new Dimension(10, 50));
			panel_4.setBackground(new Color(169, 169, 169));
			panel_4.setLayout(null);
			panel_4.add(getCkIsPublic());
			panel_4.add(getLblNewLabel_5());
		}
		return panel_4;
	}
	private JCheckBox getCkIsPublic() {
		if (ckIsPublic == null) {
			ckIsPublic = new JCheckBox("Public");
			ckIsPublic.setFont(new Font("맑은 고딕", Font.BOLD, 14));
			ckIsPublic.setBackground(new Color(169, 169, 169));
			ckIsPublic.setBounds(569, 6, 74, 38);
		}
		return ckIsPublic;
	}
	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("?");
			lblNewLabel_5.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JOptionPane.showMessageDialog(Write.this, "[공개 글 지정]\n 해당 박스를 체크하면 공개 글로 지정됩니다.\n 체크하지 않을 시 기본 개인 글로 지정됩니다.", "Set Public Content", 1);
				}
			});
			lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_5.setBounds(538, 19, 31, 15);
		}
		return lblNewLabel_5;
	}
}
