package ${package}.${parent_module}.${child_module}.repository;

import ${package}.common.config.repository.CoreRepository;
import ${package}.${parent_module}.${child_module}.domain.${tabInfo.upperTabName};

/**
 * ${tabInfo.tabComment}数据存储接口
 *
 * @version ${version}
 * @time ${time}
 *
 * @author ${author}
 * @phone ${phone}
 * @email ${email}
 *
 */
public interface ${tabInfo.upperTabName}Repository extends CoreRepository<${tabInfo.upperTabName}, String> {
}