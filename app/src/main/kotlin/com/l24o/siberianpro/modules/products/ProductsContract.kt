package com.l24o.siberianpro.modules.products

import com.l24o.siberianpro.common.mvp.IPresenter
import com.l24o.siberianpro.common.mvp.IView
import com.l24o.siberianpro.data.viewmodels.GroupViewModel
import com.l24o.siberianpro.data.viewmodels.ItemViewModel


interface IProductsView : IView {
    fun setLoadingVisible(isVisible: Boolean)
    fun showData(groups: List<GroupViewModel>)
    fun showItems(group: GroupViewModel, item: ItemViewModel)
    fun rotate(isClockWise: Boolean)
}

interface IProductsPresenter : IPresenter<IProductsView> {
    fun onGroupLongClick(group: GroupViewModel)
    fun onItemLongClick(parentPosition: Int, item: ItemViewModel)
}

