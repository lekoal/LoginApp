package com.example.loginapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapp.databinding.ActivityLoginFormBinding

class LoginFormActivity : AppCompatActivity(), LoginFormContract.View {
    private lateinit var binding: ActivityLoginFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun setEnterSuccess(enterSuccessText: String) {
        binding.enterSuccessImage.visibility = View.VISIBLE
        Toast.makeText(this, enterSuccessText, Toast.LENGTH_SHORT).show()
        onScreenTouch()
    }

    override fun setEnterError(enterErrorText: String) {
        binding.enterErrorImage.visibility = View.VISIBLE
        Toast.makeText(this, enterErrorText, Toast.LENGTH_SHORT).show()
        onScreenTouch()
    }

    override fun showProcessLoading(isLoading: Boolean) {
        if (isLoading) {
            viewElementsDisable(true)
            binding.loadingProcessContainer.visibility = View.VISIBLE
            binding.loadingProcess.visibility = View.VISIBLE
        } else {
            binding.loadingProcess.visibility = View.GONE
        }
    }

    override fun showRegistration() {
        Toast.makeText(this, getString(R.string.registration_message), Toast.LENGTH_SHORT).show()
    }

    override fun showForgotPassword() {
        Toast.makeText(this, getString(R.string.forgot_password_message), Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun onScreenTouch() {
        binding.loadingProcessContainer.setOnTouchListener { _, _ ->
            binding.enterSuccessImage.visibility = View.GONE
            binding.enterErrorImage.visibility = View.GONE
            binding.loadingProcessContainer.visibility = View.GONE
            viewElementsDisable(false)
            true
        }
    }

    private fun viewElementsDisable(isDisable: Boolean) {
        binding.activityLoginFormContainer.isEnabled = !isDisable
    }
}