package com.cbellmont.neoland2021.model.entity

import androidx.room.Embedded

data class CampusWithStudent(
    @Embedded var student: Student,
    @Embedded var campus : Campus
)