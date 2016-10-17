package org.thothlab.devilsvault.customercontrollers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thothlab.devilsvault.CustomerModel.CreditAccount;
import org.thothlab.devilsvault.db.model.ExternalUser;
import org.thothlab.devilsvault.jdbccontrollers.customerdoa.CustomerDAOHelper;
import org.thothlab.devilsvault.jdbccontrollers.customerdoa.TransferDAO;

@Controller
public class CustomerTransferFundsController {

	@RequestMapping("/customer/transferfunds")
	public ModelAndView helloworld(){
		ModelAndView model = new ModelAndView("customerPages/transferFunds");
		
		ExternalUser user = new ExternalUser(3,"JAY","TEMPE","TEMPE","USA",852,91231288.00);
		TransferDAO transferDAO = CustomerDAOHelper.transferDAO();
		
		int payerId=8;
		List<Integer> currentUserAccounts  = transferDAO.getMultipleAccounts(payerId);
		List<String> populatedPayeeAccounts=new ArrayList<>();
		List<String> userAccounts=new ArrayList<>();		
		for(Integer currentElements: currentUserAccounts){
			 transferDAO.getRelatedAccounts(currentElements,populatedPayeeAccounts);
			 transferDAO.getPayerAccounts(currentElements,userAccounts);	
		}
		
		 

	//	System.out.println(account.size());
	
		for(String elem: populatedPayeeAccounts){
			System.out.println(elem);
		}
		
		model.addObject("payeeAccounts",populatedPayeeAccounts);
		model.addObject("userAccounts",userAccounts);
	//	model.addObject("msg","Hello Gaurav");
		return model;
	}

}