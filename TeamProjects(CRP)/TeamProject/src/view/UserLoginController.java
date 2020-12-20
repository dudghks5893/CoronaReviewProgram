package view;



import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.DBConnect;
import model.Singleton;

public class UserLoginController{

    @FXML
    private Pane LoginPane;

	@FXML
    private JFXTextField UserId;

	@FXML
    private JFXPasswordField UserPw;

    @FXML
    private JFXButton UserLogin;	//�α��� ��ư

    @FXML
    private JFXButton SignUp;	//ȸ������ ��ư

    Message msg = new Message();
    DBConnect connect = new DBConnect();
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

    @FXML
    void SignUpBtn(ActionEvent event) throws IOException {	//ȸ������ �������� �����ϴ� ��Ʈ��
    	SignUp.getScene().getWindow().hide();
    	Stage signup = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("../view/UserSignUp.fxml"));
    	Scene scene = new Scene(root);
    	signup.setScene(scene);
    	signup.setTitle("ȸ�� ����");
    	signup.show();
    }

    @FXML
    void UserLoginBtn(ActionEvent event) throws IOException, SQLException{	//�α��� ��ư�� ���� ��Ʈ��

    	conn = connect.getConnection();

    	String sql = "SELECT * FROM customer WHERE id=? AND password=?";	//SELECT������ CUSTOMER���̺���
    																		//���̵�� �н����带 ������
    	Singleton.setUsname(UserId.getText());

    	pstmt = conn.prepareStatement(sql);
    	pstmt.setString(1, UserId.getText());
    	pstmt.setString(2, UserPw.getText());
    	rs = pstmt.executeQuery();

    	if(rs.next()) {	//���� sql���� �� id�� password�� �Է� �� DB�� �� => ���� ���� ��

    		UserLogin.getScene().getWindow().hide();	//���� �α���â�� ȭ���� .hide()�� ����� ���� �������� �Ѿ �غ�

    		Stage cate = new Stage(); //�� ȭ���� ����մϴ�. cate��� �ν��Ͻ����� ���Ƿ� ���� ������.
        	Parent root = FXMLLoader.load(getClass().getResource("../view/Category.fxml")); //��ȭ���� Category.fxml�� ����ǰ� ���ݴϴ�
        	Scene scene = new Scene(root);
        	cate.setScene(scene);
        	cate.setTitle("������ ī�װ���");	//����� Category.fxml ȭ���� Ÿ��Ʋ�� "������ ī�װ���"�� ����
        	cate.show();

    	}
    	else if((UserId.getText().equals("")) && UserPw.getText().equals("")){	//���̵�� ��й�ȣ �ؽ�Ʈ�� �ƹ��͵� �Է����� �ʾ��� ��
    		msg.setMessage("�α��� ������ �Է��� �ּ���.");
    	}

    	else if(UserPw.getText().equals("")){	//���̵� �Է��ϰ� ��й�ȣ�� �Է����� �ʾ��� ��
    		msg.setMessage("��й�ȣ�� �Է��� �ּ���.");
    	}
    	else if(UserId.getText().equals("")){	//��й�ȣ�� �Է��ϰ� ���̵� �Է����� �ʾ��� ��
    		msg.setMessage("���̵� �Է��� �ּ���.");
    	}
    	else{									//���̵�� ��й�ȣ ��� �Է�������, �Է������� DB�� ���� ������ ��
    		msg.setMessage("���̵�� ��й�ȣ�� ��Ȯ�� ���ּ���.");
    	}
    }
}

