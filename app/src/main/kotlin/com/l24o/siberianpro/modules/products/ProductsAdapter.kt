package com.l24o.siberianpro.modules.products

import android.view.*
import com.bignerdranch.expandablerecyclerview.ChildViewHolder
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter
import com.bignerdranch.expandablerecyclerview.ParentViewHolder
import com.l24o.siberianpro.R
import com.l24o.siberianpro.data.viewmodels.GroupViewModel
import com.l24o.siberianpro.data.viewmodels.ItemViewModel
import kotlinx.android.synthetic.main.item_child.view.*
import kotlinx.android.synthetic.main.item_group.view.*
import org.jetbrains.anko.onLongClick

/**
 * @author Alexander Popov on 22/02/2017.
 */

class ProductsAdapter(val data: List<GroupViewModel>,
                      val onGroupLongClick: (GroupViewModel) -> Unit,
                      val onItemLongClick: (Int, ItemViewModel) -> Unit) : ExpandableRecyclerAdapter<GroupViewModel, ItemViewModel, GroupViewHolder, ItemViewHolder>(data) {
    override fun onCreateParentViewHolder(parentViewGroup: ViewGroup, viewType: Int): GroupViewHolder {
        val view = LayoutInflater.from(parentViewGroup.context).inflate(R.layout.item_group, parentViewGroup, false)
        return GroupViewHolder(view)
    }

    override fun onBindParentViewHolder(parentViewHolder: GroupViewHolder, parentPosition: Int, parent: GroupViewModel) {
        parentViewHolder.bind(data[parentPosition], onGroupLongClick)
    }

    override fun onCreateChildViewHolder(childViewGroup: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(childViewGroup.context).inflate(R.layout.item_child, childViewGroup, false)
        return ItemViewHolder(view)
    }

    override fun onBindChildViewHolder(childViewHolder: ItemViewHolder, parentPosition: Int, childPosition: Int, child: ItemViewModel) {
        childViewHolder.bind(child, parentPosition, onItemLongClick)
    }
}

class GroupViewHolder(val view: View) : ParentViewHolder<GroupViewModel, ItemViewModel>(view) {
    fun bind(group: GroupViewModel, onGroupLongClick: (GroupViewModel) -> Unit) {
        with(itemView) {
            groupName.text = group.group.name
            onLongClick {
                onGroupLongClick(group)
                true
            }
        }
    }
}

class ItemViewHolder(val view: View) : ChildViewHolder<ItemViewModel>(view) {
    fun bind(item: ItemViewModel, parentPosition: Int, onItemClick: (Int, ItemViewModel) -> Unit) {
        with(itemView) {
            childName.text = item.name
            onLongClick {
                onItemClick(parentPosition, item)
                true
            }
        }

    }
}