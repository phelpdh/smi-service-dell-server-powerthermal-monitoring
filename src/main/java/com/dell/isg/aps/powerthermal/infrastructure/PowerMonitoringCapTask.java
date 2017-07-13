/**
 * Copyright � 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */

package com.dell.isg.aps.powerthermal.infrastructure;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dell.isg.aps.powerthermal.common.EnumDefinition;
import com.dell.isg.aps.powerthermal.common.SetPowerThermalRequest;
import com.dell.isg.smi.adapter.server.model.WsmanCredentials;
import com.dell.isg.smi.adapter.server.powerthermal.IPowerThermalAdapter;
import com.dell.isg.smi.commons.model.server.JobStatus;

/**
 * @author rahman.muhammad
 *
 */
public class PowerMonitoringCapTask implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(PowerMonitoringCapTask.class.getName());
			
	private IPowerThermalAdapter adapter;
	private List <JobStatus> jobs;
	private SetPowerThermalRequest request;
	
	public PowerMonitoringCapTask(List <JobStatus> jobs,IPowerThermalAdapter adapter,SetPowerThermalRequest request){
			this.jobs=jobs;
			this.adapter=adapter;
			this.request=request;
				
	}
	
	
	@Override
	public void run() {
	
		  try {
			  this.setPowerCapping();			  			  
		  }
        catch (Exception exp){
        	logger.error("Error setting  power cap values for server",exp);
        }
		  
	}

	
	private void setPowerCapping() throws Exception {
		
		
		JobStatus status = null;
		
		 WsmanCredentials wsmanCredentials = new WsmanCredentials(request.getServerAddress(), request.getUserName(), request.getPassword());
		String powerSettings = request.isEnableCapping() ? EnumDefinition.ENABLED.toString() : EnumDefinition.DISABLED.toString();
		
		adapter.enablePowerCapping(wsmanCredentials, powerSettings);

		if (request.getPowerCap() > 0) {
			adapter.setPowerCapping(wsmanCredentials, String.valueOf(request.getPowerCap()));
		}

		status = adapter.createConfigJob(wsmanCredentials);
		status.setServerAddress(request.getServerAddress());
		status.setDescription("Configure Power and Thermal consumption job");
		jobs.add(status);
			
	}
	
	
}
