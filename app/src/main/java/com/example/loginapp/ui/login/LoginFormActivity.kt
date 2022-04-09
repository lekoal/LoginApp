package com.example.loginapp.ui.login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapp.R
import com.example.loginapp.app
import com.example.loginapp.databinding.ActivityLoginFormBinding

const val IS_PRESENTER_RESTORED = "IS_PRESENTER_RESTORED"

class LoginFormActivity : AppCompatActivity(), LoginFormContract.View {
    private lateinit var binding: ActivityLoginFormBinding
    private var presenter: LoginFormContract.Presenter? = null

    private var isButtonClicked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = restoreLoginFormPresenter()

        if (savedInstanceState?.getBoolean(IS_PRESENTER_RESTORED) == true) {
            presenter?.onRestored(true)
        }

        presenter?.onViewAttach(this)

        binding.enterButton.setOnClickListener {
            isButtonClicked = true
            presenter?.onEnter(
                binding.usernameEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }
        binding.registrationButton.setOnClickListener {
            presenter?.onRegistration()
        }
        binding.forgotPasswordButton.setOnClickListener {
            presenter?.onForgotPassword()
        }
    }

    @MainThread
    override fun setEnterSuccess(enterSuccessText: String, isRestored: Boolean) {
        binding.loadingProcessContainer.visibility = View.VISIBLE
        binding.enterSuccessImage.visibility = View.VISIBLE
        if (!isRestored) Toast.makeText(this, enterSuccessText, Toast.LENGTH_SHORT).show()
        onScreenTouch()
    }

    @MainThread
    override fun setEnterError(enterErrorText: String, isRestored: Boolean) {
        binding.loadingProcessContainer.visibility = View.VISIBLE
        binding.enterErrorImage.visibility = View.VISIBLE
        if (!isRestored) Toast.makeText(this, enterErrorText, Toast.LENGTH_SHORT).show()
        onScreenTouch()
    }

    @MainThread
    override fun showProcessLoading(isLoading: Boolean) {
        hideKeyboard()
        if (isLoading) {
            viewElementsDisable(true)
            binding.loadingProcessContainer.visibility = View.VISIBLE
            binding.loadingProcess.visibility = View.VISIBLE
        } else {
            binding.loadingProcess.visibility = View.GONE
        }
    }

    @MainThread
    override fun showRegistration() {
        Toast.makeText(this, getString(R.string.registration_message), Toast.LENGTH_SHORT).show()
    }

    @MainThread
    override fun showForgotPassword() {
        Toast.makeText(this, getString(R.string.forgot_password_message), Toast.LENGTH_SHORT).show()
    }

    override fun getHandler(): Handler {
        return Handler(Looper.getMainLooper())
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

    private fun restoreLoginFormPresenter(): LoginFormPresenter {
        val currentPresenter = lastCustomNonConfigurationInstance as? LoginFormPresenter

        return currentPresenter ?: LoginFormPresenter(app.loginFormUsecase)
    }

    @Deprecated("Deprecated in Java")
    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

    private fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (isButtonClicked) {
            outState.putBoolean(IS_PRESENTER_RESTORED, true)
        }
        super.onSaveInstanceState(outState)
    }
}