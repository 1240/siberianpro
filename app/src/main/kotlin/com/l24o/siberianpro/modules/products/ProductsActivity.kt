package com.l24o.siberianpro.modules.products

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import com.l24o.siberianpro.R
import com.l24o.siberianpro.common.mvp.MvpActivity
import com.l24o.siberianpro.data.viewmodels.GroupViewModel
import com.l24o.siberianpro.data.viewmodels.ItemViewModel
import com.l24o.siberianpro.di.AppComponent
import com.l24o.siberianpro.extensions.materialDialog
import kotlinx.android.synthetic.main.activity_signin.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import javax.inject.Inject


class ProductsActivity : MvpActivity(), IProductsView {

    @Inject lateinit var presenter: IProductsPresenter

    private var productAdapter: ProductsAdapter? = null
    private var stop = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        presenter.onViewAttached()
    }

    override fun resolveDependencies(appComponent: AppComponent) {
        DaggerProductsComponent.builder()
                .appComponent(appComponent)
                .productsModule(ProductsModule(this))
                .build()
                .inject(this)
    }

    override fun beforeDestroy() {
        presenter.dropView()
    }

    override fun setLoadingVisible(isVisible: Boolean) {
        progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun showItems(group: GroupViewModel, item: ItemViewModel) {
        materialDialog {
            title(group.group.name)
            content(item.name)
            positiveText(R.string.ok)
            onPositive { materialDialog, dialogAction -> }
        }.show()
    }

    override fun rotate(isClockWise: Boolean) {
        // для бесконечной прокрутки и остановке по проставке stop = true
//        recyclerView.clearAnimation()
//        stop = false
//        val runnable = object : Runnable {
//            override fun run() {
//                if (!stop) {
//                    recyclerView
//                            .animate()
//                            .rotationBy(if (isClockWise) 360f else -360f)
//                            .withEndAction(this)
//                            .setDuration(1000).setInterpolator(LinearInterpolator()).start()
//                }
//            }
//        }
//        recyclerView.animate()
//                .rotationBy(if (isClockWise) 360f else -360f)
//                .withEndAction(runnable)
//                .setDuration(1000)
//                .setInterpolator(LinearInterpolator()).start()

        val rotateAnimation = RotateAnimation(if (isClockWise) 0f else 360f, if (isClockWise) 360f else 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotateAnimation.duration = 1000
        rotateAnimation.interpolator = LinearInterpolator()
        recyclerView.startAnimation(rotateAnimation)
    }

    override fun showData(groups: List<GroupViewModel>) {
        productAdapter = ProductsAdapter(groups, {
            group ->
            presenter.onGroupLongClick(group)
        }, {
            parentPosition, item ->
            presenter.onItemLongClick(parentPosition, item)
        })
        recyclerView.adapter = productAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        productAdapter?.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        productAdapter?.onRestoreInstanceState(savedInstanceState)
    }

}

