package cn.gorun8.easyfk.@component-name@.dao;

import cn.gorun8.easyfk.annotation.EntityGroup;
import cn.gorun8.easyfk.entity.datasource.EntityGroupType;


import cn.gorun8.easyfk.annotation.EntityGroup;
import cn.gorun8.easyfk.entity.datasource.EntityGroupType;
import cn.gorun8.easyfk.entity.GenericValue;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by hezhiping(110476592@qq.com) on 15-10-19.
 */
@Repository("@component-name@DemoDao")
public interface DemoDao {

	/**
	*
	*
	*/
    @EntityGroup(EntityGroupType.MASTER)
    public void saveMaster();

    @EntityGroup(EntityGroupType.SLAVE)
    public void saveSlave();

    @EntityGroup(EntityGroupType.MASTER)
    List<GenericValue> listByPage();
}
