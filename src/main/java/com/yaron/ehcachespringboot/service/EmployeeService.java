package com.yaron.ehcachespringboot.service;

import com.yaron.ehcachespringboot.domain.TbInfEmployee;

/**
 * @author Yaron
 * @version 1.0
 * @date 2023/02/08
 * @description
 */

public interface EmployeeService {

    Iterable<TbInfEmployee> findAll();

    TbInfEmployee findById(Integer id);


    TbInfEmployee updateSalaryById(Integer id, double salary);

    boolean deleteById(Integer id);

}
