package com.kantinsehat.gklf.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kantinsehat.gklf.R
import com.kantinsehat.gklf.sign.signin.SignInActivity
import kotlinx.android.synthetic.main.activity_onboarding_two.*

class OnboardingTwoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_two)
        btn_home.setOnClickListener {
            val intent = Intent(this@OnboardingTwoActivity,
                SignInActivity::class.java)
            startActivity(intent)
        }
    }
}
