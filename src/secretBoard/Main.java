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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridLayout;
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
	String[] header = {"글번호", "제목", "아이디", "날짜", "공개유무"};
	DefaultTableModel tModel = new DefaultTableModel(header, 0);
	
	// 현재 페이지 번호 (페이지 당 글 20 개)
	private int pageNum = 1;
	// 마지막 페이지 번호
	private int endPage = 1;
	
	private JPanel panel_4;
	private JButton btnPre;
	private JButton btnNext;
	private JLabel lblOne;
	private JLabel lblTwo;
	private JLabel lblThree;
	private JLabel lblFour;
	private JLabel lblFive;
	private JLabel lblSix;
	private JLabel lblFin;
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
	private JPanel panel_9;
	private JPanel panel_10;
	private JLabel lblYM;
	private JButton btnNewButton_1;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	private JButton button_6;
	private JButton button_7;
	private JButton button_8;
	private JButton button_9;
	private JButton button_10;
	private JButton button_11;
	private JButton button_12;
	private JButton button_13;
	private JButton button_14;
	private JButton button_15;
	private JButton button_16;
	private JButton button_17;
	private JButton button_18;
	private JButton button_19;
	private JButton button_20;
	private JButton button_21;
	private JButton button_22;
	private JButton button_23;
	private JButton button_24;
	private JButton button_25;
	private JButton button_26;
	private JButton button_27;
	private JButton button_28;
	private JButton button_29;
	private JButton button_30;
	private JButton button_31;
	private JButton btnTags;
	private JLabel lblNewLabel_5;
	
	/*
	 * create method
	 */
	public void search(String id, int pageNum) {
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
			
			// 테이블 테스트를 위해 임의의 데이터 삽입
			Object[] obj = {"0", "test", "testID", "9999/99/99", "private"};
			tModel.addRow(obj);
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
	
	public void setPageLabel(int pageNum) {
		// pageNum 가 6 보다 작다면
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
		if (endPage <= 1) {
			panel_4.add(getLblOne());
			panel_4.add(getBtnNext());
		} else if (endPage == 2) {
			panel_4.add(getLblOne());
			panel_4.add(getLblTwo());
			panel_4.add(getBtnNext());
		} else if (endPage == 3) {
			panel_4.add(getLblOne());
			panel_4.add(getLblTwo());
			panel_4.add(getLblThree());
			panel_4.add(getBtnNext());
		} else if (endPage == 4) {
			panel_4.add(getLblOne());
			panel_4.add(getLblTwo());
			panel_4.add(getLblThree());
			panel_4.add(getLblFin());
			panel_4.add(getBtnNext());
		} else if (endPage == 5) {
			panel_4.add(getLblOne());
			panel_4.add(getLblTwo());
			panel_4.add(getLblThree());
			panel_4.add(getLblFour());
			panel_4.add(getLblFin());
			panel_4.add(getBtnNext());
		} else if (endPage == 6) {
			panel_4.add(getLblOne());
			panel_4.add(getLblTwo());
			panel_4.add(getLblThree());
			panel_4.add(getLblFour());
			panel_4.add(getLblFive());
			panel_4.add(getLblFin());
			panel_4.add(getBtnNext());
		} else if (endPage == 7) {
			panel_4.add(getLblOne());
			panel_4.add(getLblTwo());
			panel_4.add(getLblThree());
			panel_4.add(getLblFour());
			panel_4.add(getLblFive());
			panel_4.add(getLblSix());
			panel_4.add(getLblFin());
			panel_4.add(getBtnNext());
		} else if (endPage > 7) {
			panel_4.add(getLblOne());
			panel_4.add(getLblTwo());
			panel_4.add(getLblThree());
			panel_4.add(getLblFour());
			panel_4.add(getLblFive());
			panel_4.add(getLblSix());
			panel_4.add(getLblFin());
			panel_4.add(getBtnNext());
			lblSix.setText("...");
		}
		// lblFin.setText();
		
		// 메인화면 상단에 유저명과 메시지를 출력하기 위해 현재 아이디로 getUserInfo 메서드를 실행함
		UsersVo uvo = new UsersDao().getUserInfo(userId);
		String welcomMsg = " " + uvo.getName() + " 님, 환영합니다";
		lblWelcom.setText(welcomMsg);
		
		// 현재 유저의 글 목록을 페이지번호대로 출력
		// 생성자에서는 무조건 1 페이지임
		search(userId, pageNum);
		
		// 최초 페이지이므로 이전 버튼 비활성화
		btnPre.setEnabled(false);
		
		// 최초 페이지 1 을 의미하는 lblOne 세팅(배경색, 글자색)
		// 추후 모듈화 해볼 것
		lblOne.setText("1");
		lblOne.setOpaque(true);
		lblOne.setBackground(new Color(169, 169, 169));
		lblOne.setForeground(new Color(0, 0, 0));
		
		// 테이블 테스트를 위해 임의의 데이터 삽입
		Object[] obj = {"0", "test", "testID", "9999/99/99", "private"};
		tModel.addRow(obj);
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
			panel_1.add(getPanel_4(), BorderLayout.WEST);
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
	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setPreferredSize(new Dimension(390, 10));
			panel_4.setBackground(new Color(51, 51, 51));
			panel_4.add(getBtnPre());
		}
		return panel_4;
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
						search(userId, pageNum);
						
						// 다음 버튼이 비활성화라면 활성화
						// 현재 페이지가 마지막 페이지일 경우에만 해당
						if (!btnNext.isEnabled()) {
							btnNext.setEnabled(true);
						}
						
						// 현재 페이지번호를 넘겨주면 그것에 맞는 페이지라벨을 세팅
						setPageLabel(pageNum);
						
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
						search(userId, pageNum);
						
						// 이전 버튼이 비활성화라면 활성화
						// 현재 페이지가 1 일 경우에만 해당
						if (!btnPre.isEnabled()) {
							btnPre.setEnabled(true);
						}
						
						// 현재 페이지번호를 넘겨주면 그것에 맞는 페이지라벨을 세팅
						setPageLabel(pageNum);
						
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
	private JLabel getLblOne() {
		if (lblOne == null) {
			lblOne = new JLabel("1");
			lblOne.setHorizontalAlignment(SwingConstants.CENTER);
			lblOne.setFont(new Font("맑은 고딕", Font.BOLD, 12));
			lblOne.setForeground(Color.WHITE);
			lblOne.setPreferredSize(new Dimension(15, 15));
		}
		return lblOne;
	}
	private JLabel getLblTwo() {
		if (lblTwo == null) {
			lblTwo = new JLabel("2");
			lblTwo.setPreferredSize(new Dimension(15, 15));
			lblTwo.setHorizontalAlignment(SwingConstants.CENTER);
			lblTwo.setForeground(Color.WHITE);
			lblTwo.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		}
		return lblTwo;
	}
	private JLabel getLblThree() {
		if (lblThree == null) {
			lblThree = new JLabel("3");
			lblThree.setPreferredSize(new Dimension(15, 15));
			lblThree.setHorizontalAlignment(SwingConstants.CENTER);
			lblThree.setForeground(Color.WHITE);
			lblThree.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		}
		return lblThree;
	}
	private JLabel getLblFour() {
		if (lblFour == null) {
			lblFour = new JLabel("4");
			lblFour.setPreferredSize(new Dimension(15, 15));
			lblFour.setHorizontalAlignment(SwingConstants.CENTER);
			lblFour.setForeground(Color.WHITE);
			lblFour.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		}
		return lblFour;
	}
	private JLabel getLblFive() {
		if (lblFive == null) {
			lblFive = new JLabel("5");
			lblFive.setPreferredSize(new Dimension(15, 15));
			lblFive.setHorizontalAlignment(SwingConstants.CENTER);
			lblFive.setForeground(Color.WHITE);
			lblFive.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		}
		return lblFive;
	}
	private JLabel getLblSix() {
		if (lblSix == null) {
			lblSix = new JLabel("6");
			lblSix.setPreferredSize(new Dimension(15, 15));
			lblSix.setHorizontalAlignment(SwingConstants.CENTER);
			lblSix.setForeground(Color.WHITE);
			lblSix.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		}
		return lblSix;
	}
	private JLabel getLblFin() {
		if (lblFin == null) {
			lblFin = new JLabel(endPage + "");
			lblFin.setPreferredSize(new Dimension(15, 15));
			lblFin.setHorizontalAlignment(SwingConstants.CENTER);
			lblFin.setForeground(Color.WHITE);
			lblFin.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		}
		return lblFin;
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
			panel_8.add(getPanel_9(), BorderLayout.SOUTH);
			panel_8.add(getPanel_10(), BorderLayout.CENTER);
		}
		return panel_8;
	}
	private JPanel getPanel_9() {
		if (panel_9 == null) {
			panel_9 = new JPanel();
			panel_9.setBackground(new Color(211, 211, 211));
			FlowLayout flowLayout = (FlowLayout) panel_9.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			panel_9.setPreferredSize(new Dimension(10, 40));
			panel_9.add(getButton_1());
			panel_9.add(getButton_2());
			panel_9.add(getButton_3());
			panel_9.add(getButton_4());
			panel_9.add(getButton_5());
			panel_9.add(getButton_6());
			panel_9.add(getButton_7());
			panel_9.add(getButton_8());
			panel_9.add(getButton_9());
			panel_9.add(getButton_10());
			panel_9.add(getButton_11());
			panel_9.add(getButton_12());
			panel_9.add(getButton_13());
			panel_9.add(getButton_14());
			panel_9.add(getButton_15());
			panel_9.add(getButton_16());
			panel_9.add(getButton_17());
			panel_9.add(getButton_18());
			panel_9.add(getButton_19());
			panel_9.add(getButton_20());
			panel_9.add(getButton_21());
			panel_9.add(getButton_22());
			panel_9.add(getButton_23());
			panel_9.add(getButton_24());
			panel_9.add(getButton_25());
			panel_9.add(getButton_26());
			panel_9.add(getButton_27());
			panel_9.add(getButton_28());
			panel_9.add(getButton_29());
			panel_9.add(getButton_30());
			panel_9.add(getButton_31());
		}
		return panel_9;
	}
	private JPanel getPanel_10() {
		if (panel_10 == null) {
			panel_10 = new JPanel();
			panel_10.setBackground(new Color(255, 255, 224));
			FlowLayout flowLayout = (FlowLayout) panel_10.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			panel_10.add(getBtnNewButton_1());
			panel_10.add(getLblYM());
			panel_10.add(getButton());
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
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("<<");
			btnNewButton_1.setBorder(null);
			btnNewButton_1.setBackground(new Color(255, 255, 224));
			btnNewButton_1.setOpaque(true);
			btnNewButton_1.setPreferredSize(new Dimension(30, 20));
		}
		return btnNewButton_1;
	}
	private JButton getButton() {
		if (button == null) {
			button = new JButton(">>");
			button.setPreferredSize(new Dimension(30, 20));
			button.setOpaque(true);
			button.setBorder(null);
			button.setBackground(new Color(255, 255, 224));
		}
		return button;
	}
	private JButton getButton_1() {
		if (button_1 == null) {
			button_1 = new JButton("1");
			button_1.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_1.setPreferredSize(new Dimension(12, 30));
			button_1.setOpaque(true);
			button_1.setBorder(null);
			button_1.setBackground(new Color(211, 211, 211));
		}
		return button_1;
	}
	private JButton getButton_2() {
		if (button_2 == null) {
			button_2 = new JButton("2");
			button_2.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_2.setPreferredSize(new Dimension(12, 30));
			button_2.setOpaque(true);
			button_2.setBorder(null);
			button_2.setBackground(new Color(211, 211, 211));
		}
		return button_2;
	}
	private JButton getButton_3() {
		if (button_3 == null) {
			button_3 = new JButton("3");
			button_3.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_3.setPreferredSize(new Dimension(12, 30));
			button_3.setOpaque(true);
			button_3.setBorder(null);
			button_3.setBackground(new Color(211, 211, 211));
		}
		return button_3;
	}
	private JButton getButton_4() {
		if (button_4 == null) {
			button_4 = new JButton("4");
			button_4.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_4.setPreferredSize(new Dimension(12, 30));
			button_4.setOpaque(true);
			button_4.setBorder(null);
			button_4.setBackground(new Color(211, 211, 211));
		}
		return button_4;
	}
	private JButton getButton_5() {
		if (button_5 == null) {
			button_5 = new JButton("5");
			button_5.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_5.setPreferredSize(new Dimension(12, 30));
			button_5.setOpaque(true);
			button_5.setBorder(null);
			button_5.setBackground(new Color(211, 211, 211));
		}
		return button_5;
	}
	private JButton getButton_6() {
		if (button_6 == null) {
			button_6 = new JButton("6");
			button_6.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_6.setPreferredSize(new Dimension(12, 30));
			button_6.setOpaque(true);
			button_6.setBorder(null);
			button_6.setBackground(new Color(211, 211, 211));
		}
		return button_6;
	}
	private JButton getButton_7() {
		if (button_7 == null) {
			button_7 = new JButton("7");
			button_7.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_7.setPreferredSize(new Dimension(12, 30));
			button_7.setOpaque(true);
			button_7.setBorder(null);
			button_7.setBackground(new Color(211, 211, 211));
		}
		return button_7;
	}
	private JButton getButton_8() {
		if (button_8 == null) {
			button_8 = new JButton("8");
			button_8.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_8.setPreferredSize(new Dimension(12, 30));
			button_8.setOpaque(true);
			button_8.setBorder(null);
			button_8.setBackground(new Color(211, 211, 211));
		}
		return button_8;
	}
	private JButton getButton_9() {
		if (button_9 == null) {
			button_9 = new JButton("9");
			button_9.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_9.setPreferredSize(new Dimension(12, 30));
			button_9.setOpaque(true);
			button_9.setBorder(null);
			button_9.setBackground(new Color(211, 211, 211));
		}
		return button_9;
	}
	private JButton getButton_10() {
		if (button_10 == null) {
			button_10 = new JButton("10");
			button_10.setPreferredSize(new Dimension(16, 30));
			button_10.setOpaque(true);
			button_10.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_10.setBorder(null);
			button_10.setBackground(new Color(211, 211, 211));
		}
		return button_10;
	}
	private JButton getButton_11() {
		if (button_11 == null) {
			button_11 = new JButton("11");
			button_11.setPreferredSize(new Dimension(16, 30));
			button_11.setOpaque(true);
			button_11.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_11.setBorder(null);
			button_11.setBackground(new Color(211, 211, 211));
		}
		return button_11;
	}
	private JButton getButton_12() {
		if (button_12 == null) {
			button_12 = new JButton("12");
			button_12.setPreferredSize(new Dimension(16, 30));
			button_12.setOpaque(true);
			button_12.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_12.setBorder(null);
			button_12.setBackground(new Color(211, 211, 211));
		}
		return button_12;
	}
	private JButton getButton_13() {
		if (button_13 == null) {
			button_13 = new JButton("13");
			button_13.setPreferredSize(new Dimension(16, 30));
			button_13.setOpaque(true);
			button_13.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_13.setBorder(null);
			button_13.setBackground(new Color(211, 211, 211));
		}
		return button_13;
	}
	private JButton getButton_14() {
		if (button_14 == null) {
			button_14 = new JButton("14");
			button_14.setPreferredSize(new Dimension(16, 30));
			button_14.setOpaque(true);
			button_14.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_14.setBorder(null);
			button_14.setBackground(new Color(211, 211, 211));
		}
		return button_14;
	}
	private JButton getButton_15() {
		if (button_15 == null) {
			button_15 = new JButton("15");
			button_15.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchByDate(userId, 15);
				}
			});
			button_15.setPreferredSize(new Dimension(16, 30));
			button_15.setOpaque(true);
			button_15.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_15.setBorder(null);
			button_15.setBackground(new Color(211, 211, 211));
		}
		return button_15;
	}
	private JButton getButton_16() {
		if (button_16 == null) {
			button_16 = new JButton("16");
			button_16.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchByDate(userId, 16);
				}
			});
			button_16.setPreferredSize(new Dimension(16, 30));
			button_16.setOpaque(true);
			button_16.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_16.setBorder(null);
			button_16.setBackground(new Color(211, 211, 211));
		}
		return button_16;
	}
	private JButton getButton_17() {
		if (button_17 == null) {
			button_17 = new JButton("17");
			button_17.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchByDate(userId, 17);
				}
			});
			button_17.setPreferredSize(new Dimension(16, 30));
			button_17.setOpaque(true);
			button_17.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_17.setBorder(null);
			button_17.setBackground(new Color(211, 211, 211));
		}
		return button_17;
	}
	private JButton getButton_18() {
		if (button_18 == null) {
			button_18 = new JButton("18");
			button_18.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchByDate(userId, 18);
				}
			});
			button_18.setPreferredSize(new Dimension(16, 30));
			button_18.setOpaque(true);
			button_18.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_18.setBorder(null);
			button_18.setBackground(new Color(211, 211, 211));
		}
		return button_18;
	}
	private JButton getButton_19() {
		if (button_19 == null) {
			button_19 = new JButton("19");
			button_19.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchByDate(userId, 19);
				}
			});
			button_19.setPreferredSize(new Dimension(16, 30));
			button_19.setOpaque(true);
			button_19.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_19.setBorder(null);
			button_19.setBackground(new Color(211, 211, 211));
		}
		return button_19;
	}
	private JButton getButton_20() {
		if (button_20 == null) {
			button_20 = new JButton("20");
			button_20.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchByDate(userId, 20);
				}
			});
			button_20.setPreferredSize(new Dimension(16, 30));
			button_20.setOpaque(true);
			button_20.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_20.setBorder(null);
			button_20.setBackground(new Color(211, 211, 211));
		}
		return button_20;
	}
	private JButton getButton_21() {
		if (button_21 == null) {
			button_21 = new JButton("21");
			button_21.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchByDate(userId, 21);
				}
			});
			button_21.setPreferredSize(new Dimension(16, 30));
			button_21.setOpaque(true);
			button_21.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_21.setBorder(null);
			button_21.setBackground(new Color(211, 211, 211));
		}
		return button_21;
	}
	private JButton getButton_22() {
		if (button_22 == null) {
			button_22 = new JButton("22");
			button_22.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchByDate(userId, 22);
				}
			});
			button_22.setPreferredSize(new Dimension(16, 30));
			button_22.setOpaque(true);
			button_22.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_22.setBorder(null);
			button_22.setBackground(new Color(211, 211, 211));
		}
		return button_22;
	}
	private JButton getButton_23() {
		if (button_23 == null) {
			button_23 = new JButton("23");
			button_23.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchByDate(userId, 23);
				}
			});
			button_23.setPreferredSize(new Dimension(16, 30));
			button_23.setOpaque(true);
			button_23.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_23.setBorder(null);
			button_23.setBackground(new Color(211, 211, 211));
		}
		return button_23;
	}
	private JButton getButton_24() {
		if (button_24 == null) {
			button_24 = new JButton("24");
			button_24.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchByDate(userId, 24);
				}
			});
			button_24.setPreferredSize(new Dimension(16, 30));
			button_24.setOpaque(true);
			button_24.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_24.setBorder(null);
			button_24.setBackground(new Color(211, 211, 211));
		}
		return button_24;
	}
	private JButton getButton_25() {
		if (button_25 == null) {
			button_25 = new JButton("25");
			button_25.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchByDate(userId, 25);
				}
			});
			button_25.setPreferredSize(new Dimension(16, 30));
			button_25.setOpaque(true);
			button_25.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_25.setBorder(null);
			button_25.setBackground(new Color(211, 211, 211));
		}
		return button_25;
	}
	private JButton getButton_26() {
		if (button_26 == null) {
			button_26 = new JButton("26");
			button_26.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchByDate(userId, 26);
				}
			});
			button_26.setPreferredSize(new Dimension(16, 30));
			button_26.setOpaque(true);
			button_26.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_26.setBorder(null);
			button_26.setBackground(new Color(211, 211, 211));
		}
		return button_26;
	}
	private JButton getButton_27() {
		if (button_27 == null) {
			button_27 = new JButton("27");
			button_27.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchByDate(userId, 27);
				}
			});
			button_27.setPreferredSize(new Dimension(16, 30));
			button_27.setOpaque(true);
			button_27.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_27.setBorder(null);
			button_27.setBackground(new Color(211, 211, 211));
		}
		return button_27;
	}
	private JButton getButton_28() {
		if (button_28 == null) {
			button_28 = new JButton("28");
			button_28.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchByDate(userId, 28);
				}
			});
			button_28.setPreferredSize(new Dimension(16, 30));
			button_28.setOpaque(true);
			button_28.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_28.setBorder(null);
			button_28.setBackground(new Color(211, 211, 211));
		}
		return button_28;
	}
	private JButton getButton_29() {
		if (button_29 == null) {
			button_29 = new JButton("29");
			button_29.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchByDate(userId, 29);
				}
			});
			button_29.setPreferredSize(new Dimension(16, 30));
			button_29.setOpaque(true);
			button_29.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_29.setBorder(null);
			button_29.setBackground(new Color(211, 211, 211));
		}
		return button_29;
	}
	private JButton getButton_30() {
		if (button_30 == null) {
			button_30 = new JButton("30");
			button_30.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchByDate(userId, 30);
				}
			});
			button_30.setPreferredSize(new Dimension(16, 30));
			button_30.setOpaque(true);
			button_30.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_30.setBorder(null);
			button_30.setBackground(new Color(211, 211, 211));
		}
		return button_30;
	}
	private JButton getButton_31() {
		if (button_31 == null) {
			button_31 = new JButton("31");
			button_31.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchByDate(userId, 31);
				}
			});
			button_31.setPreferredSize(new Dimension(16, 30));
			button_31.setOpaque(true);
			button_31.setFont(new Font("Bookman Old Style", Font.ITALIC, 11));
			button_31.setBorder(null);
			button_31.setBackground(new Color(211, 211, 211));
		}
		return button_31;
	}
	private JButton getBtnTags() {
		if (btnTags == null) {
			btnTags = new JButton("Tags");
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
