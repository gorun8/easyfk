/*
 * Project:Easy Web Framework
 *
 * Description: This project is based on much more open source projects than ever before,
 *              and can be applied to mostly web development environment.
 * Author:hezhiping   Email:110476592@qq.com
 * 
 * 
 *==========================================================================================
 * 
 */
package cn.gorun8.easyfk.workflow.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javolution.util.FastList;
import javolution.util.FastMap;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.FlowElementsContainer;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.GraphicInfo;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.image.ProcessDiagramGenerator;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cn.gorun8.easyfk.base.util.FileUtil;
import cn.gorun8.easyfk.base.util.UtilIO;
import cn.gorun8.easyfk.base.util.UtilUUID;
import cn.gorun8.easyfk.workflow.service.DemoService;

/**
 * demo默认首页控制器
 * 
 */
@Controller
@RequestMapping(value = "/model")
public class DemoController {

	private static final Logger logger = LoggerFactory
			.getLogger(DemoController.class);

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private DemoService demoService;

	@Autowired
	private HistoryService historyService;

	@Autowired
	private ProcessDiagramGenerator processDiagramGenerator;

	@RequestMapping("")
	public String index() {
		return "page/index";
	}

	@RequestMapping(value = "deploy", method = RequestMethod.POST)
	public ModelAndView deploy(
			@RequestParam(value = "file", required = true) MultipartFile file) {
		ModelAndView mv = new ModelAndView();
		try {
			String fileName = file.getOriginalFilename();
			InputStream fileInputStream = file.getInputStream();
			Deployment deployment = null;
			String extension = FilenameUtils.getExtension(fileName);
			if (extension.equals("zip") || extension.equals("bar")) {
				ZipInputStream zip = new ZipInputStream(fileInputStream);
				deployment = repositoryService.createDeployment()
						.addZipInputStream(zip).deploy();
			} else {
				deployment = repositoryService.createDeployment()
						.addInputStream(fileName, fileInputStream).deploy();
			}
			mv.addObject("result", "suc");

			// List<ProcessDefinition> list =
			// repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).list();
			// for(ProcessDefinition it : list){
			// WorkflowUtils.exportDiagramToFile(repositoryService, it,
			// exportDir);
			// }

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}

	@RequestMapping(value = "start")
	@ResponseBody
	public String start(@RequestParam(value = "id") String id) {
		Map<String, Object> variables = FastMap.newInstance();
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceById(id, variables);
		String pid = processInstance.getId();

		HashMap<String, String> leave = new HashMap<String, String>();
		leave.put("aptime", "2015-07-31 09:00:00");
		leave.put("endtime", "2015-08-9 09:00:00");
		leave.put("type", "1");
		leave.put("pid", pid);
		leave.put("reason", "sss");
		leave.put("uid", "hzp");
		demoService.addLeave(leave);
		return pid;
	}

	/**
	 * 列举所有的Model
	 * 
	 * @return
	 */
	@RequestMapping(value = "list")
	public ModelAndView list(@RequestParam(value = "index") int index,
			@RequestParam(value = "size") int size) {
		ModelAndView model = new ModelAndView("page/modellist");
		List<Model> modelList = this.repositoryService.createModelQuery()
				.orderByModelId().desc().list();
		model.addObject("modelList", modelList);
		return model;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public void create(@RequestParam("name") String name,
			@RequestParam("key") String key,
			@RequestParam("description") String description,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			ObjectNode editorNode = objectMapper.createObjectNode();
			editorNode.put("id", "canvas");
			editorNode.put("resourceId", "canvas");
			ObjectNode stencilSetNode = objectMapper.createObjectNode();
			stencilSetNode.put("namespace",
					"http://b3mn.org/stencilset/bpmn2.0#");
			editorNode.put("stencilset", stencilSetNode);
			Model modelData = repositoryService.newModel();

			ObjectNode modelObjectNode = objectMapper.createObjectNode();
			modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
			description = StringUtils.defaultString(description);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION,
					description);
			modelData.setMetaInfo(modelObjectNode.toString());
			modelData.setName(name);
			modelData.setKey(StringUtils.defaultString(key));

			repositoryService.saveModel(modelData);
			repositoryService.addModelEditorSource(modelData.getId(),
					editorNode.toString().getBytes("utf-8"));

			response.sendRedirect(request.getContextPath()
					+ "/service/editor?id=" + modelData.getId());
		} catch (Exception e) {
			logger.error("创建模型失败：", e);
		}
	}

	@RequestMapping(value = "edit/{id}")
	public String edit(@PathVariable(value = "id") String id,
			HttpServletRequest request, HttpServletResponse response) {
		Model model = repositoryService.getModel(id);
		return "redirect:/service/editor?id=" + model.getId();
	}

	@RequestMapping(value = "trace")
	public ModelAndView trace(@RequestParam(value = "pid") String pid,
			@RequestParam(value="xf")int xf,
			@RequestParam(value="yf")int yf,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ProcessInstance processInstance = this.runtimeService
				.createProcessInstanceQuery().processInstanceId(pid)
				.singleResult();
		BpmnModel bpmnModel = this.repositoryService
				.getBpmnModel(processInstance.getProcessDefinitionId());
		List<HistoricActivityInstance> hisTaskList = historyService
				.createHistoricActivityInstanceQuery().processInstanceId(pid)
				.list();
		List<String> activityLists = this.runtimeService
				.getActiveActivityIds(pid);

		for (HistoricActivityInstance ht : hisTaskList) {
			String id = ht.getActivityId();
			activityLists.add(id);
		}

		List<String> seqFlowLists = new ArrayList<String>();

		for (HistoricActivityInstance ht : hisTaskList) {
			String id = ht.getActivityId();

			FlowNode flowNode = (FlowNode) bpmnModel.getFlowElement(id);
			List<SequenceFlow> seqList = flowNode.getIncomingFlows();
			for (SequenceFlow sq : seqList) {
				String srcource = sq.getSourceRef();
				String target = sq.getTargetRef();
				if (activityLists.contains(srcource)
						&& activityLists.contains(target)) {
					seqFlowLists.add(sq.getId());
				}
			}// end for
		}

		InputStream imgStream = processDiagramGenerator.generateDiagram(
				bpmnModel, "png", activityLists, seqFlowLists);
		File file = FileUtil.getFile("component://images/webapp/images/" + pid
				+ ".png");
		FileOutputStream out = new FileOutputStream(file);

		// OutputStream out = response.getOutputStream();
		UtilIO.copy(imgStream, true, out, true);
		ModelAndView mv = new ModelAndView("page/traceflow");
		caluteMaps(mv,bpmnModel,xf,yf);
		mv.addObject("imgurl", pid + ".png");
		return mv;
	}

	private static void caluteMaps(ModelAndView mv, BpmnModel bpmnModel,int xoff,int yoff) {
		List<FlowNode> flowNodes = gatherAllFlowNodes(bpmnModel);
		List<String> maps = FastList.newInstance();//.newList(); 
		for (FlowNode flowNode : flowNodes) {
			System.out.println(flowNode);
			GraphicInfo flowNodeGraphicInfo = bpmnModel.getGraphicInfo(flowNode
					.getId());
			double x = flowNodeGraphicInfo.getX();
			double y = flowNodeGraphicInfo.getY();
			
			double w = flowNodeGraphicInfo.getWidth();
			double h = flowNodeGraphicInfo.getHeight();
			
			String area = "";
			area += String.valueOf(x-xoff);
			area +=",";
			area += String.valueOf(y-yoff);
			area +=",";
			area += String.valueOf(x+w - xoff);
			area +=",";
			area += String.valueOf(y+h - yoff);
			maps.add(area);
			
		}
		mv.addObject("maps", maps);
	}

	protected static List<FlowNode> gatherAllFlowNodes(BpmnModel bpmnModel) {
		List<FlowNode> flowNodes = new ArrayList();
		for (Process process : bpmnModel.getProcesses()) {
			flowNodes.addAll(gatherAllFlowNodes(process));
		}
		return flowNodes;
	}

	protected static List<FlowNode> gatherAllFlowNodes(
			FlowElementsContainer flowElementsContainer) {
		List<FlowNode> flowNodes = new ArrayList();
		for (FlowElement flowElement : flowElementsContainer.getFlowElements()) {
			if ((flowElement instanceof FlowNode)) {
				flowNodes.add((FlowNode) flowElement);
			}
			if ((flowElement instanceof FlowElementsContainer)) {
				flowNodes
						.addAll(gatherAllFlowNodes((FlowElementsContainer) flowElement));
			}
		}
		return flowNodes;
	}

}
