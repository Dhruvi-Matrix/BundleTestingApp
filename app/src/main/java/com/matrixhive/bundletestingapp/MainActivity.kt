package com.matrixhive.bundletestingapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.tasks.OnFailureListener
import com.google.android.play.core.tasks.OnSuccessListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var splitInstallManager: SplitInstallManager?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        splitInstallManager=SplitInstallManagerFactory.create(this)

        feature1Button.setOnClickListener {
            loadFeatureOne()
        }
    }

    private fun loadFeatureOne() {
        // Builds a request to install the feature1 module
        val request = SplitInstallRequest
                .newBuilder() // You can download multiple on demand modules per
                // request by invoking the following method for each
                // module you want to install.
                .addModule("feature1")
                .build()

        // Begin the installation of the feature1 module and handle success/failure
        splitInstallManager
                ?.startInstall(request)
                ?.addOnSuccessListener(OnSuccessListener<Int?> {
                    Toast.makeText(this,"The feature 1 module is downloaded",Toast.LENGTH_SHORT).show()
                })
                ?.addOnFailureListener(OnFailureListener {
                    Log.d("MainActivity", "loadFeatureOne:"+it.stackTrace)
                })
    }
}