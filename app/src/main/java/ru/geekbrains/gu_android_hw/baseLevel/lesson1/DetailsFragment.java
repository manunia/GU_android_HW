package ru.geekbrains.gu_android_hw.baseLevel.lesson1;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import ru.geekbrains.gu_android_hw.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    private TextView cityTemperature;
    private TextView cityWindSpeed;
    private TextView cityPressure;
    private TextView cityName;

    private TableRow windspeedRow;
    private TableRow pressureRow;

    public DetailsFragment() {
        // Required empty public constructor
    }

    //Фабричный метод создания фрагмента
    public static DetailsFragment create(CityEntity index) {
        DetailsFragment f = new DetailsFragment();

        Bundle args = new Bundle();
        args.putSerializable(Constants.CREATE_CITY,index);
        f.setArguments(args);
        return f;
    }

    public CityEntity getCity() {
        CityEntity city = (CityEntity) getArguments().getSerializable(Constants.CREATE_CITY);
        return city;
    }

    // Получить индекс из списка (фактически из параметра)
    public String getName() {
        return getCity().getName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View layout = inflater.inflate(R.layout.fragment_details,container,false);

        cityTemperature = layout.findViewById(R.id.moscowTemperature);
        cityWindSpeed = layout.findViewById(R.id.windSpeed);
        cityPressure = layout.findViewById(R.id.pressure);
        cityName = layout.findViewById(R.id.cityName);

        windspeedRow = layout.findViewById(R.id.windspeedRow);
        pressureRow = layout.findViewById(R.id.pressureRow);

        //CityEntity city = (CityEntity) layout.getIntent().getExtras().getSerializable(Constants.CREATE_CITY);

        //Intent intent = layout.getIntent();
        //boolean isWindChecked = intent.getBooleanExtra(Constants.WIND_SPEED_CHECK,false);
        //boolean isPressureChecked = intent.getBooleanExtra(Constants.PRESSURE_CHECK,false);

        cityName.setText(getCity().getName());
        cityTemperature.setText(((Integer)getCity().getTodayTemperature()).toString());
        //showParametr(isWindChecked,getCity().getTodaySpeed(), cityWindSpeed,windspeedRow);
        //showParametr(isPressureChecked,getCity().getTodayPressure(), cityPressure,pressureRow);

        return layout;
    }

    //show or hide selected param
    private void showParametr(boolean isParametrChecked, int todayParam, TextView parametrText, TableRow parametrRow) {
        if (isParametrChecked) {
            parametrText.setText(((Integer)todayParam).toString());
        } else {
            parametrRow.setVisibility(View.INVISIBLE);
        }
    }

}
