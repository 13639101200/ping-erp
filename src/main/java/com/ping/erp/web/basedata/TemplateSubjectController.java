package com.ping.erp.web.basedata;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ping.erp.basedata.subject.domain.SubjectTemplate;
import com.ping.erp.basedata.subject.domain.TemplateSubject;
import com.ping.erp.basedata.subject.service.TemplateSubjectService;
import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.SimpleResult;
import com.ping.erp.common.result.impl.SimpleResultImpl;
import com.ping.erp.system.code.domain.BaseCode;
import com.ping.erp.system.code.service.BaseCodeService;

/**
 * 模板科目控制器
 *
 * @version 1.1.2-RELEASE
 * @time 2018-11-23 12:33:48
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Controller
@RequestMapping("basedata/templateSubject")
public class TemplateSubjectController {

	@Autowired
	private TemplateSubjectService service;
	@Autowired
	private BaseCodeService codeService;

	@RequestMapping("index")
	public String index(Model model, String templateId) {
		try {
			model.addAttribute("templateId", templateId);
			return "basedata/templateSubject/index";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("page")
	@ResponseBody
	public PageResult<TemplateSubject> page(SubjectTemplate template, String keyword, String field, String order, int size, int page) {
		try {
			List<String> fields = new ArrayList<String>();
			fields.add("subjectCode");
			fields.add("subjectName");
			fields.add("subjectType.codeValue");
			fields.add("subjectDirection.codeValue");
			return service.findByTemplate(template, fields, keyword, field, order, size, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping("edit")
	public String edit(Model model, String id) {
		try {
			if (id != null && !"".equals(id)) {
				TemplateSubject templateSubject = service.findOne(id);
				model.addAttribute("templateSubject", templateSubject);
			}
			List<BaseCode> typeList = codeService.findByTypeCode("finance_subject_subject_type");
			List<BaseCode> directionList = codeService.findByTypeCode("finance_subject_subject_direction");
			model.addAttribute("typeList", typeList);
			model.addAttribute("directionList", directionList);
			return "basedata/templateSubject/edit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("save")
	@ResponseBody
	public SimpleResult save(TemplateSubject entity) {
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
	public SimpleResult delete(@RequestBody List<TemplateSubject> entities) {
		try {
			service.delete(entities);
			return new SimpleResultImpl(1, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

}