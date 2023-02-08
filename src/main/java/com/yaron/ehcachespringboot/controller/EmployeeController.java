package com.yaron.ehcachespringboot.controller;

import com.yaron.ehcachespringboot.domain.TbInfEmployee;
import com.yaron.ehcachespringboot.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author Yaron
 * @version 1.0
 * @date 2023/02/08
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @ResponseBody
    @RequestMapping(value = "findAll")
    public Iterable<TbInfEmployee> findAll(HttpServletRequest request) {
        log.info("findAll请求时间：{}", LocalDateTime.now());
        Iterable<TbInfEmployee> employees = employeeService.findAll();
        log.info("findAll返回结果：{}", employees);
        log.info("findAll返回时间：{}", LocalDateTime.now());
        return employees;
    }

    @ResponseBody
    @RequestMapping(value = "findById")
    public TbInfEmployee findById(HttpServletRequest request) {
        log.info("findById请求时间：{}", LocalDateTime.now());
        Integer id = Integer.parseInt(request.getParameter("id"));
        TbInfEmployee employee = employeeService.findById(id);
        log.info("findById返回结果：{}", employee);
        log.info("findById返回时间：{}", LocalDateTime.now());
        return employee;
    }

    @ResponseBody
    @RequestMapping(value = "updateSalaryById")
    public TbInfEmployee updateSalaryById(HttpServletRequest request) {
        log.info("updateSalaryById请求时间：{}", LocalDateTime.now());
        Integer id = Integer.parseInt(request.getParameter("id"));
        String salaryStr = request.getParameter("salary");
        double salary = Double.parseDouble(salaryStr);
        TbInfEmployee employee = employeeService.updateSalaryById(id, salary);
        log.info("updateSalaryById返回结果：{}", employee);
        log.info("updateSalaryById返回时间：{}", LocalDateTime.now());
        return employee;
    }

    @ResponseBody
    @RequestMapping(value = "deleteById")
    public boolean deleteById(HttpServletRequest request) {
        log.info("deleteById请求时间：{}", LocalDateTime.now());
        Integer id = Integer.parseInt(request.getParameter("id"));
        boolean b = employeeService.deleteById(id);
        log.info("deleteById返回时间：{}", LocalDateTime.now());
        return b;
    }

}
