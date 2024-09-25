package io.marelso.shineyard

 import android.os.Build
 import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
 import androidx.annotation.RequiresApi
 import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
 import androidx.navigation.NavHostController
 import androidx.navigation.compose.rememberNavController
 import com.google.firebase.database.DatabaseReference
 import com.google.firebase.database.FirebaseDatabase
 import io.marelso.shineyard.navigation.AppNavigationGraph
 import io.marelso.shineyard.ui.detail.DetailScreenHoisting
 import io.marelso.shineyard.ui.detail.DetailViewModel
 import io.marelso.shineyard.ui.theme.ShineYardTheme
 import org.koin.androidx.compose.koinViewModel

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