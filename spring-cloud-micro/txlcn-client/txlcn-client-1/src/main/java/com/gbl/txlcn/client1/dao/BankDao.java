package com.gbl.txlcn.client1.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by guobaolin on 2019/7/10.
 */
@Repository
public interface BankDao {

    /**
     * 转账
     *
     * @param fromId
     * @param money
     * @return
     */
    @Update("update t_bank set money = money - #{money} where id = #{fromId}")
    int update(@Param("fromId") int fromId, @Param("money") int money);
}
