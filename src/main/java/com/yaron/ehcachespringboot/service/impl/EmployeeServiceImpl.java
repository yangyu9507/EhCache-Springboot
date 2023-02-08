package com.yaron.ehcachespringboot.service.impl;

import com.yaron.ehcachespringboot.domain.TbInfEmployee;
import com.yaron.ehcachespringboot.respository.EmployeeRespository;
import com.yaron.ehcachespringboot.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @CacheConfig 注解是声明本类所有方法的缓存名称，如果方法上设置了自己的名称则以方法上名称为准，
 *   比如类上设置了名称employee_info,方法上设置了employee_all，
 *   则最终的名称为employee_all，具体看本类的实例
 *
 * @author Yaron
 * @version 1.0
 * @date 2023/02/08
 * @description
 *
 */
@CacheConfig(cacheNames = {"employee_info"})
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRespository employeeRespository;

    /**
     * 查询所有员工信息
     * <p>
     * 这里指定了缓存的名称：employee_all，则最终的缓存名称为employee_all
     * 我这里顺便也实现了自定义的key生成方法keyGenerator
     * <p>
     * cacheNames/value:二选一使用
     * key/keyGenerator:二选一使用
     */
    @Override
    @Cacheable(cacheNames= "employee_all", keyGenerator = "keyGenerator")
    public Iterable<TbInfEmployee> findAll() {
        log.info("findAll查询数据库");
        return employeeRespository.findAll();
    }

    /**
     * 根据id查询员工信息（id长度为8才缓存）
     * <p>
     * key/keyGenerator:二选一使用
     * condition表示的是条件（为true才缓存）
     */
    @Override
//    @Cacheable(key = "#id", condition = "#id.length()==8")
    @Cacheable(key = "#id")
    public TbInfEmployee findById(Integer id) {
        log.info("findById查询数据库");
        Optional<TbInfEmployee> optional = employeeRespository.findById(id);

        TbInfEmployee tbInfEmployee = optional.orElse(null);
        return tbInfEmployee;
    }

    /**
     * 更加id更新员工工资（员工工资大于0才缓存）
     *
     * @CachePut 缓存的是返回值，所以更新方法的返回值一定要注意
     * <p>
     * key/keyGenerator:二选一使用
     * condition表示的是条件（为true才缓存）
     */
    @Override
    @CachePut(key = "#id", condition = "#salary>0")
    public TbInfEmployee updateSalaryById(Integer id, double salary) {
        log.info("updateSalaryById查询数据库");
        Optional<TbInfEmployee> optional = employeeRespository.findById(id);
        if (!optional.isPresent()) {
            return null;
        }
        TbInfEmployee employee = optional.get();
        employee.setSalary(salary);
        employeeRespository.save(employee);
        return employee;
    }

    /**
     * 更加id删除员工
     *
     * @CacheEvict Spring会在调用该方法之前清除缓存中的指定元素
     * allEntries : 为true表示清除value空间名里的所有的数据，默认为false
     * beforeInvocation 缓存的清除是在方法前执行还是方法后执行，默认是为false,方法执行后删除
     * beforeInvocation = false : 方法执行后删除，如果出现异常缓存就不会清除
     * beforeInvocation = true : 方法执行前删除，无论方法是否出现异常，缓存都清除
     */
    @Override
    @CacheEvict(key = "#id", beforeInvocation = true)
    public boolean deleteById(Integer id) {
        log.info("deleteById查询数据库");
        try {
            employeeRespository.deleteById(id);
            log.info("删除成功");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
