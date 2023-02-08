package com.yaron.ehcachespringboot.respository;

import com.yaron.ehcachespringboot.domain.TbInfEmployee;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Yaron
 * @version 1.0
 * @date 2023/02/08
 * @description
 */
public interface EmployeeRespository extends PagingAndSortingRepository<TbInfEmployee,Integer> {
}
