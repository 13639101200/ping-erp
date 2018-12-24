package com.ping.erp.finance.assist.repository;

import java.util.List;

import com.ping.erp.common.config.repository.CoreRepository;
import com.ping.erp.finance.assist.domain.VoucherWord;
import com.ping.erp.system.company.domain.BaseCompany;

/**
 * 凭证字数据存储接口
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:04:36
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface VoucherWordRepository extends CoreRepository<VoucherWord, String> {

	List<VoucherWord> findByCompany(BaseCompany company);

}