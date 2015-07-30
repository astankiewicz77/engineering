package androidhive.info.materialdesign.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

import androidhive.info.materialdesign.R;
import androidhive.info.materialdesign.adapter.ImageAdapter;

/**
 * Created by a.stankiewicz on 2015-07-28.
 */
public class StartFragment extends Fragment{

    int count = 0;
    private Button login, registration;
    private ViewPager viewPager;
    private ImageAdapter adapter;
    private Timer timer;

    public StartFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_start, container, false);

        ((ActionBarActivity)getActivity()).getSupportActionBar().hide();

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        adapter = new ImageAdapter(this.getActivity());
        viewPager.setAdapter(adapter);

        //AUTO SLIDING IMAGES IN ViewPager
        viewPager.setCurrentItem(0);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(getActivity()!=null){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(count<=2){
                                viewPager.setCurrentItem(count);
                                count++;
                            }else{
                                count = 0;
                                viewPager.setCurrentItem(count);
                            }
                        }
                    });
                }
            }
        }, 500, 2500);

        login = (Button) view.findViewById(R.id.btn_login);
        registration = (Button) view.findViewById(R.id.btn_register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_body, new LoginFragment(), "fragment_stack");
                fragmentTransaction.commit();
            }
        });

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_body, new RegistrationFragment(), "fragment_stack");
                fragmentTransaction.commit();
            }
        });

        return view;
    }

}