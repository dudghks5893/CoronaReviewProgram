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

public class ShopController { //shopListCard�� ������ �����ؾߵǱ� ������ shop�� ���� ����

	@FXML
	private HBox cardList; // ��ü Ʋ

	@FXML
	private ImageView img; //�̹����� �ִ� ��

	@FXML
	private Label shopName; //��ȣ���� �ִ� ��

	@FXML
	private Label shopid; // ���Ͼ��̵� ���� �� �ִ� ������ �� ����

	@FXML
	private Label shopAddress; //�ּҰ� ���� ��

	@FXML
	private Label shopTel; //��ȭ��ȣ�� ���� ��

	@FXML
	private Label shopOpenHours; //�����ð��� ���� ��

	DBConnect connect = new DBConnect();//DB�ּ� ����
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

//���콺Ŭ���̺�Ʈ ����Ʈ Ŭ���� ���������� �̵��ϴ� �޼ҵ�
	@FXML
	void mouseClick(MouseEvent event) throws IOException {

		ShopController control = new ShopController();// ShopController��ü ����
		Markets mbean = new Markets();// Markets��ü ����
		mbean = control.MnameToMid(shopName.getText());// ���� �̸��� �����Ͽ� ���Ͼ��̵� mbean������ ����
		// �ε���2 �� ���� �� �����ڸ� ���� �ε���2 �ȿ� �ִ� MnameTomid ������ �� shopname�� �����ؼ� marketid�� ������

		Singleton.setMartid(mbean.getMARKETID()); // �������� ������ marketid�� �̱��� ���Ͼ��̵� �������μ� ������������ ���� ���Ͼ��̵� �ҷ��ü� ����
		cardList.getScene().getWindow().hide();
		Stage viewReview = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("../view/Details.fxml"));
		Scene scene = new Scene(root);
		viewReview.setTitle("���亸��");
		viewReview.setScene(scene);
		viewReview.show();
	}

//ShopListCardControllerŬ������ ���� �޼ҵ�
	public void setData(Shop shop) {

		Image shopImg = new Image(getClass().getResourceAsStream(shop.getImgSrc()));
		img.setImage(shopImg);
		shopName.setText(shop.getName());
		shopAddress.setText(shop.getAddress());
		shopTel.setText(shop.getTel());
		shopOpenHours.setText(shop.getOpenhours());
		shopid.setText(Integer.toString(shop.getMarketid()));
		shopid.setVisible(false); // shopid�� ���Ͼ��̵� �ְ� setVisible(false)�� ���� ����
	}
//���Ͼ��̵� �������µ� �̸��� �����ؼ� �ش� �����̸��� ���Ͼ��̵� �������� �޼ҵ�
	public Markets MnameToMid(String name) {

		conn = connect.getConnection(); // ������ ���� DB���� �޼ҵ�
		String sql = "select marketid from market where name = ?";
		Markets Mbean = new Markets();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery(); // ���ӵ� DB���� ������ �����ϰ� ����� ����
			if(rs.next())
			Mbean.setMARKETID(rs.getInt(1));

		} catch (Exception e) {
			System.out.println("DB���� sql���� ����Ұ�: " + e);
		}
		return Mbean;
	}


}