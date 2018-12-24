package com.ping.erp.finance.subject.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.impl.PageResultImpl;
import com.ping.erp.finance.period.domain.FinancePeriod;
import com.ping.erp.finance.subject.dao.ArchivedSubjectDao;
import com.ping.erp.finance.subject.domain.ArchivedSubject;
import com.ping.erp.finance.subject.repository.ArchivedSubjectRepository;
import com.ping.erp.system.company.domain.BaseCompany;

/**
 * 结账科目数据访问接口实现
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:05:35
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Repository
public class ArchivedSubjectDaoImpl implements ArchivedSubjectDao {

	@Autowired
	private ArchivedSubjectRepository repository;

	public PageResult<ArchivedSubject> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<ArchivedSubject> springPage = repository.findAll(fields, keyword, field, order, size, page);
		return new PageResultImpl<ArchivedSubject>(springPage.getTotalElements(), springPage.getContent());
	}

	public ArchivedSubject findOne(String id) {
		return repository.findOne(id);
	}

	public void save(ArchivedSubject entity) {
		repository.save(entity);
	}

	public void delete(List<ArchivedSubject> entities) {
		repository.delete(entities);
	}

	public PageResult<ArchivedSubject> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<ArchivedSubject> springPage = repository.findByCompany(company, fields, keyword, field, order, size, page);
		return new PageResultImpl<ArchivedSubject>(springPage.getTotalElements(), springPage.getContent());
	}

	@Override
	public PageResult<ArchivedSubject> findByPeriod(FinancePeriod period, List<String> fields, String keyword, String field, String order, int size, int page) {
		Specification<ArchivedSubject> specification = new Specification<ArchivedSubject>() {
			@Override
			public Predicate toPredicate(Root<ArchivedSubject> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				List<Predicate> predicates = new ArrayList<Predicate>();

				predicates.add(builder.equal(root.get("period"), period));

				List<Predicate> likePredicates = new ArrayList<Predicate>();
				if (fields != null && fields.size() > 0 && keyword != null && !"".equals(keyword)) {
					for (String field : fields) {
						Expression<String> exp = null;
						String[] f = field.split("\\.");
						if (f.length == 2) {
							exp = root.join(f[0]).get(f[1]);
						} else if (f.length == 1) {
							exp = root.get(field);
						}
						likePredicates.add(builder.like(exp, "%" + keyword + "%"));
					}
					predicates.add(builder.or(likePredicates.toArray(new Predicate[likePredicates.size()])));
				}
				return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
			}
		};

		Direction direction = null;
		if (field == null || "".equals(field)) {
			field = "subjectCode";
			direction = Direction.ASC;
		} else if ("desc".equals(order)) {
			direction = Direction.DESC;
		} else {
			direction = Direction.ASC;
		}
		Pageable pageable = new PageRequest(page - 1, size, direction, field);

		Page<ArchivedSubject> springPage = repository.findAll(specification, pageable);
		return new PageResultImpl<ArchivedSubject>(springPage.getTotalElements(), springPage.getContent());
	}

	@Override
	public void save(List<ArchivedSubject> entities) {
		repository.save(entities);
	}

	@Override
	public List<ArchivedSubject> findByPeriod(FinancePeriod period) {
		return repository.findByPeriod(period);
	}

}