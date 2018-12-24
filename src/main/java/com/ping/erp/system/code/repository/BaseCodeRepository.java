package com.ping.erp.system.code.repository;

import java.util.List;

import com.ping.erp.common.config.repository.CoreRepository;
import com.ping.erp.system.code.domain.BaseCode;

/**
 * 基础编码数据存储接口
 *
 * @version 1.0.0-RELEASE
 * @time 2018-11-14 04:37:41
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface BaseCodeRepository extends CoreRepository<BaseCode, String> {

	List<BaseCode> findByTypeCode(String typeCode);

}