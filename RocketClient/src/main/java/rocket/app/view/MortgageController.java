package rocket.app.view;

import eNums.eAction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	
	ObservableList<String> comboTerm = FXCollections.observableArrayList("15 Yrs", "30 Yrs");
	
	@FXML
	private Label lblMortgagePayment;
	
	@FXML
	private Button CalculatePayment;
	
	@FXML 
	private Label exception;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	//			Call this when btnPayment is pressed, calculate the payment
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Object message = null;
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		
		//			set the loan request details...  rate, term, amount, credit score, downpayment
		
		lq.setdRate(Double.parseDouble(txtHouseCost.getText())-Double.parseDouble(txtDownPayment.getText()));
		lq.setdIncome(Double.parseDouble((txtIncome.getText())));
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		lq.setiDownPayment(Integer.parseInt(txtDownPayment.getText()));
		
		if (cmbTerm.getValue() == "15 Yrs") {
			lq.setiTerm(15);
		}
		else {
			lq.setiTerm(30);
		}
		
		//			I've created you an instance of lq...  execute the setters in lq
		
		a.setLoanRequest(lq);
		
		//	send lq as a message to RocketHub		
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		
		//			lRequest is an instance of LoanRequest.
		//			after it's returned back from the server, the payment (dPayment)
		//			should be calculated.
		//			Display dPayment on the form, rounded to two decimal places
		
		double PITIpay;
		
		double PITI1 = (lRequest.getdIncome() / 12 * 0.28);
		double PITI2 = ((lRequest.getdIncome() / 12 * 0.36) - lRequest.getdExpenses());
		
		if (PITI1 < PITI2) {
			PITIpay = PITI1;
		}
		else {
			PITIpay = PITI2;
		}
		
		if (lRequest.getdPayment() < PITIpay) {
			lblMortgagePayment.setText(String.format("%.2f", Double.toString(lRequest.getdPayment())));
		}
		else {
			lblMortgagePayment.setText("You cannot afford this payment.");
		}
			
		
		
	}
}
