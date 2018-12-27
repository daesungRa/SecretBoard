package secretBoard;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import dbConn.SecretBoardVo;
import dbConn.UsersDao;
import dbConn.UsersVo;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.SystemColor;

public class View extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JPanel panel;
	
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblNewLabel_2;
	private JPanel panel_3;
	private JLabel lblWelcom;
	private JLabel lblUserInfo;

	// 접속한 유저의 아이디 저장
	private String userId;
	
	// 테이블 모델 정의
	String[] header = {"글번호", "제목", "아이디", "날짜", "공개유무"};
	DefaultTableModel tModel = new DefaultTableModel(header, 0);
	
	// 현재 글에 해당하는 vo 객체 저장
	private SecretBoardVo vo;
	
	private JLabel lblLogout;
	private JPanel mainPane;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblSubject;
	private JPanel panel_5;
	private JButton btnCancel;
	private JPanel panel_4;
	private JPanel panel_6;
	private JLabel lblSerial;
	private JLabel lblTags;
	private JLabel lblUserid;
	private JScrollPane scrollPane;
	private JTextArea mainTextArea;
	private JScrollPane panel_7;
	private JPanel panel_8;
	private JLabel lblDate;
	private JButton btnNewButton;
	private JLabel lblIsPublic;
	
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
					View frame = new View();
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
	public View() {
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
	public View(SecretBoardVo vo) {
		this();
		// 전달받은 아이디 세팅
		this.userId = vo.getId();
		
		// 메인화면 상단에 유저명과 메시지를 출력하기 위해 현재 아이디로 getUserInfo 메서드를 실행함
		UsersVo uvo = new UsersDao().getUserInfo(userId);
		String welcomMsg = " " + uvo.getName() + " 님, 환영합니다";
		lblWelcom.setText(welcomMsg);
		
		// vo 로부터 얻어온 데이터를 view 화면에 뿌려줌
		this.vo = vo;
		lblSubject.setText("     " + vo.getSubject());
		lblSerial.setText(vo.getSerial());
		lblTags.setText(vo.getTags());
		lblUserid.setText(vo.getId() + "  -");
		lblDate.setText(vo.getCdate());
		
		if (vo.getIsPublic()) {
			lblIsPublic.setText("public");
		} else {
			lblIsPublic.setText("private");
		}
		
		mainTextArea.setText("\n\n" + vo.getContent());
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
			panel_2.setPreferredSize(new Dimension(290, 10));
			panel_2.setBackground(new Color(51, 51, 51));
			panel_2.add(getBtnNewButton());
			panel_2.add(getBtnCancel());
		}
		return panel_2;
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
			mainPane.add(getLblSubject(), BorderLayout.NORTH);
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
	private JLabel getLblSubject() {
		if (lblSubject == null) {
			lblSubject = new JLabel("     [제 목]");
			lblSubject.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 16));
			lblSubject.setPreferredSize(new Dimension(76, 50));
		}
		return lblSubject;
	}
	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.setLayout(new BorderLayout(0, 0));
			panel_5.add(getPanel_6(), BorderLayout.NORTH);
			panel_5.add(getScrollPane(), BorderLayout.CENTER);
			panel_5.add(getPanel_7(), BorderLayout.EAST);
		}
		return panel_5;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("돌아가기");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					if (JOptionPane.showConfirmDialog(View.this, "메인 화면으로 돌아가시겠습니까?") == 0) {
						Main main = new Main(userId);
						main.setVisible(true);
						
						View.this.dispose();
					};
				}
			});
			btnCancel.setPreferredSize(new Dimension(90, 30));
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
		}
		return panel_4;
	}
	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			panel_6.setBackground(new Color(210, 210, 210));
			panel_6.setPreferredSize(new Dimension(10, 60));
			panel_6.setLayout(null);
			panel_6.add(getLblSerial());
			panel_6.add(getLblTags());
			panel_6.add(getLblUserid());
			panel_6.add(getLblDate());
			panel_6.add(getLblIsPublic());
		}
		return panel_6;
	}
	private JLabel getLblSerial() {
		if (lblSerial == null) {
			lblSerial = new JLabel("[serial]");
			lblSerial.setForeground(new Color(105, 105, 105));
			lblSerial.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			lblSerial.setBounds(77, 10, 43, 15);
		}
		return lblSerial;
	}
	private JLabel getLblTags() {
		if (lblTags == null) {
			lblTags = new JLabel("[tags]");
			lblTags.setForeground(new Color(95, 158, 160));
			lblTags.setFont(new Font("맑은 고딕", Font.ITALIC, 11));
			lblTags.setBounds(12, 35, 108, 15);
		}
		return lblTags;
	}
	private JLabel getLblUserid() {
		if (lblUserid == null) {
			lblUserid = new JLabel("[userId]");
			lblUserid.setForeground(new Color(105, 105, 105));
			lblUserid.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			lblUserid.setBounds(12, 10, 57, 15);
		}
		return lblUserid;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getMainTextArea());
		}
		return scrollPane;
	}
	private JTextArea getMainTextArea() {
		if (mainTextArea == null) {
			mainTextArea = new JTextArea();
			mainTextArea.setBackground(new Color(210, 210, 210));
			mainTextArea.setEditable(false);
		}
		return mainTextArea;
	}
	private JScrollPane getPanel_7() {
		if (panel_7 == null) {
			panel_7 = new JScrollPane();
			panel_7.setPreferredSize(new Dimension(120, 10));
			panel_7.setViewportView(getPanel_8());
		}
		return panel_7;
	}
	private JPanel getPanel_8() {
		if (panel_8 == null) {
			panel_8 = new JPanel();
			panel_8.setBackground(new Color(210, 210, 210));
		}
		return panel_8;
	}
	private JLabel getLblDate() {
		if (lblDate == null) {
			lblDate = new JLabel("2018/12/27");
			lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDate.setForeground(new Color(105, 105, 105));
			lblDate.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			lblDate.setBounds(525, 35, 77, 15);
		}
		return lblDate;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("글 수정");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new Update(vo).setVisible(true);
					View.this.dispose();
				}
			});
			btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
			btnNewButton.setPreferredSize(new Dimension(80, 30));
		}
		return btnNewButton;
	}
	private JLabel getLblIsPublic() {
		if (lblIsPublic == null) {
			lblIsPublic = new JLabel("[isPublic]");
			lblIsPublic.setHorizontalAlignment(SwingConstants.RIGHT);
			lblIsPublic.setForeground(SystemColor.controlDkShadow);
			lblIsPublic.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			lblIsPublic.setBounds(545, 10, 57, 15);
		}
		return lblIsPublic;
	}
}
