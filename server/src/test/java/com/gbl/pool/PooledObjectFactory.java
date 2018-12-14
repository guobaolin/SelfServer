package com.gbl.pool;

/**
 * Created by guobaolin on 2018/10/25.
 *
 * <p>用来创建池对象{@link #makeObject makeObject},
 * <p>将不用的池对象进行钝化{@link #passivateObject},
 * <p>对要使用的池对象进行激活{@link #activateObject},
 * <p>对池对象进行验证{@link #validateObject},
 * <p>对有问题的池对象进行销毁{@link #destroyObject}等工作
 */
public interface PooledObjectFactory<T> {

    /**
     * 在必要时产生新的对象
     *
     * @return
     * @throws Exception
     */
    PooledObject<T> makeObject() throws Exception;

    /**
     * 销毁被validateObject判定为已失效的对象
     *
     * @param var1
     * @throws Exception
     */
    void destroyObject(PooledObject<T> var1) throws Exception;

    /**
     * 校验一个具体的对象是否仍然有效，
     * 已失效的对象会被自动交给destroyObject方法销毁
     *
     * @param var1
     * @return
     */
    boolean validateObject(PooledObject<T> var1);

    /**
     * 将对象“激活”——设置为适合开始使用的状态
     *
     * @param var1
     * @throws Exception
     */
    void activateObject(PooledObject<T> var1) throws Exception;

    /**
     * 将对象“挂起”——设置为适合开始休眠的状态
     *
     * @param var1
     * @throws Exception
     */
    void passivateObject(PooledObject<T> var1) throws Exception;
}
