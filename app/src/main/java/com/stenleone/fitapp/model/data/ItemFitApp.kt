package com.stenleone.fitapp.model.data

data class ItemFitApp(

    val allowFreeAccess: Boolean,
    val athleteFirstName: String,
    val athleteId: Int,
    val athleteLastName: String,
    val athleteSlug: String,
    val available: Boolean,
    val daysCount: Int,
    val daysPerWeek: Int,
    val displayPriority: Int,
    val free: Boolean,
    val id: Int,
    val imageSmallUrl: String,
    val imageTvUrl: String,
    val imageUrl: String,
    val location: Int,
    val metadata: String,
    val modifiedTimestamp: Long,
    val name: String,
    val presentationType: Int,
    val sex: String,
    val singleLength: Int,
    val slug: String,
    val type: Int
)