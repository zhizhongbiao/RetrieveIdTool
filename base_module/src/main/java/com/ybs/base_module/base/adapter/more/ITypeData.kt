package com.ybs.base_module.base.adapter.more

/**
 *
 * @ProjectName: robotwater
 * @Package: com.foodom.robotdelivery.ui.newfunction.communication.adapter
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/25 17:42
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/25 17:42
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */



interface ITypeData {


   fun getType()= DATA_TYPE_L

   companion object {
      const val DATA_TYPE_R=888
      const val DATA_TYPE_L=999
   }
}