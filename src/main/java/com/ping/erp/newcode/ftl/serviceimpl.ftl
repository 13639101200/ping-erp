package ${package}.${parent_module}.${child_module}.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${package}.common.result.PageResult;
import ${package}.common.util.StringUtil;
import ${package}.system.company.domain.BaseCompany;
import ${package}.${parent_module}.${child_module}.dao.${tabInfo.upperTabName}Dao;
import ${package}.${parent_module}.${child_module}.domain.${tabInfo.upperTabName};
import ${package}.${parent_module}.${child_module}.service.${tabInfo.upperTabName}Service;

/**
 * ${tabInfo.tabComment}业务服务接口实现
 *
 * @version ${version}
 * @time ${time}
 *
 * @author ${author}
 * @phone ${phone}
 * @email ${email}
 *
 */
@Service
@Transactional
public class ${tabInfo.upperTabName}ServiceImpl implements ${tabInfo.upperTabName}Service {

	@Autowired
	private ${tabInfo.upperTabName}Dao dao;

	public PageResult<${tabInfo.upperTabName}> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findAll(fields, keyword, field, order, size, page);
	}

	public ${tabInfo.upperTabName} findOne(String id) {
		return dao.findOne(id);
	}

	public void save(${tabInfo.upperTabName} entity) {
		if (entity.get${tabInfo.upperTabKey}() == null || "".equals(entity.get${tabInfo.upperTabKey}())) {
			entity.set${tabInfo.upperTabKey}(StringUtil.getUUID());
		}
		dao.save(entity);
	}

	public void delete(List<${tabInfo.upperTabName}> entities) {
		dao.delete(entities);
	}

	public PageResult<${tabInfo.upperTabName}> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findByCompany(company, fields, keyword, field, order, size, page);
	}

}