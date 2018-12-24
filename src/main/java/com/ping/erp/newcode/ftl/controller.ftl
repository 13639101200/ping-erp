package ${package}.web.${parent_module};

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ${package}.common.result.PageResult;
import ${package}.common.result.SimpleResult;
import ${package}.common.result.impl.SimpleResultImpl;
import ${package}.${parent_module}.${child_module}.domain.${tabInfo.upperTabName};
import ${package}.${parent_module}.${child_module}.service.${tabInfo.upperTabName}Service;

/**
 * ${tabInfo.tabComment}控制器
 *
 * @version ${version}
 * @time ${time}
 *
 * @author ${author}
 * @phone ${phone}
 * @email ${email}
 *
 */
@Controller
@RequestMapping("${parent_module}/${tabInfo.lowerTabName}")
public class ${tabInfo.upperTabName}Controller {

	@Autowired
	private ${tabInfo.upperTabName}Service service;

	@RequestMapping("index")
	public String index() {
		try {
			return "${parent_module}/${tabInfo.lowerTabName}/index";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("page")
	@ResponseBody
	public PageResult<${tabInfo.upperTabName}> page(String keyword, String field, String order, int size, int page) {
		try {
			List<String> fields = new ArrayList<String>();
			return service.findAll(fields, keyword, field, order, size, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping("edit")
	public String edit(Model model, String id) {
		try {
			if (id != null && !"".equals(id)) {
				${tabInfo.upperTabName} ${tabInfo.lowerTabName} = service.findOne(id);
				model.addAttribute("${tabInfo.lowerTabName}", ${tabInfo.lowerTabName});
			}
			return "${parent_module}/${tabInfo.lowerTabName}/edit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("save")
	@ResponseBody
	public SimpleResult save(${tabInfo.upperTabName} entity) {
		try {
			service.save(entity);
			return new SimpleResultImpl(1, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

	@RequestMapping("delete")
	@ResponseBody
	public SimpleResult delete(@RequestBody List<${tabInfo.upperTabName}> entities) {
		try {
			service.delete(entities);
			return new SimpleResultImpl(1, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

}