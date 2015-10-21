package com.agenda.omarche.agenda;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener, ViewPager.OnPageChangeListener {


    private ViewPager viewPager;
    private TabsPagerAdapter adapter;
    private ActionBar actionBar;

    private String[] titulos = {"Crear Contacto","Lista contactos"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inicializarTabs();
        setContentView(R.layout.activity_main);

    }

    private void inicializarTabs() {
        viewPager = (ViewPager) findViewById(R.id.pager);

        actionBar = getSupportActionBar();
        adapter = new TabsPagerAdapter(getFragmentManager());

        viewPager.setAdapter(adapter);

        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        for( String titulo: titulos){
            ActionBar.Tab tab = actionBar.newTab().setText(titulo);
            tab.setTabListener(this);
            actionBar.addTab(tab);
        }

        viewPager.setOnPageChangeListener(this);
    }


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






}
