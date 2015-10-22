package com.agenda.omarche.agenda;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

//public class MainActivity extends OrmLiteBaseActivity<DatabaseHelper> implements ActionBar.TabListener, ViewPager.OnPageChangeListener {
public class MainActivity extends AppCompatActivity implements ActionBar.TabListener, ViewPager.OnPageChangeListener {


    private ViewPager viewPager;
    private ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarTabUI();
    }

    private void inicializarTabUI() {
        View view = findViewById(R.id.pager); //El mismo ID carga el phone y tablet
        String viewTag = String.valueOf(view.getTag());
        Log.d(getClass().getSimpleName(), String.format("Layout: %s", viewTag));
        if (viewTag.equals("phone")) {
            viewPager = (ViewPager) findViewById(R.id.pager);
            actionBar = getSupportActionBar();
            TabsPagerAdapter adapter = new TabsPagerAdapter(getFragmentManager());
            String[] titulos = {"Crear Contacto", "Lista Contacto"};
            viewPager.setAdapter(adapter);
            actionBar.setHomeButtonEnabled(false);
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

            for (String nombre : titulos) {
                ActionBar.Tab tab = actionBar.newTab().setText(nombre);
                tab.setTabListener(this);
                actionBar.addTab(tab);
            }

            viewPager.setOnPageChangeListener(this);
        }


    }

    //region Metodos Page
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        actionBar.setSelectedNavigationItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    //endregion

    //region Metodos Tabs


    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }
    //endregion









/*

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    */


}
