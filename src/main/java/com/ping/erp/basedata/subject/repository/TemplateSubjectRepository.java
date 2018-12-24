package com.ping.erp.basedata.subject.repository;

import com.ping.erp.common.config.repository.CoreRepository;

import java.util.List;

import com.ping.erp.basedata.subject.domain.SubjectTemplate;
import com.ping.erp.basedata.subject.domain.TemplateSubject;

/**
 * 模板科目数据存储接口
 *
 * @version 1.1.2-RELEASE
 * @time 2018-11-23 12:33:48
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface TemplateSubjectRepository extends CoreRepository<TemplateSubject, String> {

	List<TemplateSubject> findByTemplate(SubjectTemplate template);

}