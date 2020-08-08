package com.example.mynews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.example.mynews.callbacks.ArticlesListCallBack;
import com.example.mynews.callbacks.SourceListCallBack;
import com.example.mynews.callbacks.UserAthenticatedCallBack;
import com.example.mynews.fragments.FragmentManager;
import com.example.mynews.fragments.menubar.MenuBarFragment;
import com.example.mynews.login.LogUser;
import com.example.mynews.models.Category;
import com.example.mynews.onClickListeners.LogOutOnClickListener;
import com.example.mynews.onClickListeners.MenuBarOnClickListener;
import com.example.mynews.services.mynews.MyNewsRetrofit;
import com.example.mynews.toast.ShowToast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MyNewsRetrofit retrofit;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.validateUser();
        this.getSupportActionBar().hide();
        this.retrofit = new MyNewsRetrofit();
        this.loadCategoryFragment();
        this.loadSearchView();
        FragmentManager.menuBarFragment = (MenuBarFragment) this.getSupportFragmentManager().findFragmentById(R.id.menu_bar_fragment);
        FragmentManager.menuBarFragment.addButtons(this.createButtons());
        this.loadAdminButtons();
    }

    private void loadAdminButtons() {
        ArrayList<ImageButton> imageButtons = new ArrayList<ImageButton>();
        int dp = (int)this.getApplicationContext().getResources().getDisplayMetrics().density;
        int size=60*dp;
        int padding = 15*dp;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size,size);
        ImageButton usersButton = this.createButton(R.drawable.users_icon, padding, layoutParams);
        usersButton.setOnClickListener(new MenuBarOnClickListener(usersButton, FragmentManager.userAdminOperationsFragment, this));

        ImageButton sourceButton = this.createButton(R.drawable.source_icon, padding, layoutParams);
        sourceButton.setOnClickListener(new MenuBarOnClickListener(sourceButton, FragmentManager.sourceFragment, this));

        ImageButton categoriesButton = this.createButton(R.drawable.categories_icon, padding, layoutParams);
        categoriesButton.setOnClickListener(new MenuBarOnClickListener(categoriesButton, FragmentManager.categoryListFragment, this));

        imageButtons.add(usersButton);
        imageButtons.add(sourceButton);
        imageButtons.add(categoriesButton);
        this.retrofit.userAthenticated().enqueue(new UserAthenticatedCallBack(this.getApplicationContext(), this, imageButtons, 1));
    }

    public ArrayList<ImageButton> createButtons(){
        ArrayList<ImageButton> imageButtons = new ArrayList<ImageButton>();
        int dp = (int)this.getApplicationContext().getResources().getDisplayMetrics().density;
        int size=60*dp;
        int padding = 15*dp;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size,size);
        ImageButton homeButton = this.createButton(R.drawable.home_icon, padding, layoutParams);
        homeButton.setOnClickListener(new MenuBarOnClickListener(homeButton, FragmentManager.categoryFragment, this));
        imageButtons.add(homeButton);


        ImageButton logOutButton = this.createButton(R.drawable.logout_icon, padding, layoutParams);
        logOutButton.setOnClickListener(new LogOutOnClickListener(this.retrofit, this, logOutButton));
        imageButtons.add(logOutButton);
        return  imageButtons;
    }

    public ImageButton createButton(int drawable, int padding, LinearLayout.LayoutParams params){
        ImageButton button = new ImageButton(getApplicationContext());
        button.setImageResource(drawable);
        button.setScaleType(ImageView.ScaleType.FIT_XY);
        button.setBackground(null);
        button.setPadding(padding, padding, padding, padding);
        button.setLayoutParams(params);
        return button;
    }

    private void validateUser() {
        LogUser.currentLogUser = new LogUser(this);
        LogUser.currentLogUser.validatelog();
    }

    private void loadSearchView() {
        this.searchView = (SearchView) this.findViewById(R.id.search_input_view);
        this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ShowToast.show(getApplicationContext(), "Buscando " + query + ", espere por favor.");
                retrofit.search(query).enqueue(new ArticlesListCallBack(MainActivity.this));
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }


    public void loadCategoryFragment(){
        this.retrofit.getCategories().enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                if (response.isSuccessful()) {
                    FragmentManager.categoryFragment.setDataSet(response.body());
                    FragmentManager.changeFragment(MainActivity.this, FragmentManager.categoryFragment);
                    FragmentManager.menuBarFragment.getImageButton(0).setBackgroundColor(getApplicationContext().getResources().getColor(R.color.button_bar_pressed));
                } else {
                    FragmentManager.changeFragment(MainActivity.this, FragmentManager.errorFragment);
                    }
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                FragmentManager.changeFragment(MainActivity.this, FragmentManager.errorFragment);
            }
        });
    }
}