package ${package}.${parent_module}.${child_module}.service;

import java.util.List;

import ${package}.common.result.PageResult;
import ${package}.system.company.domain.BaseCompany;
import ${package}.${parent_module}.${child_module}.domain.${tabInfo.upperTabName};

/**
 * ${tabInfo.tabComment}业务服务接口
 *
 * @version ${version}
 * @time ${time}
 *
 * @author ${author}
 * @phone ${phone}
 * @email ${email}
 *
 */
public interface ${tabInfo.upperTabName}Service {

	PageResult<${tabInfo.upperTabName}> findAll(List<String> fields, String keyword, String field, String order, int size, int page);

	${tabInfo.upperTabName} findOne(String id);

	void save(${tabInfo.upperTabName} entity);

	void delete(List<${tabInfo.upperTabName}> entities);

	PageResult<${tabInfo.upperTabName}> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page);

}