package com.ping.erp.web.finance;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ping.erp.basedata.subject.domain.SubjectTemplate;
import com.ping.erp.basedata.subject.service.SubjectTemplateService;
import com.ping.erp.common.action.SessionAction;
import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.SimpleResult;
import com.ping.erp.common.result.impl.SimpleResultImpl;
import com.ping.erp.finance.assist.domain.AssistUnit;
import com.ping.erp.finance.assist.service.AssistUnitService;
import com.ping.erp.finance.period.domain.FinancePeriod;
import com.ping.erp.finance.period.service.FinancePeriodService;
import com.ping.erp.finance.subject.domain.ArchivedSubject;
import com.ping.erp.finance.subject.service.ArchivedSubjectService;
import com.ping.erp.system.code.domain.BaseCode;
import com.ping.erp.system.code.service.BaseCodeService;
import com.ping.erp.system.company.domain.BaseCompany;

/**
 * 结账科目控制器
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:05:35
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Controller
@RequestMapping("finance/archivedSubject")
public class ArchivedSubjectController {

	@Autowired
	private SessionAction sessionAction;
	@Autowired
	private ArchivedSubjectService service;
	@Autowired
	private BaseCodeService codeService;
	@Autowired
	private AssistUnitService assistUnitService;
	@Autowired
	private SubjectTemplateService templateService;
	@Autowired
	private FinancePeriodService financePeriodService;

	@RequestMapping("index")
	public String index(Model model) {
		try {
			BaseCompany company = sessionAction.getCompany();
			FinancePeriod period = financePeriodService.findByCompanyAndNoArchived(company);
			model.addAttribute("period", period);
			return "finance/archivedSubject/index";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("page")
	@ResponseBody
	public PageResult<ArchivedSubject> page(String keyword, String field, String order, int size, int page) {
		try {
			BaseCompany company = sessionAction.getCompany();
			List<String> fields = new ArrayList<String>();
			fields.add("subjectCode");
			fields.add("subjectName");
			fields.add("subjectType.codeValue");
			fields.add("subjectDirection.codeValue");
			if (field == null || "".equals(field)) {
				field = "subjectCode";
				order = "asc";
			}
			return service.findByCompanyAndCurrentPeriod(company, fields, keyword, field, order, size, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping("edit")
	public String edit(Model model, String id) {
		try {
			if (id != null && !"".equals(id)) {
				ArchivedSubject archivedSubject = service.findOne(id);
				model.addAttribute("archivedSubject", archivedSubject);
			}
			BaseCompany company = sessionAction.getCompany();
			List<BaseCode> typeList = codeService.findByTypeCode("finance_subject_subject_type");
			List<BaseCode> directionList = codeService.findByTypeCode("finance_subject_subject_direction");
			List<AssistUnit> unitList = assistUnitService.findByCompany(company);
			model.addAttribute("typeList", typeList);
			model.addAttribute("directionList", directionList);
			model.addAttribute("unitList", unitList);
			return "finance/archivedSubject/edit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("save")
	@ResponseBody
	public SimpleResult save(ArchivedSubject entity) {
		try {
			BaseCompany company = sessionAction.getCompany();
			service.saveToCurrentPeriod(company, entity);
			return new SimpleResultImpl(1, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

	@RequestMapping("delete")
	@ResponseBody
	public SimpleResult delete(@RequestBody List<ArchivedSubject> entities) {
		try {
			service.delete(entities);
			return new SimpleResultImpl(1, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

	@RequestMapping("initIndex")
	public String initIndex() {
		try {
			return "finance/archivedSubject/initIndex";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("initPage")
	@ResponseBody
	public PageResult<ArchivedSubject> initPage(String keyword, String field, String order, int size, int page) {
		try {
			BaseCompany company = sessionAction.getCompany();
			List<String> fields = new ArrayList<String>();
			fields.add("subjectCode");
			fields.add("subjectName");
			fields.add("subjectType.codeValue");
			fields.add("subjectDirection.codeValue");
			if (field == null || "".equals(field)) {
				field = "subjectCode";
				order = "asc";
			}
			return service.findByCompanyAndCurrentPeriod(company, fields, keyword, field, order, size, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping("initEdit")
	public String initEdit(Model model, String id) {
		try {
			if (id != null && !"".equals(id)) {
				ArchivedSubject archivedSubject = service.findOne(id);
				model.addAttribute("archivedSubject", archivedSubject);
			}
			BaseCompany company = sessionAction.getCompany();
			List<BaseCode> typeList = codeService.findByTypeCode("finance_subject_subject_type");
			List<BaseCode> directionList = codeService.findByTypeCode("finance_subject_subject_direction");
			List<AssistUnit> unitList = assistUnitService.findByCompany(company);
			model.addAttribute("typeList", typeList);
			model.addAttribute("directionList", directionList);
			model.addAttribute("unitList", unitList);
			return "finance/archivedSubject/initEdit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("initSave")
	@ResponseBody
	public SimpleResult initSave(ArchivedSubject entity) {
		try {
			BaseCompany company = sessionAction.getCompany();
			service.saveToCurrentPeriod(company, entity);
			return new SimpleResultImpl(1, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

	@RequestMapping("initDelete")
	@ResponseBody
	public SimpleResult initDelete(@RequestBody List<ArchivedSubject> entities) {
		try {
			service.delete(entities);
			return new SimpleResultImpl(1, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

	@RequestMapping("importEdit")
	public String importEdit(Model model) {
		try {
			List<SubjectTemplate> templateList = templateService.findAll();
			model.addAttribute("templateList", templateList);
			return "finance/archivedSubject/importEdit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("importSave")
	@ResponseBody
	public SimpleResult importSave(SubjectTemplate template) {
		try {
			BaseCompany company = sessionAction.getCompany();
			service.importSaveToCurrentPeriod(company, template);
			return new SimpleResultImpl(1, "导入成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

	@RequestMapping("startSubject")
	@ResponseBody
	public SimpleResult startSubject() {
		try {
			BaseCompany company = sessionAction.getCompany();
			return service.startSubject(company);
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

	@RequestMapping("isStart")
	@ResponseBody
	public SimpleResult isStart() {
		try {
			BaseCompany company = sessionAction.getCompany();
			return service.isStart(company);
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

	@RequestMapping("archivedSubject")
	@ResponseBody
	public SimpleResult archivedSubject() {
		try {
			BaseCompany company = sessionAction.getCompany();
			return service.archivedSubject(company);
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

}