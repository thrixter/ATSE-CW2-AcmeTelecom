package com.acmetelecom.billing;

import com.acmetelecom.calling.Call;
import com.acmetelecom.customer.Tariff;

import java.math.BigDecimal;

/**
 * User: javad
 * Date: 07/12/2011
 */
public interface BillCalculator {

    BigDecimal getCallCost(Call call, Tariff tariff);

}
