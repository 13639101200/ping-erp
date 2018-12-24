package ${package}.${parent_module}.${child_module}.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import ${package}.common.result.PageResult;
import ${package}.common.result.impl.PageResultImpl;
import ${package}.system.company.domain.BaseCompany;
import ${package}.${parent_module}.${child_module}.dao.${tabInfo.upperTabName}Dao;
import ${package}.${parent_module}.${child_module}.domain.${tabInfo.upperTabName};
import ${package}.${parent_module}.${child_module}.repository.${tabInfo.upperTabName}Repository;

/**
 * ${tabInfo.tabComment}数据访问接口实现
 *
 * @version ${version}
 * @time ${time}
 *
 * @author ${author}
 * @phone ${phone}
 * @email ${email}
 *
 */
@Repository
public class ${tabInfo.upperTabName}DaoImpl implements ${tabInfo.upperTabName}Dao {

	@Autowired
	private ${tabInfo.upperTabName}Repository repository;

	public PageResult<${tabInfo.upperTabName}> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<${tabInfo.upperTabName}> springPage = repository.findAll(fields, keyword, field, order, size, page);
		return new PageResultImpl<${tabInfo.upperTabName}>(springPage.getTotalElements(), springPage.getContent());
	}

	public ${tabInfo.upperTabName} findOne(String id) {
		return repository.findOne(id);
	}

	public void save(${tabInfo.upperTabName} entity) {
		repository.save(entity);
	}

	public void delete(List<${tabInfo.upperTabName}> entities) {
		repository.delete(entities);
	}

	public PageResult<${tabInfo.upperTabName}> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<${tabInfo.upperTabName}> springPage = repository.findByCompany(company, fields, keyword, field, order, size, page);
		return new PageResultImpl<${tabInfo.upperTabName}>(springPage.getTotalElements(), springPage.getContent());
	}

}