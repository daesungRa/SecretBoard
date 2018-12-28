package secretBoard;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Calendar;
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
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private JLabel lblNewLabel_2;
	private JPanel panel_3;
	private JLabel lblWelcom;
	private JLabel lblUserInfo;

	// 접속한 유저의 아이디 저장
	private String userId;
	
	// 테이블 모델 정의
	private String[] header = {"글번호", "제목", "아이디", "날짜", "공개유무"};
	private DefaultTableModel tModel = new DefaultTableModel(header, 0);
	
	// 현재 페이지 번호 (페이지 당 글 20 개)
	private int pageNum = 1;
	// 마지막 페이지 번호
	private int endPage = 1;
	
	private JPanel panelSetPage;
	private JButton btnPre;
	private JButton btnNext;
	private JLabel lblLogout;
	private JPanel mainPane;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JLabel label;
	private JPanel panel_7;
	private JLabel lblMonth;
	private JPanel panel_8;
	private JPanel panelDate;
	private JPanel panel_10;
	private JLabel lblYM;
	private JButton btnPreYM;
	private JButton btnNextYM;
	private JButton btnTags;
	private JLabel lblNewLabel_5;
	
	/*
	 * create method
	 */
	public void searchByPage(String id, int pageNum) {
		// 해당 유저의 글을 페이지별로 검색해 화면에 출력
		SecretBoardDao dao = new SecretBoardDao();
		List<SecretBoardVo> searchList = dao.searchAll(id, pageNum);
		
		// 일단 테이블 초기화
		tModel.setRowCount(0);
		
		if (searchList.size() == 0) {
			JOptionPane.showMessageDialog(Main.this, "글이 존재하지 않습니다", "No Content", 1);
			
			return;
		}
		
		// 반환된 List 에 저장된 글 개수만큼 반복하며 테이블에 추가한다
		for (int i = 0; i < searchList.size(); i++) {
			SecretBoardVo bvo = searchList.get(i);
			if (!bvo.getIsPublic()) { // public 이 아니라면 (false == private)
				Object[] obj = {bvo.getSerial(), bvo.getSubject(), bvo.getId(), bvo.getCdate(), "private"};
				tModel.addRow(obj);
			} else { // true
				Object[] obj = {bvo.getSerial(), bvo.getSubject(), bvo.getId(), bvo.getCdate(), "public"};
				tModel.addRow(obj);
			}
		}
	}
	
	public void searchByDate(String id, int date) {
		// 해당 유저의 글 중 날짜별로 필터링해 페이지별로 검색해 화면에 출력
		SecretBoardDao dao = new SecretBoardDao();
		
		// sql 에 삽입할 날짜포맷 설정 (yyyy/mm/dd)
		String format = lblYM.getText().trim() + "/" + date;
		// System.out.println(format);
		
		List<SecretBoardVo> searchList = dao.searchByDate(id, format);
		
		// 일단 테이블 초기화
		tModel.setRowCount(0);
		
		if (searchList.size() == 0) {
			JOptionPane.showMessageDialog(Main.this, "검색된 글이 없습니다", "No Content", 1);
			
			return;
		}
		
		// 반환된 List 에 저장된 글 개수만큼 반복하며 테이블에 추가한다
		for (int i = 0; i < searchList.size(); i++) {
			SecretBoardVo bvo = searchList.get(i);
			if (!bvo.getIsPublic()) { // public 이 아니라면 (false == private)
				Object[] obj = {bvo.getSerial(), bvo.getSubject(), bvo.getId(), bvo.getCdate(), "private"};
				tModel.addRow(obj);
			} else { // true
				Object[] obj = {bvo.getSerial(), bvo.getSubject(), bvo.getId(), bvo.getCdate(), "public"};
				tModel.addRow(obj);
			}
		}
	}
	
	// 하단의 페이지라벨 세팅
	public void setPage(int pageNum) {
		// 패널 비우기
		panelSetPage.removeAll();
		
		// 이전 버튼 적재
		panelSetPage.add(getBtnPre());
		
		// 존재하는 마지막 페이지까지 라벨링 (라벨은 7 개로 한정)
		if (endPage < 8) {
			for (int i = 1; i <= endPage; i++) {
				JLabel lbl = getPageLabel(userId, i);
				panelSetPage.add(lbl);
			}
		} else {
			for (int i = 1; i <= 7; i++) {
				JLabel lbl = getPageLabel(userId, i);
				panelSetPage.add(lbl);
				
				if (i == 6 && endPage > 7) {
					lbl.setText("...");
				} else if (i == 7) {
					lbl.setText(endPage + "");
				}
			}
		}
		
		// 현재 페이지만 특수처리
		
		// 다음 버튼 적재
		panelSetPage.add(getBtnNext());
	}
	
	// 유저의 아이디와 페이지가 세팅된 라벨 생성
	public JLabel getPageLabel(String userId, int page) {
		JLabel lbl = new JLabel(page + "");
		lbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchByPage(userId, page);
			}
		});
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lbl.setForeground(Color.WHITE);
		lbl.setPreferredSize(new Dimension(15, 15));
		
		return lbl;
	}
	
	// 상단의 달력 세팅
	public void setCal(int year, int month) {
		// Calendar 인스턴스를 얻어와 매개변수로 입력된 연월 세팅
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1); // 월은 -1
		
		// 해당 년월의 마지막 일자 세팅
		int lastDay = cal.getActualMaximum(Calendar.DATE);
		
		// 세팅된 날짜를 화면에 표현
		for (int i = 1; i <= lastDay; i++) {
			// 각 날짜에 맞는 버튼을 생성해 panelDate 에 세팅
			JButton btn = getDateBtn(userId, i);
			panelDate.add(btn);
		}
	}
	
	// 유저의 아이디와 날짜가 세팅된 버튼 생성
	public JButton getDateBtn(String userId, int date) {
		JButton btn = new JButton(date + "");
		btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchByDate(userId, date);
				}
		});
		btn.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
		
		if (date < 10) {
			btn.setPreferredSize(new Dimension(12, 30)); // 1 - 9
		} else {
			btn.setPreferredSize(new Dimension(16, 30)); // 10 - 31
		}
		
		btn.setOpaque(true);
		btn.setBorder(null);
		btn.setBackground(new Color(211, 211, 211));
		
		return btn;
	}
	
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
		contentPane.add(getPanel_1(), BorderLayout.SOUTH);
		contentPane.add(getMainPane(), BorderLayout.CENTER);
	}
	
	/*
	 * constructor
	 */
	public Main(String id) {
		this();
		// 전달받은 아이디 세팅
		this.userId = id;
		
		// 해당 유저의 전체 글 수를 얻어와 마지막 페이지 세팅
		// 만약 총 81 개 글이라면 5 페이지까지 나와야 함 > (81 - 1) / 20 + 1 = 5
		// 만약 총 80 개 글이라면 4 페이지까지 나와야 함 > (80 - 1) / 20 + 1 = 4
		endPage = (((new SecretBoardDao().getTotContent(userId)) - 1) / 20) + 1;
		// endPage 에 따라서 표현되는 페이지라벨 개수 변동
		setPage(pageNum);
		
		// 메인화면 상단에 유저명과 메시지를 출력하기 위해 현재 아이디로 getUserInfo 메서드를 실행함
		UsersVo uvo = new UsersDao().getUserInfo(userId);
		String welcomMsg = " " + uvo.getName() + " 님, 환영합니다";
		lblWelcom.setText(welcomMsg);
		
		// 현재 유저의 글 목록을 페이지번호대로 출력
		// 생성자에서는 무조건 1 페이지임
		searchByPage(userId, pageNum);
		
		// 최초 페이지이므로 이전 버튼 비활성화
		btnPre.setEnabled(false);
		
		// 달력 세팅
		setCal(2018, 12);
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
			
			// 생성한 모델을 테이블에 적재
			table = new JTable(tModel);
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					// 원하는 글(행)을 클릭 시 그 행에 해당하는 serial 값을 얻어와 dao 의 view 메서드를 실행,
					// 쿼리의 결과를 vo 객체에 담아 View.java 를 생성해 값을 vo 값을 뿌려줌
					int row = table.getSelectedRow();
					String serial = "-1";
					serial = (String) tModel.getValueAt(row, 0);
					// System.out.println(serial);
					
					// serial 이 -1 이 아니라면
					if (!serial.equals("-1")) {
						SecretBoardVo vo = new SecretBoardDao().view(serial);
						new View(vo).setVisible(true);
						
						Main.this.dispose();
					} else {
						JOptionPane.showMessageDialog(Main.this, "해당 글이 존재하지 않습니다", "Can't Found", 1);
					}
					
					
				}
			});
			
			// 테이블 컬럼 사이즈 조정
			TableColumn column = table.getColumnModel().getColumn(0); // serial
			column.setPreferredWidth(5);
			column = table.getColumnModel().getColumn(2); // id
			column.setPreferredWidth(5);
			column = table.getColumnModel().getColumn(3); // cdate
			column.setPreferredWidth(10);
			column = table.getColumnModel().getColumn(4); // ispublic
			column.setPreferredWidth(5);
			
			
			// 테이블 헤더 background, border, dimension 설정
			JTableHeader tHeader = table.getTableHeader();
			tHeader.setBackground(new Color(230, 230, 230));
			tHeader.setForeground(new Color(0, 0, 0));
			tHeader.setBorder(new LineBorder(new Color(169, 169, 169), 1, true));
			tHeader.setPreferredSize(new Dimension(0, 30));
			
			// 테이블 바디 background, 행 dimension 설정
			table.setOpaque(true);
			table.setFillsViewportHeight(true);
			table.setFont(new Font("맑은 고딕", Font.BOLD, 12));
			table.setBackground(new Color(210, 210, 210));
			table.setRowHeight(24);
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
			panel_1.add(getPanelSetPage(), BorderLayout.WEST);
		}
		return panel_1;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setPreferredSize(new Dimension(240, 10));
			panel_2.setBackground(new Color(51, 51, 51));
			panel_2.add(getBtnNewButton());
		}
		return panel_2;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("새글 쓰기");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Write write = new Write(userId);
					write.setVisible(true);
					
					Main.this.dispose();
				}
			});
			btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
			btnNewButton.setPreferredSize(new Dimension(110, 30));
		}
		return btnNewButton;
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
	private JPanel getPanelSetPage() {
		if (panelSetPage == null) {
			panelSetPage = new JPanel();
			panelSetPage.setPreferredSize(new Dimension(390, 10));
			panelSetPage.setBackground(new Color(51, 51, 51));
			panelSetPage.add(getBtnPre());
		}
		return panelSetPage;
	}
	private JButton getBtnPre() {
		if (btnPre == null) {
			btnPre = new JButton("이전");
			btnPre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					// 현재 페이지가 1 보다 크다면
					if (pageNum > 1) {
						// 현재 페이지에서 1 감소시키고 페이지에 해당하는 글 목록 출력
						pageNum --;
						searchByPage(userId, pageNum);
						
						// 다음 버튼이 비활성화라면 활성화
						// 현재 페이지가 마지막 페이지일 경우에만 해당
						if (!btnNext.isEnabled()) {
							btnNext.setEnabled(true);
						}
						
						// 현재 페이지번호를 넘겨주면 그것에 맞는 페이지라벨을 세팅
						setPage(pageNum);
						
						// 현재 페이지가 1 이 되면 이전 버튼 비활성화
						if (pageNum == 1) {
							btnPre.setEnabled(false);
						}
					}
				}
			});
			btnPre.setPreferredSize(new Dimension(60, 30));
			btnPre.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		}
		return btnPre;
	}
	private JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton("다음");
			btnNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					// 마지막 페이지가 1이 아니고 현재 페이지가 마지막 페이지보다 작다면
					if (endPage != 1 && pageNum < endPage) {
						// 현재 페이지에서 1 증가시키고 페이지에 해당하는 글 목록 출력
						pageNum ++;
						searchByPage(userId, pageNum);
						
						// 이전 버튼이 비활성화라면 활성화
						// 현재 페이지가 1 일 경우에만 해당
						if (!btnPre.isEnabled()) {
							btnPre.setEnabled(true);
						}
						
						// 현재 페이지번호를 넘겨주면 그것에 맞는 페이지라벨을 세팅
						setPage(pageNum);
						
						// 현재 페이지가 마지막 페이지라면 다음 버튼 비활성화
						if (pageNum == endPage) {
							btnNext.setEnabled(false);
						}
					}
					
				}
			});
			btnNext.setPreferredSize(new Dimension(60, 30));
			btnNext.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		}
		return btnNext;
	}
	private JLabel getLblLogout() {
		if (lblLogout == null) {
			lblLogout = new JLabel("[로그아웃]");
			lblLogout.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					int confirm = JOptionPane.showConfirmDialog(Main.this, "로그아웃 하시겠습니까?");
					
					if (confirm == 0) {
						// 창닫고 로그인 페이지로 넘어감
						new Login().setVisible(true);
						Main.this.dispose();
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
			mainPane.add(getScrollPane(), BorderLayout.CENTER);
			mainPane.add(getLblNewLabel_3(), BorderLayout.WEST);
			mainPane.add(getLblNewLabel_4(), BorderLayout.EAST);
			mainPane.add(getPanel_5(), BorderLayout.SOUTH);
			mainPane.add(getPanel_6(), BorderLayout.NORTH);
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
	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.setPreferredSize(new Dimension(10, 50));
			panel_5.setBackground(new Color(169, 169, 169));
			panel_5.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			panel_5.add(getBtnTags());
			panel_5.add(getLblNewLabel_5());
		}
		return panel_5;
	}
	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			panel_6.setPreferredSize(new Dimension(10, 120));
			panel_6.setLayout(new BorderLayout(0, 0));
			panel_6.add(getLabel(), BorderLayout.SOUTH);
			panel_6.add(getPanel_7(), BorderLayout.CENTER);
		}
		return panel_6;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("     글 목록");
			label.setOpaque(true);
			label.setBackground(new Color(169, 169, 169));
			label.setPreferredSize(new Dimension(57, 50));
			label.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		}
		return label;
	}
	private JPanel getPanel_7() {
		if (panel_7 == null) {
			panel_7 = new JPanel();
			panel_7.setLayout(new BorderLayout(0, 0));
			panel_7.add(getLblMonth(), BorderLayout.WEST);
			panel_7.add(getPanel_8_1(), BorderLayout.CENTER);
		}
		return panel_7;
	}
	private JLabel getLblMonth() {
		if (lblMonth == null) {
			lblMonth = new JLabel();
			lblMonth.setOpaque(true);
			lblMonth.setBackground(new Color(211, 211, 211));
			lblMonth.setHorizontalAlignment(SwingConstants.CENTER);
			lblMonth.setText("12");
			lblMonth.setPreferredSize(new Dimension(50, 0));
			lblMonth.setFont(new Font("Bookman Old Style", lblMonth.getFont().getStyle() | Font.BOLD | Font.ITALIC, 28));
		}
		return lblMonth;
	}
	private JPanel getPanel_8_1() {
		if (panel_8 == null) {
			panel_8 = new JPanel();
			panel_8.setLayout(new BorderLayout(0, 0));
			panel_8.add(getPanelDate(), BorderLayout.SOUTH);
			panel_8.add(getPanel_10(), BorderLayout.CENTER);
		}
		return panel_8;
	}
	private JPanel getPanelDate() {
		if (panelDate == null) {
			panelDate = new JPanel();
			panelDate.setBackground(new Color(211, 211, 211));
			FlowLayout fl_panelDate = (FlowLayout) panelDate.getLayout();
			fl_panelDate.setAlignment(FlowLayout.LEFT);
			panelDate.setPreferredSize(new Dimension(10, 40));
		}
		return panelDate;
	}
	private JPanel getPanel_10() {
		if (panel_10 == null) {
			panel_10 = new JPanel();
			panel_10.setBackground(new Color(255, 255, 224));
			FlowLayout flowLayout = (FlowLayout) panel_10.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			panel_10.add(getBtnPreYM());
			panel_10.add(getLblYM());
			panel_10.add(getBtnNextYM());
		}
		return panel_10;
	}
	private JLabel getLblYM() {
		if (lblYM == null) {
			lblYM = new JLabel("2018/12");
			lblYM.setHorizontalAlignment(SwingConstants.CENTER);
			lblYM.setPreferredSize(new Dimension(60, 20));
		}
		return lblYM;
	}
	private JButton getBtnPreYM() {
		if (btnPreYM == null) {
			btnPreYM = new JButton("<<");
			btnPreYM.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// 연월 문자열을 얻어와 / 를 기준으로 split
					String[] str = lblYM.getText().split("/");
					int year = Integer.parseInt(str[0]);
					int month = Integer.parseInt(str[1]);
					
					// 1 월이라면 작년 12 월로 세팅
					if (month == 1) {
						year --;
						month = 12;
					} else {
						month --;
					}
					
					// 변경된 연월로 연월 라벨 세팅
					lblYM.setText(year + "/" + month);
					lblMonth.setText(month + "");
					
					// 변경된 연월로 달력 세팅
					setCal(year, month);
				}
			});
			btnPreYM.setBorder(null);
			btnPreYM.setBackground(new Color(255, 255, 224));
			btnPreYM.setOpaque(true);
			btnPreYM.setPreferredSize(new Dimension(30, 20));
		}
		return btnPreYM;
	}
	private JButton getBtnNextYM() {
		if (btnNextYM == null) {
			btnNextYM = new JButton(">>");
			btnNextYM.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// 연월 문자열을 얻어와 / 를 기준으로 split
					String[] str = lblYM.getText().split("/");
					int year = Integer.parseInt(str[0]);
					int month = Integer.parseInt(str[1]);
					
					// 12 월이라면 내년 1 월로 세팅
					if (month == 12) {
						year ++;
						month = 1;
					} else {
						month ++;
					}
					
					// 변경된 연월로 연월 라벨 세팅
					lblYM.setText(year + "/" + month);
					lblMonth.setText(month + "");
					
					// 변경된 연월로 달력 세팅
					setCal(year, month);
				}
			});
			btnNextYM.setPreferredSize(new Dimension(30, 20));
			btnNextYM.setOpaque(true);
			btnNextYM.setBorder(null);
			btnNextYM.setBackground(new Color(255, 255, 224));
		}
		return btnNextYM;
	}
	private JButton getBtnTags() {
		if (btnTags == null) {
			btnTags = new JButton("Tags");
			btnTags.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnTags.setFont(new Font("\uB9D1\uC740 \uACE0\uB515", btnTags.getFont().getStyle() | Font.BOLD, 12));
			btnTags.setPreferredSize(new Dimension(50, 30));
			btnTags.setOpaque(true);
			btnTags.setBorder(null);
			btnTags.setBackground(new Color(190, 190, 190));
		}
		return btnTags;
	}
	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("");
			lblNewLabel_5.setPreferredSize(new Dimension(40, 30));
		}
		return lblNewLabel_5;
	}
}
