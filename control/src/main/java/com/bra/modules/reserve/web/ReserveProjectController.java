package com.bra.modules.reserve.web;

import com.bra.common.config.Global;
import com.bra.common.persistence.Page;
import com.bra.common.utils.StringUtils;
import com.bra.common.web.BaseController;
import com.bra.modules.reserve.entity.ReserveProject;
import com.bra.modules.reserve.service.ReserveProjectService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 项目管理Controller
 * @author jiang
 * @version 2016-07-12
 */
@Controller
@RequestMapping(value = "${adminPath}/reserve/reserveProject")
public class ReserveProjectController extends BaseController {

	@Autowired
	private ReserveProjectService reserveProjectService;

	@ModelAttribute
	public ReserveProject get(@RequestParam(required=false) String id) {
		ReserveProject entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = reserveProjectService.get(id);
		}
		if (entity == null){
			entity = new ReserveProject();
		}
		return entity;
	}

	@RequiresPermissions("reserve:reserveProject:view")
	@RequestMapping(value = {"list", ""})
	public String list(ReserveProject reserveProject, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ReserveProject> page = reserveProjectService.findPage(new Page<ReserveProject>(request, response), reserveProject);
		model.addAttribute("page", page);
		return "reserve/project/reserveProjectList";
	}

	@RequiresPermissions("reserve:reserveProject:view")
	@RequestMapping(value = "form")
	public String form(ReserveProject reserveProject, Model model) {
		model.addAttribute("reserveProject", reserveProject);
		return "reserve/project/reserveProjectForm";
	}

	@RequiresPermissions("reserve:reserveProject:edit")
	@RequestMapping(value = "save")
	public String save(ReserveProject reserveProject, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, reserveProject)){
			return form(reserveProject, model);
		}
		reserveProjectService.save(reserveProject);
		addMessage(redirectAttributes, "保存项目管理成功");
		return "redirect:"+Global.getAdminPath()+"/reserve/reserveProject/?repage";
	}

	@RequiresPermissions("reserve:reserveProject:edit")
	@RequestMapping(value = "delete")
	public String delete(ReserveProject reserveProject, RedirectAttributes redirectAttributes) {
		reserveProjectService.delete(reserveProject);
		addMessage(redirectAttributes, "删除项目管理成功");
		return "redirect:"+Global.getAdminPath()+"/reserve/reserveProject/?repage";
	}

}