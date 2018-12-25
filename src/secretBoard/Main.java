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

import dbConn.SecretBoardDao;
import dbConn.SecretBoardVo;
import dbConn.UsersDao;
import dbConn.UsersVo;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Main extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTable table;
	
	private JPanel panel_1;
	private JPanel panel_2;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_2;
	private JPanel panel_3;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;

	// 접속한 유저의 아이디 저장
	private String userId;
	
	// 테이블 모델 정의
	String[] header = {"글번호", "제목", "아이디", "날짜", "공개유무"};
	DefaultTableModel tModel = new DefaultTableModel(header, 0);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
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
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
		contentPane.add(getPanel_1(), BorderLayout.SOUTH);
	}
	
	/*
	 * constructor
	 */
	public Main(String id) {
		this();
		// 전달받은 아이디 세팅
		this.userId = id;
		
		// 메인화면 상단에 유저명과 메시지를 출력하기 위해 현재 아이디로 getUserInfo 메서드를 실행함
		UsersVo uvo = new UsersDao().getUserInfo(userId);
		String welcomMsg = " " + uvo.getName() + " 님, 환영합니다";
		lblNewLabel_3.setText(welcomMsg);
		
		// 생성자에서 해당 유저의 모든 글을 검색해 화면에 출력
		SecretBoardDao dao = new SecretBoardDao();
		List<SecretBoardVo> searchList = dao.searchAll(id);
		
		// 일단 테이블 초기화
		tModel.setRowCount(0);
		
		// 반환된 List 에 저장된 글 개수만큼 반복하며 테이블에 추가한다
		for (int i = 0; i < searchList.size(); i++) {
			SecretBoardVo bvo = searchList.get(i);
			if (bvo.getIsPublic() == 0) { // 반환값이 0 이면 private
				Object[] obj = {bvo.getSerial(), bvo.getSubject(), bvo.getId(), bvo.getCdate(), "private"};
				tModel.addRow(obj);
			} else { // else, 반환값이 1
				Object[] obj = {bvo.getSerial(), bvo.getSubject(), bvo.getId(), bvo.getCdate(), "public"};
				tModel.addRow(obj);
			}
		}
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
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBorder(new LineBorder(new Color(169, 169, 169), 3, true));
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	private JTable getTable() {
		if (table == null) {
			
			// 생성한 모델 적재
			table = new JTable(tModel);
			
			// 테이블 헤더 color, border, dimension 설정
			JTableHeader tHeader = table.getTableHeader();
			tHeader.setBackground(new Color(128, 128, 128));
			tHeader.setBorder(new LineBorder(new Color(169, 169, 169), 1, true));
			tHeader.setPreferredSize(new Dimension(0, 30));
			
			// 테이블 바디 색 설정
			table.setOpaque(true);
			table.setFillsViewportHeight(true);
			table.setBackground(new Color(169, 169, 169));
		}
		return table;
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
			panel_2.setPreferredSize(new Dimension(330, 10));
			panel_2.setBackground(new Color(51, 51, 51));
			panel_2.add(getBtnNewButton());
			panel_2.add(getBtnNewButton_1());
		}
		return panel_2;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("새글 쓰기");
			btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
			btnNewButton.setPreferredSize(new Dimension(110, 30));
		}
		return btnNewButton;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("글 수정");
			btnNewButton_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
			btnNewButton_1.setPreferredSize(new Dimension(110, 30));
		}
		return btnNewButton_1;
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
			panel_3.setPreferredSize(new Dimension(250, 10));
			panel_3.setLayout(new BorderLayout(0, 0));
			panel_3.add(getLblNewLabel_3(), BorderLayout.CENTER);
			panel_3.add(getLblNewLabel_4(), BorderLayout.EAST);
		}
		return panel_3;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel();
			lblNewLabel_3.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			lblNewLabel_3.setForeground(Color.WHITE);
		}
		return lblNewLabel_3;
	}
	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("[회원정보 수정]");
			lblNewLabel_4.setPreferredSize(new Dimension(120, 15));
			lblNewLabel_4.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
			lblNewLabel_4.setForeground(Color.WHITE);
		}
		return lblNewLabel_4;
	}
}
