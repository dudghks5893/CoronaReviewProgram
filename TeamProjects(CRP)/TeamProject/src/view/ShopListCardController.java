package view;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import model.DBConnect;
import model.Shop;
import model.Singleton;

public class ShopListCardController implements Initializable {
	@FXML
	private Pane restauranticon;

	@FXML
	private Pane cafeicon;

	@FXML
	private Pane storeicon;

	@FXML
	private TextField searchBar;

	@FXML
	private ScrollPane scroll;

	@FXML
	private VBox shopBox; // ī�� ����Ʈ��(hbox) �� ����

	@FXML
	private ImageView imgSearch;

	List<Shop> list; //����Ʈ<Shop>�� ���� ���� (������ �����ڿ� �޼ҵ� �������� ���ǰ� �����ڿ� �޼ҵ尡 ���� ����Ǹ� �ڵ� �Ҹ�ȴ�.)
	DBConnect connect = new DBConnect();//DB�ּ� ����
	Connection conn;
	// java.sql.PreparedStatement Ŭ������ �ڹٿ��� SQL������ ������ �� ����ϴ� Ŭ����. �������� ����������
	PreparedStatement pstmt;  // �����Ͽ� ����� ���� �ְ�, ���� ������ �����Ͽ��� �� Ŀ�ǵ�â���� ���ϵǴ� ������ ��¹��� ���� �ִ�
	ResultSet rs;			 // �ڹٿ��� SELECT ���� ��ȸ �������� �����ϸ� Ŀ�ǵ�â�� �ƴϱ� ������ �ٷ� ������ �������� ��ư�, �̿��ϱ⿡�� ������� ����
							// �� �� ����ϰ� �Ǵ� Ŭ������ ResultSet Ŭ����

	@FXML
	void searchItem(MouseEvent event) { //�˻� �̹����� Ŭ��������
		if (event.getSource() == imgSearch) { // �Ʒ� ��ġ�� ���� �˻��ڽ��� �ۼ� �ϸ�
			shopBox.getChildren().clear(); // �Ʒ� ������ �����
			list = search(); // �˻��ڽ��� �ۼ��� ������ sql�˻�
			showShops(list); // �˻��� sql���� ����Ʈ�� �߰�
							 // �ǾƷ��� �̴ϼȶ���� ���� �߰��� ����Ʈ�� ȭ�鿡 ǥ��
		}
	}

	private List<Shop> search() {
		list = new ArrayList<Shop>(); // ��ġ���� �� ShopŬ���� ���׸��� ��̸���Ʈ ��ü�� �����Ͽ� list ������ ����
		String sql = "SELECT * FROM market WHERE name LIKE ? OR address LIKE ?";  // ���� ��ü ����(pstmt�� ? ��밡��)
		 conn = connect.getConnection();//DB�ּ� ����
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, '%' + searchBar.getText() + '%');
			pstmt.setString(2, '%' + searchBar.getText() + '%'); // �̿� ���� ������� sql���� �Է����ϰ�
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Shop shop = new Shop(rs.getString("NAME"), rs.getString("ADDRESS"), rs.getString("TEL"),
						rs.getString("TIME"), rs.getString("IMG"), rs.getInt("marketid"));
				list.add(shop); // ����Ʈ�� sql���� ����
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	@FXML
	void restaurantClick(MouseEvent event) throws IOException { // ������������� Ŭ����
		Singleton.setCategory(1);
		shopBox.getChildren().clear(); // Ŭ���ص� ��ü ����Ʈ�� �ѹ��� ���� �� �ֵ��� ����
		list = shops(Singleton.getCategory());
		showShops(list);
	}

	@FXML
	void cafeClick(MouseEvent event) throws IOException { //ī������� Ŭ����
		Singleton.setCategory(2);
		shopBox.getChildren().clear();
		list = shops(Singleton.getCategory());
		showShops(list);
	}

	@FXML
	void storeClick(MouseEvent event) throws IOException { //������������ Ŭ����
		Singleton.setCategory(3);
		shopBox.getChildren().clear();
		list = shops(Singleton.getCategory());
		showShops(list);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		list = shops(Singleton.getCategory()); // ī�װ������� ������ ī�װ����ѹ��� �޾ƿͼ� ����Ʈ ����ȭ���� ����
		showShops(list); // ���� �˻��� �˻��� ����Ʈ�� ȭ�鿡 ǥ��
	}

//2.�ҷ��� �������� �´� ��ġ�� �־� �����ִ� �޼ҵ�
	public void showShops(List<Shop> list) {
		for (int i = 0; i < list.size(); i++) { // ����� �� ��ü�� ��ŭ �ݺ�
			FXMLLoader fxmlLoader = new FXMLLoader(); // fxml ��ü ������
			fxmlLoader.setLocation(getClass().getResource("Shop.fxml")); // shop ������ �ҷ���
			try {
				HBox hbox = fxmlLoader.load(); //hbox�� �ε��ϰ�
				ShopController cic = fxmlLoader.getController(); //shopController�� �ҷ���
				cic.setData(list.get(i)); //�ҷ��� shopController�� setData�� �����͸� ä������
				shopBox.getChildren().add(hbox); // ī�� ����Ʈ�� �� ������ �����Ͱ� ü���� hbox�� �ϳ��� ����
			} catch (IOException e) {
				System.out.println("������");
			}
		}
	}

//1. DB���� ���Կ� ���� �������� �ҷ����� �޼ҵ�
	private List<Shop> shops(int x) {
		 // Shop Ÿ�Ը� ���� �� �ִ� list ��̸���Ʈ ����
		list = new ArrayList<>();
		 // DB���� cateid= ?�� �ش��ϴ� ���� ����(��ȣ��,�ּ�,��ȭ��ȣ,�����ð�,�̹����ּ�,���Ͼ��̵�)�� �ҷ���
		String sql = "SELECT name, address, tel, time, img, marketid FROM market WHERE cateid = ? ";
		 conn = connect.getConnection(); //DB�ּ� ����
		try {
			pstmt = conn.prepareStatement(sql); // Connection������ ���� ��ü conn���κ��� pstmt �ν��Ͻ��� ����
			pstmt.setInt(1, Singleton.getCategory());
			// executeQuery(); �޼���� ���� ����� ���ÿ� ResultSet Ŭ������ �ǵ�����
			rs = pstmt.executeQuery(); // sql������ �־ ������ �����ϰ� ����� rs�� ��ƿ�
			while (rs.next()) { // ���� ���� �� ����� ����
				Shop shop = new Shop(rs.getString("NAME"), rs.getString("ADDRESS"), rs.getString("TEL"),
						rs.getString("TIME"), rs.getString("IMG"), rs.getInt("marketid"));
				// model.shop.java�� int�� ���Ͼ��̵� �߰��ؼ� ������ �ְ� ����
				list.add(shop); // shoplist�� ������� ����
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}