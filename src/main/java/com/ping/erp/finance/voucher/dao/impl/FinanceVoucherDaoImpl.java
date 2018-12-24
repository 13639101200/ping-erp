package com.ping.erp.finance.voucher.dao.impl;

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
import com.ping.erp.finance.voucher.dao.FinanceVoucherDao;
import com.ping.erp.finance.voucher.domain.FinanceVoucher;
import com.ping.erp.finance.voucher.repository.FinanceVoucherRepository;
import com.ping.erp.system.company.domain.BaseCompany;

/**
 * 记账凭证数据访问接口实现
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:06:44
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Repository
public class FinanceVoucherDaoImpl implements FinanceVoucherDao {

	@Autowired
	private FinanceVoucherRepository repository;

	public PageResult<FinanceVoucher> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<FinanceVoucher> springPage = repository.findAll(fields, keyword, field, order, size, page);
		return new PageResultImpl<FinanceVoucher>(springPage.getTotalElements(), springPage.getContent());
	}

	public FinanceVoucher findOne(String id) {
		return repository.findOne(id);
	}

	public void save(FinanceVoucher entity) {
		repository.save(entity);
	}

	public void delete(List<FinanceVoucher> entities) {
		repository.delete(entities);
	}

	public PageResult<FinanceVoucher> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<FinanceVoucher> springPage = repository.findByCompany(company, fields, keyword, field, order, size, page);
		return new PageResultImpl<FinanceVoucher>(springPage.getTotalElements(), springPage.getContent());
	}

	@Override
	public PageResult<FinanceVoucher> findByPeriod(FinancePeriod period, List<String> fields, String keyword, String field, String order, int size, int page) {
		Specification<FinanceVoucher> specification = new Specification<FinanceVoucher>() {
			@Override
			public Predicate toPredicate(Root<FinanceVoucher> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
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

		Page<FinanceVoucher> springPage = repository.findAll(specification, pageable);
		return new PageResultImpl<FinanceVoucher>(springPage.getTotalElements(), springPage.getContent());
	}

}