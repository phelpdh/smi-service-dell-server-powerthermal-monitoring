/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
/**
 * 
 */
package com.dell.isg.aps.powerthermal.infrastructure;

import java.util.List;

import com.dell.isg.aps.powerthermal.common.BasePowerThermalRequest;
import com.dell.isg.aps.powerthermal.common.HwMonitoringAgg;
import com.dell.isg.aps.powerthermal.common.SetPowerThermalAggRequest;
import com.dell.isg.aps.powerthermal.common.SetPowerThermalRequest;
import com.dell.isg.smi.adapter.server.model.WsmanCredentials;
import com.dell.isg.smi.commons.model.server.JobStatus;

/**
 * @author rahman.muhammad
 *
 */
public interface IPowerThermalInfrastructure {

    public Object collectPowerMonitoring(WsmanCredentials wsmanCredentials) throws Exception;
    public HwMonitoringAgg collectPowerThermalAll(List<BasePowerThermalRequest> request) throws Exception;
    
    public JobStatus setPowerThermalCapping(SetPowerThermalRequest request) throws Exception;
    public List<JobStatus> setPowerThermalCappingOnAll(SetPowerThermalAggRequest request) throws Exception;
    
}
