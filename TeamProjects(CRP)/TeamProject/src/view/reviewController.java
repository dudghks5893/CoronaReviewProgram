package view;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.DBConnect;
import model.Singleton;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;

public class reviewController implements Initializable {
	@FXML
	private AnchorPane AP;
	@FXML
	private VBox VB2;
	@FXML
	private HBox VB2_HB1;
	@FXML
	private Label VB2_HB1_La;
	@FXML
	private ImageView VB2_HB1_img1;
	@FXML
	private ImageView VB2_HB1_img2;
	@FXML
	private ImageView VB2_HB1_img3;
	@FXML
	private ImageView VB2_HB1_img4;
	@FXML
	private ImageView VB2_HB1_img5;
	@FXML
	private Label VB2_HB1_Score;
	@FXML
	private HBox VB2_HB2;
	@FXML
	private Label VB2_HB2_La;
	@FXML
	private ImageView VB2_HB2_img1;
	@FXML
	private ImageView VB2_HB2_img2;
	@FXML
	private ImageView VB2_HB2_img3;
	@FXML
	private ImageView VB2_HB2_img4;
	@FXML
	private ImageView VB2_HB2_img5;
	@FXML
	private Label VB2_HB2_Score;
	@FXML
	private HBox VB2_HB3;
	@FXML
	private Label VB2_HB3_La;
	@FXML
	private ImageView VB2_HB3_img1;
	@FXML
	private ImageView VB2_HB3_img2;
	@FXML
	private ImageView VB2_HB3_img3;
	@FXML
	private ImageView VB2_HB3_img4;
	@FXML
	private ImageView VB2_HB3_img5;
	@FXML
	private Label VB2_HB3_Score;
	@FXML
	private HBox VB2_HB4;
	@FXML
	private Label VB2_HB4_La;
	@FXML
	private ImageView VB2_HB4_img1;
	@FXML
	private ImageView VB2_HB4_img2;
	@FXML
	private ImageView VB2_HB4_img3;
	@FXML
	private ImageView VB2_HB4_img4;
	@FXML
	private ImageView VB2_HB4_img5;
	@FXML
	private Label VB2_HB4_Score;
	@FXML
	private VBox VB1;
	@FXML
	private Label VB1_La;
	@FXML
	private VBox VB3;
	@FXML
	private HBox VB3_HB1;
	@FXML
	private Button VB3_HB1_but1;
	@FXML
	private Button VB3_HB1_but2;
	@FXML
	private Text VB3_text;
	@FXML
	private TextArea TA;

	//DB����
	DBConnect conOBJ = new DBConnect();
	Connection con;
	PreparedStatement ps; //��� ���� ��ü

	//�̱������� ���� �����̸� �޾ƿ�
	public void mtName() {
		VB1_La.setText(Singleton.getMtname());
	}
	//�̱������� ������ ���� �̸��� ������
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			mtName();
		}
	// ���콺Ŭ���̺�Ʈ1�� ����
	@FXML
	void imgclick1(MouseEvent event) {
		// ��̸���Ʈ ����
		ArrayList<ImageView> img = new ArrayList<ImageView>();
		// ����Ʈ�� �̹������ ���̵� ���
		img.add(VB2_HB1_img1);
		img.add(VB2_HB1_img2);
		img.add(VB2_HB1_img3);
		img.add(VB2_HB1_img4);
		img.add(VB2_HB1_img5);
		//�ؿ��� ���� �޼ҵ��� �Ű������� ���ڸ� ����
		insertimage(event, img, VB2_HB1_Score);
	}

	// ���콺Ŭ���̺�Ʈ2�� ����
	@FXML
	void imgclick2(MouseEvent event) {
		// ��̸���Ʈ ����
		ArrayList<ImageView> img = new ArrayList<ImageView>();
		// ����Ʈ�� �̹������ ���̵� ���
		img.add(VB2_HB2_img1);
		img.add(VB2_HB2_img2);
		img.add(VB2_HB2_img3);
		img.add(VB2_HB2_img4);
		img.add(VB2_HB2_img5);
		//�ؿ��� ���� �޼ҵ��� �Ű������� ���ڸ� ����
		insertimage(event, img, VB2_HB2_Score);
	}

	// ���콺Ŭ���̺�Ʈ3���� ����
	@FXML
	void imgclick3(MouseEvent event) {
		// ��̸���Ʈ ����
		ArrayList<ImageView> img = new ArrayList<ImageView>();
		// ����Ʈ�� �̹������ ���̵� ���
		img.add(VB2_HB3_img1);
		img.add(VB2_HB3_img2);
		img.add(VB2_HB3_img3);
		img.add(VB2_HB3_img4);
		img.add(VB2_HB3_img5);
		//�ؿ��� ���� �޼ҵ��� �Ű������� ���ڸ� ����
		insertimage(event, img, VB2_HB3_Score);
	}

	// ���콺Ŭ���̺�Ʈ4�� ����
	@FXML
	void imgclick4(MouseEvent event) {
		// ��̸���Ʈ ����
		ArrayList<ImageView> img = new ArrayList<ImageView>();
		// ����Ʈ�� �̹������ ���̵� ���
		img.add(VB2_HB4_img1);
		img.add(VB2_HB4_img2);
		img.add(VB2_HB4_img3);
		img.add(VB2_HB4_img4);
		img.add(VB2_HB4_img5);
		//�ؿ��� ���� �޼ҵ��� �Ű������� ���ڸ� ����
		insertimage(event, img, VB2_HB4_Score);
	}
	//�ڷΰ����ư ������ ���������� �̵�
	@FXML
	void GoBack(ActionEvent event) {
		VB3_HB1_but1.getScene().getWindow().hide(); // ���� �������� �Ⱥ��̰� �ݱ�

		Stage details = new Stage();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Details.fxml"));
			Scene scene = new Scene(root);
			details.setScene(scene);
			details.setTitle("���亸��");
			details.show();
		} catch (IOException e) {
			System.out.println("�ڷΰ��� ���� �߻�" + e.getMessage());
		}
	}

	// �Ϸ��ư ������ DB�� ������ ����
	@FXML
	void Completion(ActionEvent event) throws SQLException {
		con = conOBJ.getConnection();//DB ���� ��ü
		Statement stmt = null; // SQL ���� �ϱ� ���� ��ü
		ResultSet rs = null; // SQL ���� �� ��� ����
		//Query��
		String id = "select reviewid from review";
		// ��ȸ�Ҷ� ���� �޼ҵ�
		stmt = con.createStatement();
		// ����� �޴� �޼ҵ�
		rs = stmt.executeQuery(id);

		int n = 0;
		// reviewid�� �� �྿ ������Ű�鼭 ���� ex) reviewid�� 10���� ���� n���� 10
		while (rs.next()) {
			n++;
		}
		// ����ð��� ������ Date�� ������ ����
		Date date = new Date(System.currentTimeMillis());
		// ��,��,��,��,��,�� 14�ڸ��� ����
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		// 14�ڸ� �������� ������ ������ ����
		String times = format.format(date);

		// DB�� maskrate���̺��� ������ ���� ����
		String insert2 = "INSERT INTO maskrate(maskid,type1,type2,type3,type4,marketid)"
						+ "Values(?,?,?,?,?,?)"; // �� ������ ���� ���ٷ� �ۼ��ص� ����
		// PreparedStatement�� ����Ͽ� ��� ����
		ps = con.prepareStatement(insert2);
		// �����ִ� ���� n������ �ٷ� 1���������� �� ���� ���� �ۼ��� ���ο� ����ũ���̵� �ް� �Ǵ°�
		ps.setInt(1, ++n);
		// �ۼ��� ���� ����ũ ���� ��� ����
		ps.setString(2, VB2_HB1_Score.getText()); //�Ÿ��α� ����
		ps.setString(3, VB2_HB2_Score.getText()); //ü������ ����
		ps.setString(4, VB2_HB3_Score.getText()); //����û�� ����
		ps.setString(5, VB2_HB4_Score.getText()); //�湮���� ����
		// �̱������� ���� ��Ʈ���̵� ��� ����
		ps.setInt(6, Singleton.getMartid());
		// DB������Ʈ (���並 �ۼ��ϰ� �ϷḦ ������ �����ִ� ������� DB�� ���Ե�)
		ps.executeUpdate();

		//DB�� review���̺��� ������ ���� ���� (maskrateid�� ����(FK)�� �̿��� �� �ڵ尡 �ؿ� �־�� ��)
		String insert = "INSERT INTO review(reviewid,id,time,text,marketid,maskid)"
						+ "Values (?,?,?,?,?,?)"; // �� ������ ���� ���ٷ� �ۼ��ص� ����
		//PreparedStatement�� ����Ͽ� ��� ����
		ps = con.prepareStatement(insert);
		// n==���� (���� ++n ��)
		ps.setInt(1, n); // ���� �ۼ��� n������ ������̵� ��� ����
		ps.setString(2, Singleton.getUsname()); // ���� �ۼ��� �̱������� ���� ���̵� ��� ����
		ps.setString(3, times); // ���� �ۼ��� ������ ���� 14�ڸ� �ð� ��� ����
		ps.setString(4, TA.getText()); // ���� �ۼ��� �ؽ�Ʈ�� ���� �� ��� ����
		ps.setInt(5, Singleton.getMartid()); // �̱������� ���� ��Ʈ���̵� ��� ����
		ps.setInt(6, n); // ���� �ۼ��� n������ ����ũ���̵� ��� ����
		ps.executeUpdate(); // DB������Ʈ (���並 �ۼ��ϰ� �ϷḦ ������ �����ִ� ������� DB�� ���Ե�)

		//�Ϸ��ư ������ ���������� �̵�
		VB3_HB1_but2.getScene().getWindow().hide(); // ���� �������� �Ⱥ��̰� �ݱ�
		Stage details = new Stage();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Details.fxml"));
			Scene scene = new Scene(root);
			details.setScene(scene);
			details.setTitle("���亸��");
			details.show();
		} catch (IOException e) {
			System.out.println("�Ϸ��ư ���� �߻�" + e.getMessage());
		}
	}
	// ���� reviewControllerŬ���� �ȼ���
	// �ּҰ� ���ڿ��� �Է�
	String imgAddress = "img/Mask.png";
	String imgAddress2 = "img/Mask2.png";
	// �̹��� ��ü�� ����µ� imgAddress,imgAddress2������ ���� �̰� image,image2 ������ ����
	Image image = new Image(imgAddress);
	Image image2 = new Image(imgAddress2);

	// �̹��� �ֱ� �޼ҵ� ����
	public void insertimage(MouseEvent event, ArrayList<ImageView> img, Label score) {
		// �̹������ Ŭ���� �ش� ���̵� �ҷ���
		String value = ((ImageView) event.getSource()).getId();
		// Ŭ���� �̹������ ���̵��� 11��° �ε����� ������ tempstr�� ���� ex)VB2_HB4_img5�� 11��°�� '5'�̴�.
		char tempstr = value.charAt(11);
		// �������� ���������� �ٲ� (�̰� �ٲٴ� �޼ҵ� Character.getNumericValue)
		int Selectimg = Character.getNumericValue(tempstr);
		// �������� ���ڿ������� �ٲ㼭 ������ȯ�Ҷ� ���
		score.setText(String.valueOf(Selectimg));
		// �̹����� Ŭ���� ����Ʈ�� ��� ���̵� ���������� ���� (�ʱ�ȭ�ϱ����� ���ǿ� Selectimg�� �ƴ� 5�� ��)
		for (int i = 0; i < 5; i++) {
			img.get(i).setImage(image2);
		}
		// �̹����� Ŭ���� �ش� ���̵� �Է��� �Ǳ� ������ Selectimg�� ��� ���ڰ� �ٲ� (����������� ����)
		for (int i = 0; i < Selectimg; i++) {
			img.get(i).setImage(image);
		}
	}
}

//=================================================������ ����====================================================================

// �ʱⰪ ���� (�޼ҵ� �ȿ� ������ ��� true���̿��� ������ ������)
// boolean check = true;
//
//// ���콺Ŭ���׼� 1���� ����
// @FXML
// void imgclick2(MouseEvent event){
// //�̹��� �ּҸ� ���ڿ��� ����
//// String imgAddress = "D:\\gitProject\\Details\\src\\img\\Mask2.png";
// //���ϰ�ü�� ����µ� imgAddress�� �ּҷ� ����
//// File file = new File(imgAddress);
// //���ϰ�ü �ȿ��ִ� �ּ� ������ ���ڿ��� ��ȯ
//// imgAddress = file.toURI().toURL().toExternalForm();
// //�ּҰ� ���
//// System.out.println(tmpAddress);
//
// // ���콺 Ŭ���׼ǽ� Ŭ���� ���̵� �ҷ���
// String value = ((ImageView)event.getSource()).getId();
// Ŭ���� �̹������ ���̵� ��µ�
// System.out.println(value);
// //������ ������ �����̹����ּҰ��� ���ڿ��� ����
// String imgAddress = "file:/D:/gitProject/Details/src/img/Mask.png";
// String imgAddress2 = "file:/D:/gitProject/Details/src/img/Mask2.png";
// //�̹��� ��ü�� ����µ� imgAddress������ ���� �̰� image ������ ����
// Image image = new Image(imgAddress);
// Image image2 = new Image(imgAddress2);
//
// //��̸���Ʈ ����
// ArrayList<ImageView> img = new ArrayList<ImageView>();
// //����Ʈ�� �̹������ ���̵� ���
// img.add(VB2_HB2_img1);
// img.add(VB2_HB2_img2);
// img.add(VB2_HB2_img3);
// img.add(VB2_HB2_img4);
// img.add(VB2_HB2_img5);
//
//
// ���������� ���ڿ��� 11��° �ִ� ���ڸ� ��������.charAt(11��° �ε����� ��������) (VB2_HB2_img5)
// char tempstr = value.charAt(11);
// //�������� ���������� �ٲ� (�̰� �ٲٴ� �޼ҵ� Character.getNumericValue)
// int Selectimg = Character.getNumericValue(tempstr);
// if(check == true){
// �̹����� Ŭ���� ����Ʈ�� ��� ���̵� ���������� ���� (�ʱ�ȭ)
// for(int i=0; i < 5; i++){
// img.get(i).setImage(image2);
// }
// // �̹����� Ŭ���� �ش� ���̵� �Է��� �Ǳ� ������ Selectimg�� ��� ���ڰ� �ٲ� (����̹����� ����)
// for(int i=0; i < Selectimg; i++){
// img.get(i).setImage(image);
// }
// }
//
// }

// ========================�ݺ��� ���� ��=============================

// �ʱⰪ ���� (�޼ҵ� �ȿ� ������ ��� true���̿��� ������ ������)
// boolean check = true;
// ���콺 Ŭ�� �׼��� �̹������ ���̵� �ϳ� �ϳ��� �ٸ��� �������� ���
// @FXML
// void imgclick1(MouseEvent event) {
//
// if(check == true){
// �ּҸ� ���ڿ��� ����
// String imgAddress = "D:\\gitProject\\Details\\src\\img\\Mask.png";
// ������ �ʱ�ȭ
// String tmpAddress = null;
// ���ϰ�ü�� ����µ� imgAddress�� �ּҷ� ���� �̰� file�̶�� ������ ����
// File file = new File(imgAddress);
//
// try {
// ���ϰ�ü�ȿ� �ִ� �ּ������� �ٽ� ���ڿ��� ��ȯ
// tmpAddress = file.toURI().toURL().toExternalForm();
// �̹�������ü �����ϴµ� tmpAddress�ּҷ� ���� �̰� image��� ������ ����
// Image image = new Image(tmpAddress);
// �ش� �̹������ ���̵� �̹����ּҸ� ���
// VB2_HB1_img1.setImage(image);
// } catch (MalformedURLException e) {
// e.printStackTrace();
// }
// }
// else{
// String imgAddress = "D:\\gitProject\\Details\\src\\img\\Mask2.png";
// String tmpAddress = null;
// File file = new File(imgAddress);
//
// try {
// tmpAddress = file.toURI().toURL().toExternalForm();
// Image image = new Image(tmpAddress);
// VB2_HB1_img1.setImage(image);
// } catch (MalformedURLException e) {
// e.printStackTrace();
// }
// }
// �޽��� ���������
// check = !check;
// }