package com.kantinsehat.gklf.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bagicode.bwamov.utils.Preferences
import com.kantinsehat.gklf.R
import com.kantinsehat.gklf.sign.SignInActivity
import com.kantinsehat.gklf.sign.signin.SignInActivity
import kotlinx.android.synthetic.main.activity_onboarding_one.*
import java.util.prefs.Preferences

class OnboardingOneActivity : AppCompatActivity() {
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_one)
        preferences = Preferences(this)

        if (preferences.getValues("onboarding").equals("1")) {
            finishAffinity()

            val intent = Intent(this@OnboardingOneActivity,
                SignInActivity::class.java)
            startActivity(intent)
        }
        btn_home.setOnClickListener {
            val intent = Intent(this@OnboardingOneActivity,
                OnboardingTwoActivity::class.java)
            startActivity(intent)
        }

        btn_daftar.setOnClickListener {
            finishAffinity()

            val intent = Intent(this@OnboardingOneActivity,
                SignInActivity::class.java)
            startActivity(intent)
        }
    }
}
