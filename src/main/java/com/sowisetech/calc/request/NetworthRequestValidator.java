package com.sowisetech.calc.request;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sowisetech.calc.util.CalcAppMessages;
import com.sowisetech.calc.util.CalcConstants;
import com.sowisetech.calc.util.CalcCommon;

@Component
public class NetworthRequestValidator implements CalcIValidator {
	
	@Autowired
	CalcAppMessages appmessages;
	
	@Autowired
	CalcCommon common;

	public HashMap<String, HashMap<String, String>> validate(NetworthRequest networthRequest) {
		HashMap<String, HashMap<String, String>> allErrors = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> error = new HashMap<String, String>();
		error.put("EMPTY", appmessages.getValue_empty());
		if (networthRequest == null) {
			allErrors.put("NULL", error);
		} else {
			for (NetworthReq networthReq : networthRequest.getNetworthReq()) {

				if (networthReq != null && networthReq.getValue() != null) {
					error = validateValue(networthReq.getValue());
					if (error.isEmpty() == false) {
						allErrors.put("VALUE", error);
					}
				}
				if (networthReq != null && networthReq.getFutureValue() != null) {
					error = validateFutureValue(networthReq.getFutureValue());
					if (error.isEmpty() == false) {
						allErrors.put("FUTUREVALUE", error);
					}
				}
			}
		}

		return allErrors;

	}

	protected HashMap<String, String> validateFutureValue(String futureValue) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.FUTUREVALUE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.isValidDoubleNumber(futureValue, inputParamName).isEmpty() == false) {
			errors.put("VALUE", common.isValidDoubleNumber(futureValue, inputParamName));
		}
		return errors;
	}

	protected HashMap<String, String> validateValue(String value) {
		String inputParamName = CalcConstants.SPACE_WTIH_COLON + CalcConstants.VALUE;
		HashMap<String, String> errors = new HashMap<String, String>();
		if (common.isValidDoubleNumber(value, inputParamName).isEmpty() == false) {
			errors.put("VALUE", common.isValidDoubleNumber(value, inputParamName));
		}
		return errors;
	}
}