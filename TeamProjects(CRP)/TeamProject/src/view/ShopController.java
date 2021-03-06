package view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.DBConnect;
import model.Markets;
import model.Shop;
import model.Singleton;

public class ShopController { //shopListCard와 별개로 관리해야되기 때문에 shop을 따로 만듬

	@FXML
	private HBox cardList; // 전체 틀

	@FXML
	private ImageView img; //이미지를 넣는 라벨

	@FXML
	private Label shopName; //상호명을 넣는 라벨

	@FXML
	private Label shopid; // 마켓아이디를 받을 수 있는 숨겨진 라벨 지정

	@FXML
	private Label shopAddress; //주소가 들어가는 라벨

	@FXML
	private Label shopTel; //전화번호가 들어가는 라벨

	@FXML
	private Label shopOpenHours; //영업시간이 들어가는 라벨

	DBConnect connect = new DBConnect();//DB주소 연결
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

//마우스클릭이벤트 리스트 클릭시 상세페이지로 이동하는 메소드
	@FXML
	void mouseClick(MouseEvent event) throws IOException {

		ShopController control = new ShopController();// ShopController객체 생성
		Markets mbean = new Markets();// Markets객체 생성
		mbean = control.MnameToMid(shopName.getText());// 가게 이름을 참조하여 마켓아이디를 mbean변수에 넣음
		// 인덱스2 와 마켓 의 생성자를 만들어서 인덱스2 안에 있는 MnameTomid 생성자 에 shopname을 지정해서 marketid를 가져옴

		Singleton.setMartid(mbean.getMARKETID()); // 위를통해 가져온 marketid를 싱글톤 마켓아이디에 보냄으로서 상세페이지에서 현재 마켓아이디를 불러올수 있음
		cardList.getScene().getWindow().hide();
		Stage viewReview = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("../view/Details.fxml"));
		Scene scene = new Scene(root);
		viewReview.setTitle("리뷰보기");
		viewReview.setScene(scene);
		viewReview.show();
	}

//ShopListCardController클래스에 사용될 메소드
	public void setData(Shop shop) {

		Image shopImg = new Image(getClass().getResourceAsStream(shop.getImgSrc()));
		img.setImage(shopImg);
		shopName.setText(shop.getName());
		shopAddress.setText(shop.getAddress());
		shopTel.setText(shop.getTel());
		shopOpenHours.setText(shop.getOpenhours());
		shopid.setText(Integer.toString(shop.getMarketid()));
		shopid.setVisible(false); // shopid에 마켓아이디를 넣고 setVisible(false)을 통해 숨김
	}
//마켓아이디를 가져오는데 이름을 참조해서 해당 가게이름의 마켓아이디를 가져오는 메소드
	public Markets MnameToMid(String name) {

		conn = connect.getConnection(); // 위에서 만든 DB연결 메소드
		String sql = "select marketid from market where name = ?";
		Markets Mbean = new Markets();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery(); // 접속된 DB에서 쿼리를 실행하고 결과를 리턴
			if(rs.next())
			Mbean.setMARKETID(rs.getInt(1));

		} catch (Exception e) {
			System.out.println("DB에서 sql문을 실행불가: " + e);
		}
		return Mbean;
	}


}
