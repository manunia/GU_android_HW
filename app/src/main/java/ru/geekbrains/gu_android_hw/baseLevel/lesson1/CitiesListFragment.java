package ru.geekbrains.gu_android_hw.baseLevel.lesson1;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import ru.geekbrains.gu_android_hw.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CitiesListFragment extends Fragment {

    private boolean isLanscapeOrientation;
    private CityEntity currentEntity;

    private Button submitButton;
    private EditText textCity;
    private String enteredText;

    private CheckBox windSpeedcheck;
    private CheckBox pressureCheck;

    public CitiesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cities_list,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList((FrameLayout) view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isLanscapeOrientation = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        if (savedInstanceState != null) {
            currentEntity = (CityEntity) savedInstanceState.getSerializable("CurrentCity");
        } else {
            currentEntity = createCity();
        }

        if (isLanscapeOrientation) {
            showDetails(currentEntity);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable("CurrentCity",currentEntity);
        super.onSaveInstanceState(outState);
    }

    private void initList(@NonNull final FrameLayout view) {
        FrameLayout layoutView = view;

        submitButton = layoutView.findViewById(R.id.submit_buton);
        textCity = layoutView.findViewById(R.id.edit_city);

        windSpeedcheck = layoutView.findViewById(R.id.windSpeedCheck);
        pressureCheck = layoutView.findViewById(R.id.pressureCheck);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredText = textCity.getText().toString();
                if (enteredText.length() == 0) {
                    Toast.makeText(view.getContext(), "Invalid enter", Toast.LENGTH_SHORT).show();
                } else {
                    showDetails(createCity());
                }
            }
        });

    }

    private void showDetails(CityEntity currentEntity) {
        if (isLanscapeOrientation) {
            DetailsFragment detail = (DetailsFragment) getFragmentManager().findFragmentById(R.id.city_details);
            if (detail == null || detail.getName() != currentEntity.getName()) {
                detail = DetailsFragment.create(currentEntity);

                // Выполняем транзакцию по замене фрагмента
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.city_details, detail);  // замена фрагмента
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
            Intent intent = new Intent();
            intent.setClass(getActivity(),CityActivity.class);
            intent.putExtra(Constants.CREATE_CITY,currentEntity);
            startActivity(intent);
        } else {
            Intent intent = new Intent();
            intent.setClass(getActivity(),CityActivity.class);
            intent.putExtra(Constants.CREATE_CITY,currentEntity);
            startActivity(intent);
        }
    }

    private CityEntity createCity() {
        CityEntity city = new CityEntity();
        city.setName(textCity.getText().toString());
        city.setTodayTemperature(-14);
        city.setTodayPressure(760);
        city.setTodaySpeed(3);
        return city;
    }
}
