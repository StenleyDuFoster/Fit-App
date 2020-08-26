package com.stenleone.fitapp.model.data.details

import android.provider.MediaStore

data class ItemDetailsFitPlan(

    val allowFreeAccess: Boolean,
    val athleteFirstName: String,
    val athleteId: Int,
    val athleteImageUrl: String,
    val athleteLastName: String,
    val athleteSlug: String,
    val basicWorkouts: List<DetailsWorkOut>,
    val branchInviteLink: String,
    val completed: Boolean,
    val daysCount: Int,
    val daysPerWeek: Int,
    val description: String,
    val displayDaysCountInWeeks: Boolean,
    val free: Boolean,
    val id: Int,
    val imageLargeUrl: String,
    val imageSmallUrl: String,
    val imageTv: String,
    val imageUrl: String,
    val level: Int,
    val location: Int,
    val metadata: String,
    val mobileOnly: Boolean,
    val modifiedTimestamp: Long,
    val name: String,
    val numberOfWeeks: Int,
    val presentationType: Int,
    val restDayImage1: String,
    val restDayImage2: String,
    val sex: String,
    val singleLength: Int,
    val slug: String,
    val type: Int,
    val video: MediaStore.Video
)