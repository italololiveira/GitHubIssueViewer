package br.com.githubissueviewer.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.githubissueviewer.MainFragment
import br.com.githubissueviewer.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    MainFragment.newInstance()
                )
                .commitNow()
        }
    }

}
