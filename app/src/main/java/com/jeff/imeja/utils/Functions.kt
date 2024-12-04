package com.jeff.imeja.utils

import com.jeff.imeja.models.MenuItem
import com.jeff.imeja.R
import java.time.LocalTime

class Functions {

    fun userName(): String {
        val names = listOf(
            "Alice", "Bob", "Charlie", "Diana", "Eve",
            "Frank", "Grace", "Hank", "Ivy", "Jack",
            "Karen", "Leo", "Mona", "Nina", "Oscar",
            "Paul", "Quinn", "Rose", "Steve", "Tina"
        )

        // Pick a random name
        val randomName = names.random()

        return randomName
    }

    fun getAncMenuItems(): List<MenuItem> {
        return listOf(
            MenuItem("Patient Registration", R.drawable.ic_patient_registration),
            MenuItem("ANC Checkups", R.drawable.ic_checkup),
            MenuItem("Health Tips", R.drawable.ic_health_tips)
        )
    }

    fun getPncMenuItems(): List<MenuItem> {
        return listOf(
            MenuItem("Newborn Care", R.drawable.ic_newborn_care),
            MenuItem("Postpartum Checkups", R.drawable.ic_postpartum),
            MenuItem("PNC Health Tips", R.drawable.ic_health_tips)
        )
    }

    fun greetings(): String {
        val currentHour = LocalTime.now().hour

        // Determine the greeting based on the hour
        val greeting = when (currentHour) {
            in 0..4 -> "Good Night, \n"
            in 5..11 -> "Good Morning, \n"
            in 12..17 -> "Good Afternoon, \n"
            in 18..20 -> "Good Evening, \n"
            else -> "Good Night, \n"
        }
        return greeting
    }
}