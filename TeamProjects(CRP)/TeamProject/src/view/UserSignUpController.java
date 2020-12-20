package view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import application.SignUpMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.DBConnect;

public class UserSignUpController{

	@FXML
	private JFXButton SignUp;
	SignUpMessage msg = new SignUpMessage();
	DBConnect connect = new DBConnect(); // 모델패키지의 커넥트를 가졈
	Connection conn;


	@FXML
	private JFXButton SignUpBack;

	@FXML
	private JFXTextField SignUpId;

	@FXML
	private JFXTextField SignUpName;

	@FXML
	private JFXPasswordField SignUpPw;

	@FXML
	private JFXTextField SignUpTel;

	@FXML
	private Label PwCheck;

	@FXML
	void SignUpBackBtn(ActionEvent event) throws IOException {
		SignUpBack.getScene().getWindow().hide();
		Stage findaccount = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("../view/UserLogin.fxml"));
		Scene scene = new Scene(root);
		findaccount.setScene(scene);
		findaccount.setTitle("음식점 위생 App");	//뒤로가기 버튼 클릭 시 메인 타이틀의 이름을 "음식점 위생 App"으로 수정
		findaccount.show();
	}

	@FXML
	void SignUpResultBtn(ActionEvent event) throws SQLException, IOException {	//회원가입 내용을 DB로 보내는 회원가입 버튼

		conn = connect.getConnection();
		String checkId = "SELECT * FROM customer WHERE id=?";	//SELECT문을 통해 DB에 담겨있는 id를 검색한다
		PreparedStatement pstmt = conn.prepareStatement(checkId);
		pstmt.setString(1, SignUpId.getText());	//pstmt.setString를 통해 회원가입 페이지에서 id 입력시 위에있는 SELECT 쿼리로 DB의 id와 비교
		ResultSet rs = pstmt.executeQuery();	//결과를 rs에 담는다

		if (rs.next()) {	//결과를 담은 rs를 활용하여 if-else문으로 중복된 아이디값부터 비교
			SignUp.getScene().getWindow();
			msg.setMessage("중복된 아이디 입니다.");
		}
		//회원가입창 4개의 항목에 텍스트가 비어있을 시 출력하는 코드
		//회원가입창 4개의 항목 중 텍스트가 듬성듬성 입력되어 있을 경우, 텍스트가 비어있는 가장 위에서부터 입력해달라는 메시지를 출력한다
		  else if ((SignUpId.getText().equals("")) && SignUpName.getText().equals("") && SignUpPw.getText().equals("")	//모든 내용을 입력 하지않고 회원가입 버튼을 눌렀을 시
				&& SignUpTel.getText().equals("")) {
			msg.setMessage("회원가입 정보를 입력해 주세요.");
		} else if (SignUpId.getText().equals("")) {		//ID를 입력하지 않고 회원가입 버튼을 눌렀을 시
			msg.setMessage("ID를 입력해 주세요.");
		} else if (SignUpName.getText().equals("")) {	//이름을 입력하지 않고 회원가입 버튼을 눌렀을 시
			msg.setMessage("이름을 입력해 주세요.");
		} else if (SignUpPw.getText().equals("")) {		//비밀번호를 입력하지 않고 회원가입 버튼을 눌렀을 시
			msg.setMessage("비밀번호를 입력해 주세요.");
		} else if (SignUpTel.getText().equals("")) {	//전화번호를 입력하지 않고 회원가입 버튼을 눌렀을 시
			msg.setMessage("전화번호를 입력해 주세요.");
		}
		  else {	//중복이 되지 않는다면 => else로 표현

			String sql = "insert into customer values(?,?,?,?)";	//insert문을 통해 회원가입란에 입력한 항목들을 DB에 삽입한다
			PreparedStatement pstmt1 = conn.prepareCall(sql);
			pstmt1.setString(1, SignUpTel.getText());
			pstmt1.setString(2, SignUpName.getText());
			pstmt1.setString(3, SignUpPw.getText());
			pstmt1.setString(4, SignUpId.getText());
			pstmt1.executeUpdate();

			SignUp.getScene().getWindow().hide();	//모든 내용을 입력 후 SignUp(회원가입 버튼)을 클릭 시 .hide로 화면이 사라짐
			Stage signup = new Stage();	//새 화면을 준비한다
			Parent root = FXMLLoader.load(getClass().getResource("../view/UserLogin.fxml"));	//로그인 페이지로 돌아간다(회원가입 정보를 입력할 수 있게 하기위해)
			Scene scene = new Scene(root);
			signup.setScene(scene);
			signup.setTitle("음식점 위생 App");	//로그인 화면으로 돌아갔을 때 타이틀 명을 뜻한다
			signup.show();	//로그인 페이지를 보여주는 .show를 사용
			msg.setMessage("회원가입 성공! 아이디는 " + SignUpId.getText() + " 입니다!");	//회원가입 완료 시 ID에 입력했던 문자만 화면에 메시지로 출력한다
		}
	}
}