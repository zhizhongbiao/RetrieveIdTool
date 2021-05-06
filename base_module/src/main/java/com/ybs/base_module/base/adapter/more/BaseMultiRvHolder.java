package com.ybs.base_module.base.adapter.more;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

/**
 * @ProjectName: robotwater
 * @Package: com.foodom.robotdelivery.ui.newfunction.communication.adapter
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/25 17:19
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/25 17:19
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */

public class BaseMultiRvHolder extends RecyclerView.ViewHolder {

    public ViewBinding bd;

    public BaseMultiRvHolder(@NonNull ViewBinding binding) {
        super(binding.getRoot());
        bd=binding;
    }
}
