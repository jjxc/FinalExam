package rocket.app.view;

import eNums.eAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	private MainApp mainApp;
	
	//	TODO - RocketClient.RocketMainController
	
	//	Create private instance variables for:
	//		TextBox  - 	txtIncome
	//		TextBox  - 	txtExpenses
	//		TextBox  - 	txtCreditScore
	//		TextBox  - 	txtHouseCost
	//		ComboBox -	loan term... 15 year or 30 year
	//		Labels   -  various labels for the controls
	//		Button   -  button to calculate the loan payment
	//		Label    -  to show error messages (exception throw, payment exception)
	
	@FXML
	private TextField txtIncome;
	
	@FXML
	private TextField txtExpenses;
	
	@FXML
	private TextField txtCreditScore;
	
	@FXML
	private TextField txtHouseCost;
	
	@FXML
	private TextField txtDownPayment;
	
	@FXML
	private ComboBox<String> cmbTerm;
	
	@FXML
	private Label lblMortgagePayment;
	
	@FXML
	private Button CalculatePayment;
	
	@FXML
	private Label msgReturn;
	
	@FXML 
	private Label exception;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	
	//	TODO - RocketClient.RocketMainController
	//			Call this when btnPayment is pressed, calculate the payment
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Object message = null;
		//	TODO - RocketClient.RocketMainController
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		
		//			set the loan request details...  rate, term, amount, credit score, downpayment
		
		lq.setdRate(Double.parseDouble(txtHouseCost.getText())-Double.parseDouble(txtDownPayment.getText()));
		lq.setiTerm(Integer.parseInt(cmbTerm.getStyle()));
		lq.setdIncome(Double.parseDouble((txtIncome.getText())));
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		lq.setiDownPayment(Integer.parseInt(txtDownPayment.getText()));
		
		//			I've created you an instance of lq...  execute the setters in lq
		
		a.setLoanRequest(lq);
		
		//	send lq as a message to RocketHub		
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		//	TODO - RocketClient.HandleLoanRequestDetails
		//			lRequest is an instance of LoanRequest.
		//			after it's returned back from the server, the payment (dPayment)
		//			should be calculated.
		//			Display dPayment on the form, rounded to two decimal places
		
		double PITI1 = (lRequest.getdIncome() * 0.28);
		double PITI2 = ((lRequest.getdIncome() * 0.36) - lRequest.getdExpenses());
		
		//double PITI = Math.min((lRequest.getdIncome() - lRequest.getdExpenses()) * 0.36, lRequest.getdIncome() * 0.28);
		//lblMortgagePayment.setText(String.format("%.2f", -lRequest.getdPayment()));
		
		if (PITI >= Math.abs(lRequest.getdPayment())) {
			msgReturn.setText("You can afford this payment.");
		}
		else{
			msgReturn.setText("You cannot afford this payment.");
		}
		
			
		
		
	}
}
