package com.anadoluhayat.customnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        loadFragment(HomeFragment())

        bottomNavigationView.setOnNavigationItemSelectedListener {
            var fragment:Fragment? = null
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            when(it.itemId)
            {
                R.id.navigation_home->{fragment=HomeFragment()}
                R.id.navigation_dashboard->{fragment=DashboardFragment()}
                R.id.navigation_notifications->{fragment=NotificationsFragment()}
                R.id.navigation_profile->{fragment=ProfileFragment()}
            }
            loadFragment(fragment)
        }
    }

     fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            val name=fragment.javaClass.simpleName
            val fragmentPopped = supportFragmentManager.popBackStackImmediate(name, 1)

            Log.d("printStack","$fragmentPopped")
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(name)
                .commit()
            printStack()
            return true
        }
        return false
    }




    private fun printStack()
    {

        val size = supportFragmentManager.backStackEntryCount

        for(i in 0 until size)
        {
            val fragmetn=supportFragmentManager.getBackStackEntryAt(i)
            Log.d("printStack","$i"+fragmetn.name?:"${fragmetn.id}")

        }

        Log.d("printStack", "***********************************")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if( supportFragmentManager.backStackEntryCount<1)
        {
            finish()
        }
        printStack()
    }

}
