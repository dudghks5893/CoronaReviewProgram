package view;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import application.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.DBConnect;
import model.Singleton;

// �޼ҵ� �ּ�  �λ����� ī�װ��� �� ���� ��ü�� �Ű������� ������ �־���ϰ�  db�˻��Ҷ� ?�� ��
public class CategoryController implements Initializable {

	@FXML
	private ComboBox<String> Address_ci;

    @FXML
    private ComboBox<String> Address_gu;

    @FXML
    private Button Restaurant;

    @FXML
    private Button Cafe;

    @FXML
    private Button Store;


    @FXML
    void nextResList(ActionEvent event) throws IOException {
    	message();//�ؿ� ���� �޼ҵ� �̸�
    	if(condition==true){//message�޼ҵ��� msg�� ������ true�� �����Ͽ� ���� �� ���ǹ��� ����
    		condition = false;//����=false�� �ް�
    		return;//������ ���� �޼ҵ� ������ ��������
    	}
    	Singleton.setCategory(1);
    	next();
    }


    @FXML
    void nextCafeList(ActionEvent event) throws IOException {
    	message();//�ؿ� ���� �޼ҵ� �̸�
    	if(condition==true){//message�޼ҵ��� msg�� ������ true�� �����Ͽ� ���� �� ���ǹ��� ����
    		condition = false;//����=false�� �ް�
    		return;//������ ���� �޼ҵ� ������ ��������
    	}
    	Singleton.setCategory(2);
    	next();
    }


    @FXML
    void nextStoreList(ActionEvent event) throws IOException {
    	message();//�ؿ� ���� �޼ҵ� �̸�
    	if(condition==true){//message�޼ҵ��� msg�� ������ true�� �����Ͽ� ���� �� ���ǹ��� ����
    		condition = false;//����=false�� �ް�
    		return;//������ ���� �޼ҵ� ������ ��������
    	}
     	Singleton.setCategory(3);
     	next();
    	}

//�׼��̺�Ʈ�� ���� �޼ҵ� ����
    Message msg = new Message();
    Boolean condition= false;

    public void message(){
    	if(Address_ci.getValue() == null && Address_gu.getValue() == null) {
		msg.setMessage("�ּҸ� Ȯ�����ּ���");
		condition = true;
	}
	else if(Address_ci.getValue() == null){
		msg.setMessage("�ø� �������ּ���");
		condition = true;
	}
	else if(Address_gu.getValue() == null){
		msg.setMessage("���� �������ּ���");
		condition = true;
	}
  }

//�׼��̺�Ʈ�� ���� �޼ҵ� ����
    public void next() {
	   	Restaurant.getScene().getWindow().hide();
    	Stage resList = new Stage();
   		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("../view/ShopListCard.fxml"));
			Scene scene = new Scene(root);
			resList.setTitle("���� ����Ʈ");
			resList.setScene(scene);
			resList.show();
		} catch (IOException e) {
			System.out.println("����Ʈ�������̵�����:"+e.getMessage());
		}
 }

//DB���� �޺� �۽��� ���� �޼ҵ� ����
    DBConnect connect = new DBConnect();

    public void getLocalList(){
    	String sql = "SELECT * FROM local";
    	ObservableList<String> ciList = FXCollections.observableArrayList(); // ���� sql���� ����Ʈ�� ���� ci�κ��� �־��� �޺�����Ʈ�� ����
    	ObservableList<String> guList = FXCollections.observableArrayList(); // ���� sql���� ����Ʈ�� ���� gu�κ��� �־��� �޺�����Ʈ�� ����

    	Connection conn = connect.getConnection();
    	Statement stmt;
    	ResultSet rs;

    	try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ciList.add(rs.getString("ci"));  //����Ʈ�� select�� ���� ci �κ��� ����
				guList.add(rs.getString("gu"));  //����Ʈ�� select�� ���� gu �κ��� ����
			}
		} catch (Exception e) {
			System.out.println("�޺��ڽ� ����Ʈ ���Կ���:"+e.getMessage());
		}
    	Address_ci.setItems(ciList);  //�޺��ڽ��� ����Ʈ�� ���� ci�� ����
    	Address_gu.setItems(guList);  //�޺��ڽ��� ����Ʈ�� ���� gu�� ����
    }

//initialize�� �޼��带 �������ν� ȭ�鿡 ���̰� ǥ��
	@Override
    public void initialize(URL location, ResourceBundle resources) {
		getLocalList();
    }

    public void comboChange(ActionEvent event) {
    }


}
