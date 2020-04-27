package ru.geekbrains.gu_android_hw.baseLevel.lesson1.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.geekbrains.gu_android_hw.R;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.CityDataSource;
import ru.geekbrains.gu_android_hw.baseLevel.lesson1.data.implementation.City;

//адаптер
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private CityDataSource datasource;

    private OnItemClickListener itemClickListener;

    public CityDataSource getDatasource() {
        return datasource;
    }

    public ListAdapter(CityDataSource datasource) {
        this.datasource = datasource;
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
        City city = datasource.getCity(i);
        viewHolder.setData(city.getName(),city.getPicture());
        Log.d("SocnetAdapter", "onBindViewHolder");
    }

    //возвращаем размер массива данных
    @Override
    public int getItemCount() {
        return datasource.size();
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
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cityName = itemView.findViewById(R.id.itemCityName);
            image = itemView.findViewById(R.id.cityImage);
        }

        public void setOnClickListener(final OnItemClickListener listener) {
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Получаем позицию адаптера
                    int adapterPosition = getAdapterPosition();
                    // Проверяем ее на корректность
                    if (adapterPosition == RecyclerView.NO_POSITION) return;

                    listener.onItemClick(v, getDatasource().getCity(adapterPosition).getName(), adapterPosition);
                }
            });
        }

        public void setData(String name, int picture) {
            getImage().setImageResource(picture);
            getCityName().setText(name);
        }

        public ImageView getImage() {
            return image;
        }

        public TextView getCityName() {
            return cityName;
        }
    }
}
