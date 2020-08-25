package com.stenleone.fitapp.util.constant

class ApiFitPlanConstant {

    companion object {

        const val MAIN_URL = "https://input.fitplanapp.com/fitplan-server/"
        const val LOGIN = "oauth/token?client_id=XW9LtUlJfcCHMJbLyLen3lglY4COUgmCQErwjze7&client_secret=" +
                "ae55LjKyfVAf9dWaUX9HwoU5tpwHAVn2jKh8Of9zu3TP4zlD7JwguJhDYxXRT9zR2iuOIfHLrNiOAQSyAfR" +
                "Fs6dI7uXE8Yg7l3yyw7NTABnLr94VuPFKUOaaaCZ7xAv3&grant_type=password"

        const val CONTENT_LIST = "v2/plans?locale=en"
        const val ITEM_DETAILS = "v2/plan/details"

        const val USER_LOGIN = "test@fitplanapp.com"
        const val USER_PASSWORD = "fitplan123"
    }
}