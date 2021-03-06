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
    private JFXButton UserLogin;	//로그인 버튼

    @FXML
    private JFXButton SignUp;	//회원가입 버튼

    Message msg = new Message();
    DBConnect connect = new DBConnect();
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

    @FXML
    void SignUpBtn(ActionEvent event) throws IOException {	//회원가입 페이지로 연결하는 컨트롤
    	SignUp.getScene().getWindow().hide();
    	Stage signup = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("../view/UserSignUp.fxml"));
    	Scene scene = new Scene(root);
    	signup.setScene(scene);
    	signup.setTitle("회원 가입");
    	signup.show();
    }

    @FXML
    void UserLoginBtn(ActionEvent event) throws IOException, SQLException{	//로그인 버튼에 대한 컨트롤

    	conn = connect.getConnection();

    	String sql = "SELECT * FROM customer WHERE id=? AND password=?";	//SELECT문으로 CUSTOMER테이블의
    																		//아이디와 패스워드를 가져옴
    	Singleton.setUsname(UserId.getText());

    	pstmt = conn.prepareStatement(sql);
    	pstmt.setString(1, UserId.getText());
    	pstmt.setString(2, UserPw.getText());
    	rs = pstmt.executeQuery();

    	if(rs.next()) {	//위의 sql문에 들어갈 id와 password를 입력 후 DB와 비교 => 성공 했을 시

    		UserLogin.getScene().getWindow().hide();	//유저 로그인창의 화면을 .hide()로 숨기며 다음 페이지로 넘어갈 준비

    		Stage cate = new Stage(); //새 화면을 출력합니다. cate라는 인스턴스명을 임의로 만들어서 지정함.
        	Parent root = FXMLLoader.load(getClass().getResource("../view/Category.fxml")); //새화면이 Category.fxml로 연결되게 해줍니다
        	Scene scene = new Scene(root);
        	cate.setScene(scene);
        	cate.setTitle("지역구 카테고리");	//연결된 Category.fxml 화면의 타이틀을 "지역구 카테고리"로 변경
        	cate.show();

    	}
    	else if((UserId.getText().equals("")) && UserPw.getText().equals("")){	//아이디와 비밀번호 텍스트에 아무것도 입력하지 않았을 시
    		msg.setMessage("로그인 정보를 입력해 주세요.");
    	}

    	else if(UserPw.getText().equals("")){	//아이디만 입력하고 비밀번호를 입력하지 않았을 시
    		msg.setMessage("비밀번호를 입력해 주세요.");
    	}
    	else if(UserId.getText().equals("")){	//비밀번호만 입력하고 아이디를 입력하지 않았을 시
    		msg.setMessage("아이디를 입력해 주세요.");
    	}
    	else{									//아이디와 비밀번호 모두 입력했지만, 입력정보가 DB에 없는 내용일 시
    		msg.setMessage("아이디와 비밀번호를 재확인 해주세요.");
    	}
    }
}


