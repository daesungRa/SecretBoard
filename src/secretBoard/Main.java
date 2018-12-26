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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private JLabel lblNewLabel_5;
	
	/*
	 * create method
	 */
	public void search(String id, int pageNum) {
		// 생성자에서 해당 유저의 글을 페이지별로 검색해 화면에 출력
		SecretBoardDao dao = new SecretBoardDao();
		List<SecretBoardVo> searchList = dao.searchAll(id, pageNum);
		
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
			table.setRowHeight(28);
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
			mainPane.add(getLblNewLabel_5(), BorderLayout.NORTH);
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
		}
		return panel_5;
	}
	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("     전체 글 목록");
			lblNewLabel_5.setFont(new Font("맑은 고딕", Font.BOLD, 16));
			lblNewLabel_5.setPreferredSize(new Dimension(57, 50));
		}
		return lblNewLabel_5;
	}
}
