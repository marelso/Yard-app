package io.marelso.shineyard

 import android.content.ContentValues.TAG
 import android.os.Build
 import android.os.Bundle
 import android.util.Log
 import android.widget.Toast
 import androidx.activity.ComponentActivity
 import androidx.activity.compose.setContent
 import androidx.annotation.RequiresApi
 import androidx.compose.foundation.layout.fillMaxSize
 import androidx.compose.material3.MaterialTheme
 import androidx.compose.material3.Surface
 import androidx.compose.ui.Modifier
 import androidx.navigation.NavHostController
 import androidx.navigation.compose.rememberNavController
 import com.google.firebase.messaging.FirebaseMessaging
 import io.marelso.shineyard.navigation.AppNavigationGraph
 import io.marelso.shineyard.ui.theme.ShineYardTheme

class MainActivity : ComponentActivity() {

    private var navHostController: NavHostController? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShineYardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navHostController = rememberNavController()
                    navHostController?.let {
                        AppNavigationGraph(navHostController = it)
                    }
                }
            }
        }
    }
}