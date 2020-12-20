package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.DBConnect;
import model.Markets;
import model.Review;
import model.Singleton;

public class DetailsController implements Initializable {
	@FXML
	private Button backbt;
	@FXML
	private Button Reviewbt;
	@FXML
	private Label Mname;
	@FXML
	private Label Maddress;
	@FXML
	private Label Mtel;
	@FXML
	private Label Mtime;
	@FXML
	private Label totalAVG;
	@FXML
	private ImageView TotalAVG1;
	@FXML
	private ImageView TotalAVG2;
	@FXML
	private ImageView TotalAVG3;
	@FXML
	private ImageView TotalAVG4;
	@FXML
	private ImageView TotalAVG5;
	@FXML
	private ImageView AVG1_1;
	@FXML
	private ImageView AVG1_2;
	@FXML
	private ImageView AVG1_3;
	@FXML
	private ImageView AVG1_4;
	@FXML
	private ImageView AVG1_5;
	@FXML
	private ImageView AVG2_1;
	@FXML
	private ImageView AVG2_2;
	@FXML
	private ImageView AVG2_3;
	@FXML
	private ImageView AVG2_4;
	@FXML
	private ImageView AVG2_5;
	@FXML
	private ImageView AVG3_1;
	@FXML
	private ImageView AVG3_2;
	@FXML
	private ImageView AVG3_3;
	@FXML
	private ImageView AVG3_4;
	@FXML
	private ImageView AVG3_5;
	@FXML
	private ImageView AVG4_1;
	@FXML
	private ImageView AVG4_2;
	@FXML
	private ImageView AVG4_3;
	@FXML
	private ImageView AVG4_4;
	@FXML
	private ImageView AVG4_5;
	@FXML
	private Label ReviewCount;
	@FXML
	private VBox reviewbox;

	List<Review> list;
	DBConnect connect = new DBConnect();
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;

// 주소값 문자열로 입력
	String imgAddress = "img/Mask.png";
	String imgAddress2 = "img/Mask2.png";
// 이미지 객체를 만드는데 imgAddress,imgAddress2값으로 만듬 이걸 image,image2 변수에 넣음
	Image image = new Image(imgAddress);
	Image image2 = new Image(imgAddress2);

//보여지는 곳 이니셜라이즈(초기화하다)
		@Override
		public void initialize(URL location, ResourceBundle resources) {

			//어레이 리스트 생성 (총 평균) 이미지 변환
			ArrayList<ImageView> totalimg = new ArrayList<ImageView>();

			totalimg.add(TotalAVG1);
			totalimg.add(TotalAVG2);
			totalimg.add(TotalAVG3);
			totalimg.add(TotalAVG4);
			totalimg.add(TotalAVG5);
			totalimg(totalimg);//밑에서 구현한 totalimg메소드 매개변수에 totalimg객체를 넣음

			//어레이 리스트 생성 (거리두기 평균)
			ArrayList<ImageView> type1 = new ArrayList<ImageView>();

			type1.add(AVG1_1);
			type1.add(AVG1_2);
			type1.add(AVG1_3);
			type1.add(AVG1_4);
			type1.add(AVG1_5);
			img1(type1);//밑에서 구현한 img1메소드 매개변수에 type1객체를 넣음

			//어레이 리스트 생성 (손소독제 평균)
			ArrayList<ImageView> type2 = new ArrayList<ImageView>();

			type2.add(AVG2_1);
			type2.add(AVG2_2);
			type2.add(AVG2_3);
			type2.add(AVG2_4);
			type2.add(AVG2_5);
			img2(type2);//밑에서 구현한 img2메소드 매개변수에 type2객체를 넣음

			//어레이 리스트 생성 (체온측정 평균)
			ArrayList<ImageView> type3 = new ArrayList<ImageView>();

			type3.add(AVG3_1);
			type3.add(AVG3_2);
			type3.add(AVG3_3);
			type3.add(AVG3_4);
			type3.add(AVG3_5);
			img3(type3);//밑에서 구현한 img3메소드 매개변수에 type3객체를 넣음

			//어레이 리스트 생성 (방문일지 평균)
			ArrayList<ImageView> type4 = new ArrayList<ImageView>();

			type4.add(AVG4_1);
			type4.add(AVG4_2);
			type4.add(AVG4_3);
			type4.add(AVG4_4);
			type4.add(AVG4_5);
			img4(type4);//밑에서 구현한 img4메소드 매개변수에 type4객체를 넣음

			// 리뷰를 새로 쓸때마다 리뷰카드에 내용을 담아서  보여지게 구현
			list = ReviewList();
			showReview(list);

			//가게 이름,주소,전화번호,영업시간 보여지게 구현
			Markets nbean = getMarkets(); // getMarkets()는 밑에서 구현한 메소드 이름이다.
			Singleton.setMtname(nbean.getNAME()); // 싱글톤으로 현재 가게이름 보냄 (리뷰쓰기에서 가게이름 받아오기 위해서)
			Mname.setText(nbean.getNAME());// getMarkets()에서 보낸 NAME필드 변수값을 받은 다음 해당 아이디로 출력.
			Maddress.setText(nbean.getADDRESS());// getMarkets()에서 보낸 ADDRESS필드 변수값을 받은 다음 해당 아이디로 출력.
			Mtel.setText(nbean.getTEL());// getMarkets()에서 보낸 TEL필드 변수값을 받은 다음 해당 아이디로 출력.
			Mtime.setText(nbean.getTIME());// getMarkets()에서 보낸 TIME필드 변수값을 받은 다음 해당 아이디로 출력.
			//리뷰갯수 보여지게 구현
			Markets count = reviewcount();// reviewcount()는 밑에서 구현한 메소드 이름이다.
			ReviewCount.setText(Integer.toString(count.getReviewCount()));// reviewcount()에서 보낸 ReviewCount필드 변수값을 문자형으로 바꿔서 받은 다음 해당 아이디로 출력
			//리뷰 각요소들의 평균을 총 평균으로 보여지게 구현
			Markets AVG = TotalAVG();// TotalAVG()는 밑에서 구현한 메소드 이름이다.
			totalAVG.setText(Integer.toString(AVG.getTotalAVG()));// TotalAVG()에서 보낸 TotalAVG필드 변수값을 받은 다음 해당 아이디로 출력.

//			imgAVG();//각 요소들의 이미지들이 평균값을 참조하여 이미지가 바뀌게 구현(void형 메소드로 구현 맨밑에 있음) 수정해서 참조겸 주석처리함
//			imgtotalAVG();//총 평균값을 참조하여 이미지가 바뀌게 구현(void형 메소드로 구현 맨밑에 있음) 수정해서 참조겸 주석처리함
		}
//뒤로 돌아가기 메소드
	@FXML
	public void GoBack(ActionEvent event) {
		backbt.getScene().getWindow().hide(); // 현재 페이지를 안보이게 닫기

		Stage ShopList = new Stage();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/ShopListCard.fxml"));
			Scene scene = new Scene(root);
			ShopList.setScene(scene);
			ShopList.setTitle("가게 리스트");
			ShopList.show();
		} catch (IOException e) {
			System.out.println("리뷰쓰러가기 오류 발생" + e.getMessage());
		}
	}
//리뷰쓰러가기 메소드
	@FXML
	public void GoReview(ActionEvent event) {
		Reviewbt.getScene().getWindow().hide(); // 현재 페이지를 안보이게 닫기

		Stage review = new Stage();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/review.fxml"));
			Scene scene = new Scene(root);
			review.setScene(scene);
			review.setTitle("리뷰 쓰기");
			review.show();
		} catch (IOException e) {
			System.out.println("리뷰쓰러가기 오류 발생" + e.getMessage());
		}
	}
// 현재 리뷰개수 받아오는 메소드
	public Markets reviewcount(){
		// 마켓 아이디를 참조하여 리뷰 갯수를 물어보는 질의
		String sql = "select COUNT(*) FROM maskrate where marketid = ?";
		conn = connect.getConnection();
		Markets count = new Markets();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,Singleton.getMartid());// ShopController에서 싱글톤으로 보낸 현재 마켓 아이디를 싱글톤으로 불러와 현재 마켓아이디를 참조하게 구현
		 	rs = pstmt.executeQuery(); // 접속된 DB에서 쿼리를 실행하고 결과를 리턴(현재 마켓의 리뷰 갯수를 불러와서 rs에 담음)
		 	if(rs.next()){
		 		count.setReviewCount(rs.getInt(1));// 현재 마켓의 리뷰 갯수를 모델패키지에 있는 Markets클래스의 ReviewCount필드 변수로 보냄
		 	}
		} catch (Exception e) {
			System.out.println("DB에서 리뷰횟수 불러오기 실패:"+e.getMessage());
		}
		return count;
	}
// 마켓 이름 주소 전화번호 영업시간 가져오기 구현
		public Markets getMarkets() {
			// 마켓아이디를 참조하여 마켓 이름 주소 전화번호 영업시간을 물어보는 질의
			String sql = "SELECT name,address,tel,time FROM market where marketid =? ";
			conn = connect.getConnection(); // 위에서 만든 DB연결 메소드
			Markets MT = new Markets();
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,Singleton.getMartid());// ShopController에서 싱글톤으로 보낸 현재 마켓 아이디를 싱글톤으로 불러와 현재 마켓아이디를 참조하게 구현
				rs = pstmt.executeQuery(); // 접속된 DB에서 쿼리를 실행하고 결과를 리턴(현재 마켓의 이름,주소,전화번호,영업시간을 불러와서 rs에 담음)
				if (rs.next()) {
					MT.setNAME(rs.getString(1));// 현재 마켓의 이름을 모델패키지에 있는 Markets클래스의 NAME필드 변수로 보냄
					MT.setADDRESS(rs.getString(2));// 현재 마켓의 주소를 모델패키지에 있는 Markets클래스의 ADDRESS필드 변수로 보냄
					MT.setTEL(rs.getString(3));// 현재 마켓의 전화번호를 모델패키지에 있는 Markets클래스의 TEL필드 변수로 보냄
					MT.setTIME(rs.getString(4));// 현재 마켓의 영업시간을 모델패키지에 있는 Markets클래스의 TIME필드 변수로 보냄
				}
			} catch (Exception e) {
				System.out.println("DB에서 이름,주소,전화번호,영업시간 불러오기 실패: " + e.getMessage());
			}
			return MT;
		}

//2. 불러온 가게정보들을 맞는 위치에 넣어 보여주는 메소드
		public void showReview(List<Review> list){
			for (int i = 0; i < list.size(); i++) {// 저장된 총 객체수 만큼 반복
				FXMLLoader fxmlLoader = new FXMLLoader();// fxml 객체 생성후
				fxmlLoader.setLocation(getClass().getResource("Reviewcard.fxml"));// Reviewcard 파일을 불러옴

				try {
					HBox hbox = fxmlLoader.load();// hbox를 로딩하고
					ReviewcardController cic = fxmlLoader.getController();// ReviewcardController도 불러옴
					cic.setData(list.get(i));// 불러온 ReviewcardController에 setData로 데이터를 채워넣음

					reviewbox.getChildren().add(hbox);// 카드 리스트가 들어갈 공간에 데이터가 체워진 hbox를 하나씩 넣음
				} catch (Exception e) {
					System.out.println("오류남");
				}
			}
		}

//1. DB에서 가게에 대한 정보들을 불러와 리뷰카드에 담기위한 메소드
		private List<Review> ReviewList() {
		 // Review 타입만 넣을 수 있는 list 어레이리스트 생성
			list = new ArrayList<>();
			// 아이디, 시간, 리뷰내용,거리두기점수,손소독제점수,체온측정점수,방문일지점수를 불러오기위해 review테이블과 maskrate테이블을 조인시킨후 marketid를 참조하게 하고 리뷰아이디를 내림차순으로 변경
			String sql = "SELECT id,time,text,type1,type2,type3,type4 from review,maskrate where review.maskid = maskrate.maskid and review.marketid = ? order by reviewid DESC";

			 conn = connect.getConnection(); // 위에서 만든 DB연결 메소드

			try {
				pstmt = conn.prepareStatement(sql); // 쿼리 객체 생성
				pstmt.setInt(1,Singleton.getMartid());// ShopController에서 싱글톤으로 보낸 현재 마켓 아이디를 싱글톤으로 불러와 현재 마켓아이디를 참조하게 구현
				rs = pstmt.executeQuery(); // 접속된 DB에서 쿼리를 실행하고 결과를 리턴
				while (rs.next()) {
				Review review = new Review(rs.getString("id"), rs.getString("time"), rs.getString("text"), rs.getString("type1"),
							rs.getString("type2"), rs.getString("type3"), rs.getString("type4")); //리뷰클래스에 생성자의 매개변수에 순서에 맞게 삽입
				list.add(review);
				}
			} catch (SQLException e) {
				System.out.println("DB에서 sql문을 실행불가:" + e.getMessage());
			}
			return list;
		}

// 각 요소들의 평균값을 다 합쳐서 총 평균값을 내는 메소드
	public Markets TotalAVG(){
		// 마켓아이디를 참조하여 거리두기점수,손소독제점수,체온측정점수,방문일지점수를 더한다음 평균화해서 나누기 4를 하여 총 평균을 질의
		String sql = "select AVG(type1+type2+type3+type4)/4 from maskrate where marketid = ?";
		conn = connect.getConnection();
		Markets Total = new Markets();
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,Singleton.getMartid());// ShopController에서 싱글톤으로 보낸 현재 마켓 아이디를 싱글톤으로 불러와 현재 마켓아이디를 참조하게 구현
		 	rs = pstmt.executeQuery();
		 	if(rs.next()){
		 		Total.setTotalAVG(rs.getInt(1));// 현재 마켓의 총평균을 모델패키지에 있는 Markets클래스의 TotalAVG필드 변수로 보냄
		 	}
		} catch (Exception e) {
			System.out.println("DB에서 평균점수 불러오기 실패:"+e.getMessage());
		}
		return Total;
	}
// 거리두기,손소독제,체온측정,방문일지들을 평균내는 메소드
	public Markets AVG(){
		// 마켓아이디를 참조하여 거리두기점수,손소독제점수,체온측정점수,방문일지점수를 각 각 평균화 질의
		String sql = "select AVG(type1),AVG(type2),AVG(type3),AVG(type4) from maskrate where marketid = ?";
		conn = connect.getConnection();
		Markets avg = new Markets();
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,Singleton.getMartid());//  ShopController에서 싱글톤으로 보낸 현재 마켓 아이디를 싱글톤으로 불러와 현재 마켓아이디를 참조하게 구현
		 	rs = pstmt.executeQuery();
		 	if(rs.next()){
		 		avg.setTYPE1(rs.getInt(1));// 현재 마켓의 거리두기점수를 모델패키지에 있는 Markets클래스의 TYPE1필드 변수로 보냄
		 		avg.setTYPE2(rs.getInt(2));// 현재 마켓의 손소독제점수를 모델패키지에 있는 Markets클래스의 TYPE2필드 변수로 보냄
		 		avg.setTYPE3(rs.getInt(3));// 현재 마켓의 체온측정점수를 모델패키지에 있는 Markets클래스의 TYPE3필드 변수로 보냄
		 		avg.setTYPE4(rs.getInt(4));// 현재 마켓의 방문일지점수를 모델패키지에 있는 Markets클래스의 TYPE4필드 변수로 보냄
		 	}
		} catch (Exception e) {
			System.out.println("DB에서 평균점수 불러오기 실패:"+e.getMessage());
		}
		return avg;
	}
// 총 평균값을 참조하여 이미지 변환시키는 메소드
	public void totalimg(ArrayList<ImageView> img){
		int totalAVG = TotalAVG().getTotalAVG();// TotalAVG()메소드에서 Markets클래스의 TotalAVG필드 변수로 보낸걸 가져와 정수형 totalAVG변수에 넣음
		for(int i=0; i<totalAVG; i++){
			img.get(i).setImage(image);// 총평균 개수만큼 이미지를 보냄
		}
	}
// 거리두기 평균값을 참조하여 이미지 변환시키는 메소드
	public void img1(ArrayList<ImageView> img){
			int img1 = AVG().getTYPE1();// AVG()메소드에서 Markets클래스의 TYPE1필드 변수로 보낸걸 가져와 정수형 img1변수에 넣음
			for(int i=0; i<img1; i++){
				img.get(i).setImage(image);// 거리두기 평균 개수만큼 이미지를 보냄
		}
	}
// 손소독제 평균값을 참조하여 이미지 변환시키는 메소드
	public void img2(ArrayList<ImageView> img){
		int img2 = AVG().getTYPE2();// AVG()메소드에서 Markets클래스의 TYPE2필드 변수로 보낸걸 가져와 정수형 img2변수에 넣음
		for(int i=0; i<img2; i++){
			img.get(i).setImage(image);// 손소독제 평균 개수만큼 이미지를 보냄
		}
	}
// 체온측정 평균값을 참조하여 이미지 변환시키는 메소드
	public void img3(ArrayList<ImageView> img){
		int img3 = AVG().getTYPE3();// AVG()메소드에서 Markets클래스의 TYPE3필드 변수로 보낸걸 가져와 정수형 img3변수에 넣음
		for(int i=0; i<img3; i++){
			img.get(i).setImage(image);// 체온측정 평균 개수만큼 이미지를 보냄
		}
	}
// 방문일지 평균값을 참조하여 이미지 변환시키는 메소드
	public void img4(ArrayList<ImageView> img){
		int img4 = AVG().getTYPE4();// AVG()메소드에서 Markets클래스의 TYPE4필드 변수로 보낸걸 가져와 정수형 img4변수에 넣음
		for(int i=0; i<img4; i++){
			img.get(i).setImage(image);// 방문일지 평균 개수만큼 이미지를 보냄
		}
	}




//=================================================수정된 내용====================================================================
//// 각 요소들을 평균값들을 총 합쳐서 평균낸 값을 참조하여 이미지 변형시키는 메소드
//	public void imgtotalAVG(){
//
//		int totalAVG = TotalAVG().getTotalAVG();// TotalAVG()메소드에서 Markets클래스의 TotalAVG필드 변수로 보낸걸 가져와 정수형 totalAVG변수에 넣음
//		// 스위치문 조건을 토탈 평균점수로 지정 (리뷰쓸때마다 값이 바뀜에따라 이미지 변형)
//			switch (totalAVG) {
//			case 1:TotalAVG1.setImage(image);
//				break;
//			case 2:TotalAVG1.setImage(image);
//				   TotalAVG2.setImage(image);
//				break;
//			case 3:TotalAVG1.setImage(image);
//			       TotalAVG2.setImage(image);
//			       TotalAVG3.setImage(image);
//				break;
//			case 4:TotalAVG1.setImage(image);
//				   TotalAVG2.setImage(image);
//				   TotalAVG3.setImage(image);
//				   TotalAVG4.setImage(image);
//				break;
//			case 5:TotalAVG1.setImage(image);
//			       TotalAVG2.setImage(image);
//			       TotalAVG3.setImage(image);
//			       TotalAVG4.setImage(image);
//			       TotalAVG5.setImage(image);
//				break;
//			default:TotalAVG1.setImage(image2);
//		            TotalAVG2.setImage(image2);
//		            TotalAVG3.setImage(image2);
//		            TotalAVG4.setImage(image2);
//		            TotalAVG5.setImage(image2);
//				break;
//			}
//		}
////거리두기,손소독제,체온측정,방문밀지 요소들 평균값을 참조하여 이미지 변형하는 메소드
//	public void imgAVG(){
//
//		int type1 = AVG().getTYPE1();// AVG()메소드에서 Markets클래스의 TYPE1필드 변수로 보낸걸 가져와 정수형 type1변수에 넣음
//		//스위치문 조건을 거리두기 평균점수로 지정 (리뷰쓸때마다 값이 바뀜에따라 이미지 변형)
//			switch (type1) {
//			case 1:AVG1_1.setImage(image);
//				break;
//			case 2:AVG1_1.setImage(image);
//				   AVG1_2.setImage(image);
//				break;
//			case 3:AVG1_1.setImage(image);
//				   AVG1_2.setImage(image);
//				   AVG1_3.setImage(image);
//				break;
//			case 4:AVG1_1.setImage(image);
//				   AVG1_2.setImage(image);
//				   AVG1_3.setImage(image);
//				   AVG1_4.setImage(image);
//				break;
//			case 5:AVG1_1.setImage(image);
//				   AVG1_2.setImage(image);
//				   AVG1_3.setImage(image);
//				   AVG1_4.setImage(image);
//				   AVG1_5.setImage(image);
//			    break;
//			default:AVG1_1.setImage(image2);
//					AVG1_2.setImage(image2);
//					AVG1_3.setImage(image2);
//					AVG1_4.setImage(image2);
//					AVG1_5.setImage(image2);
//				break;
//			}
//
//			int type2 = AVG().getTYPE2();// AVG()메소드에서 Markets클래스의 TYPE2필드 변수로 보낸걸 가져와 정수형 type2변수에 넣음
//			//스위치문 조건을 손소독제 평균점수로 지정 (리뷰쓸때마다 값이 바뀜에따라 이미지 변형)
//			switch (type2) {
//			case 1:AVG2_1.setImage(image);
//				break;
//			case 2:AVG2_1.setImage(image);
//				   AVG2_2.setImage(image);
//				break;
//			case 3:AVG2_1.setImage(image);
//				   AVG2_2.setImage(image);
//				   AVG2_3.setImage(image);
//				break;
//			case 4:AVG2_1.setImage(image);
//				   AVG2_2.setImage(image);
//				   AVG2_3.setImage(image);
//				   AVG2_4.setImage(image);
//				break;
//			case 5:AVG2_1.setImage(image);
//				   AVG2_2.setImage(image);
//				   AVG2_3.setImage(image);
//				   AVG2_4.setImage(image);
//				   AVG2_5.setImage(image);
//			    break;
//			default:AVG2_1.setImage(image2);
//					AVG2_2.setImage(image2);
//					AVG2_3.setImage(image2);
//					AVG2_4.setImage(image2);
//					AVG2_5.setImage(image2);
//				break;
//			}
//			int type3 = AVG().getTYPE3();// AVG()메소드에서 Markets클래스의 TYPE3필드 변수로 보낸걸 가져와 정수형 type3변수에 넣음
//			//스위치문 조건을 체온측정 평균점수로 지정 (리뷰쓸때마다 값이 바뀜에따라 이미지 변형)
//			switch (type3) {
//			case 1:AVG3_1.setImage(image);
//				break;
//			case 2:AVG3_1.setImage(image);
//				   AVG3_2.setImage(image);
//				break;
//			case 3:AVG3_1.setImage(image);
//				   AVG3_2.setImage(image);
//				   AVG3_3.setImage(image);
//				break;
//			case 4:AVG3_1.setImage(image);
//				   AVG3_2.setImage(image);
//				   AVG3_3.setImage(image);
//				   AVG3_4.setImage(image);
//				break;
//			case 5:AVG3_1.setImage(image);
//				   AVG3_2.setImage(image);
//				   AVG3_3.setImage(image);
//				   AVG3_4.setImage(image);
//				   AVG3_5.setImage(image);
//			    break;
//			default:AVG3_1.setImage(image2);
//					AVG3_2.setImage(image2);
//					AVG3_3.setImage(image2);
//					AVG3_4.setImage(image2);
//					AVG3_5.setImage(image2);
//				break;
//			}
//			int type4 = AVG().getTYPE4();// AVG()메소드에서 Markets클래스의 TYPE4필드 변수로 보낸걸 가져와 정수형 type4변수에 넣음
//			//스위치문 조건을 방문일지 평균점수로 지정 (리뷰쓸때마다 값이 바뀜에따라 이미지 변형)
//			switch (type4) {
//			case 1:AVG4_1.setImage(image);
//				break;
//			case 2:AVG4_1.setImage(image);
//				   AVG4_2.setImage(image);
//				break;
//			case 3:AVG4_1.setImage(image);
//				   AVG4_2.setImage(image);
//				   AVG4_3.setImage(image);
//				break;
//			case 4:AVG4_1.setImage(image);
//				   AVG4_2.setImage(image);
//				   AVG4_3.setImage(image);
//				   AVG4_4.setImage(image);
//				break;
//			case 5:AVG4_1.setImage(image);
//				   AVG4_2.setImage(image);
//				   AVG4_3.setImage(image);
//				   AVG4_4.setImage(image);
//				   AVG4_5.setImage(image);
//			break;
//			default:AVG4_1.setImage(image2);
//					AVG4_2.setImage(image2);
//					AVG4_3.setImage(image2);
//					AVG4_4.setImage(image2);
//					AVG4_5.setImage(image2);
//				break;
//			}
//
//	}
}
