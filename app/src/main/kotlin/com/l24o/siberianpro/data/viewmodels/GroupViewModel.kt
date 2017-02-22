package com.l24o.siberianpro.data.viewmodels

import com.bignerdranch.expandablerecyclerview.model.Parent
import com.l24o.siberianpro.data.rest.models.Group

/**
 * @author Alexander Popov on 22/02/2017.
 */

class GroupViewModel(val group: Group, val items: List<ItemViewModel>) : Parent<ItemViewModel> {

    override fun getChildList(): List<ItemViewModel> {
        return items
    }

    override fun isInitiallyExpanded(): Boolean {
        return false
    }

}

class ItemViewModel(val name: String)