/**
  
   * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 * 
 */
package com.dell.isg.aps.powerthermal.infrastructure;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dell.isg.aps.powerthermal.common.Credentials;
import com.dell.isg.smi.adapter.server.model.WsmanCredentials;
import com.dell.isg.smi.adapter.server.powerthermal.IPowerThermalAdapter;

/**
 * @author rahman.muhammad
 *
 */
public class PowerMonitoringCollectionTask implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(PowerMonitoringCollectionTask.class.getName());
	private IPowerThermalAdapter adapter;
	private List <Object> hwPowerMonitoring;
	private Credentials credential;
	
	public PowerMonitoringCollectionTask(List <Object> hwPowerMonitoring,IPowerThermalAdapter adapter, Credentials credentials){
		this.hwPowerMonitoring=	hwPowerMonitoring;
		this.adapter=adapter;
		this.credential=credentials;
	 	
	}
	
	
	
	@Override
	public void run() {
	   
		 try {
			 WsmanCredentials wsmanCredentials = new WsmanCredentials(credential.getAddress(), credential.getUserName(), credential.getPassword());
			   Object pwMonitoring=adapter.collectPowerMonitoring(wsmanCredentials);
			   hwPowerMonitoring.add(pwMonitoring);
			 	 
		 }
		 catch(Exception exp){
			logger.error("TaskExecutor failed to execute wman command for power monitoring",exp); 
		 }
    	}
	
 }
