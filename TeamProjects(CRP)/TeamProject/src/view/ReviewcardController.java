package view;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Review;

public class ReviewcardController {
	@FXML
	private Label userId;
	@FXML
	private Label time;
	@FXML
	private Label text;
	@FXML
	private VBox maskrate;
	@FXML
	private Label type1;
	@FXML
	private Label type2;
	@FXML
	private Label type3;
	@FXML
	private Label type4;
	@FXML
	private HBox cardList;

//리뷰카드에 넣을 내용을 해당 아이디에 맞게 순서대로 삽입
	public void setData(Review review) {
		userId.setText(review.getId());
		time.setText(review.getDate());
		text.setText(review.getText());
		type1.setText(review.getType1());
		type2.setText(review.getType2());
		type3.setText(review.getType3());
		type4.setText(review.getType4());
	}

}
