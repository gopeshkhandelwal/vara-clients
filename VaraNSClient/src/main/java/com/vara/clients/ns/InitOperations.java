package com.vara.clients.ns;

import java.rmi.RemoteException;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netsuite.suitetalk.client.v2020_1.WsClient;
import com.netsuite.suitetalk.proxy.v2020_1.lists.accounting.Currency;
import com.netsuite.suitetalk.proxy.v2020_1.platform.core.Record;
import com.netsuite.suitetalk.proxy.v2020_1.platform.core.types.GetAllRecordType;

/**
 * <p>
 * Displays a list of all sample operations and invokes the selected operation
 * by the user.
 * </p>
 * <p>
 * © 2019 NetSuite Inc. All rights reserved.
 * </p>
 */
@ParametersAreNonnullByDefault
public class InitOperations {

	private WsClient client;
	
	private static Logger LOGGER = LoggerFactory.getLogger(InitOperations.class);

	/**
	 * Constructor initializing a list of all sample operations.
	 *
	 * @param client Client used for all SOAP requests
	 */
	public InitOperations(WsClient client) {
		this.client = client;
	}
	
	/**
     * Starts selection of sample operation.
     */
    public void run() {
    	getAllRecord();
    }

	private void getAllRecord() {

		// Invoke the get() operation to retrieve the record
		List<Record> response;
		try {
			response = client.getAllRecords(GetAllRecordType.currency);
			System.out.println("response:" + response);

			response.stream().forEach(record -> {
				Currency c = (Currency) record;
				System.out.println("c.getName():" + c.getName());
				System.out.println("c.getDisplaySymbol():" + c.getDisplaySymbol());
				System.out.println("c.getInternalId():" + c.getInternalId());
			});

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
