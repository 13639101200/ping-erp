package com.ping.erp.common.config.repository.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.ping.erp.common.config.repository.CoreRepository;
import com.ping.erp.system.company.domain.BaseCompany;

/**
 * 核心存储库实现
 *
 * @version 1.0.0-RELEASE
 * @time 2018-11-14
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public class CoreRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements CoreRepository<T, ID> {

	public CoreRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
	}

	@Override
	public Page<T> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		Specification<T> specification = new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				List<Predicate> predicates = new ArrayList<Predicate>();

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
			field = "createTime";
			direction = Direction.DESC;
		} else if ("desc".equals(order)) {
			direction = Direction.DESC;
		} else {
			direction = Direction.ASC;
		}
		Pageable pageable = new PageRequest(page - 1, size, direction, field);

		return findAll(specification, pageable);
	}

	@Override
	public Page<T> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		Specification<T> specification = new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				List<Predicate> predicates = new ArrayList<Predicate>();

				predicates.add(builder.equal(root.get("company"), company));

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
			field = "createTime";
			direction = Direction.DESC;
		} else if ("desc".equals(order)) {
			direction = Direction.DESC;
		} else {
			direction = Direction.ASC;
		}
		Pageable pageable = new PageRequest(page - 1, size, direction, field);

		return findAll(specification, pageable);
	}

}
