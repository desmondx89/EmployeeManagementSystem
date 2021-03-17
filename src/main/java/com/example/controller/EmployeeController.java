package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.model.Department;
import com.example.model.Employee;
import com.example.repository.DepartmentRepository;
import com.example.repository.EmployeeRepository;
import com.example.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private DepartmentRepository depRepo;

	@Autowired
	private EmployeeService service;

	@GetMapping("/employees")
	public String listEmployees(Model model) {
		String keyword = null;
//		List<Employee> listEmployees = empRepo.findAll();
//		model.addAttribute("listEmp", listEmployees);
		return listEmployeesByPage(model, 1, "empid", "asc", keyword);

	}

	@GetMapping("/employees/new")
	public String showNewEmployeeForm(Model model) {
		List<Department> listDepartments = depRepo.findAll();
		
		int empid = empRepo.maxEmp() + 1;
		
		model.addAttribute("employee", new Employee(empid));
		model.addAttribute("listDepartments", listDepartments);
		return "employee_form";
	}

	@PostMapping("/employees/add")
	public String saveEmployee(Employee employee, HttpServletRequest request) {
		empRepo.save(employee);
		return "redirect:/employees";
	}

	@GetMapping("/employees/edit/{empid}")
	public String editEmployee(@PathVariable("empid") int empid, Model model) {
		Employee employee = empRepo.findById(empid).get();
		model.addAttribute("employee", employee);
		List<Department> listDepartments = depRepo.findAll();
		model.addAttribute("listDepartments", listDepartments);

		return "employee_form";
	}

	@GetMapping("/employees/delete/{empid}")
	public String deleteEmployees(@PathVariable("empid") int id, Model model) {
		empRepo.deleteById(id);
		return "redirect:/employees";
	}

	@GetMapping("/epage/{pageNumber}")
	public String listEmployeesByPage(Model model, @PathVariable("pageNumber") int currentPage,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir, @Param("keyword") String keyword) {
		Page<Employee> page = service.listAll(currentPage, sortField, sortDir, keyword);
		Long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		List<Employee> listEmployees = page.getContent();

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("listEmployees", listEmployees);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("keyword", keyword);

		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		model.addAttribute("reverseSortDir", reverseSortDir);
		return "employees";
	}
	
//	@GetMapping("/employees/export/pdf")
//    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
//        response.setContentType("application/pdf");
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//         
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
//        response.setHeader(headerKey, headerValue);
//         
//        List<Employee> listUsers = empRepo.findAll();
//         
//       UserPDFExporter exporter = new UserPDFExporter(listUsers);
//       exporter.export(response);
//    }
//	
//	@GetMapping("/employees/export")
//    public void exportToCSV(HttpServletResponse response) throws IOException {
//        response.setContentType("text/csv");
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
//        String currentDateTime = dateFormatter.format(new Date());
//         
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
//        response.setHeader(headerKey, headerValue);
//         
//        List<Users> listUsers = repo.findAll();
// 
//        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
//        String[] csvHeader = {"User ID", "Username", "Password", "Roles", "Enabled"};
//        String[] nameMapping = {"id", "username", "password", "roles", "enabled"};
//         
//        csvWriter.writeHeader(csvHeader);
//         
//        for (Users user : listUsers) {
//            csvWriter.write(user, nameMapping);
//        }
//         
//        csvWriter.close();
//         
//    }

}
