package com.l24o.siberianpro.modules.products

import com.l24o.siberianpro.common.SiberianProAssetsManager
import com.l24o.siberianpro.common.mvp.RxPresenter
import com.l24o.siberianpro.data.rest.models.Products
import com.l24o.siberianpro.data.viewmodels.GroupViewModel
import com.l24o.siberianpro.data.viewmodels.ItemViewModel
import com.l24o.siberianpro.extensions.parsedMessage
import rx.lang.kotlin.plusAssign
import java.util.*
import javax.inject.Inject


class ProductsPresenter @Inject constructor(view: IProductsView) : RxPresenter<IProductsView>(view), IProductsPresenter {


    @Inject lateinit var assetsManager: SiberianProAssetsManager

    private var groups: ArrayList<GroupViewModel> = ArrayList()

    override fun onGroupLongClick(group: GroupViewModel) {
        view?.rotate(groups.indexOf(group) % 2 == 0)
    }

    override fun onItemLongClick(parentPosition: Int, item: ItemViewModel) {
        view?.showItems(groups[parentPosition], item)
    }

    override fun onViewAttached() {
        super.onViewAttached()
        subscriptions += assetsManager.getProducts()
                .subscribe({
                    products ->
                    convertToViewModels(products)
                }, {
                    error ->
                    error.printStackTrace()
                    view?.showMessage(error.parsedMessage())
                })
    }

    private fun convertToViewModels(products: Products) {
        products.groups.forEach {
            val group = it
            val items = products.items.filter { it.groupId == group.id }
            if (items.isNotEmpty()) {
                groups.add(
                        GroupViewModel(group, items.map {
                            ItemViewModel(it.name)
                        }))
            }
        }
        view?.showData(groups)
    }


}