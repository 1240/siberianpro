package com.l24o.siberianpro.data.rest.models

import com.google.gson.annotations.SerializedName

data class Products(
        val groups: List<Group>,
        val items: List<Item>
)

data class Group(
        val id: Int,
        val name: String
)

data class Item(
        val id: Int,
        @SerializedName("group_id")
        val groupId: Int,
        val name: String
)