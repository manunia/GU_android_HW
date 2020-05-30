package ru.geekbrains.gu_android_hw.baseLevel.lesson1.ui;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.geekbrains.gu_android_hw.R;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.dao.City;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.dao.CitySource;

//адаптер
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private CitySource datasource;
    private Activity activity;
    private long menuPosition;
    private OnItemClickListener itemClickListener;

    public CitySource getDatasource() {
        return datasource;
    }

    public ListAdapter(CitySource datasource, Activity activity) {
        this.datasource = datasource;
        this.activity = activity;
    }

    //создаем новый элемент ui
    //запускается менеджером
    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Создаем новый элемент пользовательского интерфейса
        // Через Inflater
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
        //здесь установим параметры
        ViewHolder vh = new ViewHolder(v);
        if (itemClickListener != null) {
            vh.setOnClickListener(itemClickListener);
        }
        Log.d("SocnetAdapter", "onCreateViewHolder");
        return vh;
    }

    // Заменить данные в пользовательском интерфейсе
    // Вызывается менеджером
    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder viewHolder, int i) {
        //получаем элемент из источника данных
        List<City> cities = datasource.getCities();
        City city = cities.get(i);

        viewHolder.cityName.setText(city.name);

        viewHolder.cardView.setOnLongClickListener(view -> {
            menuPosition = i;
            return false;
        });
        if (activity != null) {
            activity.registerForContextMenu(viewHolder.cardView);
        }

        Log.d("SocnetAdapter", "onBindViewHolder");
    }

    //возвращаем размер массива данных
    @Override
    public int getItemCount() {
        return (int)datasource.getCountCities();
    }

    public long getMenuPosition() {
        return menuPosition;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, String name, int position);
    }

    //класс, который хранит связь между данными и элементами view
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView cityName;
        View cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView;
            cityName = itemView.findViewById(R.id.itemCityName);
        }

        public void setOnClickListener(final OnItemClickListener listener) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Получаем позицию адаптера
                    int adapterPosition = getAdapterPosition();
                    // Проверяем ее на корректность
                    if (adapterPosition == RecyclerView.NO_POSITION) return;

                    listener.onItemClick(v, getDatasource().getCities().get(adapterPosition).getName(), adapterPosition);
                }
            });
        }


    }
}
