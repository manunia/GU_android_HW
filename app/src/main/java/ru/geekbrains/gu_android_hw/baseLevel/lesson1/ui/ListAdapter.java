package ru.geekbrains.gu_android_hw.baseLevel.lesson1.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.geekbrains.gu_android_hw.R;

//адаптер
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private String[] datasource;

    private OnItemClickListener itemClickListener;

    public ListAdapter(String[] datasource) {
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
        return new ViewHolder(v);
    }

    // Заменить данные в пользовательском интерфейсе
    // Вызывается менеджером
    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder viewHolder, int i) {
        //получаем элемент из источника данных
        viewHolder.getTextView().setText(datasource[i]);
    }

    //возвращаем размер массива данных
    @Override
    public int getItemCount() {
        return datasource.length;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    //класс, который хранит связь между данными и элементами view
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView;

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if (itemClickListener != null) {
                       itemClickListener.onItemClick(v, getAdapterPosition());
                   }

               }
           });
        }

        public TextView getTextView() {
            return textView;
        }
    }
}
