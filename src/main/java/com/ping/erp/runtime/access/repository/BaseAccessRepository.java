package com.ping.erp.runtime.access.repository;

import com.ping.erp.common.config.repository.CoreRepository;
import com.ping.erp.runtime.access.domain.BaseAccess;
import com.ping.erp.system.user.domain.BaseUser;

/**
 * 访问统计数据存储接口
 *
 * @version 1.0.9-RELEASE
 * @time 2018-11-20 05:33:27
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface BaseAccessRepository extends CoreRepository<BaseAccess, String> {

	BaseAccess findByUserAndClientIpAndServerUrl(BaseUser user, String clientIp, String serverUrl);

}