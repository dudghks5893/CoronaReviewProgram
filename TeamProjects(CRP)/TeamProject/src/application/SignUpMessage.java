package application;

import javafx.scene.control.Alert;

public class SignUpMessage {
	public void setMessage(String gogo)
	{
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("ȸ������");
		alert.setHeaderText("");
		alert.setContentText(gogo);
		alert.show();
	}

}
