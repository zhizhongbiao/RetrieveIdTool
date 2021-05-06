package com.ybs.distributenettool.base.view.loading

/**
 *
 * @ProjectName: DistributeNetTool
 * @Package: com.ybs.distributenettool.base.view.loading
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/16 20:15
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/16 20:15
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
data class DlgStateBean(
    val state: Int = DLG_LOAD_LOADING,
    val msg: String? = null,
    var onDismissedCb: (() -> Unit)? = null
) {
    override fun toString(): String {
        return "DlgStateBean(state=$state, msg=$msg)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DlgStateBean

        if (state != other.state) return false
        if (msg != other.msg) return false

        return true
    }

    override fun hashCode(): Int {
        var result = state
        result = 31 * result + (msg?.hashCode() ?: 0)
        return result
    }


}
