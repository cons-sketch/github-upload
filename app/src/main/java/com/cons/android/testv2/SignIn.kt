package com.cons.android.testv2

import android.content.Context
import android.os.Build
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.cons.android.testv2.api.AuthBody
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ClassCastException

private const val TAG = "SignIn"

class SignIn : Fragment() {

    companion object {
        fun newInstance() = SignIn()
    }

    private lateinit var listener: OnSignIn
    private lateinit var viewModel: SignInViewModel
    private lateinit var signInButton: Button
    private lateinit var username: EditText
    private lateinit var password: EditText
    var authBody: AuthBody = AuthBody()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.signin_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SignInViewModel::class.java)
        viewModel.signedIn.observe(viewLifecycleOwner, Observer {
            if(it == 1) {
                listener.signedIn()
                goToListFragment()
            }
        })
        username = view.findViewById(R.id.username)
        password = view.findViewById(R.id.password)
        signInButton = view.findViewById(R.id.signin_button)
        signInButton.setOnClickListener {
            this.requireView().hideKeyboard()
            authBody.username = username.text.toString()
            authBody.password = password.text.toString()
            viewModel.signIn(authBody)
            viewModel.signedIn.observe(viewLifecycleOwner, Observer {
                if(it == 2) {
                    Toast.makeText(this.context,"Authorization failed",Toast.LENGTH_LONG).show()
                } else if (it == 1) {
                    listener.signedIn()
                    Log.d(TAG, "action called")
                    goToListFragment()
                }
            })
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSignIn) {
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement OnSignIn")
        }
    }

    fun goToListFragment() {
        val action = SignInDirections.actionSignInToListFragment()
        val navController = findNavController()
        if(navController.currentDestination?.id == R.id.signIn) {
            navController.navigate(action)
        }
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    interface OnSignIn {
        fun signedIn()
    }

}
