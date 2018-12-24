package com.ping.erp.finance.subject.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ping.erp.basedata.subject.dao.TemplateSubjectDao;
import com.ping.erp.basedata.subject.domain.SubjectTemplate;
import com.ping.erp.basedata.subject.domain.TemplateSubject;
import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.SimpleResult;
import com.ping.erp.common.result.impl.PageResultImpl;
import com.ping.erp.common.result.impl.SimpleResultImpl;
import com.ping.erp.common.util.StringUtil;
import com.ping.erp.finance.period.dao.FinancePeriodDao;
import com.ping.erp.finance.period.domain.FinancePeriod;
import com.ping.erp.finance.subject.dao.ArchivedSubjectDao;
import com.ping.erp.finance.subject.dao.InitialSubjectDao;
import com.ping.erp.finance.subject.domain.ArchivedSubject;
import com.ping.erp.finance.subject.domain.InitialSubject;
import com.ping.erp.finance.subject.service.ArchivedSubjectService;
import com.ping.erp.system.company.domain.BaseCompany;

/**
 * 结账科目业务服务接口实现
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:05:35
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Service
@Transactional
public class ArchivedSubjectServiceImpl implements ArchivedSubjectService {

	/**
	 * 结账科目-科目方向-借方ID
	 */
	@Value("${define.finance.subject.archivedsubject.subjectdirection.borrowid}")
	private String borrowid;
	/**
	 * 结账科目-科目方向-贷方ID
	 */
	@Value("${define.finance.subject.archivedsubject.subjectdirection.creditid}")
	private String creditid;

	@Autowired
	private ArchivedSubjectDao dao;
	@Autowired
	private FinancePeriodDao financePeriodDao;
	@Autowired
	private TemplateSubjectDao templateSubjectDao;
	@Autowired
	private InitialSubjectDao initialSubjectDao;

	public PageResult<ArchivedSubject> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findAll(fields, keyword, field, order, size, page);
	}

	public ArchivedSubject findOne(String id) {
		return dao.findOne(id);
	}

	public void save(ArchivedSubject entity) {
		if (entity.getSubjectId() == null || "".equals(entity.getSubjectId())) {
			entity.setSubjectId(StringUtil.getUUID());
		}
		dao.save(entity);
	}

	public void delete(List<ArchivedSubject> entities) {
		dao.delete(entities);
	}

	public PageResult<ArchivedSubject> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findByCompany(company, fields, keyword, field, order, size, page);
	}

	@Override
	public PageResult<ArchivedSubject> findByCompanyAndCurrentPeriod(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		FinancePeriod period = financePeriodDao.findByCompanyAndNoArchived(company);
		if (period == null) {
			period = new FinancePeriod();
			period.setPeriodId(StringUtil.getUUID());
			period.setCompany(company);
			financePeriodDao.save(period);
			return new PageResultImpl<ArchivedSubject>(0, new ArrayList<ArchivedSubject>());
		} else {
			return dao.findByPeriod(period, fields, keyword, field, order, size, page);
		}
	}

	@Override
	public void saveToCurrentPeriod(BaseCompany company, ArchivedSubject entity) {
		FinancePeriod period = financePeriodDao.findByCompanyAndNoArchived(company);
		entity.setPeriod(period);
		if (entity.getSubjectId() == null || "".equals(entity.getSubjectId())) {
			entity.setSubjectId(StringUtil.getUUID());
		}
		dao.save(entity);
	}

	@Override
	public void importSaveToCurrentPeriod(BaseCompany company, SubjectTemplate template) {
		FinancePeriod period = financePeriodDao.findByCompanyAndNoArchived(company);
		List<TemplateSubject> templateSubjectList = templateSubjectDao.findByTemplate(template);
		List<ArchivedSubject> archivedSubjectList = new ArrayList<ArchivedSubject>();
		for (TemplateSubject subject : templateSubjectList) {
			archivedSubjectList.add(new ArchivedSubject(subject, period));
		}
		dao.save(archivedSubjectList);
	}

	@Override
	public SimpleResult startSubject(BaseCompany company) {
		FinancePeriod currentPeriod = financePeriodDao.findByCompanyAndNoArchived(company);
		List<ArchivedSubject> currentSubject = dao.findByPeriod(currentPeriod);

		// 验证借贷是否平衡-开始
		double borrowTotal = 0;
		double creditTotal = 0;
		for (ArchivedSubject subject : currentSubject) {
			if (borrowid.equals(subject.getSubjectDirection().getCodeId())) {
				borrowTotal += subject.getSubjectMoney();
			} else if (creditid.equals(subject.getSubjectDirection().getCodeId())) {
				creditTotal += subject.getSubjectMoney();
			}
		}
		if (borrowTotal != creditTotal) {
			return new SimpleResultImpl(-1, "启用失败：借贷不平衡");
		}
		// 验证借贷是否平衡-结束

		// 启用会计科目-开始
		Timestamp now = new Timestamp(new Date().getTime());
		currentPeriod.setArchivedTime(now);
		currentPeriod.setIsArchived(true);
		FinancePeriod newPeriod = new FinancePeriod();
		newPeriod.setPeriodId(StringUtil.getUUID());
		newPeriod.setCompany(company);
		newPeriod.setInitialTime(now);

		List<InitialSubject> newInitialSubject = new ArrayList<InitialSubject>();
		List<ArchivedSubject> newArchivedSubject = new ArrayList<ArchivedSubject>();
		for (ArchivedSubject subject : currentSubject) {
			String subjectId = StringUtil.getUUID();
			newInitialSubject.add(new InitialSubject(subject, subjectId, newPeriod));
			newArchivedSubject.add(new ArchivedSubject(subject, subjectId, newPeriod));
		}

		financePeriodDao.save(currentPeriod);
		financePeriodDao.save(newPeriod);
		initialSubjectDao.save(newInitialSubject);
		dao.save(newArchivedSubject);

		return new SimpleResultImpl(1, "启用成功");
		// 启用会计科目-结束
	}

	@Override
	public SimpleResult isStart(BaseCompany company) {
		List<FinancePeriod> periodList = financePeriodDao.findByCompanyAndIsArchived(company, true);
		if (periodList != null && periodList.size() > 0) {
			return new SimpleResultImpl(1, "已经启用");
		} else {
			return new SimpleResultImpl(-1, "尚未启用");
		}
	}

	@Override
	public SimpleResult archivedSubject(BaseCompany company) {
		FinancePeriod currentPeriod = financePeriodDao.findByCompanyAndNoArchived(company);
		List<ArchivedSubject> currentSubject = dao.findByPeriod(currentPeriod);

		// 验证借贷是否平衡-开始
		double borrowTotal = 0;
		double creditTotal = 0;
		for (ArchivedSubject subject : currentSubject) {
			if (borrowid.equals(subject.getSubjectDirection().getCodeId())) {
				borrowTotal += subject.getSubjectMoney();
			} else if (creditid.equals(subject.getSubjectDirection().getCodeId())) {
				creditTotal += subject.getSubjectMoney();
			}
		}
		if (borrowTotal != creditTotal) {
			return new SimpleResultImpl(-1, "启用失败：借贷不平衡");
		}
		// 验证借贷是否平衡-结束

		// 结账会计科目-开始
		Timestamp now = new Timestamp(new Date().getTime());
		currentPeriod.setArchivedTime(now);
		currentPeriod.setIsArchived(true);
		FinancePeriod newPeriod = new FinancePeriod();
		newPeriod.setPeriodId(StringUtil.getUUID());
		newPeriod.setCompany(company);
		newPeriod.setInitialTime(now);

		List<InitialSubject> newInitialSubject = new ArrayList<InitialSubject>();
		List<ArchivedSubject> newArchivedSubject = new ArrayList<ArchivedSubject>();
		for (ArchivedSubject subject : currentSubject) {
			String subjectId = StringUtil.getUUID();
			newInitialSubject.add(new InitialSubject(subject, subjectId, newPeriod));
			newArchivedSubject.add(new ArchivedSubject(subject, subjectId, newPeriod));
		}

		financePeriodDao.save(currentPeriod);
		financePeriodDao.save(newPeriod);
		initialSubjectDao.save(newInitialSubject);
		dao.save(newArchivedSubject);

		return new SimpleResultImpl(1, "结账成功").setDate(now.getTime());
		// 结账会计科目-结束
	}

}