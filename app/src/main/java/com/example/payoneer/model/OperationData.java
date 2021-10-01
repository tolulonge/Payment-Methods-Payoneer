/*
 * Copyright (c) 2020 Payoneer Germany GmbH
 * https://www.payoneer.com
 *
 * This file is open source and available under the MIT license.
 * See the LICENSE file for more information.
 */

package com.example.payoneer.model;

import java.util.Map;


/**
 * This class is designed to hold information for operation (CHARGE, PAYOUT, UPDATE) with selected payment network.
 */
public class OperationData {
    /** Simple API, optional */
    private AccountInputData account;
    /** Simple API, optional */
    private Boolean autoRegistration;
    /** Simple API, optional */
    private Boolean allowRecurrence;
    /** Advanced API, optional */
    private Map<String, Boolean> checkboxes;
    /** Provider request parameters. */
    private ProviderParameters providerRequest;
    /** Customer web browser data */
    private BrowserData browserData;
}
